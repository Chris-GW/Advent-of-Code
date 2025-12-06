package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2025/day/6">Advent of Code - day 6</a>
 */
public class AdventOfCodeDay06 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay06(List<String> inputLines) {
        super(Year.of(2025), 6, inputLines);
    }


    @Override
    public Long solveFirstPart() {
        return IntStream.iterate(0, start -> start < rowLength(), start -> columnEnd(start) + 1)
                .mapToObj(WorksheetColumn::new)
                .mapToLong(WorksheetColumn::solveFirstPart)
                .sum();
    }

    @Override
    public Long solveSecondPart() {
        return IntStream.iterate(0, start -> start < rowLength(), start -> columnEnd(start) + 1)
                .mapToObj(WorksheetColumn::new)
                .mapToLong(WorksheetColumn::solveSecondPart)
                .sum();
    }


    private int columnEnd(int fromIndex) {
        return IntStream.range(fromIndex, rowLength())
                .filter(this::isSpacerColumnAt)
                .findFirst()
                .orElse(rowLength());
    }

    private boolean isSpacerColumnAt(int index) {
        return inputLines().allMatch(row -> row.charAt(index) == ' ');
    }


    private int rowLength() {
        return getInputLines().getFirst().length();
    }


    private class WorksheetColumn {

        final int start;
        final int end;

        public WorksheetColumn(int start) {
            this.start = start;
            this.end = columnEnd(start);
        }


        public long solveFirstPart() {
            return solveColumnValues(values());
        }

        public long solveSecondPart() {
            Stream<String> verticallyNumbers = IntStream.range(start, end)
                    .map(index -> index - start)
                    .mapToObj(this::parseNumberVertically);
            return solveColumnValues(verticallyNumbers);
        }


        private Stream<String> values() {
            return inputLines()
                    .limit(getInputLines().size() - 1L)
                    .map(s -> s.substring(start, end));
        }


        private long solveColumnValues(Stream<String> values) {
            return values
                    .map(String::trim)
                    .mapToLong(Long::parseLong)
                    .reduce(columnOperator())
                    .orElse(0);
        }

        private LongBinaryOperator columnOperator() {
            String operationStr = getInputLines().getLast().substring(start, end);
            return switch (operationStr.trim()) {
                case "+" -> (left, right) -> left + right;
                case "*" -> (left, right) -> left * right;
                default -> throw new IllegalStateException("Unexpected operation: " + operationStr);
            };
        }


        private String parseNumberVertically(int index) {
            return values().reduce("", (left, right) -> left + right.charAt(index));
        }


        @Override
        public String toString() {
            return "[%d-%d)".formatted(start, end);
        }
    }

}
