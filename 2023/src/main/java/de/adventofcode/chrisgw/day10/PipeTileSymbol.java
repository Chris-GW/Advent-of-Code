package de.adventofcode.chrisgw.day10;

import java.util.Arrays;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day10.PipeDirection.NORTH;
import static de.adventofcode.chrisgw.day10.PipeDirection.SOUTH;


public enum PipeTile {
    VERTICAL('|', NORTH, SOUTH),
    HORIZONTAL('-', NORTH, SOUTH),
    NORTH_EAST('L', NORTH, SOUTH),
    NORTH_WEST('J', NORTH, SOUTH),
    SOUTH_WEST('7', NORTH, SOUTH),
    SOUTH_EAST('F', NORTH, SOUTH),
    GROUND('.');

    private char tileSymbol;
    private PipeDirection[] connectedDirections;

    PipeTile(char tileSymbol, PipeDirection... connectedDirections) {
        this.tileSymbol = tileSymbol;
        this.connectedDirections = connectedDirections;
    }

    public char getTileSymbol() {
        return tileSymbol;
    }

    public boolean isConnectedTo(PipeDirection direction) {
        return connections().anyMatch(direction::equals);
    }

    public Stream<PipeDirection> connections() {
        return Arrays.stream(connectedDirections);
    }


    @Override
    public String toString() {
        return String.valueOf(getTileSymbol());
    }

}
