package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


/**
 * <a href="https://adventofcode.com/2025/day/2">Advent of Code - day 2</a>
 */
public class AdventOfCodeDay02 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay02(List<String> inputLines) {
        super(Year.of(2025), 2, inputLines);
    }


    private List<ProductIdRange> readProductIdRanges() {
        String[] split = getInputLines().getFirst().split(",");
        List<ProductIdRange> productIdRanges = new ArrayList<>(split.length);
        for (String productIdRangeStr : split) {
            var productIdRange = ProductIdRange.parse(productIdRangeStr);
            productIdRanges.add(productIdRange);
        }
        return productIdRanges;
    }


    @Override
    public Long solveFirstPart() {
        List<ProductIdRange> productIdRanges = readProductIdRanges();
        return productIdRanges.stream()
                .flatMapToLong(ProductIdRange::productIds)
                .filter(this::isInvalidProductId)
                .sum();
    }

    @Override
    public Long solveSecondPart() {
        List<ProductIdRange> productIdRanges = readProductIdRanges();
        return productIdRanges.stream()
                .flatMapToLong(ProductIdRange::productIds)
                .filter(this::isInvalidProductId2)
                .sum();
    }


    private boolean isInvalidProductId(long id) {
        String idStr = String.valueOf(id);
        int repeatedLength = idStr.length() / 2;
        return idStr.length() % 2 == 0 && isRepeatedSequence(idStr, repeatedLength);
    }

    private boolean isInvalidProductId2(long id) {
        String idStr = String.valueOf(id);
        return IntStream.rangeClosed(1, idStr.length() / 2)
                .anyMatch(repeatedLength -> isRepeatedSequence(idStr, repeatedLength));
    }


    private boolean isRepeatedSequence(String idStr, int repeatedLength) {
        if (idStr.length() % repeatedLength != 0) {
            return false;
        }
        String repeatedPart = idStr.substring(0, repeatedLength);
        int startIndex = repeatedLength;

        while (startIndex + repeatedLength <= idStr.length()) {
            int endIndex = startIndex + repeatedLength;
            String part = idStr.substring(startIndex, endIndex);
            if (!repeatedPart.equals(part)) {
                return false;
            }
            startIndex += repeatedLength;
        }
        return true;
    }


}
