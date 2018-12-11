package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.day11.ChronalCharge.FuelCellSquare;
import org.junit.Test;

import static org.junit.Assert.*;


public class ChronalChargeTest {


    @Test
    public void fuelCell_powerLevel_exampple01() {
        int expectedPowerLevel = 4;
        int gridSerialNumber = 8;
        int x = 3, y = 5;

        FuelCell fuelCell = new FuelCell(x, y, gridSerialNumber);
        int powerLevel = fuelCell.getPowerLevel();
        assertEquals("calculatePowerLevel", expectedPowerLevel, powerLevel);
    }

    @Test
    public void fuelCell_powerLevel_exampple02() {
        int expectedPowerLevel = -5;
        int gridSerialNumber = 57;
        int x = 122, y = 79;

        FuelCell fuelCell = new FuelCell(x, y, gridSerialNumber);
        int powerLevel = fuelCell.getPowerLevel();
        assertEquals("calculatePowerLevel", expectedPowerLevel, powerLevel);
    }

    @Test
    public void fuelCell_powerLevel_exampple03() {
        int expectedPowerLevel = 0;
        int gridSerialNumber = 39;
        int x = 217, y = 196;

        FuelCell fuelCell = new FuelCell(x, y, gridSerialNumber);
        int powerLevel = fuelCell.getPowerLevel();
        assertEquals("calculatePowerLevel", expectedPowerLevel, powerLevel);
    }

    @Test
    public void fuelCell_powerLevel_exampple04() {
        int expectedPowerLevel = 4;
        int gridSerialNumber = 71;
        int x = 101, y = 153;

        FuelCell fuelCell = new FuelCell(x, y, gridSerialNumber);
        int powerLevel = fuelCell.getPowerLevel();
        assertEquals("calculatePowerLevel", expectedPowerLevel, powerLevel);
    }


    @Test
    public void findHighestPowerLevelFuelCellSquare_example01() {
        int fuelCellGridSize = 300;
        int gridSerialNumber = 18;
        int expectedTotalPowerLevel = 29;

        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);
        FuelCellSquare highestFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare(3);
        int totalPowerLevel = highestFuelCellSquare.getTotalPowerLevel();
        assertEquals("getTotalPowerLevel", expectedTotalPowerLevel, totalPowerLevel);
    }

    @Test
    public void findHighestPowerLevelFuelCellSquare_example02() {
        int fuelCellGridSize = 300;
        int gridSerialNumber = 42;
        int expectedTotalPowerLevel = 30;

        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);
        FuelCellSquare highestFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare(3);
        int totalPowerLevel = highestFuelCellSquare.getTotalPowerLevel();
        assertEquals("getTotalPowerLevel", expectedTotalPowerLevel, totalPowerLevel);
    }


    @Test
    public void findHighestPowerLevelFuelCellSquare_size_3_myExample() {
        int fuelCellGridSize = 300;
        int gridSerialNumber = 3628;
        int expectedTotalPowerLevel = 30;
        int expectedX = 216;
        int expectedY = 12;

        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);
        FuelCellSquare highestFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare(3);
        int totalPowerLevel = highestFuelCellSquare.getTotalPowerLevel();
        FuelCell topLeftFuelCell = highestFuelCellSquare.topLeftFuelCell();
        int x = topLeftFuelCell.getX();
        int y = topLeftFuelCell.getY();

        System.out.println(highestFuelCellSquare);
        assertEquals("getTotalPowerLevel", expectedTotalPowerLevel, totalPowerLevel);
        assertEquals("x", expectedX, x);
        assertEquals("y", expectedY, y);
    }


    // part 02

    @Test
    public void findHighestPowerLevelFuelCellSquare_allSizes_example01() {
        int fuelCellGridSize = 300;
        int gridSerialNumber = 18;
        int expectedX = 90;
        int expectedY = 269;
        int expectedSuqareSize = 16;
        int expectedTotalPower = 113;

        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);
        FuelCellSquare highestFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare();
        FuelCell topLeftFuelCell = highestFuelCellSquare.topLeftFuelCell();

        System.out.println(highestFuelCellSquare);
        assertEquals("x", expectedX, topLeftFuelCell.getX());
        assertEquals("y", expectedY, topLeftFuelCell.getY());
        assertEquals("fuelCellSquareSize", expectedSuqareSize, highestFuelCellSquare.size());
        assertEquals("totalPower", expectedTotalPower, highestFuelCellSquare.getTotalPowerLevel());
    }

    @Test
    public void findHighestPowerLevelFuelCellSquare_allSizes_example02() {
        int fuelCellGridSize = 300;
        int gridSerialNumber = 42;
        int expectedX = 232;
        int expectedY = 251;
        int expectedSuqareSize = 12;
        int expectedTotalPower = 119;

        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);
        FuelCellSquare highestFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare();
        FuelCell topLeftFuelCell = highestFuelCellSquare.topLeftFuelCell();

        System.out.println(highestFuelCellSquare);
        assertEquals("x", expectedX, topLeftFuelCell.getX());
        assertEquals("y", expectedY, topLeftFuelCell.getY());
        assertEquals("fuelCellSquareSize", expectedSuqareSize, highestFuelCellSquare.size());
        assertEquals("totalPower", expectedTotalPower, highestFuelCellSquare.getTotalPowerLevel());
    }


    @Test
    public void findHighestPowerLevelFuelCellSquare_allSizes_myExample() {
        int fuelCellGridSize = 300;
        int gridSerialNumber = 3628;
        int expectedX = 236;
        int expectedY = 175;
        int expectedSuqareSize = 11;
        int expectedTotalPower = 88;

        ChronalCharge chronalCharge = new ChronalCharge(fuelCellGridSize, gridSerialNumber);
        FuelCellSquare highestFuelCellSquare = chronalCharge.findHighestPowerLevelFuelCellSquare();
        FuelCell topLeftFuelCell = highestFuelCellSquare.topLeftFuelCell();

        System.out.println(highestFuelCellSquare);
        assertEquals("x", expectedX, topLeftFuelCell.getX());
        assertEquals("y", expectedY, topLeftFuelCell.getY());
        assertEquals("fuelCellSquareSize", expectedSuqareSize, highestFuelCellSquare.size());
        assertEquals("totalPower", expectedTotalPower, highestFuelCellSquare.getTotalPowerLevel());
    }

}
