package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


/**
 * <a href="https://adventofcode.com/2025/day/4">Advent of Code - day 4</a>
 */
public class AdventOfCodeDay04 extends AdventOfCodePuzzleSolver {

    private GridPosition[][] grid;

    public AdventOfCodeDay04(List<String> inputLines) {
        super(Year.of(2025), 4, inputLines);
    }

    private GridPosition[][] parseGrid() {
        List<String> inputLines = getInputLines();
        GridPosition[][] newGrid = new GridPosition[inputLines.size()][];
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);
            newGrid[y] = new GridPosition[line.length()];
            for (int x = 0; x < line.length(); x++) {
                boolean withPaperRoll = line.charAt(x) == '@';
                newGrid[y][x] = new GridPosition(x, y, withPaperRoll);
            }
        }
        return newGrid;
    }


    @Override
    public Long solveFirstPart() {
        grid = parseGrid();
        return gridPositions().filter(GridPosition::isAccessiblePaperRoll).count();
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    private Stream<GridPosition> gridPositions() {
        return Arrays.stream(grid).flatMap(Arrays::stream);
    }

    private boolean containsPosition(int x, int y) {
        return 0 <= y && y < grid.length &&
                0 <= x && x < grid[y].length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (GridPosition[] gridPositions : grid) {
            for (GridPosition gridPoisition : gridPositions) {
                if (gridPoisition.isAccessiblePaperRoll()) {
                    sb.append('x');
                } else if (gridPoisition.isPaperRoll()) {
                    sb.append('@');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    private class GridPosition {

        final int x;
        final int y;
        final boolean isPaperRoll;


        private GridPosition(int x, int y, boolean isPaperRoll) {
            this.x = x;
            this.y = y;
            this.isPaperRoll = isPaperRoll;
        }


        public boolean isAccessiblePaperRoll() {
            return isPaperRoll() && adjacentPositions().filter(GridPosition::isPaperRoll).count() < 4;
        }


        public Stream<GridPosition> adjacentPositions() {
            Builder<GridPosition> adjacentPositions = Stream.builder();
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    int x = this.x + dx;
                    int y = this.y + dy;

                    if ((dy != 0 || dx != 0) && containsPosition(x, y)) {
                        var gridPoisition = grid[y][x];
                        adjacentPositions.add(gridPoisition);
                    }
                }
            }
            return adjacentPositions.build();
        }


        public boolean isPaperRoll() {
            return isPaperRoll;
        }

    }

}
