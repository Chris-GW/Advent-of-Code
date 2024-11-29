package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;


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
        if (0 > y || y > height() || 0 > x || x > width()) {
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
            if (!visitedTiles.add(tailTile)) {
                return distance + 1;
            }
        }
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                PipeTile pipeTile = tileAt(x, y);
                if (!pipeTile.isPipe()) {
                    sb.append('.');
                } else if (pipeTile.distance == 0) {
                    sb.append(pipeTile.tileSymbol);
                } else {
                    sb.append(pipeTile.distance % 10);
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

    }


}
