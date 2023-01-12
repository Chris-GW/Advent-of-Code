package de.adventofcode.chrisgw.day17;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2022/day/17">Advent of Code - day 17</a>
 */
public class AdventOfCodeDay17 extends AdventOfCodePuzzleSolver {


    public AdventOfCodeDay17(List<String> inputLines) {
        super(Year.of(2022), 17, inputLines);
    }


    public Integer solveFirstPart() {
        List<JetOfHotGas> jetsInChamber = parseJetsInChamber();
        VerticalChamber chamber = new VerticalChamber(7, jetsInChamber);
        for (int rockCounter = 1; rockCounter < 2023; rockCounter++) {
            chamber.nextFallingRock();
        }
        return chamber.rockTowerHeight();
    }

    private List<JetOfHotGas> parseJetsInChamber() {
        return inputLines().flatMapToInt(String::chars)
                .mapToObj(r -> JetOfHotGas.parseJetOfHotGasFrom((char) r))
                .toList();
    }

    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
