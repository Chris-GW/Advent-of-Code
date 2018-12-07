package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day07.TheSumOfOtsPart.toStepLetterString;
import static org.junit.Assert.*;


public class TheSumOfOtsPartTest {

    List<String> stepInstructions_example01 = Arrays.asList( //
            "Step C must be finished before step A can begin.", //
            "Step C must be finished before step F can begin.", //
            "Step A must be finished before step B can begin.", //
            "Step A must be finished before step D can begin.", //
            "Step B must be finished before step E can begin.", //
            "Step D must be finished before step E can begin.", //
            "Step F must be finished before step E can begin.");

    @Test
    public void getStepCompletionOrder_example_01() {
        String expectedStepComplitionOrder = "CABDFE";

        TheSumOfOtsPart theSumOfOtsPart = TheSumOfOtsPart.fromStepInstructions(stepInstructions_example01.stream());
        String stepComplitionOrder = toStepLetterString(theSumOfOtsPart.getStepCompletionOrder());
        assertEquals("stepComplitionOrder", expectedStepComplitionOrder, stepComplitionOrder);
    }


    @Test
    public void getStepCompletionOrder_myPuzzleInput() throws Exception{
        String expectedStepComplitionOrder = "OCPUEFIXHRGWDZABTQJYMNKVSL";
        Path stepInstructionFile = TestUtils.getResourcePath("/day07/myPuzzleInput.txt");

        TheSumOfOtsPart theSumOfOtsPart = TheSumOfOtsPart.fromStepInstructions(stepInstructionFile);
        String stepComplitionOrder = toStepLetterString(theSumOfOtsPart.getStepCompletionOrder());
        assertEquals("stepComplitionOrder", expectedStepComplitionOrder, stepComplitionOrder);
    }




}