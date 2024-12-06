package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.MatchResult;
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
        List<MatchResult> foundInstructions = collectInstructions();
        int totalResult = 0;

        for (MatchResult foundInstruction : foundInstructions) {
            if (foundInstruction.group().startsWith("mul(")) {
                totalResult += executeMult(foundInstruction);
            }
        }
        return totalResult;
    }


    @Override
    public Integer solveSecondPart() {
        List<MatchResult> foundInstructions = collectInstructions();
        int totalResult = 0;
        boolean mulEnabled = true;

        for (MatchResult foundInstruction : foundInstructions) {
            if (mulEnabled && foundInstruction.group().startsWith("mul(")) {
                totalResult += executeMult(foundInstruction);
            } else if (foundInstruction.group().startsWith("don't(")) {
                mulEnabled = false;
            } else if (foundInstruction.group().startsWith("do(")) {
                mulEnabled = true;
            }
        }
        return totalResult;
    }


    private List<MatchResult> collectInstructions() {
        Pattern doPattern = Pattern.compile("do\\(\\)");
        Pattern dontPattern = Pattern.compile("don't\\(\\)");
        Pattern mulPattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

        List<MatchResult> foundInstructions = new ArrayList<>();
        String input = inputLines().collect(Collectors.joining());
        doPattern.matcher(input).results().forEach(foundInstructions::add);
        dontPattern.matcher(input).results().forEach(foundInstructions::add);
        mulPattern.matcher(input).results().forEach(foundInstructions::add);
        foundInstructions.sort(Comparator.comparingInt(MatchResult::start));
        return foundInstructions;
    }


    private static int executeMult(MatchResult foundInstruction) {
        int x = Integer.parseInt(foundInstruction.group(1));
        int y = Integer.parseInt(foundInstruction.group(2));
        return x * y;
    }

}
