package de.adventofcode.chrisgw.day11;

import lombok.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


public class ChronalCharge {

    private FuelCell[][] fuelCellGrid;

    public ChronalCharge(int fuelCellGridSize, int gridSerialNumber) {
        this.fuelCellGrid = new FuelCell[fuelCellGridSize][fuelCellGridSize];
        for (int y = 0; y < fuelCellGridSize; y++) {
            for (int x = 0; x < fuelCellGridSize; x++) {
                FuelCell fuelCell = new FuelCell(x, y, gridSerialNumber);
                this.fuelCellGrid[y][x] = fuelCell;
            }
        }
    }


    public FuelCellSquare findHighestPowerLevelFuelCellSquare() {
        FuelCell topLeftFuelCell = fuelCellAt(0, 0);
        FuelCellSquare firstFuelCellSquare = new FuelCellSquare(topLeftFuelCell, 1);
        int maxFuelCellSquareSize = 300;
        FindHighestPowerSquareTask findHighestPowerSquareTask = new FindHighestPowerSquareTask( //
                firstFuelCellSquare, maxFuelCellSquareSize, logHeighestPowerSquareProgression());
        return ForkJoinPool.commonPool().invoke(findHighestPowerSquareTask);
    }

    private Consumer<FuelCellSquare> logHeighestPowerSquareProgression() {
        AtomicInteger completedSquareSizes = new AtomicInteger();
        return fuelCellSquare -> {
            double fuelCellGridSize = fuelCellGridSize();
            int percent = (int) (completedSquareSizes.incrementAndGet() / fuelCellGridSize * 100.0);
            int size = fuelCellSquare.getSize();
            int x = fuelCellSquare.getTopLeftFuelCell().getX();
            int y = fuelCellSquare.getTopLeftFuelCell().getY();
            int totalPowerLevel = fuelCellSquare.getTotalPowerLevel();
            System.out.printf("%3d%%: Square with fuelCellSquareSize = %3d\tat <%3d;%3d> with totalPowerLevel = %9d%n",
                    percent, size, x, y, totalPowerLevel);
        };
    }

    public FuelCellSquare findHighestPowerLevelFuelCellSquare(int fuelCellSquareSize) {
        return fuelCellSquares(fuelCellSquareSize) //
                .max(Comparator.comparingInt(FuelCellSquare::getTotalPowerLevel)) //
                .orElse(null);
    }

    private Stream<FuelCellSquare> fuelCellSquares(int fuelCellSquareSize) {
        Builder<FuelCellSquare> fuelCellSquares = Stream.builder();
        for (int y = 0; y < fuelCellGridSize() - fuelCellSquareSize; y++) {
            for (int x = 0; x < fuelCellGridSize() - fuelCellSquareSize; x++) {
                FuelCell topLeftFuelCell = fuelCellAt(x, y);
                FuelCellSquare fuelCellSquare = new FuelCellSquare(topLeftFuelCell, fuelCellSquareSize);
                fuelCellSquares.add(fuelCellSquare);
            }
        }
        return fuelCellSquares.build();
    }


    public FuelCell fuelCellAt(int x, int y) {
        return fuelCellGrid[y][x];
    }


    public int fuelCellGridSize() {
        return fuelCellGrid.length;
    }


