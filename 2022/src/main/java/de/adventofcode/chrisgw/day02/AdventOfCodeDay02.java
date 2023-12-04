package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * <a href="https://adventofcode.com/2022/day/2">Advent of Code 2022 - day 2</a>
 */
public class AdventOfCodeDay02 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay02(List<String> inputLines) {
        super(Year.of(2022), 2, inputLines);
    }


    public Integer solveFirstPart() {
        return inputLinesAsRockPaperScissorsRounds().stream()
                .mapToInt(RockPaperScissorsRound::scoreAccordingToFixedHandShape)
                .sum();
    }

    public Integer solveSecondPart() {
        return inputLinesAsRockPaperScissorsRounds().stream()
                .mapToInt(RockPaperScissorsRound::scoreAccordingToFixedOutcome)
                .sum();
    }


    public List<RockPaperScissorsRound> inputLinesAsRockPaperScissorsRounds() {
        Pattern strategyGuideLinePattern = Pattern.compile("([ABC])\\s([XYZ])");
        List<String> inputLines = getInputLines();
        List<RockPaperScissorsRound> rockPaperScissorsRounds = new ArrayList<>(inputLines.size());

        for (int round = 0; round < inputLines.size(); round++) {
            String line = inputLines.get(round);
            var matcher = strategyGuideLinePattern.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException( //
                        "Expect strategyGuideLine at %6d matching pattern '%s', but was: '%s'" //
                                .formatted(round, strategyGuideLinePattern, line));
            }

            HandShape opponentHandShape = HandShape.valueOfCode(matcher.group(1).charAt(0));
            char strategyCode = matcher.group(2).charAt(0);
            var rockPaperScissorsRound = new RockPaperScissorsRound(round, opponentHandShape, strategyCode);
            rockPaperScissorsRounds.add(rockPaperScissorsRound);
        }
        return rockPaperScissorsRounds;
    }

}
