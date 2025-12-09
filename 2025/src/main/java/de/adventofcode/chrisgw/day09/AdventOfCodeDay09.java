package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2025/day/9">Advent of Code - day 9</a>
 */
public class AdventOfCodeDay09 extends AdventOfCodePuzzleSolver {

    private final List<TileLocation> redTiles;
    private final Set<TileLocation> greenTiles;
    private final Set<TileLocation> illeagleTiles = new HashSet<>();

    private final List<TileLocation> directions = List.of(
            new TileLocation(0, 1),
            new TileLocation(0, -1),
            new TileLocation(1, 0),
            new TileLocation(-1, 0));


    public AdventOfCodeDay09(List<String> inputLines) {
        super(Year.of(2025), 9, inputLines);
        this.redTiles = inputLines().map(TileLocation::parse).toList();
        this.greenTiles = fillInGreenTiles();
        floodFillEmptyFiles();
        System.out.println(this);
    }

    private Set<TileLocation> fillInGreenTiles() {
        Set<TileLocation> result = new HashSet<>();
        TileLocation previousTile = redTiles.getLast();
        for (TileLocation nextTile : redTiles) {
            previousTile.tileLineTo(nextTile).forEach(result::add);
            previousTile = nextTile;
        }
        return result;
    }


    private void floodFillEmptyFiles() {
        TileLocation startLocation = redTiles.stream()
                .min(Comparator.naturalOrder())
                .orElseThrow()
                .add(new TileLocation(-1, 0));
        Queue<TileLocation> queue = new LinkedList<>();
        queue.offer(startLocation);

        while (!queue.isEmpty()) {
            TileLocation location = queue.poll();
            directions.stream()
                    .map(location::add)
                    .filter(this::hasColoredTileNeighbours)
                    .filter(illeagleTiles::add)
                    .forEach(queue::offer);
        }
    }

    private boolean hasColoredTileNeighbours(TileLocation location) {
        if (greenTiles.contains(location)) {
            return false;
        }
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                TileLocation neigbour = new TileLocation(location.x() + dx, location.y() + dy);
                if (greenTiles.contains(neigbour)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Long solveFirstPart() {
        return IntStream.range(0, redTiles.size())
                .mapToObj(this::buildTileRectangles)
                .flatMap(Function.identity())
                .mapToLong(TileRectangle::calculateArea)
                .max()
                .orElse(0L);
    }

    private Stream<TileRectangle> buildTileRectangles(int fromIndex) {
        return IntStream.range(fromIndex + 1, getInputLines().size())
                .mapToObj(redTiles::get)
                .map(k -> new TileRectangle(redTiles.get(fromIndex), k));
    }


    @Override
    public Long solveSecondPart() {
        floodFillEmptyFiles();
        System.out.println(illeagleTiles.size());
        return IntStream.range(0, redTiles.size())
                .parallel()
                .mapToObj(this::buildTileRectangles)
                .flatMap(Function.identity())
                .filter(tileRectangle -> tileRectangle.borderTiles().noneMatch(illeagleTiles::contains))
                .mapToLong(TileRectangle::calculateArea)
                .max()
                .orElse(0L);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 14; x++) {
                var tileLocation = new TileLocation(x, y);
                if (redTiles.contains(tileLocation)) {
                    sb.append('#');
                } else if (illeagleTiles.contains(tileLocation)) {
                    sb.append('-');
                } else if (greenTiles.contains(tileLocation)) {
                    sb.append('X');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
