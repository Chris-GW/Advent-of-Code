package de.adventofcode.chrisgw.day25;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;


public class TheHaltingProblemTest {


    @Test
    public void TheHaltingProblem_part1_example() {
        String turningMachineBlueprint = "" //
                + "Begin in state A.\n" //
                + "Perform a diagnostic checksum after 6 steps.\n" //
                + "\n" //
                + "In state A:\n" //
                + "  If the current value is 0:\n" //
                + "    - Write the value 1.\n" //
                + "    - Move one slot to the right.\n" //
                + "    - Continue with state B.\n" //
                + "  If the current value is 1:\n" //
                + "    - Write the value 0.\n" //
                + "    - Move one slot to the left.\n" //
                + "    - Continue with state B.\n" //
                + "\n" //
                + "In state B:\n" //
                + "  If the current value is 0:\n" //
                + "    - Write the value 1.\n" //
                + "    - Move one slot to the left.\n" //
                + "    - Continue with state A.\n" //
                + "  If the current value is 1:\n" //
                + "    - Write the value 1.\n" //
                + "    - Move one slot to the right.\n" //
                + "    - Continue with state A."; //
        long expectedChecksum = 3;

        TurningMachine turningMachine = TheHaltingProblem.debugTurningMachine(turningMachineBlueprint);
        long checksum = turningMachine.calculateChecksum();

        Assert.assertEquals("expect turning machine checksum", expectedChecksum, checksum);
    }


    @Test
    public void TheHaltingProblem_part1_myTask() {
        String classpathResource = "/day25/TheHaltingProblem_chrisgw.txt";
        String turningMachineBlueprint = String.join("\n", TestUtils.readAllLinesOfClassPathResource(classpathResource)); //
        long expectedChecksum = 4387;

        TurningMachine turningMachine = TheHaltingProblem.debugTurningMachine(turningMachineBlueprint);
        long checksum = turningMachine.calculateChecksum();

        Assert.assertEquals("expect turning machine checksum", expectedChecksum, checksum);
    }

}