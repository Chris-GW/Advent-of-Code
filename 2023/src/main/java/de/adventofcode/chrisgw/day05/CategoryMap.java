package de.adventofcode.chrisgw.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.adventofcode.chrisgw.day05.MappedNumberRange.MAPPED_NUMBER_RANGE_PATTERN;


public record CategoryMap(Category sourceCategory, Category destinationCategory,
                          Collection<MappedNumberRange> mappedNumberRanges) {

    public static final Pattern MAP_PATTERN = Pattern.compile(
            Category.captureGroup() + "-to-" + Category.captureGroup() + " map:" //
                    + "((\\s*" + MAPPED_NUMBER_RANGE_PATTERN + ")+)");


    public static List<CategoryMap> parseCategoryMaps(String almanacText) {
        List<CategoryMap> categoryMaps = new ArrayList<>();
        Matcher matcher = MAP_PATTERN.matcher(almanacText);
        while (matcher.find()) {
            Category sourceCategory = Category.valueOf(matcher.group(1).toUpperCase());
            Category destinationCategory = Category.valueOf(matcher.group(2).toUpperCase());
            List<MappedNumberRange> mappedNumberRanges = Arrays.stream(matcher.group(3).trim().split("\n"))
                    .map(MappedNumberRange::parseMappedNumberRange)
                    .toList();
            var categoryMap = new CategoryMap(sourceCategory, destinationCategory, mappedNumberRanges);
            categoryMaps.add(categoryMap);
        }
        return categoryMaps;
    }


    public long map(long number) {
        return mappedNumberRanges().stream()
                .filter(mappedNumberRange -> mappedNumberRange.test(number))
                .findAny()
                .map(mappedNumberRange -> mappedNumberRange.applyAsLong(number))
                .orElse(number);
    }

    public boolean isSourceCategory(Category category) {
        return sourceCategory.equals(category);
    }

}
