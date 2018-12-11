package de.adventofcode.chrisgw.day11;

import org.junit.Test;

import static org.junit.Assert.*;


public class ChronalChargeTest {


    @Test
    public void fuelCell_powerLevel_exampple01() {
        int expectedPowerLevel = 4;
        int gridSerialNumber = 8;
        int x = 3, y = 5;

        FuelCell fuelCell = new FuelCell(x, y, gridSerialNumber);
        int powerLevel = fuelCell.powerLevel();
        assertEquals("powerLevel", expectedPowerLevel, powerLevel);
    }

    @Test
    public void fuelCell_powerLevel_exampple02() {
        int expectedPowerLevel = -5;
        int gridSerialNumber = 57;
        int x = 122, y = 79;

        FuelCell fuelCell = new FuelCell(x, y, gridSerialNumber);
        int powerLevel = fuelCell.powerLevel();
        assertEquals("powerLevel", expectedPowerLevel, powerLevel);
    }

    @Test
    public void fuelCell_powerLevel_exampple03() {
        int expectedPowerLevel = 0;
        int gridSerialNumber = 39;
        int x = 217, y = 196;

        FuelCell fuelCell = new FuelCell(x, y, gridSerialNumber);
        int powerLevel = fuelCell.powerLevel();
        assertEquals("powerLevel", expectedPowerLevel, powerLevel);
    }

    @Test
    public void fuelCell_powerLevel_exampple04() {
        int expectedPowerLevel = 4;
        int gridSerialNumber = 71;
        int x = 101, y = 153;

        FuelCell fuelCell = new FuelCell(x, y, gridSerialNumber);
        int powerLevel = fuelCell.powerLevel();
        assertEquals("powerLevel", expectedPowerLevel, powerLevel);
    }


    @Test
    public void findHighestPowerLevelFuelCellSquare_example01() {
        int fuelCellGridSize = 300;
        int gridSerialNumber = 18;
        int expectedTotalPowerLevel = 29;

        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);
        FuelCellSquare highestFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare(3);
        int totalPowerLevel = highestFuelCellSquare.totalPowerLevel();
        assertEquals("totalPowerLevel", expectedTotalPowerLevel, totalPowerLevel);
    }

    @Test
    public void findHighestPowerLevelFuelCellSquare_example02() {
        int fuelCellGridSize = 300;
        int gridSerialNumber = 42;
        int expectedTotalPowerLevel = 30;

        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);
        FuelCellSquare highestFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare(3);
        int totalPowerLevel = highestFuelCellSquare.totalPowerLevel();
        assertEquals("totalPowerLevel", expectedTotalPowerLevel, totalPowerLevel);
    }


    @Test
    public void findHighestPowerLevelFuelCellSquare_myExample() {
        int fuelCellGridSize = 300;
        int gridSerialNumber = 3628;
        int expectedTotalPowerLevel = 30;
        int expectedX = 216;
        int expectedY = 12;

        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);
        FuelCellSquare highestFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare(3);
        int totalPowerLevel = highestFuelCellSquare.totalPowerLevel();
        FuelCell topLeftFuelCell = highestFuelCellSquare.topLeftFuelCell();
        int x = topLeftFuelCell.getX();
        int y = topLeftFuelCell.getY();

        System.out.printf("x: %d y: %d with powerLevel %d%n", x, y, totalPowerLevel);
        assertEquals("totalPowerLevel", expectedTotalPowerLevel, totalPowerLevel);
        assertEquals("x", expectedX, x);
        assertEquals("y", expectedY, y);
    }

}