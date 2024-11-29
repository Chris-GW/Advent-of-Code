package de.adventofcode.chrisgw.day10;

public enum PipeDirection {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    public final int dx;
    public final int dy;


    PipeDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }


    public PipeDirection oppositeDirection() {
        return switch (this) {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
        };
    }

}
