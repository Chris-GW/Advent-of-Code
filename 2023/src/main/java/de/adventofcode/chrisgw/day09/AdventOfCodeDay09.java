package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2023/day/9">Advent of Code - day 9</a>
 */
public class AdventOfCodeDay09 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay09(List<String> inputLines) {
        super(Year.of(2023), 9, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        return inputLines().map(HistoryRecord::parseHistoryRecord).mapToInt(HistoryRecord::predictNextValue).sum();
    }


    @Override
    public Integer solveSecondPart() {
        return inputLines().map(HistoryRecord::parseHistoryRecord).mapToInt(HistoryRecord::predictPreviousValue).sum();
    }


    public record HistoryRecord(int[] values) {

        public static HistoryRecord parseHistoryRecord(String line) {
            Pattern numberPattern = Pattern.compile("\\s+");
            int[] values = numberPattern.splitAsStream(line).mapToInt(Integer::parseInt).toArray();
            return new HistoryRecord(values);
        }


        public int predictNextValue() {
            List<HistoryRecord> steps = Stream.iterate(this,
                    HistoryRecord::isNotAllZero, HistoryRecord::newDifferenceSequence).toList();

            int previousDifference = 0;
            for (int step = steps.size() - 1; step >= 0; step--) {
                int valueToLeft = steps.get(step).lastValue();
                previousDifference = valueToLeft + previousDifference;
            }
            return previousDifference;
        }

        public int predictPreviousValue() {
            List<HistoryRecord> steps = Stream.iterate(this,
                    HistoryRecord::isNotAllZero, HistoryRecord::newDifferenceSequence).toList();

            int previousDifference = 0;
            for (int step = steps.size() - 1; step >= 0; step--) {
                int valueToRight = steps.get(step).firstValue();
                previousDifference = valueToRight - previousDifference;
            }
            return previousDifference;
        }


        private HistoryRecord newDifferenceSequence() {
            int[] differenceSequence = new int[values.length - 1];
            for (int i = 0; i < differenceSequence.length; i++) {
                int leftValue = values[i];
                int rightValue = values[i + 1];
                differenceSequence[i] = rightValue - leftValue;
            }
            return new HistoryRecord(differenceSequence);
        }


        private boolean isNotAllZero() {
            return !Arrays.stream(values).allMatch(value -> value == 0);
        }


        private int firstValue() {
            return values[0];
        }

        private int lastValue() {
            return values[values.length - 1];
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof HistoryRecord that)) {
                return false;
            }
            return new EqualsBuilder().append(values(), that.values()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(values()).toHashCode();
        }

        @Override
        public String toString() {
            return Arrays.toString(values);
        }

    }

}
