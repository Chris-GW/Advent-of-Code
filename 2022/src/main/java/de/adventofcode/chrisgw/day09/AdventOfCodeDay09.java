package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import de.adventofcode.chrisgw.day08.Direction;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2022/day/9">Advent of Code - day 9</a>
 */
public class AdventOfCodeDay09 extends AdventOfCodePuzzleSolver<Integer> {


    private List<RopePoint2D> ropeKnots;

    private final Set<RopePoint2D> visitedTailPositions = new HashSet<>();


    public AdventOfCodeDay09(List<String> inputLines) {
        super(Year.of(2022), 9, inputLines);
        visitedTailPositions.add(new RopePoint2D());
    }

    public RopePoint2D headPosition() {
        return ropeKnotAt(0);
    }

    public RopePoint2D tailPosition() {
        return ropeKnotAt(ropeKnots.size() - 1);
    }

    private RopePoint2D ropeKnotAt(int ropeKnotIndex) {
        return ropeKnots.get(ropeKnotIndex);
    }


    public Integer solveFirstPart() {
        ropeKnots = newRopeWithLength(2);
        parseMovesFromInput().forEachOrdered(this::runHeadRopeMoveCommand);
        return visitedTailPositions.size();
    }

    private List<RopePoint2D> newRopeWithLength(int ropeLength) {
        return Stream.generate(RopePoint2D::new)
                .limit(ropeLength)
                .collect(Collectors.toCollection(() -> new ArrayList<>(ropeLength)));
    }

    public Integer solveSecondPart() {
        ropeKnots = newRopeWithLength(10);
        parseMovesFromInput().forEachOrdered(this::runHeadRopeMoveCommand);
        return visitedTailPositions.size();
    }


    private Stream<RopeMoveCommand> parseMovesFromInput() {
        return inputLines().map(RopeMoveCommand::parseMoveCommand);
    }


    private void runHeadRopeMoveCommand(RopeMoveCommand moveCommand) {
        for (int i = 0; i < moveCommand.moveLength(); i++) {
            moveHeadRopeAccordingTo(moveCommand);
            for (int tailIndex = 1; tailIndex < ropeKnots.size(); tailIndex++) {
                if (isTailCovered(tailIndex)) {
                    continue;
                }
                moveTailCloser(tailIndex);
            }
            visitedTailPositions.add(tailPosition());
        }
    }

    private void moveHeadRopeAccordingTo(RopeMoveCommand moveCommand) {
        Direction moveDirection = moveCommand.direction();
        int dx = moveDirection.getDx();
        int dy = moveDirection.getDy();
        RopePoint2D newHeadPosition = headPosition().relativeMove(dx, dy);
        ropeKnots.set(0, newHeadPosition);
    }

    private void moveTailCloser(int tailIndex) {
        RopePoint2D headRopeKnot = ropeKnotAt(tailIndex - 1);
        RopePoint2D tailRopeKnot = ropeKnotAt(tailIndex);
        int dx = headRopeKnot.x() - tailRopeKnot.x();
        int dy = headRopeKnot.y() - tailRopeKnot.y();
        int signumX = Integer.signum(dx);
        int signumY = Integer.signum(dy);
        RopePoint2D newTailPosition = tailRopeKnot.relativeMove(signumX, signumY);
        ropeKnots.set(tailIndex, newTailPosition);
    }

    private boolean isTailCovered(int tailIndex) {
        return ropeKnotAt(tailIndex).isAdjacentTo(ropeKnotAt(tailIndex - 1));
    }


}
