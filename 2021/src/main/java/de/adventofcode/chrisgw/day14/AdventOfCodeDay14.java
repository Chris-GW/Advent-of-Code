package de.adventofcode.chrisgw.day14;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * https://adventofcode.com/2021/day/14
 */
public class AdventOfCodeDay14 extends AdventOfCodePuzzleSolver<Long> {

    public AdventOfCodeDay14(List<String> inputLines) {
        super(Year.of(2021), 14, inputLines);
    }


    public Long solveFirstPart() {
        return buildPolymerTillStep(10);
    }

    public Long solveSecondPart() {
        return buildPolymerTillStep(40);
    }

    private Long buildPolymerTillStep(int targetSteps) {
        PolymerTemplate polymerTemplate = parsePolymerTemplate();
        List<PairInsertionRule> pairInsertionRules = parsePairInsertionRules();
        for (int step = 1; step <= targetSteps; step++) {
            polymerTemplate = polymerTemplate.applyPairInsertionRules(pairInsertionRules);
        }
        return polymerTemplate.calculateScore();
    }


    private PolymerTemplate parsePolymerTemplate() {
        return inputLines().findFirst().map(PolymerTemplate::new).orElseThrow();
    }

    private List<PairInsertionRule> parsePairInsertionRules() {
        return inputLines().skip(2).map(PairInsertionRule::parseRule).toList();
    }

}
