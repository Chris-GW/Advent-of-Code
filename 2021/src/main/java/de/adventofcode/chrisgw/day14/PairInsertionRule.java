package de.adventofcode.chrisgw.day14;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public record PairInsertionRule(Pair<Character, Character> elementPair, Character insertedElement) {

    public static final Pattern PAIR_INSERTION_RULE_PATTERN = Pattern.compile("([A-Z]{2}) -> ([A-Z])");


    public static PairInsertionRule parseRule(String line) {
        Matcher matcher = PAIR_INSERTION_RULE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "expect line matching '%s', but was: %s".formatted(PAIR_INSERTION_RULE_PATTERN, line));
        }
        String pairStr = matcher.group(1);
        char insertedElement = matcher.group(2).charAt(0);
        var pair = Pair.of(pairStr.charAt(0), pairStr.charAt(1));
        return new PairInsertionRule(pair, insertedElement);
    }


    public boolean matchesPair(Pair<Character, Character> pair) {
        return elementPair().equals(pair);
    }

    public List<Pair<Character, Character>> newBuildPairs() {
        var leftPair = Pair.of(getLeft(), insertedElement());
        var rightPair = Pair.of(insertedElement(), getRight());
        return List.of(leftPair, rightPair);
    }


    public Character getRight() {
        return elementPair().getRight();
    }

    public Character getLeft() {
        return elementPair().getLeft();
    }


    @Override
    public String toString() {
        return "%s%s -> %s".formatted(getLeft(), getRight(), insertedElement());
    }

}
