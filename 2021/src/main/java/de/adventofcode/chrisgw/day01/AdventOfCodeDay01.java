package de.adventofcode.chrisgw.day01;


import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.List;


/**
 * https://adventofcode.com/2021/day/1
 */
public class AdventOfCodeDay01 extends AdventOfCodePuzzle<Integer> {


    public AdventOfCodeDay01(List<String> inputLines) {
        super(Year.of(2021), 1, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        int[] measurements = inputAsMeasurements();
        int increaseCounter = 0;
        for (int i = 1; i < measurements.length; i++) {
            int previousMeasurement = measurements[i - 1];
            int currentMeasurement = measurements[i];
            if (previousMeasurement < currentMeasurement) {
                increaseCounter++;
            }
        }
        return increaseCounter;
    }

    @Override
    public Integer solveSecondPart() {
        int[] measurements = inputAsMeasurements();
        int increaseCounter = 0;
        int previousWindowValue = Integer.MAX_VALUE;
        for (int i = 2; i < measurements.length; i++) {
            int currentWindowValue = measurements[i - 2] + measurements[i - 1] + measurements[i];
            if (previousWindowValue < currentWindowValue) {
                increaseCounter++;
            }
            previousWindowValue = currentWindowValue;
        }
        return increaseCounter;
    }


    private int[] inputAsMeasurements() {
        return inputLines().mapToInt(Integer::parseInt).toArray();
    }

}
