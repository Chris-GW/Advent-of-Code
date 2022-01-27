package de.adventofcode.chrisgw.day14;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * https://adventofcode.com/2021/day/14
 */
public class AdventOfCodeDay14 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay14(List<String> inputLines) {
        super(Year.of(2021), 14, inputLines);
    }

    public Integer solveFirstPart() {
        PolymerTemplate polymerTemplate = parsePolymerTemplate();
        List<PairInsertionRule> pairInsertionRules = parsePairInsertionRules();

        System.out.println("Template:     " + polymerTemplate);
        for (int step = 1; step <= 10; step++) {
            polymerTemplate = nextPolymerBuildStep(polymerTemplate, pairInsertionRules);
            System.out.println("After step " + step + ": " + polymerTemplate);
        }
        return polymerTemplate.calculateScore();
    }

    private PolymerTemplate nextPolymerBuildStep(PolymerTemplate polymerTemplate,
            List<PairInsertionRule> pairInsertionRules) {
        StringBuilder polymer = new StringBuilder(polymerTemplate.polymerTemplate());

        int insertOffset = 1;
        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            for (PairInsertionRule pairInsertionRule : pairInsertionRules) {
                if (pairInsertionRule.matchesAt(polymerTemplate, i)) {
                    polymer.insert(i + insertOffset, pairInsertionRule.insertedElement());
                    insertOffset++;
                    break;
                }
            }
        }
        return new PolymerTemplate(polymer.toString());
    }


    private PolymerTemplate parsePolymerTemplate() {
        return inputLines().limit(1).findFirst().map(PolymerTemplate::new).orElseThrow();
    }

    private List<PairInsertionRule> parsePairInsertionRules() {
        return inputLines().skip(2).map(PairInsertionRule::parseRule).toList();
    }


    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }


}
