package de.adventofcode.chrisgw.day18;

public enum DigDirection {
    UP("U", 0, 1), //
    DOWN("D", 0, -1), //
    LEFT("L", -1, 0), //
    RIGHT("R", 1, 0);

    private final String shortName;
    private final int dx;
    private final int dy;

    DigDirection(String shortName, int dx, int dy) {
        this.shortName = shortName;
        this.dx = dx;
        this.dy = dy;
    }

    public static DigDirection fromShortName(String shortName) {
        for (DigDirection direction : DigDirection.values()) {
            if (direction.shortName().equals(shortName)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("No DigDirection with shortName: " + shortName);
    }


    public String shortName() {
        return shortName;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    @Override
    public String toString() {
        return shortName();
    }

}
