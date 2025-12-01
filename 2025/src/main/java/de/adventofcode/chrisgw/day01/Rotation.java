package de.adventofcode.chrisgw.day01;

public record Rotation(Direction direction, int distance) {

    public static Rotation parse(String line) {
        Direction direction = Direction.fromCode(line.charAt(0));
        int distance = Integer.parseInt(line.substring(1));
        return new Rotation(direction, distance);
    }


    @Override
    public String toString() {
        return "%s%d".formatted(direction.code(), distance);
    }

}
