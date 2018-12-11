package de.adventofcode.chrisgw.day11;

import lombok.Value;


@Value
public class FuelCell {

    private final int x;
    private final int y;
    private final int gridSerialNumber;
    private final int powerLevel;


    public FuelCell(int x, int y, int gridSerialNumber) {
        this.x = x;
        this.y = y;
        this.gridSerialNumber = gridSerialNumber;
        this.powerLevel = calculatePowerLevel();
    }


    public int rackId() {
        return x + 10;
    }


    /**
     * <ul>
     * <li>Find the fuel cell's rack ID, which is its X coordinate plus 10.</li>
     * <li>Begin with a power level of the rack ID times the Y coordinate.</li>
     * <li>Increase the power level by the value of the grid serial number (your puzzle input).</li>
     * <li>Set the power level to itself multiplied by the rack ID.</li>
     * <li>Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).</li>
     * <li>Subtract 5 from the power level.</li>
     * </ol>
     *
     * @return calculated powerlevel for this FuelCell
     */
    private int calculatePowerLevel() {
        int powerLevel = rackId() * y;
        powerLevel += gridSerialNumber;
        powerLevel *= rackId();
        powerLevel = (powerLevel / 100) % 10;
        return powerLevel - 5;
    }

}