    @Override
    public String toString() {
        int gridSize = fuelCellGridSize();
        StringBuilder sb = new StringBuilder(gridSize * gridSize + gridSize);
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                FuelCell fuelCell = fuelCellAt(x, y);
                sb.append(String.format("%3d ", fuelCell.getPowerLevel()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    @Getter
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class FuelCellSquare {

        private FuelCell topLeftFuelCell;
        private int size;
        private int totalPowerLevel;


        public FuelCellSquare(FuelCell topLeftFuelCell, int size) {
            this(topLeftFuelCell, size, 0);
            this.totalPowerLevel = calculateTotalPowerLevel();
        }

        public FuelCellSquare(FuelCellSquare fuelCellSquare) {
            this(fuelCellSquare.topLeftFuelCell, fuelCellSquare.size, fuelCellSquare.totalPowerLevel);
        }


        private int calculateTotalPowerLevel() {
            return fuelCells().mapToInt(FuelCell::getPowerLevel).sum();
        }


        public Stream<FuelCell> fuelCells() {
            int x = topLeftFuelCell.getX();
            int y = topLeftFuelCell.getY();
            return Arrays.stream(fuelCellGrid, y, y + size).flatMap(fuelCells -> Arrays.stream(fuelCells, x, x + size));
        }


        private void moveToNextColumn() {
            int nextColumnIndex = topLeftFuelCell.getX() + 1;
            if (size <= 2) {
                this.topLeftFuelCell = fuelCellAt(nextColumnIndex, topLeftFuelCell.getY());
                this.totalPowerLevel = calculateTotalPowerLevel();
            } else {
                this.totalPowerLevel -= fuelCellColumn(0).mapToInt(FuelCell::getPowerLevel).sum();
                this.totalPowerLevel += fuelCellColumn(size).mapToInt(FuelCell::getPowerLevel).sum();
                this.topLeftFuelCell = fuelCellAt(nextColumnIndex, topLeftFuelCell.getY());
            }
        }

        private Stream<FuelCell> fuelCellColumn(int relativeColumnIndex) {
            int x = topLeftFuelCell.getX() + relativeColumnIndex;
            int y = topLeftFuelCell.getY();
            return Arrays.stream(fuelCellGrid, y, y + size).map(fuelCells -> fuelCells[x]);
        }


        private FuelCellSquare moveToNextRow() {
            int nextRowIndex = topLeftFuelCell.getY() + 1;
            FuelCell newTopLeftFuelCell = fuelCellAt(topLeftFuelCell.getX(), nextRowIndex);
            if (size <= 2) {
                return new FuelCellSquare(newTopLeftFuelCell, size);
            }
            int newTotalPowerLevel = this.totalPowerLevel;
            newTotalPowerLevel -= fuelCellRow(0).mapToInt(FuelCell::getPowerLevel).sum();
            newTotalPowerLevel += fuelCellRow(size).mapToInt(FuelCell::getPowerLevel).sum();
            return new FuelCellSquare(newTopLeftFuelCell, size, newTotalPowerLevel);
        }

        private Stream<FuelCell> fuelCellRow(int relativeRowIndex) {
            int x = topLeftFuelCell.getX();
            int y = topLeftFuelCell.getY() + relativeRowIndex;
            return Arrays.stream(fuelCellGrid[y], x, x + size);
        }


        private FuelCellSquare increaseSize() {
            if (size <= 2) {
                return new FuelCellSquare(topLeftFuelCell, size + 1);
            }
            int newTotalPowerLevel = this.totalPowerLevel;
            newTotalPowerLevel += fuelCellColumn(size).mapToInt(FuelCell::getPowerLevel).sum();
            newTotalPowerLevel += fuelCellRow(size).mapToInt(FuelCell::getPowerLevel).sum();
            int x = topLeftFuelCell.getX() + size;
            int y = topLeftFuelCell.getY() + size;
            FuelCell newCornerFuelCell = fuelCellAt(x, y);
            newTotalPowerLevel += newCornerFuelCell.getPowerLevel();
            return new FuelCellSquare(topLeftFuelCell, size + 1, newTotalPowerLevel);
        }

    }


    private class FindHighestPowerSquareTask extends RecursiveTask<FuelCellSquare> {

        private FuelCellSquare topLeftFuelCellSquare;
        private int maxFuelCellSquareSize;
        private Consumer<FuelCellSquare> fuelCellSquareLogger;


        public FindHighestPowerSquareTask(FuelCellSquare topLeftFuelCellSquare, int maxFuelCellSquareSize,
                Consumer<FuelCellSquare> fuelCellSquareLogger) {
            this.topLeftFuelCellSquare = topLeftFuelCellSquare;
            this.maxFuelCellSquareSize = maxFuelCellSquareSize;
            this.fuelCellSquareLogger = fuelCellSquareLogger;
        }


        @Override
        protected FuelCellSquare compute() {
            if (topLeftFuelCellSquare.getSize() < maxFuelCellSquareSize) {
                FuelCellSquare increasedTopLeftFuelCellSquare = this.topLeftFuelCellSquare.increaseSize();
                FindHighestPowerSquareTask nextSizeSquare = new FindHighestPowerSquareTask(
                        increasedTopLeftFuelCellSquare, maxFuelCellSquareSize, fuelCellSquareLogger);
                nextSizeSquare.fork();
                FuelCellSquare highestPowerSquare = findHighestPowerLevelFuelCellSquare();
                FuelCellSquare otherHighestPowerSquare = nextSizeSquare.join();

                if (otherHighestPowerSquare.getTotalPowerLevel() > highestPowerSquare.getTotalPowerLevel()) {
                    highestPowerSquare = otherHighestPowerSquare;
                }
                return highestPowerSquare;
            } else {
                return findHighestPowerLevelFuelCellSquare();
            }
        }

        private FuelCellSquare findHighestPowerLevelFuelCellSquare() {
            FuelCellSquare highestPowerSquare = topLeftFuelCellSquare;
            int squareSize = topLeftFuelCellSquare.getSize() + 1;
            for (int y = 0; y < fuelCellGridSize() - squareSize; y++) {
                FuelCellSquare currentFuelCellSquare = new FuelCellSquare(topLeftFuelCellSquare);
                for (int x = 0; x < fuelCellGridSize() - squareSize; x++) {
                    currentFuelCellSquare.moveToNextColumn();
                    if (currentFuelCellSquare.getTotalPowerLevel() > highestPowerSquare.getTotalPowerLevel()) {
                        highestPowerSquare = new FuelCellSquare(currentFuelCellSquare);
                    }
                }
                topLeftFuelCellSquare = topLeftFuelCellSquare.moveToNextRow();
            }
            fuelCellSquareLogger.accept(highestPowerSquare);
            return highestPowerSquare;
        }

    }


    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage <gridSerialNumber> <fuelCellGridSize>");
            return;
        }
        int gridSerialNumber = Integer.parseInt(args[0]);
        int fuelCellGridSize = Integer.parseInt(args[1]);
        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);

        FuelCellSquare highest3FuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare(3);
        System.out.println("with suare fuelCellSquareSize 3: " + highest3FuelCellSquare);

        FuelCellSquare highestAnySizeFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare();
        System.out.println("with any suare fuelCellSquareSize: " + highestAnySizeFuelCellSquare);
    }

}
