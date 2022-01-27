package de.adventofcode.chrisgw.day14;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public record PairInsertionRule(Pair<Character, Character> elementPair, char insertedElement) {

    public static final Pattern PAIR_INSERTION_RULE_PATTERN = Pattern.compile("([A-Z]{2}) -> ([A-Z])");


    public static PairInsertionRule parseRule(String line) {
        Matcher matcher = PAIR_INSERTION_RULE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "expect line matching '%s', but was: %s".formatted(PAIR_INSERTION_RULE_PATTERN, line));
        }
        String pairStr = matcher.group(1);
        char insertedElement = matcher.group(2).charAt(0);
        var pair = ImmutablePair.of(pairStr.charAt(0), pairStr.charAt(1));
        return new PairInsertionRule(pair, insertedElement);
    }


    public boolean matchesAt(PolymerTemplate polymerTemplate, int index) {
        Pair<Character, Character> pair = polymerTemplate.elementPairAt(index);
        return elementPair().equals(pair);
    }


    @Override
    public String toString() {
        return elementPair().getLeft() + "" + elementPair().getRight() + " -> " + insertedElement();
    }

}
