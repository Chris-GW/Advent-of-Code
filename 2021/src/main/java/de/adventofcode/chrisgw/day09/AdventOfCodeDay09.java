package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import de.adventofcode.chrisgw.day09.HeightMap.HeightMapLocation;

import java.time.Year;
import java.util.List;

/**
 * https://adventofcode.com/2021/day/9
 */
public class AdventOfCodeDay09 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay09(List<String> inputLines) {
        super(Year.of(2021), 9, inputLines);
    }


    public Integer solveFirstPart() {
        return parseHeightMapFromInput()
                .findLowestPoints()
                .mapToInt(HeightMapLocation::riskLevel)
                .sum();
    }

    public Integer solveSecondPart() {
        return parseHeightMapFromInput().totalBasinSize();
    }

    private HeightMap parseHeightMapFromInput() {
        return HeightMap.parseHeightMap(getInputLines());
    }

}
