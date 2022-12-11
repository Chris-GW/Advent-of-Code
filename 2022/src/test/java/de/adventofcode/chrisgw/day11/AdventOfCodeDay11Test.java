package de.adventofcode.chrisgw.day11;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay11Test {

    private static final List<String> inputLinesExample = List.of("" + //
                    "Monkey 0:", //
            "  Starting items: 79, 98", //
            "  Operation: new = old * 19", //
            "  Test: divisible by 23", //
            "    If true: throw to monkey 2", //
            "    If false: throw to monkey 3", //
            "", //
            "Monkey 1:", //
            "  Starting items: 54, 65, 75, 74", //
            "  Operation: new = old + 6", //
            "  Test: divisible by 19", //
            "    If true: throw to monkey 2", //
            "    If false: throw to monkey 0", //
            "", //
            "Monkey 2:", //
            "  Starting items: 79, 60, 97", //
            "  Operation: new = old * old", //
            "  Test: divisible by 13", //
            "    If true: throw to monkey 1", //
            "    If false: throw to monkey 3", //
            "", //
            "Monkey 3:", //
            "  Starting items: 74", //
            "  Operation: new = old + 3", //
            "  Test: divisible by 17", //
            "    If true: throw to monkey 0", //
            "    If false: throw to monkey 1");


    @Test
    void solveAocPuzzle_firstPart_example() {
        BigInteger result = new AdventOfCodeDay11(inputLinesExample).solveFirstPart();
        assertEquals(BigInteger.valueOf(10605), result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        BigInteger result = new AdventOfCodeDay11(inputLines).solveFirstPart();
        assertEquals(BigInteger.valueOf(57348), result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        BigInteger result = new AdventOfCodeDay11(inputLinesExample).solveSecondPart();
        assertEquals(BigInteger.valueOf(2713310158L), result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        BigInteger result = new AdventOfCodeDay11(inputLines).solveSecondPart();
        assertEquals(BigInteger.valueOf(1), result, "secondPart myPuzzleInput");
    }

}
