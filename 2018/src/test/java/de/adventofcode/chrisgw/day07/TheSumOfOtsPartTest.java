package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day07.TheSumOfOtsPart.StepComplitionResult;
import org.junit.Test;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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
    public void calculateStepCompletionOrder_example_01() {
        String expectedStepComplitionOrder = "CABDFE";
        Duration taskDuration = Duration.ZERO;

        TheSumOfOtsPart theSumOfOtsPart = TheSumOfOtsPart.fromStepInstructions(stepInstructions_example01.stream(),
                taskDuration);
        StepComplitionResult stepComplitionResult = theSumOfOtsPart.calculateStepCompletionOrder();
        String stepComplitionOrder = stepComplitionResult.completionOrderString();

        assertEquals("stepComplitionOrder", expectedStepComplitionOrder, stepComplitionOrder);
    }


    @Test
    public void calculateStepCompletionOrder_myPuzzleInput() throws Exception {
        String expectedStepComplitionOrder = "OCPUEFIXHRGWDZABTQJYMNKVSL";
        Path stepInstructionFile = TestUtils.getResourcePath("/day07/myPuzzleInput.txt");
        Duration taskDuration = Duration.ofSeconds(60);

        TheSumOfOtsPart theSumOfOtsPart = TheSumOfOtsPart.fromStepInstructions(stepInstructionFile, taskDuration);
        StepComplitionResult stepComplitionResult = theSumOfOtsPart.calculateStepCompletionOrder();
        String stepComplitionOrder = stepComplitionResult.completionOrderString();

        assertEquals("stepComplitionOrder", expectedStepComplitionOrder, stepComplitionOrder);
    }


    // part 02

    @Test
    public void calculateStepCompletionOrder_withWorkers02_example_01() {
        String expectedStepComplitionOrder = "CABFDE";
        int workerCount = 2;
        Duration taskDuration = Duration.ZERO;
        Duration expectedNeededDuration = Duration.ofSeconds(15);

        TheSumOfOtsPart theSumOfOtsPart = TheSumOfOtsPart.fromStepInstructions(stepInstructions_example01.stream(),
                taskDuration);
        StepComplitionResult stepComplitionResult = theSumOfOtsPart.calculateStepCompletionOrder(workerCount);
        String stepCompletionOrder = stepComplitionResult.completionOrderString();
        Duration neededDuration = stepComplitionResult.getNeededDuration();

        assertEquals("stepComplitionOrder", expectedStepComplitionOrder, stepCompletionOrder);
        assertEquals("neededDuration", expectedNeededDuration, neededDuration);
    }


    @Test
    public void calculateStepCompletionOrder__withWorkers02_myPuzzleInput() throws Exception {
        String expectedStepComplitionOrder = "OPCUXEHFIRWZGDABTQYJMNKVSL";
        Path stepInstructionFile = TestUtils.getResourcePath("/day07/myPuzzleInput.txt");
        Duration taskDuration = Duration.ofSeconds(60);
        int workerCount = 15;
        Duration expectedNeededDuration = Duration.ofSeconds(991);

        TheSumOfOtsPart theSumOfOtsPart = TheSumOfOtsPart.fromStepInstructions(stepInstructionFile, taskDuration);
        StepComplitionResult stepComplitionResult = theSumOfOtsPart.calculateStepCompletionOrder(workerCount);
        String stepCompletionOrder = stepComplitionResult.completionOrderString();
        Duration neededDuration = stepComplitionResult.getNeededDuration();

        assertEquals("stepComplitionOrder", expectedStepComplitionOrder, stepCompletionOrder);
        assertEquals("neededDuration", expectedNeededDuration, neededDuration);
    }

}
