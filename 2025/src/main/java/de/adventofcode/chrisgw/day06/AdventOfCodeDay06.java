package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


/**
 * <a href="https://adventofcode.com/2025/day/6">Advent of Code - day 6</a>
 */
public class AdventOfCodeDay06 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay06(List<String> inputLines) {
        super(Year.of(2025), 6, inputLines);
    }


    @Override
    public Long solveFirstPart() {
        return IntStream.range(0, columns())
                .mapToLong(this::solveWorksheetColumn)
                .sum();
    }

    private long solveWorksheetColumn(int column) {
        LongBinaryOperator operator = operationForColumn(column);
        return worksheetColumn(column)
                .reduce(operator)
                .orElse(0);
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    private LongStream worksheetColumn(int column) {
        return inputLines()
                .limit(rows() - 1L)
                .map(String::trim)
                .map(s -> s.split("\\s+")[column])
                .mapToLong(Long::parseLong);
    }


    private int columns() {
        return inputLines()
                .findFirst()
                .map(String::trim)
                .map(s -> s.split("\\s+").length)
                .orElse(0);
    }

    private int rows() {
        return getInputLines().size();
    }


    private LongBinaryOperator operationForColumn(int column) {
        String[] split = getInputLines().getLast().split("\\s+");
        String operationChar = split[column];
        return switch (operationChar) {
            case "+" -> (left, right) -> left + right;
            case "*" -> (left, right) -> left * right;
            default -> throw new IllegalStateException("Unexpected value: " + operationChar);
        };
    }

}
