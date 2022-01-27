package de.adventofcode.chrisgw.day14;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public record PolymerTemplate(String polymerTemplate) {


    public char elementAt(int index) {
        return polymerTemplate.charAt(index);
    }

    public Pair<Character, Character> elementPairAt(int index) {
        return ImmutablePair.of(elementAt(index), elementAt(index + 1));
    }


    public int length() {
        return polymerTemplate.length();
    }


    public int calculateScore() {
        Map<Character, Integer> quantities = new HashMap<>();
        for (int i = 0; i < length(); i++) {
            char element = polymerTemplate.charAt(i);
            quantities.merge(element, 1, Integer::sum);
        }

        Entry<Character, Integer> mostCommonElement = quantities.entrySet()
                .stream()
                .max(Comparator.comparingInt(Entry::getValue))
                .orElseThrow();
        Entry<Character, Integer> leastCommonElement = quantities.entrySet()
                .stream()
                .min(Comparator.comparingInt(Entry::getValue))
                .orElseThrow();
        return mostCommonElement.getValue() - leastCommonElement.getValue();
    }

    @Override
    public String toString() {
        return polymerTemplate();
    }

}
