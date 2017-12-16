package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


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

        long startTime = System.nanoTime();
        PermutationPromenade permutationPromenade = new PermutationPromenade(16);
        for (int i = 0; i < splitDanceMoveDescriptions.length; i++) {
            String dancingMoveDescription = splitDanceMoveDescriptions[i];
            permutationPromenade.executeDanceMove(dancingMoveDescription);
        }
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + "ns <=> " + TimeUnit.NANOSECONDS.toMillis(duration) + "ms");

        String finalDanceLine = permutationPromenade.getDancingProgramLineAsString();
        Assert.assertEquals("expect final dance line", expectedFinalDanceLine, finalDanceLine);
    }


    // --- part 2

    @Test
    public void permutationPromenade_part2_example() {
        List<String> dancingMoveDescriptions = Arrays.asList("s1", "x3/4", "pe/b");
        String expectedFinalDancingProgramLine = "ceadb";
        int danceRepetions = 2;

        PermutationPromenade permutationPromenade = new PermutationPromenade(5);
        List<DanceMove> danceMoves = dancingMoveDescriptions.stream()
                .map(permutationPromenade::parseDanceMove)
                .collect(Collectors.toList());
        permutationPromenade.danceWithRepetions(danceMoves, danceRepetions);

        String dancingProgramLine = permutationPromenade.getDancingProgramLineAsString();
        Assert.assertEquals("final dance line", expectedFinalDancingProgramLine, dancingProgramLine);
    }

    @Ignore
    @Test
    public void permutationPromenade_part2_myTask() {
        String classpathResource = "/day16/PermutationPromenade_chrisgw.txt";
        String danceMoveDescriptions = TestUtils.readFirstLineOfClassPathResource(classpathResource);
        String[] splitDanceMoveDescriptions = danceMoveDescriptions.split(",");
        String expectedFinalDanceLine = "amkjepdhifolgncb";
        int repetions = 1_000_000_000;

        long startTime = System.nanoTime();
        PermutationPromenade permutationPromenade = new PermutationPromenade(16);
        List<DanceMove> danceMoves = Arrays.stream(splitDanceMoveDescriptions)
                .map(permutationPromenade::parseDanceMove)
                .collect(Collectors.toList());
        permutationPromenade.danceWithRepetions(danceMoves, repetions);

        long duration = System.nanoTime() - startTime;
        System.out.println(duration + "ns <=> " + TimeUnit.NANOSECONDS.toMillis(duration) + "ms");

        String finalDanceLine = permutationPromenade.getDancingProgramLineAsString();
        Assert.assertEquals("expect final dance line", expectedFinalDanceLine, finalDanceLine);
    }

}
