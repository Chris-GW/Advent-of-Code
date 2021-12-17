package de.adventofcode.chrisgw.day05;

public record Position2D(int x, int y) {

    public Position2D moveHorizontal(int units) {
        return new Position2D(x + units, y);
    }

    public Position2D moveVertical(int units) {
        return new Position2D(x, y + units);
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

}
