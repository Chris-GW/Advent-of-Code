package de.adventofcode.chrisgw.day20;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2022/day/20">Advent of Code - day 20</a>
 */
public class AdventOfCodeDay20 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay20(List<String> inputLines) {
        super(Year.of(2022), 20, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        int[] numbers = inputLines().mapToInt(Integer::parseInt).toArray();
        var mixingEncryption = new MixingEncryption(numbers);
        while (mixingEncryption.hasNextMixing()) {
            mixingEncryption.doMixing();
        }
        return calculateGroveCoordinates(mixingEncryption);
    }

    private static int calculateGroveCoordinates(MixingEncryption mixingEncryption) {
        int n1000 = mixingEncryption.nthNode(1_000);
        int n2000 = mixingEncryption.nthNode(2_000);
        int n3000 = mixingEncryption.nthNode(3_000);
        return n1000 + n2000 + n3000;
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
