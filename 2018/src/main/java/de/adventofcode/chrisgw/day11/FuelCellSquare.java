package de.adventofcode.chrisgw.day11;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


@Value
public class FuelCellSquare {


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final FuelCell[][] fuelCellGrid;

    private final FuelCell topLeftFuelCell;
    private final int size;
    private final int totalPowerLevel;


    public FuelCellSquare(FuelCell[][] fuelCellGrid, FuelCell topLeftFuelCell, int size) {
        this.fuelCellGrid = fuelCellGrid;
        this.topLeftFuelCell = topLeftFuelCell;
        this.size = size;
        this.totalPowerLevel = calculateTotalPowerLevel();
    }

    private int calculateTotalPowerLevel() {
        return fuelCells().mapToInt(FuelCell::getPowerLevel).sum();
    }


    public Stream<FuelCell> fuelCells() {
        Builder<FuelCell> fuelCells = Stream.builder();
        for (int dy = 0; dy < size; dy++) {
            for (int dx = 0; dx < size; dx++) {
                int x = topLeftFuelCell.getX() + dx;
                int y = topLeftFuelCell.getY() + dy;
                fuelCells.add(fuelCellGrid[y][x]);
            }
        }
        return fuelCells.build();
    }


    public FuelCell topLeftFuelCell() {
        return topLeftFuelCell;
    }


    public int size() {
        return size;
    }


}
