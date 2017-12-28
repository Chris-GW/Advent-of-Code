package de.adventofcode.chrisgw.day25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TurningMachineFactory {

    private static Pattern BEGIN_STATE_PATTERN = Pattern.compile("Begin in state ([A-Z])\\.");

    private static final Pattern TURNING_MACHINE_STATE_DESCRIPTION_PATTERN = Pattern.compile("" //
            + "\\s*In state ([A-Z]):" //
            + "\\s*If the current value is 0:" //
            + "\\s*- Write the value (0|1)\\." //
            + "\\s*- Move one slot to the (right|left)\\." //
            + "\\s*- Continue with state ([A-Z])\\." //
            + "\\s*If the current value is 1:" //
            + "\\s*- Write the value (0|1)\\." //
            + "\\s*- Move one slot to the (right|left)\\." //
            + "\\s*- Continue with state ([A-Z])\\.", Pattern.MULTILINE); //


    public TurningMachine parseTurningMachineStatesOfBlueprint(String turningMachineBlueprint) {
        String beginStateName = null;
        List<TurningMachineStateDescription> stateDescriptions = new ArrayList<>();

        Matcher beginStateMatcher = BEGIN_STATE_PATTERN.matcher(turningMachineBlueprint);
        if (beginStateMatcher.find()) {
            beginStateName = beginStateMatcher.group(1);
        }

        Matcher stateDescriptionMatcher = TURNING_MACHINE_STATE_DESCRIPTION_PATTERN.matcher(turningMachineBlueprint);
        while (stateDescriptionMatcher.find()) {
            TurningMachineStateDescription stateDescription = TurningMachineStateDescription.parseTurningMachineStateDescriptionOfMatchResult(
                    stateDescriptionMatcher.toMatchResult());
            stateDescriptions.add(stateDescription);
        }

        Map<String, TurningMachineState> nameToTurningMachineStateMap = new HashMap<>();
        for (TurningMachineStateDescription stateDescription : stateDescriptions) {
            nameToTurningMachineStateMap.put(stateDescription.stateName, stateDescription);
            stateDescription.nameToTurningMachineStateMap = nameToTurningMachineStateMap;
        }
        return new TurningMachine(nameToTurningMachineStateMap.get(beginStateName));
    }


    private static class TurningMachineStateDescription implements TurningMachineState {

        public Map<String, TurningMachineState> nameToTurningMachineStateMap;

        public String stateName;

        public boolean writeValueBy0;
        public int moveCursorBy0;
        public String continueStateBy0;

        public boolean writeValueBy1;
        public int moveCursorBy1;
        public String continueStateBy1;


        public static TurningMachineStateDescription parseTurningMachineStateDescriptionOfMatchResult(
                MatchResult stateDescriptionMatchResult) {
            //            for (int group = 0; group <= stateDescriptionMatchResult.groupCount(); group++) {
            //                System.out.println(group + ": " + stateDescriptionMatchResult.group(group));
            //            }

            TurningMachineStateDescription stateDescription = new TurningMachineStateDescription();
            stateDescription.stateName = stateDescriptionMatchResult.group(1);

            stateDescription.writeValueBy0 = stateDescriptionMatchResult.group(2).equals("1");
            stateDescription.moveCursorBy0 = parseMoveCursorDescription(stateDescriptionMatchResult.group(3));
            stateDescription.continueStateBy0 = stateDescriptionMatchResult.group(4);

            stateDescription.writeValueBy1 = stateDescriptionMatchResult.group(5).equals("1");
            stateDescription.moveCursorBy1 = parseMoveCursorDescription(stateDescriptionMatchResult.group(6));
            stateDescription.continueStateBy1 = stateDescriptionMatchResult.group(7);

            return stateDescription;
        }

        private static int parseMoveCursorDescription(String moveCursorDirection) {
            if (moveCursorDirection.equals("right")) {
                return 1;
            } else if (moveCursorDirection.equals("left")) {
                return -1;
            } else {
                throw new IllegalArgumentException("unexpect cursor move direction: " + moveCursorDirection);
            }
        }


        @Override
        public String getStateName() {
            return stateName;
        }

        @Override
        public boolean writeValue(boolean currentValue) {
            if (currentValue) {
                return writeValueBy1;
            } else {
                return writeValueBy0;
            }
        }

        @Override
        public int moveCursor(boolean currentValue) {
            if (currentValue) {
                return moveCursorBy1;
            } else {
                return moveCursorBy0;
            }
        }

        @Override
        public TurningMachineState nextState(boolean currentValue) {
            if (currentValue) {
                return nameToTurningMachineStateMap.get(continueStateBy1);
            } else {
                return nameToTurningMachineStateMap.get(continueStateBy0);
            }
        }

    }


}
