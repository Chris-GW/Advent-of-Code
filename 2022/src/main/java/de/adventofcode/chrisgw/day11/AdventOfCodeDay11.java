package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * <a href="https://adventofcode.com/2022/day/11">Advent of Code - day 11</a>
 */
public class AdventOfCodeDay11 extends AdventOfCodePuzzleSolver {


    private Map<Integer, Monkey> monkeysByNumber = new HashMap<>();

    public AdventOfCodeDay11(List<String> inputLines) {
        super(Year.of(2022), 11, inputLines);
    }


    public Integer solveFirstPart() {
        List<String> inputLines = getInputLines();
        for (int lineIndex = 0; lineIndex < inputLines.size(); lineIndex++) {
            if (inputLines.get(lineIndex).isBlank()) {
                continue;
            }
            int monkeyNumber = parseMonkeyNumber(inputLines.get(lineIndex));
            Monkey currentMonkey = findOrCreateMonkeyByNumber(monkeyNumber);
            currentMonkey.setItemWorryLevels(parseStartItems(inputLines.get(++lineIndex)));
            currentMonkey.setInspectStolenItemOperator(parseOperation(inputLines.get(++lineIndex)));
            currentMonkey.setItemInspectionDivisor(parseItemInspectionDivisor(inputLines.get(++lineIndex)));
            int monkeyInCaseTrue = parseTargetMonkeyInCaseTrue(inputLines.get(++lineIndex));
            int monkeyInCaseFalse = parseTargetMonkeyInCaseFalse(inputLines.get(++lineIndex));
            currentMonkey.setTargetMonkeyInCaseTrue(findOrCreateMonkeyByNumber(monkeyInCaseTrue));
            currentMonkey.setTargetMonkeyInCaseFalse(findOrCreateMonkeyByNumber(monkeyInCaseFalse));
        }

        playRounds(20);

        return monkeysByNumber.values()
                .stream()
                .sorted(Comparator.comparingInt(Monkey::getItemInspectCount).reversed())
                .limit(2)
                .mapToInt(Monkey::getItemInspectCount)
                .reduce((count, otherCount) -> count * otherCount)
                .orElseThrow();
    }

    private void playRounds(int rounds) {
        for (int round = 0; round < rounds; round++) {
            monkeysByNumber.keySet()
                    .stream()
                    .sorted()
                    .map(monkeysByNumber::get)
                    .forEachOrdered(Monkey::takeTurn);
        }
    }

    private Monkey findOrCreateMonkeyByNumber(int monkeyNumber) {
        return monkeysByNumber.computeIfAbsent(monkeyNumber, Monkey::new);
    }


    private int parseMonkeyNumber(String line) {
        Pattern monkeyNumberPattern = Pattern.compile("Monkey (\\d+):");
        Matcher matcher = monkeyNumberPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + monkeyNumberPattern + "', but was: " + line);
        }
        return Integer.parseInt(matcher.group(1));
    }


    private Deque<Integer> parseStartItems(String line) {
        Pattern startItemsPattern = Pattern.compile("\\s{2}Starting items: (\\d+(?:, \\d+)*)");
        Matcher matcher = startItemsPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + startItemsPattern + "', but was: " + line);
        }
        return Arrays.stream(matcher.group(1).split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }


    private IntUnaryOperator parseOperation(String line) {
        Pattern operationPattern = Pattern.compile("\\s{2}Operation: new = (old|\\d+) ([*+]) (old|\\d+)");
        Matcher matcher = operationPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + operationPattern + "', but was: " + line);
        }
        return new IntUnaryOperator() {
            final String firstArgument = matcher.group(1);

            final String operator = matcher.group(2);
            final String secondArgument = matcher.group(3);

            @Override
            public int applyAsInt(int oldWorryLevel) {
                int firstArgument = resolveWorryLevelArgument(this.firstArgument, oldWorryLevel);
                int secondArgument = resolveWorryLevelArgument(this.secondArgument, oldWorryLevel);
                return switch (operator) {
                    case "*" -> firstArgument * secondArgument;
                    case "+" -> firstArgument + secondArgument;
                    default -> throw new IllegalArgumentException("unknown operator: " + operator);
                };
            }

            private int resolveWorryLevelArgument(String argument, int oldWorryLevel) {
                if ("old".equals(argument)) {
                    return oldWorryLevel;
                } else {
                    return Integer.parseInt(argument);
                }
            }

        };
    }


    private int parseItemInspectionDivisor(String line) {
        Pattern testPattern = Pattern.compile("\\s{2}Test: divisible by (\\d+)");
        Matcher matcher = testPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + testPattern + "', but was: " + line);
        }
        return Integer.parseInt(matcher.group(1));
    }


    private int parseTargetMonkeyInCaseTrue(String line) {
        Pattern throwTruePattern = Pattern.compile("\\s{4}If true: throw to monkey (\\d+)");
        Matcher matcher = throwTruePattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + throwTruePattern + "', but was: " + line);
        }
        return Integer.parseInt(matcher.group(1));
    }

    private int parseTargetMonkeyInCaseFalse(String line) {
        Pattern throwFalsePattern = Pattern.compile("\\s{4}If false: throw to monkey (\\d+)");
        Matcher matcher = throwFalsePattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + throwFalsePattern + "', but was: " + line);
        }
        return Integer.parseInt(matcher.group(1));
    }


    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
