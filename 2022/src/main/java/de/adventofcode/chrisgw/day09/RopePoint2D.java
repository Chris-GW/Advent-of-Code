package de.adventofcode.chrisgw.day09;

public record RopePoint2D(int x, int y) {


    public RopePoint2D relativeMove(int dx, int dy) {
        int x = this.x() + dx;
        int y = this.y() + dy;
        return new RopePoint2D(x, y);
    }


    public boolean isAdjacentTo(RopePoint2D otherPoint) {
        int dx = this.x() - otherPoint.x();
        int dy = this.y() - otherPoint.y();
        return Math.abs(dx) <= 1 && Math.abs(dy) <= 1;
    }


}
