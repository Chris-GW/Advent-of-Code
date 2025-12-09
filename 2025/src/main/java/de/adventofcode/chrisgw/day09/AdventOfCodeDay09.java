package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2025/day/9">Advent of Code - day 9</a>
 */
public class AdventOfCodeDay09 extends AdventOfCodePuzzleSolver {

    private final List<TileLocation> redTiles;

    public AdventOfCodeDay09(List<String> inputLines) {
        super(Year.of(2025), 9, inputLines);
        this.redTiles = inputLines().map(TileLocation::parse).toList();
    }


    @Override
    public Long solveFirstPart() {
        return IntStream.range(0, redTiles.size())
                .mapToObj(this::buildTileRectangles)
                .flatMap(Function.identity())
                .mapToLong(TileRectangle::calculateArea)
                .max()
                .orElse(0);
    }

    private Stream<TileRectangle> buildTileRectangles(int fromIndex) {
        return IntStream.range(fromIndex + 1, getInputLines().size())
                .mapToObj(redTiles::get)
                .map(k -> new TileRectangle(redTiles.get(fromIndex), k));
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
