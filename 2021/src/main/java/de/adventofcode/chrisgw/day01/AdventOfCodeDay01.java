package de.adventofcode.chrisgw.day01;


/**
 * https://adventofcode.com/2021/day/1
 */
public class AdventOfCodeDay01 {

    private AdventOfCodeDay01() {

    }


    public static int calculateMeasurementIncreases(int[] measurements) {
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


    public static int calculateMeasurementIncreasesWithWindow(int[] measurements) {
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

}
