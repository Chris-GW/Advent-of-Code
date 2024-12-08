package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;

import static de.adventofcode.chrisgw.day07.AdventOfCodeDay07.Operator.MULTIPLY;
import static de.adventofcode.chrisgw.day07.AdventOfCodeDay07.Operator.PLUS;


/**
 * <a href="https://adventofcode.com/2024/day/7">Advent of Code - day 7</a>
 */
public class AdventOfCodeDay07 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay07(List<String> inputLines) {
        super(Year.of(2024), 7, inputLines);
    }


    @Override
    public Long solveFirstPart() {
        final EnumSet<Operator> allowedOperators = EnumSet.of(PLUS, MULTIPLY);
        return inputLines()
                .map(Equation::parseEquation)
                .filter(equation -> equation.isPossible(allowedOperators))
                .mapToLong(Equation::testValue)
                .sum();
    }


    @Override
    public Long solveSecondPart() {
        final EnumSet<Operator> allowedOperators = EnumSet.allOf(Operator.class);
        return inputLines()
                .map(Equation::parseEquation)
                .filter(equation -> equation.isPossible(allowedOperators))
                .mapToLong(Equation::testValue)
                .sum();
    }


    record Equation(long testValue, long[] numbers) {

        public static Equation parseEquation(String line) {
            String[] splitTestValue = line.split(": ");
            long testValue = Long.parseLong(splitTestValue[0]);
            String[] splitNumbers = splitTestValue[1].split(" ");
            long[] numbers = Arrays.stream(splitNumbers).mapToLong(Long::parseLong).toArray();
            return new Equation(testValue, numbers);
        }


        public boolean isPossible(Set<Operator> allowedOperators) {
            return isPossible(allowedOperators, new Operator[neededOperatorCount()], 0);
        }

        private boolean isPossible(Set<Operator> allowedOperators, Operator[] operators, int i) {
            if (i == neededOperatorCount()) {
                return test(operators);
            }
            for (Operator operator : allowedOperators) {
                operators[i] = operator;
                if (isPossible(allowedOperators, operators, i + 1)) {
                    return true;
                }
            }
            return false;
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
        PLUS("+", (a, b) -> a + b),
        MULTIPLY("*", (a, b) -> a * b),
        CONCATENATION("|", (a, b) -> Long.parseLong(String.valueOf(a) + b));

        final String sign;
        final LongBinaryOperator operator;

        Operator(String sign, LongBinaryOperator operator) {
            this.sign = sign;
            this.operator = operator;

        }


        @Override
        public long applyAsLong(long left, long right) {
            return operator.applyAsLong(left, right);
        }

        @Override
        public String toString() {
            return sign;
        }
    }

}
