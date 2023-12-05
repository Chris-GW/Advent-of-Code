package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static de.adventofcode.chrisgw.day05.Category.LOCATION;


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
        List<CategoryRecord> records = findSeedNumbers(almanacText);

        long lowestLocationNumber = Long.MAX_VALUE;
        for (CategoryRecord record : records) {
            while (!LOCATION.equals(record.category())) {
                record = mapRecord(categoryMaps, record);
            }
            if (record.number() < lowestLocationNumber) {
                lowestLocationNumber = record.number();
            }
        }

        return lowestLocationNumber;
    }

    private CategoryRecord mapRecord(List<CategoryMap> categoryMaps, CategoryRecord record) {
        Optional<CategoryMap> fittingCategoryMap = categoryMaps.stream()
                .filter(categoryMap -> categoryMap.isSourceCategory(record.category()))
                .findAny();
        return fittingCategoryMap.map(categoryMap -> categoryMap.map(record)).orElse(record);
    }

    private static List<CategoryRecord> findSeedNumbers(String almanacText) {
        Pattern seedsPattern = Pattern.compile("seeds:((\\s+\\d+)*)");
        Matcher matcher = seedsPattern.matcher(almanacText);
        if (matcher.find()) {
            return Arrays.stream(matcher.group(1).trim().split("\\s+"))
                    .mapToLong(Long::parseLong)
                    .mapToObj(number -> new CategoryRecord(Category.SEED, number))
                    .toList();
        }
        throw new IllegalArgumentException(
                "could not find pattern " + seedsPattern + " in almanac text: " + almanacText);
    }


    @Override
    public Long solveSecondPart() {
        // TODO solveSecondPart
        return 0L;
    }


}
