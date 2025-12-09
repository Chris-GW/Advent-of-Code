package de.adventofcode.chrisgw.day09;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public record TileLocation(int x, int y) implements Comparable<TileLocation> {


    public static TileLocation parse(String line) {
        String[] split = line.split(",");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        return new TileLocation(x, y);
    }


    public TileLocation add(TileLocation other) {
        int x = this.x() + other.x();
        int y = this.y() + other.y();
        return new TileLocation(x, y);
    }


    public Stream<TileLocation> tileLineTo(TileLocation otherTileLocation) {
        if (this.x() == otherTileLocation.x()) {
            // same row
            int from = Math.min(this.y(), otherTileLocation.y());
            int to = Math.max(this.y(), otherTileLocation.y());
            return IntStream.range(from, to)
                    .mapToObj(y -> new TileLocation(otherTileLocation.x(), y));

        } else if (this.y() == otherTileLocation.y()) {
            // same column
            int from = Math.min(this.x(), otherTileLocation.x());
            int to = Math.max(this.x(), otherTileLocation.x());
            return IntStream.range(from, to)
                    .mapToObj(x -> new TileLocation(x, otherTileLocation.y()));

        } else {
            throw new IllegalStateException("Expect same row or column");
        }
    }


    @Override
    public int compareTo(TileLocation other) {
        return Comparator.comparingInt(TileLocation::y).thenComparing(TileLocation::x).compare(this, other);
    }

    @Override
    public String toString() {
        return "(%2d;%2d)".formatted(x, y);
    }

}
