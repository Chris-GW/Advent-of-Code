package de.adventofcode.chrisgw.day12;

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

}
