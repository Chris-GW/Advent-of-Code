package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2023/day/1">Advent of Code - day 1</a>
 */
public class AdventOfCodeDay01 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay01(List<String> inputLines) {
        super(Year.of(2023), 1, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        return inputLines().mapToInt(AdventOfCodeDay01::findCalibrationValue).sum();
    }

    private static int findCalibrationValue(String line) {
        int firstDigit = findFirstDigit(line);
        int lastDigit = findLastDigit(line);
        return firstDigit * 10 + lastDigit;
    }

    private static int findFirstDigit(String line) {
        for (int i = 0; i < line.length(); i++) {
            for (SpelledDigit spelledDigit : SpelledDigit.values()) {
                String digitStr = String.valueOf(spelledDigit.getDigit());
                String digitName = spelledDigit.name().toLowerCase();
                if (line.startsWith(digitStr, i) || line.startsWith(digitName, i)) {
                    return spelledDigit.getDigit();
                }
            }
        }
        throw new IllegalArgumentException("could not find digit in line: " + line);
    }

    private static int findLastDigit(String line) {
        for (int i = line.length() - 1; i >= 0; i--) {
            for (SpelledDigit spelledDigit : SpelledDigit.values()) {
                String digitStr = String.valueOf(spelledDigit.getDigit());
                String digitName = spelledDigit.name().toLowerCase();
                if (line.startsWith(digitStr, i) || line.startsWith(digitName, i)) {
                    return spelledDigit.getDigit();
                }
            }
        }
        throw new IllegalArgumentException("could not find digit in line: " + line);
    }


    @Override
    public Integer solveSecondPart() {
        return inputLines().mapToInt(AdventOfCodeDay01::findCalibrationValue).sum();
    }

}
