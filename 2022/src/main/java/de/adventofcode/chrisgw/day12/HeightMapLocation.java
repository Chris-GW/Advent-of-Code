package de.adventofcode.chrisgw.day12;

public record HeightMapLocation(int x, int y, char elevationCode) {


    public int elevation() {
        if (isStartLocation()) {
            return 0;
        } else if (isBestSignalLocation()) {
            return 'z' - 'a';
        }
        return elevationCode - 'a';
    }


    public boolean isBestSignalLocation() {
        return elevationCode == 'E';
    }

    public boolean isStartLocation() {
        return elevationCode == 'S';
    }

    public boolean isAtLowestLevel() {
        return elevation() == 0;
    }

    public boolean canClimbTo(HeightMapLocation otherLocation) {
        if (otherLocation == null) {
            return false;
        }
        return this.elevation() + 1 >= otherLocation.elevation();
    }


    public int distanceTo(HeightMapLocation otherLocation) {
        int dx = Math.abs(this.x() - otherLocation.x());
        int dy = Math.abs(this.y() - otherLocation.y());
        int dz = Math.abs(this.elevation() - otherLocation.elevation());
        return dx + dy + dz;
    }

}
