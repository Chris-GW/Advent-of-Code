package de.adventofcode.chrisgw.day12;

import lombok.Getter;

public enum StepDirection {

    UP(0, -1), //
    DOWN(0, 1), //
    RIGHT(1, 0), //
    LEFT(-1, 0);

    @Getter
    private final int dx;
    @Getter
    private final int dy;


    StepDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

}
