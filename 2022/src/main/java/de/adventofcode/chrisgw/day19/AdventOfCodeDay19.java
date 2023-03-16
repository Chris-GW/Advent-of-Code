package de.adventofcode.chrisgw.day19;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2022/day/19">Advent of Code - day 19</a>
 */
public class AdventOfCodeDay19 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay19(List<String> inputLines) {
        super(Year.of(2022), 19, inputLines);
    }


    public Integer solveFirstPart() {
        List<RobotFactoryBlueprint> blueprints = inputLines().map(RobotFactoryBlueprint::parseBlueprint).toList();
        System.out.println(blueprints);
        return 0;
    }

    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


}
