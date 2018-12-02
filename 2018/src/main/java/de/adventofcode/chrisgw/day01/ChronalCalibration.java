package de.adventofcode.chrisgw.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;


public class ChronalCalibration {


    public static long calculateFrequency(Path frequenciesDataPath) throws IOException {
        IntStream frequenciesStream = Files.lines(frequenciesDataPath).mapToInt(Integer::parseInt);
        return calculateFrequency(frequenciesStream);
    }

    public static long calculateFrequency(IntStream frequencies) {
        return frequencies.parallel().sum();
    }


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
    }

}
