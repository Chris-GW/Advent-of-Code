package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2015/day/1">Advent of Code - day 1</a>
 */
public class AdventOfCodeDay01 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay01(List<String> inputLines) {
        super(Year.of(2015), 1, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        int floor = 0;
        String inputLine = getInputLines().get(0);
        for (int i = 0; i < inputLine.length(); i++) {
            char c = inputLine.charAt(i);
            if (c == '(') {
                floor++;
            } else if (c == ')') {
                floor--;
            }
        }
        return floor;
    }


    @Override
    public Integer solveSecondPart() {
        int floor = 0;
        String inputLine = getInputLines().get(0);
        for (int i = 0; i < inputLine.length(); i++) {
            char c = inputLine.charAt(i);
            if (c == '(') {
                floor++;
            } else if (c == ')') {
                floor--;
            }
            if (floor < 0) {
                return i + 1;
            }
        }
        return 0;
    }

}
