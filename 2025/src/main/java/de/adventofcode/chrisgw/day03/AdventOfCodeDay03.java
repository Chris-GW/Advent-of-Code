package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.math.BigInteger;
import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2025/day/3">Advent of Code - day 3</a>
 */
public class AdventOfCodeDay03 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay03(List<String> inputLines) {
        super(Year.of(2025), 3, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        return inputLines()
                .map(BatteryBank::new)
                .map(batteryBank -> batteryBank.findBestBatteryCombination(2))
                .mapToInt(BigInteger::intValue)
                .sum();
    }


    @Override
    public BigInteger solveSecondPart() {
        return inputLines()
                .map(BatteryBank::new)
                .map(batteryBank -> batteryBank.findBestBatteryCombination(12))
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

}
