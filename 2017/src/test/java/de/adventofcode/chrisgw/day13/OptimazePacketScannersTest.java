package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class OptimazePacketScannersTest {

    @Test
    public void testOptimazePacketScanner_range_2() {
        OptimazePacketScanner packetScanner = new OptimazePacketScanner(0, 2);
        List<Integer> expectedPositions = Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1);

        for (int picosecond = 0; picosecond < expectedPositions.size(); picosecond++) {
            int expectedPosition = expectedPositions.get(picosecond);
            int position = packetScanner.getPosition(picosecond);
            Assert.assertEquals("expect postion for picosecond: " + picosecond, expectedPosition, position);
        }
    }

    @Test
    public void testOptimazePacketScanner_range_3() {
        OptimazePacketScanner packetScanner = new OptimazePacketScanner(0, 3);
        List<Integer> expectedPositions = Arrays.asList(0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2);

        for (int picosecond = 0; picosecond < expectedPositions.size(); picosecond++) {
            int expectedPosition = expectedPositions.get(picosecond);
            int position = packetScanner.getPosition(picosecond);
            Assert.assertEquals("expect postion for picosecond: " + picosecond, expectedPosition, position);
        }
    }

    @Test
    public void testOptimazePacketScanner_range_4() {
        OptimazePacketScanner packetScanner = new OptimazePacketScanner(0, 4);
        List<Integer> expectedPositions = Arrays.asList(0, 1, 2, 3, 2, 1, 0, 1, 2, 3, 2, 1, 0, 1, 2, 3, 2, 1, 0, 1, 2);

        for (int picosecond = 0; picosecond < expectedPositions.size(); picosecond++) {
            int expectedPosition = expectedPositions.get(picosecond);
            int position = packetScanner.getPosition(picosecond);
            Assert.assertEquals("expect postion for picosecond: " + picosecond, expectedPosition, position);
        }
    }


    @Test
    public void packetScanners_example() {
        // @formatter:off
        List<String> firewallScannerLines = Arrays.asList(
                "0: 3" ,
                "1: 2",
                "4: 4",
                "6: 4");
        // @formatter:on
        int expectedSeveritySum = 0 * 3 + 6 * 4;

        OptimazePacketScanners packetScanners = OptimazePacketScanners.parseFirewall(firewallScannerLines);
        int severitySum = packetScanners.calculateSeverity();
        Assert.assertEquals("Expect severitySum", expectedSeveritySum, severitySum);
    }

    @Test
    public void packetScanners_myExample() {
        String classpathResource = "/day13/PacketScanners_chrisgw.txt";
        List<String> firewallScannerLines = TestUtils.readAllLinesOfClassPathResource(classpathResource);
        int expectedSeveritySum = 1840;

        OptimazePacketScanners packetScanners = OptimazePacketScanners.parseFirewall(firewallScannerLines);
        int severitySum = packetScanners.calculateSeverity();

        Assert.assertEquals("Expect severitySum", expectedSeveritySum, severitySum);
    }


    // --- part 2

    @Test
    public void packetScanners_part2_example() {
        // @formatter:off
        List<String> firewallScannerLines = Arrays.asList(
                "0: 3" ,
                "1: 2",
                "4: 4",
                "6: 4");
        // @formatter:on
        int expectedPacketDelay = 10;

        OptimazePacketScanners packetScanners = OptimazePacketScanners.parseFirewall(firewallScannerLines);
        int packetDelay = packetScanners.calculateDelay();

        int neededPicoseconds = packetDelay + packetScanners.getFirewallScanners().length;
        for (int picoseconds = packetDelay; picoseconds <= neededPicoseconds; picoseconds++) {
            System.out.println(packetScanners.toString(picoseconds, packetDelay));
        }

        Assert.assertEquals("Expect packet delay", expectedPacketDelay, packetDelay);
    }

    @Test
    public void packetScanners_part2_myTask() {
        String classpathResource = "/day13/PacketScanners_chrisgw.txt";
        List<String> firewallScannerLines = TestUtils.readAllLinesOfClassPathResource(classpathResource);
        int expectedPacketDelay = 3850260;

        OptimazePacketScanners packetScanners = OptimazePacketScanners.parseFirewall(firewallScannerLines);
        int packetDelay = packetScanners.calculateDelay();

        Assert.assertEquals("Expect packet delay", expectedPacketDelay, packetDelay);
    }


}