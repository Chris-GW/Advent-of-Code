package de.adventofcode.chrisgw.day19;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;


public class SeriesOfTubesTest {

    @Test
    public void seriesOfTubes_part1_example() {
        // @formatter:off
        String network = ""
                + "     |          \n"
                + "     |  +--+    \n"
                + "     A  |  C    \n"
                + " F---|----E|--+ \n"
                + "     |  |  |  D \n"
                + "     +B-+  +--+ \n";
        // @formatter:on
        String expectedLetters = "ABCDEF";

        SeriesOfTubes seriesOfTubes = new SeriesOfTubes(network);
        for (int i = 0; !seriesOfTubes.isAtNetworkEnd(); i++) {
            seriesOfTubes.followNextConnectionTillEnd();
            System.out.println(seriesOfTubes);
        }
        String letters = seriesOfTubes.getLettersAsString();

        Assert.assertEquals("expect letters", expectedLetters, letters);
    }


    @Test
    public void seriesOfTubes_part1_myTask() {
        String classpathResource = "/day19/SeriesOfTubes_chrisgw.txt";
        String network = String.join("\n", TestUtils.readAllLinesOfClassPathResource(classpathResource));
        String expectedLetters = "GSXDIPWTU";

        SeriesOfTubes seriesOfTubes = new SeriesOfTubes(network);
        for (int i = 0; !seriesOfTubes.isAtNetworkEnd(); i++) {
            seriesOfTubes.followNextConnectionTillEnd();
        }
        String letters = seriesOfTubes.getLettersAsString();

        Assert.assertEquals("expect letters", expectedLetters, letters);
    }


    // --- part 2

    @Test
    public void seriesOfTubes_part2_example() {
        // @formatter:off
        String network = ""
                + "     |          \n"
                + "     |  +--+    \n"
                + "     A  |  C    \n"
                + " F---|----E|--+ \n"
                + "     |  |  |  D \n"
                + "     +B-+  +--+ \n";
        // @formatter:on
        int expectedStepCount = 38;

        SeriesOfTubes seriesOfTubes = new SeriesOfTubes(network);
        for (int i = 0; !seriesOfTubes.isAtNetworkEnd(); i++) {
            seriesOfTubes.followNextConnectionTillEnd();
            System.out.println(seriesOfTubes);
        }
        int stepCount = seriesOfTubes.getStepCount();

        Assert.assertEquals("expect step count", expectedStepCount, stepCount);
    }


    @Test
    public void seriesOfTubes_part2_myTask() {
        String classpathResource = "/day19/SeriesOfTubes_chrisgw.txt";
        String network = String.join("\n", TestUtils.readAllLinesOfClassPathResource(classpathResource));
        int expectedStepCount = 16100;

        SeriesOfTubes seriesOfTubes = new SeriesOfTubes(network);
        for (int i = 0; !seriesOfTubes.isAtNetworkEnd(); i++) {
            seriesOfTubes.followNextConnectionTillEnd();
        }
        int stepCount = seriesOfTubes.getStepCount();

        Assert.assertEquals("expect step count", expectedStepCount, stepCount);
    }

}