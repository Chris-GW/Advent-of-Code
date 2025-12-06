package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.function.LongBinaryOperator;
import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2025/day/6">Advent of Code - day 6</a>
 */
public class AdventOfCodeDay06 extends AdventOfCodePuzzleSolver {

    public static final Pattern OPERATOR_COLUMN_PATTERN = Pattern.compile("[+*]\\s+");

    public AdventOfCodeDay06(List<String> inputLines) {
        super(Year.of(2025), 6, inputLines);
    }


    @Override
    public Long solveFirstPart() {
        String operationRow = getInputLines().getLast();
        Matcher matcher = OPERATOR_COLUMN_PATTERN.matcher(operationRow);
        return matcher.results().mapToLong(this::solveColumn).sum();
    }

    private long solveColumn(MatchResult matchResult) {
        int beginIndex = matchResult.start();
        int endIndex = matchResult.end();
        LongBinaryOperator operator = parseOperator(matchResult.group());
        return columnValues(beginIndex, endIndex)
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .reduce(operator)
                .orElse(0);
    }


    @Override
    public Long solveSecondPart() {
        String operationRow = getInputLines().getLast();
        Matcher matcher = OPERATOR_COLUMN_PATTERN.matcher(operationRow);
        return matcher.results().mapToLong(this::solveColumnSecondPart).sum();
    }

    private long solveColumnSecondPart(MatchResult matchResult) {
        int beginIndex = matchResult.start();
        int endIndex = matchResult.end();
        LongBinaryOperator operator = parseOperator(matchResult.group());
        return IntStream.range(0, endIndex - beginIndex)
                .mapToObj(index -> columnValues(beginIndex, endIndex)
                        .reduce("", (left, right) -> left + right.charAt(index)))
                .map(String::trim)
                .filter(Predicate.not(String::isEmpty))
                .mapToLong(Long::parseLong)
                .reduce(operator)
                .orElse(0);
    }


    private Stream<String> columnValues(int beginIndex, int endIndex) {
        return inputLines()
                .limit(getInputLines().size() - 1L)
                .map(s -> s.substring(beginIndex, endIndex));
    }


    private LongBinaryOperator parseOperator(String operationStr) {
        operationStr = operationStr.trim();
        return switch (operationStr) {
            case "+" -> (left, right) -> left + right;
            case "*" -> (left, right) -> left * right;
            default -> throw new IllegalStateException("Unexpected value: " + operationStr);
        };
    }

}
