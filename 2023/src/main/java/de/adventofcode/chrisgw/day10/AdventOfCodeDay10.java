package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day10.PipeTileSymbol.GROUND;
import static de.adventofcode.chrisgw.day10.PipeTileSymbol.NORTH_EAST;
import static de.adventofcode.chrisgw.day10.PipeTileSymbol.NORTH_WEST;
import static de.adventofcode.chrisgw.day10.PipeTileSymbol.SOUTH_EAST;
import static de.adventofcode.chrisgw.day10.PipeTileSymbol.SOUTH_WEST;
import static de.adventofcode.chrisgw.day10.PipeTileSymbol.VERTICAL;


/**
 * <a href="https://adventofcode.com/2023/day/10">Advent of Code - day 10</a>
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzleSolver {


    private PipeTile[][] map;
    private PipeTile startTile;


    public AdventOfCodeDay10(List<String> inputLines) {
        super(Year.of(2023), 10, inputLines);
        parseMap(inputLines);
    }

    private void parseMap(List<String> inputLines) {
        map = new PipeTile[inputLines.size()][];
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);
            map[y] = new PipeTile[line.length()];

            for (int x = 0; x < line.length(); x++) {
                PipeTileSymbol pipeTileSymbol = PipeTileSymbol.fromSymbol(line.charAt(x));
                setTile(x, y, pipeTileSymbol);
            }
        }
    }


    private void setTile(int x, int y, PipeTileSymbol pipeTileSymbol) {
        map[y][x] = new PipeTile(x, y, pipeTileSymbol);
        if (PipeTileSymbol.START.equals(pipeTileSymbol)) {
            startTile = map[y][x];
        }
    }

    public PipeTile tileAt(int x, int y) {
        if (0 > y || y >= height() || 0 > x || x >= width()) {
            return new PipeTile(x, y, PipeTileSymbol.GROUND);
        }
        return map[y][x];
    }


    public int height() {
        return map.length;
    }

    public int width() {
        if (height() == 0) {
            return 0;
        }
        return map[0].length;
    }


    @Override
    public Integer solveFirstPart() {
        Set<PipeTile> visitedTiles = new HashSet<>();
        visitedTiles.add(startTile);

        List<PipeTile> connectedStartPipes = startTile.connectedPipes()
                .filter(PipeTile::isConnectedWithStart)
                .toList();

        PipeTile headTile = connectedStartPipes.get(0);
        PipeTile tailTile = connectedStartPipes.get(1);
        visitedTiles.add(headTile);
        visitedTiles.add(tailTile);

        for (int distance = 1; true; distance++) {
            headTile.distance = distance;
            headTile = headTile.connectedPipes()
                    .filter(Predicate.not(visitedTiles::contains))
                    .findAny()
                    .orElse(headTile);
            if (!visitedTiles.add(headTile)) {
                return distance;
            }

            tailTile.distance = distance;
            tailTile = tailTile.connectedPipes()
                    .filter(Predicate.not(visitedTiles::contains))
                    .findAny()
                    .orElse(tailTile);
            visitedTiles.add(tailTile);
        }
    }


    @Override
    public Integer solveSecondPart() {
        solveFirstPart();

        int enclosedTilesCount = 0;
        for (int y = 0; y < height(); y++) {
            boolean inside = false;
            PipeTileSymbol previousCornerSymbol = GROUND;

            for (int x = 0; x < width(); x++) {
                PipeTile tile = tileAt(x, y);
                boolean isStartTile = tile.equals(startTile);

                if (isStartTile) {
                    previousCornerSymbol = GROUND;
                } else if (isEastCornerTile(tile)) {
                    previousCornerSymbol = tile.tileSymbol;
                } else if (shouldToggleInside(tile, previousCornerSymbol)) {
                    inside = !inside;
                } else if (!tile.isLoopTile() && inside) {
                    enclosedTilesCount++;
                    tile.inside = true;
                }
            }
        }
        return enclosedTilesCount;
    }

    private static boolean isEastCornerTile(PipeTile tile) {
        return tile.isLoopTile() && (tile.isTileSymbol(NORTH_EAST) || tile.isTileSymbol(SOUTH_EAST));
    }

    private static boolean shouldToggleInside(PipeTile tile, PipeTileSymbol previousCornerSymbol) {
        return tile.isLoopTile() && (tile.isTileSymbol(VERTICAL)
                || tile.isTileSymbol(NORTH_WEST) && previousCornerSymbol.equals(SOUTH_EAST)
                || tile.isTileSymbol(SOUTH_WEST) && previousCornerSymbol.equals(NORTH_EAST));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                PipeTile pipeTile = tileAt(x, y);
                if (pipeTile.inside) {
                    sb.append('I');
                } else {
                    sb.append(pipeTile.tileSymbol);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    private class PipeTile {

        final int x;
        final int y;
        final PipeTileSymbol tileSymbol;
        int distance = 0;
        boolean inside = false;


        PipeTile(int x, int y, PipeTileSymbol tileSymbol) {
            this.x = x;
            this.y = y;
            this.tileSymbol = tileSymbol;
        }


        public boolean isPipe() {
            return !PipeTileSymbol.GROUND.equals(tileSymbol);
        }

        public boolean isConnectedWithStart() {
            return this.connectedPipes().anyMatch(startTile::equals);
        }

        public boolean isLoopTile() {
            return distance > 0 || this.equals(startTile);
        }


        public boolean isTileSymbol(PipeTileSymbol tileSymbol) {
            return this.tileSymbol.equals(tileSymbol);
        }


        public Stream<PipeTile> connectedPipes() {
            return tileSymbol.connections()
                    .map(direction -> {
                        int newX = this.x + direction.dx;
                        int newY = this.y + direction.dy;
                        return tileAt(newX, newY);
                    })
                    .filter(PipeTile::isPipe);
        }


        @Override
        public boolean equals(Object o) {
            if (!(o instanceof PipeTile pipeTile)) {
                return false;
            }
            return x == pipeTile.x && y == pipeTile.y && tileSymbol == pipeTile.tileSymbol;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, tileSymbol);
        }


        @Override
        public String toString() {
            return String.format("(%d;%d) %s", x, y, tileSymbol);
        }

    }


}
