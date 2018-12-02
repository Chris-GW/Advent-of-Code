package de.adventofcode.chrisgw.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;


public class ChronalCalibration {

    // part 01

    public static long calculateFrequency(Path frequenciesDataPath) throws IOException {
        IntStream frequenciesStream = Files.lines(frequenciesDataPath).mapToInt(Integer::parseInt);
        return calculateFrequency(frequenciesStream);
    }

    public static long calculateFrequency(IntStream frequencies) {
        return frequencies.parallel().sum();
    }


    // part 02

    public static long calculateFirstRepeatedFrequency(Path frequenciesDataPath) throws IOException {
        IntStream frequenciesStream = Files.lines(frequenciesDataPath).mapToInt(Integer::parseInt);
        return calculateFirstRepeatedFrequency(frequenciesStream);
    }

    public static long calculateFirstRepeatedFrequency(IntStream frequenciesStream) {
        int currentFrequency = 0;
        int[] frequencies = frequenciesStream.toArray();
        Set<Integer> resultingFrequencies = new HashSet<>();
        resultingFrequencies.add(0);

        while (true) {
            for (int frequencyChange : frequencies) {
                currentFrequency += frequencyChange;
                if (!resultingFrequencies.add(currentFrequency)) {
                    return currentFrequency;
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
    }

}
