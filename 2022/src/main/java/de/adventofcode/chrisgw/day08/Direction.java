package de.adventofcode.chrisgw.day08;

import lombok.Getter;


public enum Direction {

    UP(0, -1), //
    DOWN(0, 1), //
    RIGHT(1, 0), //
    LEFT(-1, 0);

    @Getter
    private final int dx;
    @Getter
    private final int dy;


    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }


}
