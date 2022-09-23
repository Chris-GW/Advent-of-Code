package de.adventofcode.chrisgw.day16;

import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class AdventOfCodeDay16Test {

    private static final List<String> inputLinesExample = List.of("");

    @Test
    public void solveAocPuzzle_firstPart_example01() {
        List<String> inputLines = List.of("D2FE28");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket(0);
        assertThat("version", bitsPacket.version(), is(6));
        assertThat("typeId", bitsPacket.typeId(), is(4));
        assertThat("BitsLiteralPacket", bitsPacket, instanceOf(BitsLiteralPacket.class));
        BitsLiteralPacket bitsLiteralPacket = (BitsLiteralPacket) bitsPacket;
        assertThat("literalValue", bitsLiteralPacket.getLiteralValue(), is(BigInteger.valueOf(2021)));
    }

    @Test
    public void solveAocPuzzle_firstPart_example02() {
        List<String> inputLines = List.of("38006F45291200");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket(0);
        assertThat("version", bitsPacket.version(), is(1));
        assertThat("typeId", bitsPacket.typeId(), is(6));
        assertThat("BitsLiteralPacket", bitsPacket, instanceOf(BitsLiteralPacket.class));
        BitsLiteralPacket bitsLiteralPacket = (BitsLiteralPacket) bitsPacket;
        assertThat("literalValue", bitsLiteralPacket.getLiteralValue(), is(BigInteger.valueOf(2021)));
    }



    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay16.txt");
        int result = new AdventOfCodeDay16(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay16(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay16.txt");
        int result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
