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
    public Long solveFirstPart() {
        return totalWinningCount(compareCardBidsWithJoker(false));
    }

    @Override
    public Long solveSecondPart() {
        return totalWinningCount(compareCardBidsWithJoker(true));
    }


    private long totalWinningCount(Comparator<CardBid> cardBidComparator) {
        List<CardBid> rankedCardBids = inputLines()
                .map(CardBid::parseCardBid)
                .sorted(cardBidComparator)
                .toList();

        for (CardBid rankedCardBid : rankedCardBids) {
            System.out.println(rankedCardBid + "\t--> " + rankedCardBid.type(true));
        }

        long totalWinningScore = 0;
        for (int rank = 1; rank <= rankedCardBids.size(); rank++) {
            var cardBid = rankedCardBids.get(rank - 1);
            totalWinningScore += (long) cardBid.bid() * rank;
        }
        return totalWinningScore;
    }

    public static Comparator<CardBid> compareCardBidsWithJoker(boolean withJoker) {
        final Comparator<GameCard> gameCardComparator;
        if (withJoker) {
            gameCardComparator = GameCard.withJokerComparator();
        } else {
            gameCardComparator = Comparator.naturalOrder();
        }
        return (cardBid, otherCardBid) -> {
            int value = otherCardBid.type(withJoker).compareTo(cardBid.type(withJoker));
            for (int i = 0; i < otherCardBid.handSize(); i++) {
                if (value != 0) {
                    return value;
                }
                value = gameCardComparator.compare(otherCardBid.cardAt(i), cardBid.cardAt(i));
            }
            return value;
        };
    }

}
