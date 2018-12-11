package de.adventofcode.chrisgw.day11;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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
                FuelCellSquare fuelCellSquare = createFuelCellSquare(x, y, fuelCellSquareSize);
                fuelCellSquares.add(fuelCellSquare);
            }
        }
        return fuelCellSquares.build();
    }

    private FuelCellSquare createFuelCellSquare(int x, int y, int fuelCellSquareSize) {
        FuelCell topLeftFuelCell = fuelCellAt(x, y);
        return new FuelCellSquare(fuelCellGrid, topLeftFuelCell, fuelCellSquareSize);
    }


    public FuelCell fuelCellAt(int x, int y) {
        return fuelCellGrid[y][x];
    }


    public int fuelCellGridSize() {
        return fuelCellGrid.length;
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);

    }

}
