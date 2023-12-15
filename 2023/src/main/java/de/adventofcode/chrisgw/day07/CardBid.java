package de.adventofcode.chrisgw.day07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

import static de.adventofcode.chrisgw.day07.CardHandType.*;
import static java.util.stream.Collectors.*;

public record CardBid(GameCard[] cards, int bid) {

    public static CardBid parseCardBid(String cardBidLine) {
        String[] split = cardBidLine.split("\\s+");
        GameCard[] cards = split[0].chars()
                .mapToObj(cardSymbol -> GameCard.valueOf((char) cardSymbol))
                .toArray(GameCard[]::new);
        int bid = Integer.parseInt(split[1]);
        return new CardBid(cards, bid);
    }


    public CardHandType type(boolean withJoker) {
        Map<GameCard, Long> cardCount = Arrays.stream(this.cards())
                .collect(groupingBy(Function.identity(), counting()));
        long jokerCount = 0L;
        if (withJoker) {
            jokerCount = cardCount.getOrDefault(GameCard.CARD_JACK, 0L);
            cardCount.remove(GameCard.CARD_JACK);
        }
        long maxKind = cardCount.entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .map(cardCount::remove)
                .orElse(0L);
        maxKind += jokerCount;
        if (maxKind == 5) {
            return FIVE_OF_A_KIND;
        }
        if (maxKind == 4) {
            return FOUR_OF_A_KIND;
        }
        boolean hasAnOtherPair = cardCount.values().stream().anyMatch(count -> count == 2);
        if (maxKind == 3) {
            if (hasAnOtherPair) {
                return FULL_HOUSE;
            } else {
                return THREE_OF_A_KIND;
            }
        }
        if (maxKind == 2) {
            if (hasAnOtherPair) {
                return TWO_PAIR;
            } else {
                return ONE_PAIR;
            }
        }
        return HIGH_CARD;
    }


    public GameCard cardAt(int index) {
        return cards[index];
    }

    public int handSize() {
        return cards.length;
    }


    @Override
    public String toString() {
        return Arrays.stream(cards()).map(GameCard::toString).collect(joining()) + " " + bid;
    }

}
