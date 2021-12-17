package de.adventofcode.chrisgw.day05;

public record Position2D(int x, int y) {

    public Position2D move(int dx, int dy) {
        return new Position2D(x + dx, y + dy);
    }


    @Override
    public String toString() {
        return x + "," + y;
    }

}
