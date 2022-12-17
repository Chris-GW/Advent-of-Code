package de.adventofcode.chrisgw.day14;

import java.util.List;
import java.util.stream.Stream;

public record Coordinate(int x, int y) {

    public static final Coordinate STEP_DOWN = new Coordinate(0, 1);
    public static final Coordinate STEP_DOWN_LEFT = new Coordinate(-1, 1);
    public static final Coordinate STEP_DOWN_RIGHT = new Coordinate(1, 1);


    public List<Coordinate> lineTo(Coordinate otherCoordinate) {
        int dx = otherCoordinate.x() - this.x();
        int dy = otherCoordinate.y() - this.y();
        int lineLength = Math.max(Math.abs(dx), Math.abs(dy)) + 1;
        if (dx != 0 && dy != 0) {
            throw new IllegalArgumentException("Can not draw straight line from " + this + " to " + otherCoordinate);
        }
        Coordinate coordinateDiff = new Coordinate(Integer.signum(dx), Integer.signum(dy));
        return Stream.iterate(this, coordinateDiff::add)
                .limit(lineLength)
                .toList();
    }


    public Coordinate moveRelative(int dx, int dy) {
        int x = this.x() + dx;
        int y = this.y() + dy;
        return new Coordinate(x, y);
    }

    public Coordinate add(Coordinate otherCoordinate) {
        return moveRelative(otherCoordinate.x(), otherCoordinate.y());
    }


    @Override
    public String toString() {
        return "(%d,%d)".formatted(x(), y());
    }

}
