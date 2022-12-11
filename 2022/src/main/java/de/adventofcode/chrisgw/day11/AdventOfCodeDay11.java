package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
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


    public Long solveFirstPart() {
        parseMonkeySituation(true);
        playRounds(20);
        return messureMonkeyLevelBusiness();
    }

    public Long solveSecondPart() {
        parseMonkeySituation(false);
        playRounds(10_000);
        return messureMonkeyLevelBusiness();
    }

    private long messureMonkeyLevelBusiness() {
        return monkeysByNumber.values()
                .stream()
                .sorted(Comparator.comparing(Monkey::getItemInspectCount).reversed())
                .limit(2)
                .mapToLong(Monkey::getItemInspectCount)
                .reduce((left, right) -> left * right)
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

    private void parseMonkeySituation(boolean parseOperation) {
        List<String> inputLines = getInputLines();
        for (int lineIndex = 0; lineIndex < inputLines.size(); lineIndex++) {
            String line = inputLines.get(lineIndex);
            if (line.isBlank()) {
                continue;
            }
            int monkeyNumber = parseMonkeyNumber(line);
            Monkey currentMonkey = findOrCreateMonkeyByNumber(monkeyNumber);

            line = inputLines.get(++lineIndex);
            currentMonkey.setItemWorryLevels(parseStartItems(line));

            line = inputLines.get(++lineIndex);
            currentMonkey.setItemInspectionOperator(parseOperation(line, parseOperation));

            line = inputLines.get(++lineIndex);
            currentMonkey.setItemInspectionDivisor(parseItemInspectionDivisor(line));

            line = inputLines.get(++lineIndex);
            int monkeyInCaseTrue = parseTargetMonkeyInCaseTrue(line);
            currentMonkey.setTargetMonkeyInCaseTrue(findOrCreateMonkeyByNumber(monkeyInCaseTrue));

            line = inputLines.get(++lineIndex);
            int monkeyInCaseFalse = parseTargetMonkeyInCaseFalse(line);
            currentMonkey.setTargetMonkeyInCaseFalse(findOrCreateMonkeyByNumber(monkeyInCaseFalse));
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


    private Deque<Long> parseStartItems(String line) {
        Pattern startItemsPattern = Pattern.compile("\\s{2}Starting items: (\\d+(?:, \\d+)*)");
        Matcher matcher = startItemsPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + startItemsPattern + "', but was: " + line);
        }
        return Arrays.stream(matcher.group(1).split(", "))
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));
    }


    private LongUnaryOperator parseOperation(String line, boolean noDamageWorryLevelRelive) {
        Pattern operationPattern = Pattern.compile("\\s{2}Operation: new = old ([*+]) (old|\\d+)");
        Matcher matcher = operationPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + operationPattern + "', but was: " + line);
        }
        String operatorString = matcher.group(1);
        String secondArgumentString = matcher.group(2);

        LongBinaryOperator operator = asBinaryOperator(operatorString);
        LongUnaryOperator secondOperator = asOperator(secondArgumentString);
        return oldWorryLevel -> {
            long secondArgument = secondOperator.applyAsLong(oldWorryLevel);
            long newWorryLevel = operator.applyAsLong(oldWorryLevel, secondArgument);
            if (noDamageWorryLevelRelive) {
                newWorryLevel = newWorryLevel / 3;
            }
            newWorryLevel = newWorryLevel % monkeysCommonDivisor();
            return newWorryLevel;
        };
    }

    private long monkeysCommonDivisor() {
        return monkeysByNumber.values()
                .stream()
                .mapToLong(Monkey::getItemInspectionDivisor)
                .reduce(1, Math::multiplyExact);
    }

    private LongUnaryOperator asOperator(String argument) {
        if (argument.equals("old")) {
            return value -> value;
        }
        final long fixedValue = Long.parseLong(argument);
        return value -> fixedValue;
    }

    private LongBinaryOperator asBinaryOperator(String operatorString) {
        return switch (operatorString) {
            case "*" -> Math::multiplyExact;
            case "+" -> Math::addExact;
            default -> throw new IllegalArgumentException("unknown operator: " + operatorString);
        };
    }


    private long parseItemInspectionDivisor(String line) {
        Pattern testPattern = Pattern.compile("\\s{2}Test: divisible by (\\d+)");
        Matcher matcher = testPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + testPattern + "', but was: " + line);
        }
        return Long.parseLong(matcher.group(1));
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


}
