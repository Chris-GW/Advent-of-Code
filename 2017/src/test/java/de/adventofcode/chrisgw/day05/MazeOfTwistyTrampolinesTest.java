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
        long expectedStepCount = 5;
        List<Integer> expectedPointerPositions = Arrays.asList(0, 1, 4, 1, 5);
        List<Integer> jumpInstructions = Arrays.asList(0, 3, 0, 1, -3);

        MazeOfTwistyTrampolines mazeOfTwistyTrampolines = new MazeOfTwistyTrampolines(jumpInstructions);
        for (int step = 0; mazeOfTwistyTrampolines.isPointerInsideOfJumpInstructions(); step++) {
            System.out.println(step + ": " + mazeOfTwistyTrampolines);
            mazeOfTwistyTrampolines.followNextJumpInstruction();
            int pointer = mazeOfTwistyTrampolines.getPointer();
            int expectedPointerPosition = expectedPointerPositions.get(step);
            Assert.assertEquals("Expect pointer in step " + step, expectedPointerPosition, pointer);
        }
        System.out.println(expectedPointerPositions.size() + ": " + mazeOfTwistyTrampolines);
        Assert.assertEquals("Expect stepCount", expectedStepCount, mazeOfTwistyTrampolines.getStepCount());
    }

    @Test
    public void shouldFollow_my_jumpInstructions() {
        long expectedStepCount = 396086;
        String classpathResource = "/day05/MazeOfTwistyTrampolinesTest_chrisgw.txt";
        List<Integer> jumpInstructions = TestUtils.readAllLinesOfClassPathResource(classpathResource)
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        MazeOfTwistyTrampolines mazeOfTwistyTrampolines = new MazeOfTwistyTrampolines(jumpInstructions);
        System.out.println(mazeOfTwistyTrampolines);
        mazeOfTwistyTrampolines.followJumpInstructionsTillEnd();
        System.out.println(mazeOfTwistyTrampolines);
        Assert.assertEquals("Expect stepCount", expectedStepCount, mazeOfTwistyTrampolines.getStepCount());
    }

}
