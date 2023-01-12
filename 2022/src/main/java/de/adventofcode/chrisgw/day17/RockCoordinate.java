package de.adventofcode.chrisgw.day17;

public record RockCoordinate(long x, long y) {


    public RockCoordinate moveRelative(long dx, long dy) {
        long x = this.x() + dx;
        long y = this.y() + dy;
        return new RockCoordinate(x, y);
    }


    @Override
    public String toString() {
        return "(%d;%d)".formatted(x(), y());
    }

}
