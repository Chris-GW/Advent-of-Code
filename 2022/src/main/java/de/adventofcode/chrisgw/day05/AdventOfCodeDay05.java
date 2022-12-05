package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * https://adventofcode.com/2022/day/5
 */
public class AdventOfCodeDay05 extends AdventOfCodePuzzleSolver<String> {


    public AdventOfCodeDay05(List<String> inputLines) {
        super(Year.of(2022), 5, inputLines);
    }

    public String solveFirstPart() {
        List<CrateStack> crateStacks = parseCrateStacksFromInput();
        List<RearrangementProcedure> rearrangementProcedures = parseRearrangementProceduresFromInput();

        CrateMover crateMover = new CrateMover9000(crateStacks);
        rearrangementProcedures.forEach(crateMover::runRearrangementProcedure);
        return crateMover.topCrateCodes();
    }

    public String solveSecondPart() {
        List<CrateStack> crateStacks = parseCrateStacksFromInput();
        List<RearrangementProcedure> rearrangementProcedures = parseRearrangementProceduresFromInput();

        CrateMover crateMover = new CrateMover9001(crateStacks);
        rearrangementProcedures.forEach(crateMover::runRearrangementProcedure);
        return crateMover.topCrateCodes();
    }


    public List<CrateStack> parseCrateStacksFromInput() {
        List<CrateStack> crateStacks = new ArrayList<>();
        List<String> inputLines = getInputLines();
        int columnWidth = "[C] ".length();

        for (String line : inputLines) {
            for (int stackIndex = 1, crateLetterIndex = 1; //
                 crateLetterIndex < line.length(); //
                 stackIndex++, crateLetterIndex += columnWidth) {
                char crateLetter = line.charAt(crateLetterIndex);
                if (Character.isSpaceChar(crateLetter)) {
                    continue;
                } else if (Character.isDigit(crateLetter)) {
                    return crateStacks;
                }

                for (int i = crateStacks.size(); i <= stackIndex; i++) {
                    crateStacks.add(new CrateStack(i));
                }
                StackableCargoCrate crate = new StackableCargoCrate(crateLetter);
                crateStacks.get(stackIndex).addLastCrate(crate);
            }
        }
        return crateStacks;
    }


    public List<RearrangementProcedure> parseRearrangementProceduresFromInput() {
        return inputLines().dropWhile(StringUtils::isNotBlank)
                .skip(1)
                .map(RearrangementProcedure::parseRearrangementProcedure)
                .toList();
    }

}
