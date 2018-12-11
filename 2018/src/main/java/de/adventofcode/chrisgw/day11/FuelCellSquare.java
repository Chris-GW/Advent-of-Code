package de.adventofcode.chrisgw.day11;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Arrays;
import java.util.stream.Stream;


@Value
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FuelCellSquare {


    private final FuelCell[][] fuelCellSquare;


    public static FuelCellSquare createFuelCellSquare(FuelCell[][] fuelCellGrid, int size, int x, int y) {
        FuelCell[][] fuelCellSquare = new FuelCell[size][size];
        for (int dy = 0; dy < size; dy++) {
            for (int dx = 0; dx < size; dx++) {
                FuelCell fuelCell = fuelCellGrid[y + dy][x + dx];
                fuelCellSquare[dy][dx] = fuelCell;
            }
        }
        return new FuelCellSquare(fuelCellSquare);
    }


    public FuelCell topLeftFuelCell() {
        return fuelCellSquare[0][0];
    }

    public int totalPowerLevel() {
        return fuelCells().mapToInt(FuelCell::powerLevel).sum();
    }


    public Stream<FuelCell> fuelCells() {
        return Arrays.stream(fuelCellSquare).flatMap(Arrays::stream);
    }


    public FuelCell fuelCellAt(int x, int y) {
        return fuelCellSquare[y][x];
    }

    public int size() {
        return fuelCellSquare.length;
    }

}
