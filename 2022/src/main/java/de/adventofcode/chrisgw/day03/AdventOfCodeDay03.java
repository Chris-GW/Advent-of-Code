package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * <a href="https://adventofcode.com/2022/day/3">Advent of Code - day 3</a>
 */
public class AdventOfCodeDay03 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay03(List<String> inputLines) {
        super(Year.of(2022), 3, inputLines);
    }


    public Integer solveFirstPart() {
        return inputLines().map(RucksackWithTwoCompartments::parseRucksackContent)
                .map(RucksackWithTwoCompartments::findMisplacedItem)
                .mapToInt(RucksackItem::priority)
                .sum();
    }

    public Integer solveSecondPart() {
        final int groupSize = 3;
        final AtomicInteger groupItemCounter = new AtomicInteger();
        return inputLines().map(RucksackWithTwoCompartments::parseRucksackContent)
                .collect(Collectors.groupingBy(rucksack -> groupItemCounter.getAndIncrement() / groupSize))
                .values()
                .stream()
                .map(this::findSharedItem)
                .mapToInt(RucksackItem::priority)
                .sum();
    }

    private RucksackItem findSharedItem(List<RucksackWithTwoCompartments> rucksacks) {
        var largestRucksackInGroup = rucksacks.stream()
                .max(Comparator.comparingInt(RucksackWithTwoCompartments::size))
                .orElseThrow();
        return largestRucksackInGroup.items()
                .stream()
                .distinct()
                .filter(item -> rucksacks.stream().allMatch(rucksack -> rucksack.containsItem(item)))
                .findAny()
                .orElse(null);
    }

}
