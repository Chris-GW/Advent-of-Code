package de.adventofcode.chrisgw.day05;

import java.util.function.LongPredicate;
import java.util.function.LongUnaryOperator;
import java.util.regex.Pattern;


public record MappedNumberRange(long destinationRangeStart, long sourceRangeStart, long rangeLength)
        implements LongPredicate, LongUnaryOperator {


    public static final Pattern MAPPED_NUMBER_RANGE_PATTERN = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(\\d+)");

    public static MappedNumberRange parseMappedNumberRange(String line) {
        var matcher = MAPPED_NUMBER_RANGE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "expected MappedNumberRange matching pattern " + MAPPED_NUMBER_RANGE_PATTERN + ", but was: "
                            + line);
        }
        long destinationRangeStart = Long.parseLong(matcher.group(1));
        long sourceRangeStart = Long.parseLong(matcher.group(2));
        long rangeLength = Long.parseLong(matcher.group(3));
        return new MappedNumberRange(destinationRangeStart, sourceRangeStart, rangeLength);
    }

    @Override
    public boolean test(long number) {
        return sourceRangeStart <= number && number <= sourceRangeStart + rangeLength;
    }

    @Override
    public long applyAsLong(long number) {
        return destinationRangeStart + (number - sourceRangeStart);
    }


    @Override
    public String toString() {
        return destinationRangeStart + " " + sourceRangeStart + " " + rangeLength;
    }

}
