package de.adventofcode.chrisgw.day10;

import java.util.Arrays;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day10.PipeDirection.EAST;
import static de.adventofcode.chrisgw.day10.PipeDirection.NORTH;
import static de.adventofcode.chrisgw.day10.PipeDirection.SOUTH;
import static de.adventofcode.chrisgw.day10.PipeDirection.WEST;


public enum PipeTileSymbol {
    VERTICAL('|', NORTH, SOUTH),
    HORIZONTAL('-', EAST, WEST),
    NORTH_EAST('L', NORTH, EAST),
    NORTH_WEST('J', NORTH, WEST),
    SOUTH_WEST('7', SOUTH, WEST),
    SOUTH_EAST('F', SOUTH, EAST),
    GROUND('.'),
    START('S', NORTH, SOUTH, EAST, WEST);

    private char tileSymbol;
    private PipeDirection[] connectedDirections;

    PipeTileSymbol(char tileSymbol, PipeDirection... connectedDirections) {
        this.tileSymbol = tileSymbol;
        this.connectedDirections = connectedDirections;
    }

    public static PipeTileSymbol fromSymbol(char tileSymbol) {
        for (PipeTileSymbol pipeTileSymbol : PipeTileSymbol.values()) {
            if (pipeTileSymbol.tileSymbol == tileSymbol) {
                return pipeTileSymbol;
            }
        }
        throw new IllegalArgumentException("could not find PipeTileSymbol for " + tileSymbol);
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
