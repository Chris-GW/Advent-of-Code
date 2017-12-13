package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class PacketScannersTest {


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

        PacketScanners packetScanners = PacketScanners.parseFirewall(firewallScannerLines);
        System.out.println(packetScanners);
        while (packetScanners.hasMoreUpcomingFirewallScanner()) {
            packetScanners.nextPicoSecond();
            System.out.println(packetScanners);
        }

        int severitySum = packetScanners.getSeveritySum();
        Assert.assertEquals("Expect severitySum", expectedSeveritySum, severitySum);
    }

    @Test
    public void packetScanners_myExample() {
        String classpathResource = "/day13/PacketScanners_chrisgw.txt";
        List<String> firewallScannerLines = TestUtils.readAllLinesOfClassPathResource(classpathResource);
        int expectedSeveritySum = 1840;

        PacketScanners packetScanners = PacketScanners.parseFirewall(firewallScannerLines);
        System.out.println(packetScanners);
        while (packetScanners.hasMoreUpcomingFirewallScanner()) {
            packetScanners.nextPicoSecond();
        }

        int severitySum = packetScanners.getSeveritySum();
        Assert.assertEquals("Expect severitySum", expectedSeveritySum, severitySum);
    }

}