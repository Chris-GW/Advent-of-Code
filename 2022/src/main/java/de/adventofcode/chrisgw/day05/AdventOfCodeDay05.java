package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.function.Predicate;

import static de.adventofcode.chrisgw.day05.RearrangementProcedure.REARRANGEMENT_PROCEDURE_PATTERN;


/**
 * https://adventofcode.com/2022/day/5
 */
public class AdventOfCodeDay05 extends AdventOfCodePuzzleSolver {


    public AdventOfCodeDay05(List<String> inputLines) {
        super(Year.of(2022), 5, inputLines);
    }

    public String solveFirstPart() {
        CrateMover crateMover = new CrateMover9000();
        return runRearrangementProcedures(crateMover);
    }

    public String solveSecondPart() {
        CrateMover crateMover = new CrateMover9001();
        return runRearrangementProcedures(crateMover);
    }

    private String runRearrangementProcedures(CrateMover crateMover) {
        crateMover.parseInitStacks(getInputLines());
        List<RearrangementProcedure> rearrangementProcedures = parseRearrangementProceduresFromInput();
        crateMover.runRearrangementProcedures(rearrangementProcedures);
        return crateMover.topCrateCodes();
    }


    public List<RearrangementProcedure> parseRearrangementProceduresFromInput() {
        Predicate<String> isRearrangementProcedure = REARRANGEMENT_PROCEDURE_PATTERN.asPredicate();
        return inputLines().filter(isRearrangementProcedure)
                .map(RearrangementProcedure::parseRearrangementProcedure)
                .toList();
    }

}
