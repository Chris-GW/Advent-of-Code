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
    public Long solveFirstPart() {
        long[] numbers = inputLines().mapToLong(Long::parseLong).toArray();
        var mixingEncryption = new MixingEncryption(numbers);
        while (mixingEncryption.hasNextMixing()) {
            mixingEncryption.doMixing();
        }
        return calculateGroveCoordinates(mixingEncryption);
    }

    private static long calculateGroveCoordinates(MixingEncryption mixingEncryption) {
        long n1000 = mixingEncryption.nthNode(1_000);
        long n2000 = mixingEncryption.nthNode(2_000);
        long n3000 = mixingEncryption.nthNode(3_000);
        return n1000 + n2000 + n3000;
    }


    @Override
    public Long solveSecondPart() {
        long decryptionKey = 811589153L;
        long[] numbers = inputLines().mapToLong(Long::parseLong).map(operand -> operand * decryptionKey).toArray();
        var mixingEncryption = new MixingEncryption(numbers);

        for (int i = 0; i < 10; i++) {
            while (mixingEncryption.hasNextMixing()) {
                mixingEncryption.doMixing();
            }
            mixingEncryption.reset();
        }
        return calculateGroveCoordinates(mixingEncryption);
    }

}
