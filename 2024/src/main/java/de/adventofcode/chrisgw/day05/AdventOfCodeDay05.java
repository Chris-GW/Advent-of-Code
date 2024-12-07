package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.function.Predicate;


/**
 * <a href="https://adventofcode.com/2024/day/5">Advent of Code - day 5</a>
 */
public class AdventOfCodeDay05 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay05(List<String> inputLines) {
        super(Year.of(2024), 5, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<PagePrintOrderRule> pagePrintOrderRules = parseAllPagePrintOrderRules();
        List<PageUpdate> pageUpdates = parseAllPageUpdates();
        return pageUpdates.stream()
                .filter(pageUpdate -> pageUpdate.isValid(pagePrintOrderRules))
                .mapToInt(PageUpdate::getMiddlePageNumber)
                .sum();
    }

    private List<PagePrintOrderRule> parseAllPagePrintOrderRules() {
        return inputLines()
                .takeWhile(Predicate.not(String::isBlank))
                .map(PagePrintOrderRule::parse)
                .toList();
    }

    private List<PageUpdate> parseAllPageUpdates() {
        return inputLines()
                .dropWhile(s -> !s.isBlank())
                .skip(1)
                .map(PageUpdate::parse)
                .toList();
    }


    @Override
    public Integer solveSecondPart() {
        List<PagePrintOrderRule> pagePrintOrderRules = parseAllPagePrintOrderRules();
        List<PageUpdate> pageUpdates = parseAllPageUpdates();
        return pageUpdates.stream()
                .filter(pageUpdate -> !pageUpdate.isValid(pagePrintOrderRules))
                .map(pageUpdate -> pageUpdate.correctOrder(pagePrintOrderRules))
                .mapToInt(PageUpdate::getMiddlePageNumber)
                .sum();
    }


}
