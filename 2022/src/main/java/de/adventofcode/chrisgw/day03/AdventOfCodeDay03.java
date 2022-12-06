package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Comparator;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2022/day/3">Advent of Code - day 3</a>
 */
public class AdventOfCodeDay03 extends AdventOfCodePuzzleSolver<Integer> {

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
        var rucksackWithTwoCompartments = inputLines().map(RucksackWithTwoCompartments::parseRucksackContent).toList();
        int groupSize = 3;

        int prioritySum = 0;
        for (int group = 0; group < rucksackWithTwoCompartments.size() / groupSize; group++) {
            int groupStartIndex = group * groupSize;
            var groupRucksacks = rucksackWithTwoCompartments.subList(groupStartIndex, groupStartIndex + groupSize);

            var largestRucksackInGroup = groupRucksacks.stream()
                    .max(Comparator.comparingInt(RucksackWithTwoCompartments::size))
                    .orElseThrow();

            int priorityForGroup = largestRucksackInGroup.items()
                    .stream()
                    .filter(item -> groupRucksacks.stream().allMatch(rucksack -> rucksack.containsItem(item)))
                    .findAny()
                    .map(RucksackItem::priority)
                    .orElse(0);
            prioritySum += priorityForGroup;
        }
        return prioritySum;
    }

}
