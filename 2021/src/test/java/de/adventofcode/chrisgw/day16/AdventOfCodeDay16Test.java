package de.adventofcode.chrisgw.day16;

import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class AdventOfCodeDay16Test {


    @Test
    public void solveAocPuzzle_firstPart_example01() {
        List<String> inputLines = List.of("D2FE28");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket();
        assertThat("version", bitsPacket.version(), is(6));
        assertThat("typeId", bitsPacket.typeId(), is(4));
        assertThat("literalValue", bitsPacket.literalValue(), is(BigInteger.valueOf(2021)));
    }

    @Test
    public void solveAocPuzzle_firstPart_example02() {
        List<String> inputLines = List.of("38006F45291200");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket();
        assertThat("version", bitsPacket.version(), is(1));
        assertThat("typeId", bitsPacket.typeId(), is(6));

        List<BigInteger> expectedLiteralValues = List.of(BigInteger.valueOf(10), BigInteger.valueOf(20));
        List<BitsPacket> subPackets = bitsPacket.subPackets();
        assertThat("subPackets.size", subPackets, hasSize(expectedLiteralValues.size()));
        for (int i = 0; i < expectedLiteralValues.size(); i++) {
            BigInteger expectedLiteralValue = expectedLiteralValues.get(i);
            BitsPacket subPacket = subPackets.get(i);
            BigInteger literalValue = subPacket.literalValue();
            assertThat("subPackets[" + i + "].literalValue", literalValue, is(expectedLiteralValue));
        }
    }

    @Test
    public void solveAocPuzzle_firstPart_example03() {
        List<String> inputLines = List.of("EE00D40C823060");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket();
        assertThat("version", bitsPacket.version(), is(7));
        assertThat("typeId", bitsPacket.typeId(), is(3));

        List<BigInteger> expectedLiteralValues = List.of(BigInteger.ONE, BigInteger.TWO, BigInteger.valueOf(3));
        List<BitsPacket> subPackets = bitsPacket.subPackets();
        assertThat("subPackets.size", subPackets, hasSize(expectedLiteralValues.size()));
        for (int i = 0; i < expectedLiteralValues.size(); i++) {
            BigInteger expectedLiteralValue = expectedLiteralValues.get(i);
            BitsPacket subPacket = subPackets.get(i);
            BigInteger literalValue = subPacket.literalValue();
            assertThat("subPackets[" + i + "].literalValue", literalValue, is(expectedLiteralValue));
        }
    }

    @Test
    public void solveAocPuzzle_firstPart_example04() {
        List<String> inputLines = List.of("8A004A801A8002F478");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket();
        assertThat("versionSum", bitsPacket.versionSum(), is(16));
    }

    @Test
    public void solveAocPuzzle_firstPart_example05() {
        List<String> inputLines = List.of("620080001611562C8802118E34");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket();
        assertThat("versionSum", bitsPacket.versionSum(), is(12));
    }

    @Test
    public void solveAocPuzzle_firstPart_example06() {
        List<String> inputLines = List.of("C0015000016115A2E0802F182340");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket();
        assertThat("versionSum", bitsPacket.versionSum(), is(23));
    }

    @Test
    public void solveAocPuzzle_firstPart_example07() {
        List<String> inputLines = List.of("A0016C880162017C3686B18A3D4780");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket();
        assertThat("versionSum", bitsPacket.versionSum(), is(31));
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay16.txt");
        int result = new AdventOfCodeDay16(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 945, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example01() {
        List<String> inputLines = List.of("A0016C880162017C3686B18A3D4780");
        int result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay16.txt");
        int result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
