package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import de.adventofcode.chrisgw.day08.Direction;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2022/day/9">Advent of Code - day 9</a>
 */
public class AdventOfCodeDay09 extends AdventOfCodePuzzleSolver<Integer> {


    private RopePoint2D headPosition = new RopePoint2D(0, 0);
    private RopePoint2D tailPosition = new RopePoint2D(0, 0);

    private final Set<RopePoint2D> visitedTailPositions = new HashSet<>();


    public AdventOfCodeDay09(List<String> inputLines) {
        super(Year.of(2022), 9, inputLines);
        visitedTailPositions.add(tailPosition);
    }


    public Integer solveFirstPart() {
        parseMovesFromInput().forEachOrdered(this::runHeadRopeMoveCommand);
        return visitedTailPositions.size();
    }

    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    private Stream<RopeMoveCommand> parseMovesFromInput() {
        return inputLines().map(RopeMoveCommand::parseMoveCommand);
    }


    private void runHeadRopeMoveCommand(RopeMoveCommand moveCommand) {
        for (int i = 0; i < moveCommand.moveLength(); i++) {
            headPosition = moveHeadRopeAccordingTo(moveCommand);
            if (isCoveredByTail()) {
                continue;
            }
            tailPosition = moveTailCloserToHeadRope();
            visitedTailPositions.add(tailPosition);
        }
    }

    private RopePoint2D moveHeadRopeAccordingTo(RopeMoveCommand moveCommand) {
        Direction moveDirection = moveCommand.direction();
        int dx = moveDirection.getDx();
        int dy = moveDirection.getDy();
        return headPosition.relativeMove(dx, dy);
    }

    private RopePoint2D moveTailCloserToHeadRope() {
        int dx = headPosition.x() - tailPosition.x();
        int dy = headPosition.y() - tailPosition.y();
        int signumX = Integer.signum(dx);
        int signumY = Integer.signum(dy);
        return tailPosition.relativeMove(signumX, signumY);
    }

    private boolean isCoveredByTail() {
        return tailPosition.isAdjacentTo(headPosition);
    }


}
