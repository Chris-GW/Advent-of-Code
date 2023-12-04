package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2023/day/4">Advent of Code - day 4</a>
 */
public class AdventOfCodeDay04 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay04(List<String> inputLines) {
        super(Year.of(2023), 4, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        return inputLines().map(ScratchCard::parseScratchCard).mapToInt(ScratchCard::points).sum();
    }


    @Override
    public Integer solveSecondPart() {
        int cardCount = 0;
        List<ScratchCard> scratchCards = inputLines().map(ScratchCard::parseScratchCard).toList();
        Deque<ScratchCard> cardStack = new ArrayDeque<>(scratchCards);
        while (!cardStack.isEmpty()) {
            cardCount++;
            var scratchCard = cardStack.removeFirst();
            int matchingNumber = scratchCard.countMatchingNumber();
            for (int i = 0; i < matchingNumber; i++) {
                int copiedCardIndex = scratchCard.cardNumber() + i;
                var copiedCard = scratchCards.get(copiedCardIndex);
                cardStack.add(copiedCard);
            }
        }
        return cardCount;
    }


}
