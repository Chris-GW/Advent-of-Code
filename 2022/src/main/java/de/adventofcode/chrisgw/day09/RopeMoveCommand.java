package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.day08.Direction;

import java.util.Arrays;


public record RopeMoveCommand(Direction direction, int moveLength) {


    public static RopeMoveCommand parseMoveCommand(String moveCommandString) {
        Direction direction = Arrays.stream(Direction.values())
                .filter(direction1 -> direction1.name().charAt(0) == moveCommandString.charAt(0))
                .findAny()
                .orElseThrow();
        int moveLength = Integer.parseInt(moveCommandString.substring(2));
        return new RopeMoveCommand(direction, moveLength);
    }


}
