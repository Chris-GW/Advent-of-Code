package de.adventofcode.chrisgw.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PrimitiveIterator.OfLong;
import java.util.function.LongPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public record SeedNumberRange(long rangeStartNumber, long rangeEndNumber) implements LongPredicate {


    public static final Pattern SEEDS_PATTERN = Pattern.compile("seeds:((\\s+\\d+)*)");

    public static List<SeedNumberRange> parseSeedNumberRanges(String almanacText) {
        Matcher matcher = SEEDS_PATTERN.matcher(almanacText);
        if (!matcher.find()) {
            throw new IllegalArgumentException(
                    "could not find pattern " + SEEDS_PATTERN + " in almanac text: " + almanacText);
        }
        List<SeedNumberRange> seedNumberRanges = new ArrayList<>();
        OfLong seedNumbers = Arrays.stream(matcher.group(1).trim().split("\\s+")).mapToLong(Long::parseLong).iterator();
        while (seedNumbers.hasNext()) {
            long rangeStartNumber = seedNumbers.nextLong();
            long rangeLength = seedNumbers.hasNext() ? seedNumbers.nextLong() : 0L;
            long rangeEndNumber = rangeStartNumber + rangeLength;
            seedNumberRanges.add(new SeedNumberRange(rangeStartNumber, rangeEndNumber));
        }
        return seedNumberRanges;
    }


    @Override
    public boolean test(long number) {
        return rangeStartNumber <= number && number < rangeEndNumber;
    }


    @Override
    public String toString() {
        return "[%d-%d)".formatted(rangeStartNumber, rangeEndNumber);
    }

}
