package de.adventofcode.chrisgw.day09;

import java.util.ArrayList;
import java.util.List;


public record RopePoint2D(int x, int y) {

    public RopePoint2D relativeMove(int dx, int dy) {
        int x = this.x() + dx;
        int y = this.y() + dy;
        return new RopePoint2D(x, y);
    }


    public boolean isAdjacentTo(RopePoint2D otherPoint) {
        return this.adjacentPoints().stream().anyMatch(otherPoint::equals);
    }

    public List<RopePoint2D> adjacentPoints() {
        List<RopePoint2D> adjacentPoints = new ArrayList<>(3 * 3);
        for (int dy = -1; dy <= 1; dy++) {
            int y = this.y + dy;
            for (int dx = -1; dx <= 1; dx++) {
                int x = this.x + dx;
                RopePoint2D adjacentPoint = new RopePoint2D(x, y);
                adjacentPoints.add(adjacentPoint);
            }
        }
        return adjacentPoints;
    }


}
