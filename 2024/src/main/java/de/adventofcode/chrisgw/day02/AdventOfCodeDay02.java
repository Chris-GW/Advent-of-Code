package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
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
                .map(Report::parseReport)
                .filter(Report::isSafe)
                .count();
        return Math.toIntExact(safeReportCount);
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    private record Report(int[] levels) {

        public static Report parseReport(String reportLine) {
            int[] levels = Pattern.compile("\\s+")
                    .splitAsStream(reportLine)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return new Report(levels);
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
            return IntStream.range(1, levels.length).map(i -> levels[i - 1] - levels[i]);
        }

        public boolean isSafe() {
            boolean sameDirection = allIncreasing() || allDecreasing();
            return sameDirection && allInRange(1, 3);
        }

    }

}
