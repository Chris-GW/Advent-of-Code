package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2024/day/1">Advent of Code - day 1</a>
 */
public class AdventOfCodeDay01 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay01(List<String> inputLines) {
        super(Year.of(2024), 1, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        int listSize = getInputLines().size();
        int[] leftIds = new int[listSize];
        int[] rightIds = new int[listSize];

        for (int i = 0; i < getInputLines().size(); i++) {
            String inputLine = getInputLines().get(i);
            String[] split = inputLine.split("\\s+");
            leftIds[i] = Integer.parseInt(split[0]);
            rightIds[i] = Integer.parseInt(split[1]);
        }
        Arrays.sort(leftIds);
        Arrays.sort(rightIds);

        int totalDistance = 0;
        for (int i = 0; i < leftIds.length; i++) {
            int distance = Math.abs(leftIds[i] - rightIds[i]);
            totalDistance += distance;
        }
        return totalDistance;
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
