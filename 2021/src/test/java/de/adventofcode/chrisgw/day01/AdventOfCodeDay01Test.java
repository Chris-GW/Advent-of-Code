package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AdventOfCodeDay01Test {

    private static final int[] exampleMeasurements = new int[] { //
            199, //
            200, //
            208, //
            210, //
            200, //
            207, //
            240, //
            269, //
            260, //
            263 };


    @Test
    public void part1_example() {
        int[] measurements = Arrays.copyOf(exampleMeasurements, exampleMeasurements.length);
        int result = AdventOfCodeDay01.calculateMeasurementIncreases(measurements);
        assertThat("calculateMeasurementIncreases", result, is(7));
    }

    @Test
    public void part1_myPuzzle() {
        List<String> measurementLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int[] measurements = measurementLines.stream().mapToInt(Integer::parseInt).toArray();
        int result = AdventOfCodeDay01.calculateMeasurementIncreases(measurements);
        assertThat("calculateMeasurementIncreases", result, is(1292));
    }


    @Test
    public void part2_example() {
        int[] measurements = Arrays.copyOf(exampleMeasurements, exampleMeasurements.length);
        int result = AdventOfCodeDay01.calculateMeasurementIncreasesWithWindow(measurements);
        assertThat("calculateMeasurementIncreasesWithWindow", result, is(5));
    }

    @Test
    public void part2_myPuzzle() {
        List<String> measurementLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int[] measurements = measurementLines.stream().mapToInt(Integer::parseInt).toArray();
        int result = AdventOfCodeDay01.calculateMeasurementIncreasesWithWindow(measurements);
        assertThat("calculateMeasurementIncreasesWithWindow", result, is(1262));
    }

}
