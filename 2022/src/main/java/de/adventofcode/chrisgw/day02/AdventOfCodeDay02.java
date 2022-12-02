package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <a href="https://adventofcode.com/2022/day/2">Advent of Code 2022 - day 2</a>
 */
public class AdventOfCodeDay02 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay02(List<String> inputLines) {
        super(Year.of(2022), 2, inputLines);
    }


    public Integer solveFirstPart() {
        return inputLinesAsRockPaperScissorsRounds().stream().mapToInt(RockPaperScissorsRound::score).sum();
    }

    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }


    public List<RockPaperScissorsRound> inputLinesAsRockPaperScissorsRounds() {
        Pattern strategyGuideLinePattern = Pattern.compile("([ABC])\\s([XYZ])");
        List<String> inputLines = getInputLines();
        List<RockPaperScissorsRound> rockPaperScissorsRounds = new ArrayList<>(inputLines.size());
        for (int round = 0; round < inputLines.size(); round++) {
            String line = inputLines.get(round);
            Matcher matcher = strategyGuideLinePattern.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException( //
                        "Expect strategyGuideLine at %6d matching pattern '%s', but was: '%s'" //
                                .formatted(round, strategyGuideLinePattern, line));
            }
            HandShape opponentHandShape = HandShape.valueOf(matcher.group(1).charAt(0));
            HandShape playerHandShape = HandShape.valueOf(matcher.group(2).charAt(0));
            var rockPaperScissorsRound = new RockPaperScissorsRound(round, opponentHandShape, playerHandShape);
            rockPaperScissorsRounds.add(rockPaperScissorsRound);
        }
        return rockPaperScissorsRounds;
    }

}
