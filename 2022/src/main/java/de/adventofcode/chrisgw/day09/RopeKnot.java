package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.day08.Direction;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RopeKnot {

    private RopePoint2D position;
    private RopeKnot tail;
    private final Set<RopePoint2D> tailVisitedPositions = new HashSet<>();


    private RopeKnot() {
        position = new RopePoint2D();
        tailVisitedPositions.add(position);
    }

    public static List<RopeKnot> ropeWithLength(int ropeLength) {
        RopeKnot headRopeKnot = new RopeKnot();
        return Stream.iterate(headRopeKnot, RopeKnot::appendTailRopeKnot)
                .limit(ropeLength)
                .collect(Collectors.toCollection(() -> new ArrayList<>(ropeLength)));
    }

    private RopeKnot appendTailRopeKnot() {
        this.tail = new RopeKnot();
        return this.tail;
    }


    public void followMoveCommand(RopeMoveCommand moveCommand) {
        for (int i = 0; i < moveCommand.moveLength(); i++) {
            Direction moveDirection = moveCommand.direction();
            int dx = moveDirection.getDx();
            int dy = moveDirection.getDy();
            position = position.relativeMove(dx, dy);

            appendedKnots().forEachOrdered(RopeKnot::moveTailCloser);
        }
    }

    public Stream<RopeKnot> appendedKnots() {
        return Stream.iterate(this, Objects::nonNull, RopeKnot::getTailKnot);
    }


    private void moveTailCloser() {
        if (isTailCloseEnough()) {
            return;
        }
        int dx = position.x() - tail.position.x();
        int dy = position.y() - tail.position.y();
        int signumX = Integer.signum(dx);
        int signumY = Integer.signum(dy);
        tail.position = tail.position.relativeMove(signumX, signumY);
        tail.tailVisitedPositions.add(tail.position);
    }

    private boolean isTailCloseEnough() {
        return tail == null || tail.position.isAdjacentTo(position);
    }


    public RopeKnot getTailKnot() {
        return tail;
    }

    public RopePoint2D getPosition() {
        return position;
    }

    public Set<RopePoint2D> visitedPositions() {
        return Collections.unmodifiableSet(tailVisitedPositions);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RopeKnot ropeKnot)) {
            return false;
        }
        return new EqualsBuilder().append(getPosition(), ropeKnot.getPosition())
                .append(getTailKnot(), ropeKnot.getTailKnot())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPosition()).append(getTailKnot()).toHashCode();
    }

}
