package de.adventofcode.chrisgw.day10;

import java.util.*;

public record Machine(BitSet indicatorLights, List<MachineButton> buttons, int[] joltageRequirements) {

    public static Machine parseMachineLine(String line) {
        String[] splitted = line.split("\\s+");

        BitSet indicatorLights = parseIndicatorLights(splitted[0]);
        List<MachineButton> buttons = Arrays.stream(splitted)
                .skip(1)
                .limit(splitted.length - 1)
                .map(MachineButton::parseButtonWiring)
                .toList();
        int[] joltageRequirements = parseJoltageRequirements(splitted[splitted.length - 1]);
        return new Machine(indicatorLights, buttons, joltageRequirements);
    }

    private static int[] parseJoltageRequirements(String line) {
        line = line.substring(1, line.length() - 1);
        String[] split = line.split(",");
        return Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
    }

    private static BitSet parseIndicatorLights(String indicatorLightsString) {
        indicatorLightsString = indicatorLightsString.substring(1, indicatorLightsString.length() - 1);
        BitSet indicatorLights = new BitSet(indicatorLightsString.length());
        for (int i = 0; i < indicatorLightsString.length(); i++) {
            if (indicatorLightsString.charAt(i) == '#') {
                indicatorLights.set(i);
            }
        }
        return indicatorLights;
    }


    public int initializeMachine() {
        for (int buttonPresses = 0; true; buttonPresses++) {
            int[] assignment = new int[buttonPresses];
            if (initializeMachine(assignment, 0)) {
                return buttonPresses;
            }
        }
    }

    private boolean initializeMachine(int[] buttonAssignment, int index) {
        if (index >= buttonAssignment.length) {
            return isInitializing(buttonAssignment);
        }

        for (int i = 0; i < buttonCount(); i++) {
            buttonAssignment[index] = i;
            if (initializeMachine(buttonAssignment, index + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInitializing(int[] assignment) {
        BitSet indicatorLights = new BitSet(indicatorLightCount());
        for (int buttonIndex : assignment) {
            MachineButton machineButton = buttons.get(buttonIndex);
            machineButton.press(indicatorLights);
        }
        return this.indicatorLights.equals(indicatorLights);
    }


    // part 02
    public int adjustJoltage() {
        for (int buttonPresses = 0; true; buttonPresses++) {
            int[] assignment = new int[buttonPresses];
            if (adjustJoltage(assignment, 0)) {
                return buttonPresses;
            }
        }
    }

    private boolean adjustJoltage(int[] buttonAssignment, int index) {
        if (index >= buttonAssignment.length) {
            return isCorrectJoltage(buttonAssignment);
        }

        for (int i = 0; i < buttonCount(); i++) {
            buttonAssignment[index] = i;
            if (adjustJoltage(buttonAssignment, index + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCorrectJoltage(int[] assignment) {
        int[] joltageLevels = new int[joltageRequirements.length];
        for (int buttonIndex : assignment) {
            MachineButton machineButton = buttons.get(buttonIndex);
            for (int counter : machineButton.wiring()) {
                if (counter < joltageLevels.length) {
                    joltageLevels[counter]++;
                }
            }
        }
        return Arrays.equals(joltageRequirements, joltageLevels);
    }


    public int buttonCount() {
        return buttons.size();
    }


    public int indicatorLightCount() {
        return joltageRequirements.length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < indicatorLightCount(); i++) {
            if (indicatorLights.get(i)) {
                sb.append('#');
            } else {
                sb.append('.');
            }
        }
        sb.append(']');
        sb.append(' ');

        for (MachineButton button : buttons) {
            sb.append(button.toString());
            sb.append(' ');
        }

        sb.append('{');
        for (int joltageRequirement : joltageRequirements) {
            sb.append(joltageRequirement);
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }

}
