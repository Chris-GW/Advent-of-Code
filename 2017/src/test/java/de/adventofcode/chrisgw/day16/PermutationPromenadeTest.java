package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class PermutationPromenadeTest {


    @Test
    public void permutationPromenade_part1_spin3() {
        List<String> dancingMoveDescriptions = Arrays.asList("s3");
        List<String> expectedDancingProgramLines = Arrays.asList("cdeab");

        PermutationPromenade permutationPromenade = new PermutationPromenade(5);
        for (int i = 0; i < dancingMoveDescriptions.size(); i++) {
            String dancingMoveDescription = dancingMoveDescriptions.get(i);
            System.out.printf("%6d: %s -[%4s]-> ", i, permutationPromenade, dancingMoveDescription);
            permutationPromenade.executeDanceMove(dancingMoveDescription);
            System.out.printf("%s\n", permutationPromenade);
            String expectedDancingLine = expectedDancingProgramLines.get(i);
            String dancingLine = permutationPromenade.getDancingProgramLineAsString();
            Assert.assertEquals("expect dancing program line " + i, expectedDancingLine, dancingLine);
        }
    }

    @Test
    public void permutationPromenade_part1_example() {
        List<String> dancingMoveDescriptions = Arrays.asList("s1", "x3/4", "pe/b");
        List<String> expectedDancingProgramLines = Arrays.asList("eabcd", "eabdc", "baedc");

        PermutationPromenade permutationPromenade = new PermutationPromenade(5);
        for (int i = 0; i < dancingMoveDescriptions.size(); i++) {
            String dancingMoveDescription = dancingMoveDescriptions.get(i);
            System.out.printf("%6d: %s -[%4s]-> ", i, permutationPromenade, dancingMoveDescription);
            permutationPromenade.executeDanceMove(dancingMoveDescription);
            System.out.printf("%s\n", permutationPromenade);
            String expectedDancingLine = expectedDancingProgramLines.get(i);
            String dancingLine = permutationPromenade.getDancingProgramLineAsString();
            Assert.assertEquals("expect dancing program line " + i, expectedDancingLine, dancingLine);
        }
    }

    @Test
    public void permutationPromenade_part1_myTask() {
        String classpathResource = "/day16/PermutationPromenade_chrisgw.txt";
        String danceMoveDescriptions = TestUtils.readFirstLineOfClassPathResource(classpathResource);
        String[] splitDanceMoveDescriptions = danceMoveDescriptions.split(",");
        String expectedFinalDanceLine = "fnloekigdmpajchb";

        PermutationPromenade permutationPromenade = new PermutationPromenade(16);
        for (int i = 0; i < splitDanceMoveDescriptions.length; i++) {
            String dancingMoveDescription = splitDanceMoveDescriptions[i];
            System.out.printf("%6d: %s -[%4s]-> ", i, permutationPromenade, dancingMoveDescription);
            permutationPromenade.executeDanceMove(dancingMoveDescription);
            System.out.printf("%s\n", permutationPromenade);
        }

        String finalDanceLine = permutationPromenade.getDancingProgramLineAsString();
        Assert.assertEquals("expect final dance line", expectedFinalDanceLine, finalDanceLine);
    }

}