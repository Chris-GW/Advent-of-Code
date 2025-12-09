package de.adventofcode.chrisgw.day09;

public record TileRectangle(TileLocation firstCorner, TileLocation secondCorner) {


    public long calculateArea() {
        long a = Math.abs(firstCorner().x() - secondCorner().x()) + 1L;
        long b = Math.abs(firstCorner().y() - secondCorner().y()) + 1L;
        return a * b;
    }

    @Override
    public String toString() {
        return "[%s - %s] = %3d".formatted(firstCorner(), secondCorner(), calculateArea());
    }

}
