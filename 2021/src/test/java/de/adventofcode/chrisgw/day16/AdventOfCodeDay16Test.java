package de.adventofcode.chrisgw.day16;

import org.junit.Ignore;
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
        assertThat("typeId", bitsPacket.typeId(), is(BitsPacketType.LITERAL_VALUE));
        assertThat("literalValue", bitsPacket.literalValue(), is(BigInteger.valueOf(2021)));
    }

    @Test
    public void solveAocPuzzle_firstPart_example02() {
        List<String> inputLines = List.of("38006F45291200");
        BitsPacket bitsPacket = new AdventOfCodeDay16(inputLines).readBitsPacket();
        assertThat("version", bitsPacket.version(), is(1));
        assertThat("typeId", bitsPacket.typeId(), is(BitsPacketType.LESS_THAN));

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
        assertThat("typeId", bitsPacket.typeId(), is(BitsPacketType.MAXIMUM));

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
        long result = new AdventOfCodeDay16(inputLines).solveFirstPart();
        assertThat("versionSum", result, is(16L));
    }

    @Test
    public void solveAocPuzzle_firstPart_example05() {
        List<String> inputLines = List.of("620080001611562C8802118E34");
        long result = new AdventOfCodeDay16(inputLines).solveFirstPart();
        assertThat("versionSum", result, is(12L));
    }

    @Test
    public void solveAocPuzzle_firstPart_example06() {
        List<String> inputLines = List.of("C0015000016115A2E0802F182340");
        long result = new AdventOfCodeDay16(inputLines).solveFirstPart();
        assertThat("versionSum", result, is(23L));
    }

    @Test
    public void solveAocPuzzle_firstPart_example07() {
        List<String> inputLines = List.of("A0016C880162017C3686B18A3D4780");
        long result = new AdventOfCodeDay16(inputLines).solveFirstPart();
        assertThat("versionSum", result, is(31L));
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay16.txt");
        long result = new AdventOfCodeDay16(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 945L, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example01() {
        List<String> inputLines = List.of("C200B40A82");
        long result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart example", 3L, result);
    }

    @Test
    @Ignore
    public void solveAocPuzzle_secondPart_example02() {
        List<String> inputLines = List.of("04005AC33890");
        long result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart example", 54L, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example03() {
        List<String> inputLines = List.of("880086C3E88112");
        long result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart example", 7L, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example04() {
        List<String> inputLines = List.of("CE00C43D881120");
        long result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart example", 9L, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example05() {
        List<String> inputLines = List.of("D8005AC2A8F0");
        long result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart example", 1L, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example06() {
        List<String> inputLines = List.of("F600BC2D8F");
        long result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart example", 0L, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example07() {
        List<String> inputLines = List.of("9C005AC2F8F0");
        long result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart example", 0L, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example08() {
        List<String> inputLines = List.of("9C0141080250320F1802104A08");
        long result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart example", 1L, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay16.txt");
        long result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 10637009915279L, result);
    }

}
