package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2015/day/3">Advent of Code - day 3</a>
 */
public class AdventOfCodeDay03 extends AdventOfCodePuzzleSolver {


    public AdventOfCodeDay03(List<String> inputLines) {
        super(Year.of(2015), 3, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        var santa = new DirectedSanta();
        String inputLine = getInputLines().getFirst();
        for (int i = 0; i < inputLine.length(); i++) {
            char directionSign = inputLine.charAt(i);
            var direction = Direction.fromSign(directionSign);
            santa.walkDirection(direction);
        }
        return santa.countVisitedHouses();
    }


    @Override
    public Integer solveSecondPart() {
        var santa = new DirectedSanta();
        var robotSanta = new DirectedSanta();
        boolean robotSantaTurn = false;

        String inputLine = getInputLines().getFirst();
        for (int i = 0; i < inputLine.length(); i++) {
            char directionSign = inputLine.charAt(i);
            var direction = Direction.fromSign(directionSign);
            if (robotSantaTurn) {
                robotSanta.walkDirection(direction);
            } else {
                santa.walkDirection(direction);
            }
            robotSantaTurn = !robotSantaTurn;
        }
        return santa.countVisitedHouses(robotSanta);
    }

}
