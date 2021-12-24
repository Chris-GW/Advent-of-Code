package de.adventofcode.chrisgw.day07;

public record CrabSubmarine(int horizontalPosition) implements Comparable<CrabSubmarine> {


    public int requiredFuelForMovementTo(int horizontalPosition) {
        return Math.abs(this.horizontalPosition - horizontalPosition);
    }

    @Override
    public int compareTo(CrabSubmarine other) {
        return Integer.compare(this.horizontalPosition(), other.horizontalPosition());
    }

}
