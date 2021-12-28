package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import de.adventofcode.chrisgw.day09.HeightMap.HeightMapLocation;

import java.time.Year;
import java.util.List;
import java.util.Set;

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
        List<Set<HeightMapLocation>> basins = parseHeightMapFromInput().findBasins();
        return basins.stream()
                .mapToInt(Set::size)
                .sorted()
                .skip(basins.size() - 3) // take only last 3
                .reduce((left, right) -> left * right)
                .orElseThrow();
    }

    private HeightMap parseHeightMapFromInput() {
        return HeightMap.parseHeightMap(getInputLines());
    }

}
