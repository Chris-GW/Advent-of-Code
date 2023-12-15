package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Comparator;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2023/day/7">Advent of Code - day 7</a>
 */
public class AdventOfCodeDay07 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay07(List<String> inputLines) {
        super(Year.of(2023), 7, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<CardBid> rankedCardBids = inputLines()
                .map(CardBid::parseCardBid)
                .sorted(Comparator.reverseOrder())
                .toList();
        int totalWinningScore = 0;
        for (int rank = 1; rank <= rankedCardBids.size(); rank++) {
            var cardBid = rankedCardBids.get(rank - 1);
            totalWinningScore += cardBid.bid() * rank;
        }
        return totalWinningScore;
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
