package de.adventofcode.chrisgw.day15;

public record Coordinate(int x, int y) {


    public int distance(Coordinate otherCoordinate) {
        int dx = Math.abs(this.x() - otherCoordinate.x());
        int dy = Math.abs(this.y() - otherCoordinate.y());
        return dx + dy;
    }

    public Coordinate moveRelative(int dx, int dy) {
        int x = this.x() + dx;
        int y = this.y() + dy;
        return new Coordinate(x, y);
    }

}
