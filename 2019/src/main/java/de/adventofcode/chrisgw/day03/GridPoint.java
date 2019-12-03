package de.adventofcode.chrisgw.day03;

import lombok.Data;


@Data
public class GridPoint {


    private final int x;
    private final int y;


    public int distanceTo(GridPoint gridPoint) {
        return Math.abs(this.x - gridPoint.x) + Math.abs(this.y - gridPoint.y);
    }

    public GridPoint moveTo(Direction direction) {
        int x = this.x + direction.getDx();
        int y = this.y + direction.getDy();
        return new GridPoint(x, y);
    }


    public boolean isAt(GridPoint gridPoint) {
        return isAt(gridPoint.getX(), gridPoint.getY());
    }

    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }

}
