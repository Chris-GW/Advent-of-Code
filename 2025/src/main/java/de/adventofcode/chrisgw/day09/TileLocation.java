package de.adventofcode.chrisgw.day09;

public record TileLocation(int x, int y) {


    public static TileLocation parse(String line) {
        String[] split = line.split(",");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        return new TileLocation(x, y);
    }


    @Override
    public String toString() {
        return "(%2d;%2d)".formatted(x, y);
    }

}
