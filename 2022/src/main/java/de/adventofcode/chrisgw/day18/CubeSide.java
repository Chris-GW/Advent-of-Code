package de.adventofcode.chrisgw.day18;

public enum CubeSide {
    UP(0, 1, 0), //
    DOWN(0, -1, 0),  //
    RIGHT(1, 0, 0), //
    LEFT(-1, 0, 0),  //
    FRONT(0, 0, 1), //
    BACK(0, 0, -1);

    public final int dx;
    public final int dy;
    public final int dz;

    CubeSide(int dx, int dy, int dz) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }


}
