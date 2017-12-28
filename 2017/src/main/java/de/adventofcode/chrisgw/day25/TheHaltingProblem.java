package de.adventofcode.chrisgw.day25;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <h1><a href="https://adventofcode.com/2017/day/25>Day 25: The Halting Problem</a></h1>
 * <pre>
 * </pre>
 */
public class TheHaltingProblem {

    private static Pattern CHECKSUM_AFTER_PATTERN = Pattern.compile(
            "Perform a diagnostic checksum after (\\d+) steps\\.");

    public static TurningMachine debugTurningMachine(String turningMachineBlueprint) {
        TurningMachineFactory turningMachineFactory = new TurningMachineFactory();
        TurningMachine turningMachine = turningMachineFactory.parseTurningMachineStatesOfBlueprint(
                turningMachineBlueprint);
        //        System.out.println(0 + ": " + turningMachine);

        int steps = parseChecksumSteps(turningMachineBlueprint);
        for (int i = 1; i <= steps; i++) {
            turningMachine.nextStep();
            //            System.out.println(i + ": " + turningMachine);
        }
        return turningMachine;
    }

    private static int parseChecksumSteps(String turningMachineBlueprint) {
        Matcher matcher = CHECKSUM_AFTER_PATTERN.matcher(turningMachineBlueprint);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Expect diagnostic checksum" + CHECKSUM_AFTER_PATTERN);
        }
        return Integer.parseInt(matcher.group(1));
    }

}
