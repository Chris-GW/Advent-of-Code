package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MazeOfTwistyTrampolinesTest {


    @Test
    public void shouldFollow_example_JumpInstructions() {
        List<Integer> jumpInstructions = Arrays.asList(0, 3, 0, 1, -3);
        long expectedStepCount = 5;
        List<Integer> expectedPointerPositions = Arrays.asList(0, 1, 4, 1, 5);

        MazeOfTwistyTrampolines mazeOfTwistyTrampolines = new MazeOfTwistyTrampolines(jumpInstructions);
        for (int step = 0; mazeOfTwistyTrampolines.isPointerInsideOfJumpInstructions(); step++) {
            System.out.println(step + ": " + mazeOfTwistyTrampolines);
            mazeOfTwistyTrampolines.followNextJumpInstruction();
            int pointer = mazeOfTwistyTrampolines.getPointer();
            int expectedPointerPosition = expectedPointerPositions.get(step);
            Assert.assertEquals("Expect pointer in step " + step, expectedPointerPosition, pointer);
        }
        System.out.println("result: " + mazeOfTwistyTrampolines);
        Assert.assertEquals("Expect stepCount", expectedStepCount, mazeOfTwistyTrampolines.getStepCount());
    }

    @Test
    public void shouldFollow_my_jumpInstructions() {
        String classpathResource = "/day05/MazeOfTwistyTrampolinesTest_chrisgw.txt";
        List<Integer> jumpInstructions = readJumpInstructionsFromClasspathResource(classpathResource);
        long expectedStepCount = 396086;

        MazeOfTwistyTrampolines mazeOfTwistyTrampolines = new MazeOfTwistyTrampolines(jumpInstructions);
        System.out.println("beginning: " + mazeOfTwistyTrampolines);
        mazeOfTwistyTrampolines.followJumpInstructionsTillEnd();
        System.out.println("result: " + mazeOfTwistyTrampolines);

        Assert.assertEquals("Expect stepCount", expectedStepCount, mazeOfTwistyTrampolines.getStepCount());
    }


    // --- part 2

    @Test
    public void shouldFollow_example_jumpInstructions_part2() {
        List<Integer> jumpInstructions = Arrays.asList(0, 3, 0, 1, -3);
        int upperLimit = 3;
        long expectedStepCount = 10;
        List<Integer> expectedJumpInsturctionsAtEnd = Arrays.asList(2, 3, 2, 3, -1);

        MazeOfTwistyTrampolines mazeOfTwistyTrampolines = new MazeOfTwistyTrampolines(jumpInstructions,
                MazeOfTwistyTrampolines.incrementWhenSmallerThanLimitOtherwiseDecrement(upperLimit));

        for (int step = 0; mazeOfTwistyTrampolines.isPointerInsideOfJumpInstructions(); step++) {
            System.out.println(step + ": " + mazeOfTwistyTrampolines);
            mazeOfTwistyTrampolines.followNextJumpInstruction();
        }
        System.out.println("result: " + mazeOfTwistyTrampolines);

        List<Integer> jumpInsturctionsAtEnd = mazeOfTwistyTrampolines.getJumpInstructions();
        Assert.assertEquals("Expect jumpInsturctions at end", expectedJumpInsturctionsAtEnd, jumpInsturctionsAtEnd);
        Assert.assertEquals("Expect stepCount", expectedStepCount, mazeOfTwistyTrampolines.getStepCount());
    }

    @Test
    public void shouldFollow_my_jumpInstructions_part2() {
        String classpathResource = "/day05/MazeOfTwistyTrampolinesTest_chrisgw.txt";
        List<Integer> jumpInstructions = readJumpInstructionsFromClasspathResource(classpathResource);
        int upperLimit = 3;
        long expectedStepCount = 28675390;

        MazeOfTwistyTrampolines mazeOfTwistyTrampolines = new MazeOfTwistyTrampolines(jumpInstructions,
                MazeOfTwistyTrampolines.incrementWhenSmallerThanLimitOtherwiseDecrement(upperLimit));

        System.out.println("beginning: " + mazeOfTwistyTrampolines);
        mazeOfTwistyTrampolines.followJumpInstructionsTillEnd();
        System.out.println("result: " + mazeOfTwistyTrampolines);

        Assert.assertEquals("Expect stepCount", expectedStepCount, mazeOfTwistyTrampolines.getStepCount());
    }


    private List<Integer> readJumpInstructionsFromClasspathResource(String classpathResource) {
        return TestUtils.readAllLinesOfClassPathResource(classpathResource)
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
