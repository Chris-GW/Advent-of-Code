package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


/**
 * <a href="https://adventofcode.com/2023/day/5">Advent of Code - day 5</a>
 */
public class AdventOfCodeDay05 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay05(List<String> inputLines) {
        super(Year.of(2023), 5, inputLines);
    }


    @Override
    public Long solveFirstPart() {
        String almanacText = inputLines().collect(Collectors.joining("\n"));
        List<CategoryMap> categoryMaps = CategoryMap.parseCategoryMaps(almanacText);
        LongStream seedNumbers = findSeedNumbers(almanacText);
        return findLowestLocation(categoryMaps, seedNumbers);
    }

    private static LongStream findSeedNumbers(String almanacText) {
        Pattern seedsPattern = Pattern.compile("seeds:((\\s+\\d+)*)");
        Matcher matcher = seedsPattern.matcher(almanacText);
        if (!matcher.find()) {
            throw new IllegalArgumentException(
                    "could not find pattern " + seedsPattern + " in almanac text: " + almanacText);
        }
        return Arrays.stream(matcher.group(1).trim().split("\\s+")).mapToLong(Long::parseLong);
    }

    private long findLowestLocation(List<CategoryMap> categoryMaps, LongStream seedStream) {
        return seedStream.distinct().parallel().map(value -> {
            long mappedValue = value;
            for (CategoryMap categoryMap : categoryMaps) {
                mappedValue = categoryMap.map(mappedValue);
            }
            return mappedValue;
        }).min().orElseThrow();
    }


    @Override
    public Long solveSecondPart() {
        String almanacText = inputLines().collect(Collectors.joining("\n"));
        List<CategoryMap> categoryMaps = CategoryMap.parseCategoryMaps(almanacText);
        List<SeedNumberRange> seedNumberRanges = SeedNumberRange.parseSeedNumberRanges(almanacText);

        List<CategoryMap> reversedCategoryMaps = categoryMaps.stream().map(CategoryMap::reverse).toList();
        for (long location = 0L; true; location++) {
            long mappedValue = location;
            for (int i = reversedCategoryMaps.size() - 1; i >= 0; i--) {
                CategoryMap categoryMap = reversedCategoryMaps.get(i);
                mappedValue = categoryMap.map(mappedValue);
            }

            for (SeedNumberRange seedNumberRange : seedNumberRanges) {
                if (seedNumberRange.test(mappedValue)) {
                    return location;
                }
            }
        }
    }

}
