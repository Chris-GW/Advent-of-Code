package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
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
        // TODO solveSecondPart
        return 1;
    }

}
