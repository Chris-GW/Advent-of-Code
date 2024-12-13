package de.adventofcode.chrisgw.day12;

import java.util.stream.Stream;


enum GardenDirection {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    final int dx;
    final int dy;

    GardenDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Stream<GardenDirection> verticalDirections() {
        return Stream.of(UP, DOWN);
    }

    public static Stream<GardenDirection> horizontalDirections() {
        return Stream.of(LEFT, RIGHT);
    }


    public GardenDirection turnRight() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }

}
