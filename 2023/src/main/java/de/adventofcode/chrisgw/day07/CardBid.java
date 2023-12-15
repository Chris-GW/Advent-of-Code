package de.adventofcode.chrisgw.day07;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static de.adventofcode.chrisgw.day07.CardHandType.*;
import static java.util.stream.Collectors.*;

public record CardBid(GameCard[] cards, int bid) implements Comparable<CardBid> {

    public static CardBid parseCardBid(String cardBidLine) {
        String[] split = cardBidLine.split("\\s+");
        GameCard[] cards = split[0].chars()
                .mapToObj(cardSymbol -> GameCard.valueOf((char) cardSymbol))
                .toArray(GameCard[]::new);
        int bid = Integer.parseInt(split[1]);
        return new CardBid(cards, bid);
    }

    public CardHandType type() {
        Map<GameCard, Long> cardCount = Arrays.stream(this.cards())
                .collect(groupingBy(Function.identity(), counting()));
        long maxKind = cardCount.values().stream().max(Long::compareTo).orElse(0L);
        if (maxKind == 5) {
            return FIVE_OF_A_KIND;
        }
        if (maxKind == 4) {
            return FOUR_OF_A_KIND;
        }
        if (maxKind == 3) {
            if (cardCount.values().stream().anyMatch(count -> count == 2)) {
                return FULL_HOUSE;
            } else {
                return THREE_OF_A_KIND;
            }
        }

        long pairCount = cardCount.values().stream().filter(count -> count == 2).count();
        if (pairCount == 2) {
            return TWO_PAIR;
        } else if (pairCount == 1) {
            return ONE_PAIR;
        }
        return HIGH_CARD;
    }

    @Override
    public int compareTo(CardBid otherCardHand) {
        int value = this.type().compareTo(otherCardHand.type());
        for (int i = 0; i < this.cards.length; i++) {
            if (value != 0) {
                return value;
            }
            value = this.cards[i].compareTo(otherCardHand.cards[i]);
        }
        return value;
    }

    @Override
    public String toString() {
        return Arrays.stream(cards()).map(GameCard::toString).collect(joining()) + " " + bid;
    }

}
