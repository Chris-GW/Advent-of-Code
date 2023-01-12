package de.adventofcode.chrisgw.day17;

public record RockCoordinate(int x, int y) {


    public RockCoordinate moveRelative(int dx, int dy) {
        int x = this.x() + dx;
        int y = this.y() + dy;
        return new RockCoordinate(x, y);
    }

}
