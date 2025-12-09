package de.adventofcode.chrisgw.day09;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public record TileRectangle(TileLocation firstCorner, TileLocation secondCorner) {


    public TileRectangle(TileLocation firstCorner, TileLocation secondCorner) {
        if (firstCorner.compareTo(secondCorner) < 0) {
            this.firstCorner = firstCorner;
            this.secondCorner = secondCorner;
        } else {
            this.firstCorner = secondCorner;
            this.secondCorner = firstCorner;
        }
    }


    public long calculateArea() {
        long a = secondCorner.x() - firstCorner.x() + 1L;
        long b = secondCorner.y() - firstCorner.y() + 1L;
        return a * b;
    }


    public boolean overlapsWith(TileRectangle other) {
        return contains(other.firstCorner) || contains(other.secondCorner)
                || contains(new TileLocation(other.firstCorner.x(), other.secondCorner.y()))
                || contains(new TileLocation(other.secondCorner.x(), other.firstCorner.y()));
    }


    public boolean contains(TileLocation location) {
        boolean containsX = firstCorner.x() <= location.x() && location.x() <= secondCorner.x();
        return containsX && firstCorner.y() <= location.y() && location.y() <= secondCorner.y();
    }


    public Stream<TileLocation> tiles() {
        int fromX = Math.min(firstCorner().x(), secondCorner().x());
        int toX = Math.max(firstCorner.x(), secondCorner().x());
        return IntStream.rangeClosed(fromX, toX)
                .mapToObj(x -> {
                    int fromY = Math.min(firstCorner.y(), secondCorner.y());
                    int toY = Math.max(firstCorner.y(), secondCorner.y());
                    return IntStream.rangeClosed(fromY, toY)
                            .mapToObj(y -> new TileLocation(x, y));
                })
                .flatMap(Function.identity());
    }


    public Stream<TileLocation> borderTiles() {
        Set<TileLocation> borderTiles = new HashSet<>();
        for (int y = firstCorner.y(); y <= secondCorner.y(); y++) {
            borderTiles.add(new TileLocation(firstCorner.x(), y));
            borderTiles.add(new TileLocation(secondCorner.x(), y));
        }
        for (int x = firstCorner.x(); x <= secondCorner.x(); x++) {
            borderTiles.add(new TileLocation(x, firstCorner.y()));
            borderTiles.add(new TileLocation(x, secondCorner.y()));
        }
        return borderTiles.stream();
    }


    @Override
    public String toString() {
        return "[%s - %s] = %3d".formatted(firstCorner(), secondCorner(), calculateArea());
    }

}
