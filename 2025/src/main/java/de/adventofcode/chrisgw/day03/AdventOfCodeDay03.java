package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

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
        List<BatteryBank> batteryBanks = inputLines().map(BatteryBank::new).toList();
        return batteryBanks.stream()
                .mapToInt(BatteryBank::turnOnLargestJoltageBatteries)
                .sum();
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
