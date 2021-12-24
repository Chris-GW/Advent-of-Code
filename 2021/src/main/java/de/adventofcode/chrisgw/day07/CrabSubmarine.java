package de.adventofcode.chrisgw.day07;

public record CrabSubmarine(int horizontalPosition) implements Comparable<CrabSubmarine> {


    public int requiredFuelForMovementTo(int horizontalPosition) {
        return Math.abs(this.horizontalPosition - horizontalPosition);
    }

    public int requiredFuelForCrabMovementTo(int horizontalPosition) {
        int difference = requiredFuelForMovementTo(horizontalPosition);
        return (difference * (difference + 1)) / 2;
    }

    @Override
    public int compareTo(CrabSubmarine other) {
        return Integer.compare(this.horizontalPosition(), other.horizontalPosition());
    }

}
