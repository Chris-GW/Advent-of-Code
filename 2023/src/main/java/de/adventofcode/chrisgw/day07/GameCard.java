package de.adventofcode.chrisgw.day07;

public enum GameCard {
    CARD_ACE('A'), //
    CARD_KING('K'), //
    CARD_QUEEN('Q'), //
    CARD_JACK('J'), //
    CARD_TEN('T'), //
    CARD_NINE('9'), //
    CARD_EIGHT('8'), //
    CARD_SEVEN('7'), //
    CARD_SIX('6'), //
    CARD_FIVE('5'), //
    CARD_FOUR('4'), //
    CARD_THREE('3'), //
    CARD_TWO('2'), //
    CARD_ONE('1'); //

    private final char cardSymbol;

    GameCard(char cardSymbol) {
        this.cardSymbol = cardSymbol;
    }

    public static GameCard valueOf(char cardSymbol) {
        for (GameCard card : GameCard.values()) {
            if (card.getCardSymbol() == cardSymbol) {
                return card;
            }
        }
        throw new IllegalArgumentException("Could not find GameCard with symbol: " + cardSymbol);
    }


    public char getCardSymbol() {
        return cardSymbol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.cardSymbol);
    }

}
