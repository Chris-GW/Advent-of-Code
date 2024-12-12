package de.adventofcode.chrisgw.day10;

enum MapDirection {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    final int dx;
    final int dy;

    MapDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

}
