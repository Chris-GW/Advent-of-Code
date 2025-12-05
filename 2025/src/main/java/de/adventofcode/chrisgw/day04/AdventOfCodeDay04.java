package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import static java.util.stream.Collectors.joining;


/**
 * <a href="https://adventofcode.com/2025/day/4">Advent of Code - day 4</a>
 */
public class AdventOfCodeDay04 extends AdventOfCodePuzzleSolver {

    private final GridPosition[][] grid;


    public AdventOfCodeDay04(List<String> inputLines) {
        super(Year.of(2025), 4, inputLines);
        grid = parseGrid();
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
    public Integer solveFirstPart() {
        return Math.toIntExact(gridPositions()
                .filter(GridPosition::isAccessiblePaperRoll)
                .count());
    }


    @Override
    public Integer solveSecondPart() {
        return IntStream.generate(this::removeAccessiblePaperRolls)
                .takeWhile(removedPaperRollCount -> removedPaperRollCount > 0)
                .sum();
    }

    public int removeAccessiblePaperRolls() {
        List<GridPosition> removablePositions = gridPositions()
                .filter(GridPosition::isAccessiblePaperRoll)
                .toList();
        removablePositions.forEach(GridPosition::removePaperRoll);
        return removablePositions.size();
    }


    public Stream<GridPosition> gridPositions() {
        return Arrays.stream(grid).flatMap(Arrays::stream);
    }

    public boolean containsGridPosition(int x, int y) {
        return 0 <= y && y < grid.length &&
                0 <= x && x < grid[y].length;
    }


    @Override
    public String toString() {
        return Arrays.stream(grid)
                .map(gridRow -> Arrays.stream(gridRow).map(GridPosition::sign).collect(joining()))
                .collect(joining("\n"));
    }


    public class GridPosition {

        private final int x;
        private final int y;
        private boolean isPaperRoll;


        private GridPosition(int x, int y, boolean isPaperRoll) {
            this.x = x;
            this.y = y;
            this.isPaperRoll = isPaperRoll;
        }


        public void removePaperRoll() {
            if (!isAccessiblePaperRoll()) {
                throw new IllegalStateException("Could not remove paper roll from " + this);
            }
            isPaperRoll = false;
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

                    if ((dy != 0 || dx != 0) && containsGridPosition(x, y)) {
                        var gridPosition = grid[y][x];
                        adjacentPositions.add(gridPosition);
                    }
                }
            }
            return adjacentPositions.build();
        }


        public boolean isPaperRoll() {
            return isPaperRoll;
        }


        public int x() {
            return x;
        }

        public int y() {
            return y;
        }


        public String sign() {
            if (isAccessiblePaperRoll()) {
                return "x";
            } else if (isPaperRoll()) {
                return "@";
            } else {
                return ".";
            }
        }


        @Override
        public boolean equals(Object o) {
            if (!(o instanceof GridPosition that)) {
                return false;
            }
            return x == that.x && y == that.y && isPaperRoll == that.isPaperRoll;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, isPaperRoll);
        }

        @Override
        public String toString() {
            return "(%2d, %2d) %s".formatted(x, y, sign());
        }

    }

}
