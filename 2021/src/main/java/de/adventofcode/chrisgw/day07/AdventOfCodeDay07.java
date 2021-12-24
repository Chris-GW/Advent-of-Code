package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://adventofcode.com/2021/day/7
 */
public class AdventOfCodeDay07 extends AdventOfCodePuzzleSolver<Integer> {

    private final List<CrabSubmarine> crabSubmarines;


    public AdventOfCodeDay07(List<String> inputLines) {
        super(Year.of(2021), 7, inputLines);
        this.crabSubmarines = parseCrabSubmarineFromInput();
    }

    private List<CrabSubmarine> parseCrabSubmarineFromInput() {
        return inputLines().map(line -> line.split(","))
                .flatMap(Arrays::stream)
                .mapToInt(Integer::parseInt)
                .mapToObj(CrabSubmarine::new)
                .toList();
    }


    public Integer solveFirstPart() {
        return findOptimalHorizontalAlignmentPosition();
    }

    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }

    public int findOptimalHorizontalAlignmentPosition() {
        int start = crabSubmarines.stream().mapToInt(CrabSubmarine::horizontalPosition).min().orElseThrow();
        int end = crabSubmarines.stream().mapToInt(CrabSubmarine::horizontalPosition).max().orElseThrow();
        return IntStream.rangeClosed(start, end)
                .map(this::requiredFuelForAlignmentAt)
                .min()
                .orElseThrow();
    }

    public int requiredFuelForAlignmentAt(int horizontalPosition) {
        return crabSubmarines.stream()
                .mapToInt(crabSubmarine -> crabSubmarine.requiredFuelForMovementTo(horizontalPosition))
                .sum();
    }

}
