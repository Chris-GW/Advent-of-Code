package de.adventofcode.chrisgw.day07;

import java.util.stream.Stream;


public record Location(int x, int y) {

    public Location down() {
        return new Location(x, y + 1);
    }

    public Stream<Location> split() {
        Location left = new Location(x - 1, y);
        Location right = new Location(x + 1, y);
        return Stream.of(left, right);
    }

    @Override
    public String toString() {
        return "(%2d;%2d)".formatted(x(), y());
    }
}
