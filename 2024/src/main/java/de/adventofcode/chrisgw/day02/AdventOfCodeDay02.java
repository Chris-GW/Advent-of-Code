package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


/**
 * <a href="https://adventofcode.com/2024/day/2">Advent of Code - day 2</a>
 */
public class AdventOfCodeDay02 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay02(List<String> inputLines) {
        super(Year.of(2024), 2, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        long safeReportCount = inputLines()
                .map(ReactorLevelReport::parseReport)
                .filter(ReactorLevelReport::isSafe)
                .count();
        return Math.toIntExact(safeReportCount);
    }


    @Override
    public Integer solveSecondPart() {
        long safeReportCount = inputLines()
                .map(ReactorLevelReport::parseReport)
                .filter(AdventOfCodeDay02::hasAnySafeReportWithProblemDampener)
                .count();
        return Math.toIntExact(safeReportCount);
    }

    private static boolean hasAnySafeReportWithProblemDampener(ReactorLevelReport report) {
        return IntStream.range(0, report.size())
                .mapToObj(report::withIgnoredLevelAt)
                .anyMatch(ReactorLevelReport::isSafe);
    }


    private record ReactorLevelReport(List<Integer> levels) {

        public static ReactorLevelReport parseReport(String reportLine) {
            List<Integer> levels = Pattern.compile("\\s+")
                    .splitAsStream(reportLine)
                    .map(Integer::parseInt)
                    .toList();
            return new ReactorLevelReport(levels);
        }


        public int size() {
            return levels.size();
        }


        public boolean isSafe() {
            boolean sameDirection = allIncreasing() || allDecreasing();
            return sameDirection && allInRange(1, 3);
        }


        private boolean allIncreasing() {
            return levelDifferences().allMatch(difference -> difference > 0);
        }

        private boolean allDecreasing() {
            return levelDifferences().allMatch(difference -> difference < 0);
        }

        private boolean allInRange(int min, int max) {
            return levelDifferences()
                    .map(Math::abs)
                    .allMatch(level -> min <= level && level <= max);
        }

        private IntStream levelDifferences() {
            return IntStream.range(1, levels.size()).map(i -> levels.get(i - 1) - levels.get(i));
        }


        public ReactorLevelReport withIgnoredLevelAt(int ignoredLevelIndex) {
            List<Integer> newLevels = new LinkedList<>(levels);
            newLevels.remove(ignoredLevelIndex);
            return new ReactorLevelReport(newLevels);
        }

    }

}
