package de.adventofcode.chrisgw.day11;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
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


    public FuelCellSquare findHighestPowerLevelFuelCellSquare(int fuelCellSquareSize) {
        return fuelCellSquares(fuelCellSquareSize) //
                .max(Comparator.comparingInt(FuelCellSquare::totalPowerLevel)) //
                .orElse(null);
    }

    private Stream<FuelCellSquare> fuelCellSquares(int fuelCellSquareSize) {
        Builder<FuelCellSquare> fuelCellSquares = Stream.builder();
        for (int y = 0; y < fuelCellGridSize() - fuelCellSquareSize; y++) {
            for (int x = 0; x < fuelCellGridSize() - fuelCellSquareSize; x++) {
                FuelCellSquare fuelCellSquare = createFuelCellSquare(fuelCellSquareSize, y, x);
                fuelCellSquares.add(fuelCellSquare);
            }
        }
        return fuelCellSquares.build();
    }

    private FuelCellSquare createFuelCellSquare(int fuelCellSquareSize, int y, int x) {
        return FuelCellSquare.createFuelCellSquare(fuelCellGrid, fuelCellSquareSize, x, y);
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
