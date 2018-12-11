package de.adventofcode.chrisgw.day11;

import lombok.Value;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;
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
        AtomicInteger completedSquareSizes = new AtomicInteger();
        return IntStream.range(1, fuelCellGridSize())
                .parallel()
                .mapToObj(this::findHighestPowerLevelFuelCellSquare)
                .peek(logFindHeighestPowerLevelFuelCellSquareProgression(completedSquareSizes))
                .max(Comparator.comparingInt(FuelCellSquare::getTotalPowerLevel))
                .orElse(null);
    }

    private Consumer<FuelCellSquare> logFindHeighestPowerLevelFuelCellSquareProgression(
            AtomicInteger completedSquareSizes) {
        return fuelCellSquare -> {
            double fuelCellGridSize = fuelCellGridSize();
            int percent = (int) (completedSquareSizes.incrementAndGet() / fuelCellGridSize * 100.0);
            int size = fuelCellSquare.size();
            int x = fuelCellSquare.getTopLeftFuelCell().getX();
            int y = fuelCellSquare.getTopLeftFuelCell().getY();
            int totalPowerLevel = fuelCellSquare.getTotalPowerLevel();
            System.out.printf("%3d%%: Square with size = %3d\tat <%3d;%3d> with totalPowerLevel = %9d%n", //
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


    @Value
    public class FuelCellSquare {

        private final FuelCell topLeftFuelCell;
        private final int size;
        private final int totalPowerLevel;


        public FuelCellSquare(FuelCell topLeftFuelCell, int size) {
            this.topLeftFuelCell = topLeftFuelCell;
            this.size = size;
            this.totalPowerLevel = calculateTotalPowerLevel();
        }

        private int calculateTotalPowerLevel() {
            return fuelCells().mapToInt(FuelCell::getPowerLevel).sum();
        }


        private Stream<FuelCell> fuelCells() {
            int x = topLeftFuelCell.getX();
            int y = topLeftFuelCell.getY();
            return Arrays.stream(fuelCellGrid, y, y + size).flatMap(fuelCells -> Arrays.stream(fuelCells, x, x + size));
        }


        public FuelCell topLeftFuelCell() {
            return topLeftFuelCell;
        }


        public int size() {
            return size;
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
        System.out.println("with suare size 3: " + highest3FuelCellSquare);

        FuelCellSquare highestAnySizeFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare();
        System.out.println("with any suare size: " + highestAnySizeFuelCellSquare);
    }

}
