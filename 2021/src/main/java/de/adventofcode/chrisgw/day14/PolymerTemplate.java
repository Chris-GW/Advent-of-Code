package de.adventofcode.chrisgw.day14;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.Map.Entry;


public class PolymerTemplate {

    private final int step;
    private final Map<Pair<Character, Character>, Long> pairQuantities = new TreeMap<>();


    public PolymerTemplate(String polymerTemplate) {
        this.step = 0;
        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            char left = polymerTemplate.charAt(i);
            char right = polymerTemplate.charAt(i + 1);
            var pair = Pair.of(left, right);
            addPairQuantity(pair, 1L);
        }
    }

    private PolymerTemplate(PolymerTemplate otherPolymerTemplate) {
        this.step = otherPolymerTemplate.step + 1;
        this.pairQuantities.putAll(otherPolymerTemplate.pairQuantities);
    }


    private void addPairQuantity(Pair<Character, Character> pair, long quantity) {
        long newQuantity = pairQuantities.merge(pair, quantity, Long::sum);
        if (newQuantity == 0L) {
            pairQuantities.remove(pair);
        }
    }


    public PolymerTemplate applyPairInsertionRules(Collection<PairInsertionRule> pairInsertionRules) {
        PolymerTemplate newPolymer = new PolymerTemplate(this);

        for (Entry<Pair<Character, Character>, Long> pairQuantityEntry : pairQuantities.entrySet()) {
            var pair = pairQuantityEntry.getKey();
            long quantity = pairQuantityEntry.getValue();

            for (PairInsertionRule pairInsertionRule : pairInsertionRules) {
                if (pairInsertionRule.matchesPair(pair)) {
                    newPolymer.addPairQuantity(pair, -quantity);
                    var newBuildPairs = pairInsertionRule.newBuildPairs();
                    newBuildPairs.forEach(newPair -> newPolymer.addPairQuantity(newPair, quantity));
                    break;
                }
            }
        }
        return newPolymer;
    }


    public long calculateScore() {
        Map<Character, Long> quantities = elementQuantities();
        Entry<Character, Long> mostCommonElement = quantities.entrySet()
                .stream()
                .max(Comparator.comparingLong(Entry::getValue))
                .orElseThrow();
        Entry<Character, Long> leastCommonElement = quantities.entrySet()
                .stream()
                .min(Comparator.comparingLong(Entry::getValue))
                .orElseThrow();
        return mostCommonElement.getValue() - leastCommonElement.getValue();
    }

    private Map<Character, Long> elementQuantities() {
        Map<Character, Long> quantities = new HashMap<>();
        for (Entry<Pair<Character, Character>, Long> pairQuantityEntry : pairQuantities.entrySet()) {
            char leftElement = pairQuantityEntry.getKey().getLeft();
            char rightElement = pairQuantityEntry.getKey().getRight();
            long quantity = pairQuantityEntry.getValue();
            quantities.merge(leftElement, quantity, Long::sum);
            quantities.merge(rightElement, quantity, Long::sum);
        }
        quantities.replaceAll((element, quantity) -> ceilDiv(quantity, 2));
        return quantities;
    }


    public static long ceilDiv(long dividend, long divisor) {
        return -Math.floorDiv(-dividend, divisor);
    }


    @Override
    public String toString() {
        return pairQuantities.toString();
    }

}
