package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.math.BigInteger;
import java.time.Year;
import java.util.*;
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


    public BigInteger solveFirstPart() {
        parseMonkeySituation(true);
        playRounds(20);
        return messureMonkeyLevelBusiness();
    }

    public BigInteger solveSecondPart() {
        parseMonkeySituation(false);
        playRounds(10_000);
        return messureMonkeyLevelBusiness();
    }

    private BigInteger messureMonkeyLevelBusiness() {
        return monkeysByNumber.values()
                .stream()
                .sorted(Comparator.comparing(Monkey::getItemInspectCount).reversed())
                .limit(2)
                .map(Monkey::getItemInspectCount)
                .reduce(BigInteger::multiply)
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
            currentMonkey.setMonkeyItemInspection(parseOperation(line, parseOperation));

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


    private Deque<BigInteger> parseStartItems(String line) {
        Pattern startItemsPattern = Pattern.compile("\\s{2}Starting items: (\\d+(?:, \\d+)*)");
        Matcher matcher = startItemsPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + startItemsPattern + "', but was: " + line);
        }
        return Arrays.stream(matcher.group(1).split(", "))
                .mapToLong(Long::parseLong)
                .mapToObj(BigInteger::valueOf)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }


    private MonkeyItemInspection parseOperation(String line, boolean noDamageWorryLevelRelive) {
        Pattern operationPattern = Pattern.compile("\\s{2}Operation: new = (old|\\d+) ([*+]) (old|\\d+)");
        Matcher matcher = operationPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching '" + operationPattern + "', but was: " + line);
        }
        String firstArgument = matcher.group(1);
        String operator = matcher.group(2);
        String secondArgument = matcher.group(3);
        return new MonkeyItemInspection(firstArgument, operator, secondArgument, noDamageWorryLevelRelive);
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


}
