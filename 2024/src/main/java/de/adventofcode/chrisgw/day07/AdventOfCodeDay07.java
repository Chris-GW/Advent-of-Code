package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;


/**
 * <a href="https://adventofcode.com/2024/day/7">Advent of Code - day 7</a>
 */
public class AdventOfCodeDay07 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay07(List<String> inputLines) {
        super(Year.of(2024), 7, inputLines);
    }


    @Override
    public Long solveFirstPart() {
        return inputLines()
                .map(Equation::parseEquation)
                .filter(Equation::isPossible)
                .mapToLong(Equation::testValue)
                .sum();
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    record Equation(long testValue, long[] numbers) {

        public static Equation parseEquation(String line) {
            String[] splitTestValue = line.split(": ");
            long testValue = Long.parseLong(splitTestValue[0]);
            String[] splitNumbers = splitTestValue[1].split(" ");
            long[] numbers = Arrays.stream(splitNumbers).mapToLong(Long::parseLong).toArray();
            return new Equation(testValue, numbers);
        }


        public boolean isPossible() {
            List<Operator[]> operatorCombinations = buildOperators(new Operator[neededOperatorCount()], 0);
            return operatorCombinations.stream().anyMatch(this::test);
        }

        private List<Operator[]> buildOperators(Operator[] operators, int i) {
            if (i == neededOperatorCount()) {
                return Collections.singletonList(Arrays.copyOf(operators, operators.length));
            }
            List<Operator[]> foundOperators = new ArrayList<>();
            for (Operator operator : Operator.values()) {
                operators[i] = operator;
                foundOperators.addAll(buildOperators(operators, i + 1));
                operators[i] = operator;
            }
            return foundOperators;
        }


        public boolean test(Operator[] operators) {
            long value = numbers[0];
            for (int i = 0; i < operators.length; i++) {
                value = operators[i].applyAsLong(value, numbers[i + 1]);
            }
            return value == testValue;
        }


        public int neededOperatorCount() {
            return numbers.length - 1;
        }

        @Override
        public String toString() {
            String numberString = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            return testValue + ": " + numberString;
        }

    }


    enum Operator implements LongBinaryOperator {
        PLUS('+', (a, b) -> a + b),
        MULT('*', (a, b) -> a * b);

        final char sign;
        final LongBinaryOperator operator;

        Operator(char sign, LongBinaryOperator operator) {
            this.sign = sign;
            this.operator = operator;

        }


        @Override
        public long applyAsLong(long left, long right) {
            return operator.applyAsLong(left, right);
        }

        @Override
        public String toString() {
            return String.valueOf(sign);
        }
    }

}
