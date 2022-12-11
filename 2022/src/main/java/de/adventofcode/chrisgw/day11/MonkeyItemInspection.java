package de.adventofcode.chrisgw.day11;

import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
public class MonkeyItemInspection implements UnaryOperator<BigInteger> {


    private final String firstArgument;
    private final String operator;
    private final String secondArgument;
    private final boolean noDamageWorryLevelRelive;


    public MonkeyItemInspection withoutNoDamageWorryLevelRelive() {
        return new MonkeyItemInspection(firstArgument, operator, secondArgument, false);
    }


    @Override
    public BigInteger apply(BigInteger oldWorryLevel) {
        BigInteger firstArgument = resolveWorryLevelArgument(this.firstArgument, oldWorryLevel);
        BigInteger secondArgument = resolveWorryLevelArgument(this.secondArgument, oldWorryLevel);
        BigInteger newWorryLevel = switch (operator) {
            case "*" -> firstArgument.multiply(secondArgument);
            case "+" -> firstArgument.add(secondArgument);
            default -> throw new IllegalArgumentException("unknown operator: " + operator);
        };
        if (noDamageWorryLevelRelive) {
            BigInteger[] result = newWorryLevel.divideAndRemainder(BigInteger.valueOf(3));
            return result[0];
        } else {
            return newWorryLevel;
        }
    }

    private BigInteger resolveWorryLevelArgument(String argument, BigInteger oldWorryLevel) {
        if ("old".equals(argument)) {
            return oldWorryLevel;
        } else {
            return new BigInteger(argument, 10);
        }
    }
}
