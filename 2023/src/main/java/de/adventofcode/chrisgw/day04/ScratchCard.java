package de.adventofcode.chrisgw.day04;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public record ScratchCard(int cardNumber, int[] winningNumbers, int[] ownedNumbers) {

    public static final Pattern SCRATCH_CARD_PATTERN = Pattern.compile(
            "Card\\s+(?<cardNumber>\\d+):(?<winningNumbers>(?:\\s+\\d+)+)\\s+\\|(?<ownedNumbers>(?:\\s+\\d+)+)");


    public static ScratchCard parseScratchCard(String cardLine) {
        Matcher matcher = SCRATCH_CARD_PATTERN.matcher(cardLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "expect scratch card matching " + SCRATCH_CARD_PATTERN + ", but was: " + cardLine);
        }
        int cardNumber = Integer.parseInt(matcher.group("cardNumber"));
        String winningNumbersStr = matcher.group("winningNumbers").trim();
        String ownedNumbersStr = matcher.group("ownedNumbers").trim();
        int[] winningNumbers = Arrays.stream(winningNumbersStr.split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] ownedNumbers = Arrays.stream(ownedNumbersStr.split("\\s+")).mapToInt(Integer::parseInt).toArray();
        return new ScratchCard(cardNumber, winningNumbers, ownedNumbers);
    }


    public int points() {
        return IntStream.rangeClosed(1, countMatchingNumber())
                .reduce(((accumulator, right) -> accumulator * 2))
                .orElse(0);
    }

    public int countMatchingNumber() {
        return Math.toIntExact(Arrays.stream(winningNumbers()).filter(this::containsOwnedNumber).count());
    }

    private boolean containsOwnedNumber(int number) {
        return Arrays.stream(ownedNumbers).anyMatch(value -> value == number);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScratchCard that)) {
            return false;
        }
        return new EqualsBuilder().append(cardNumber, that.cardNumber)
                .append(winningNumbers, that.winningNumbers)
                .append(ownedNumbers, that.ownedNumbers)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(cardNumber).append(winningNumbers).append(ownedNumbers).toHashCode();
    }


    @Override
    public String toString() {
        return String.valueOf(cardNumber());
    }

}
