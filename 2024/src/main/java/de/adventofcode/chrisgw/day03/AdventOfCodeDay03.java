package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * <a href="https://adventofcode.com/2024/day/3">Advent of Code - day 3</a>
 */
public class AdventOfCodeDay03 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay03(List<String> inputLines) {
        super(Year.of(2024), 3, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        Pattern mulPattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = mulPattern.matcher(inputLines().collect(Collectors.joining("\n")));
        int totalResult = 0;

        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int result = x * y;
            totalResult += result;
        }
        return totalResult;
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
