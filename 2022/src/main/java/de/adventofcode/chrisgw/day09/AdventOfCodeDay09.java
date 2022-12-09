package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2022/day/9">Advent of Code - day 9</a>
 */
public class AdventOfCodeDay09 extends AdventOfCodePuzzleSolver<Integer> {


    public AdventOfCodeDay09(List<String> inputLines) {
        super(Year.of(2022), 9, inputLines);
    }


    public Integer solveFirstPart() {
        return simulateRope(2);
    }

    public Integer solveSecondPart() {
        return simulateRope(10);
    }

    private int simulateRope(int ropeLength) {
        List<RopeKnot> ropeKnots = RopeKnot.ropeWithLength(ropeLength);
        RopeKnot headRopeKnot = ropeKnots.get(0);
        parseMovesFromInput().forEachOrdered(headRopeKnot::followMoveCommand);
        RopeKnot tailRopeKnot = ropeKnots.get(ropeKnots.size() - 1);
        Set<RopePoint2D> visitedPositions = tailRopeKnot.visitedPositions();
        return visitedPositions.size();
    }

    private Stream<RopeMoveCommand> parseMovesFromInput() {
        return inputLines().map(RopeMoveCommand::parseMoveCommand);
    }

}
