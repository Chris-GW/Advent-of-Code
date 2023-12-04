package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


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
        // TODO solveSecondPart
        return 0;
    }


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
            return IntStream.rangeClosed(1, countMachtingNumber())
                    .reduce(((accumulator, right) -> accumulator * 2))
                    .orElse(0);
        }

        private int countMachtingNumber() {
            return Math.toIntExact(Arrays.stream(winningNumbers()).filter(this::ownsNumber).count());
        }

        private boolean ownsNumber(int number) {
            return Arrays.stream(ownedNumbers()).anyMatch(value -> value == number);
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
            return new HashCodeBuilder(17, 37).append(cardNumber)
                    .append(winningNumbers)
                    .append(ownedNumbers)
                    .toHashCode();
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Card ").append(String.format("%3d", cardNumber())).append(":");
            for (int winningNumber : winningNumbers()) {
                sb.append(String.format(" %2d", winningNumber));
            }
            sb.append(" |");
            for (int ownedNumber : ownedNumbers()) {
                sb.append(String.format(" %2d", ownedNumber));
            }
            return sb.toString();
        }
    }

}
