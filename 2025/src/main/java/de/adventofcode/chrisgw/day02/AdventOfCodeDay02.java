package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2025/day/2">Advent of Code - day 2</a>
 */
public class AdventOfCodeDay02 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay02(List<String> inputLines) {
        super(Year.of(2025), 2, inputLines);
    }


    @Override
    public Long solveFirstPart() {
        List<ProductIdRange> productIdRanges = readProductIdRanges();
        return productIdRanges.stream()
                .mapToLong(ProductIdRange::invalidProductIdSum)
                .sum();
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
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
