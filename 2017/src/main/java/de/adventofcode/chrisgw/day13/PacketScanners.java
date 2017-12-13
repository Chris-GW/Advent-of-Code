package de.adventofcode.chrisgw.day13;

import java.util.List;
import java.util.NavigableMap;
import java.util.OptionalInt;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <h1><a href="https://adventofcode.com/2017/day/13>Day 13: Packet Scanners</a></h1>
 * <pre>
 * Description to long :P
 * </pre>
 */
public class PacketScanners {

    private static final Pattern FIREWALL_SCAN_LAYER_LINE_PATTERN = Pattern.compile("(\\d+)\\s*:\\s*(\\d+)");

    private NavigableMap<Integer, FirewallScanner> layerToFirewallScannerMap;
    private int packetDelay = 0;
    private int picoseconds = 0;
    private int severitySum = 0;


    private PacketScanners(NavigableMap<Integer, FirewallScanner> layerToFirewallScannerMap) {
        this.layerToFirewallScannerMap = layerToFirewallScannerMap;
    }

    public static PacketScanners parseFirewall(List<String> firewallLayerLines) {
        NavigableMap<Integer, FirewallScanner> layerToFirewallScannerMap = new TreeMap<>();
        for (String firewallLayerLine : firewallLayerLines) {
            Matcher matcher = FIREWALL_SCAN_LAYER_LINE_PATTERN.matcher(firewallLayerLine);
            if (matcher.matches()) {
                int layer = Integer.parseInt(matcher.group(1));
                int range = Integer.parseInt(matcher.group(2));
                FirewallScanner firewallScanner = new FirewallScanner(layer, range);
                layerToFirewallScannerMap.put(layer, firewallScanner);
            }
        }
        return new PacketScanners(layerToFirewallScannerMap);
    }


    public int findNeededPacketDelayWhichPassesAllFirewallScanners() {
        for (int delay = 0; true; delay++) {
            reset();
            setPacketDelay(delay);

            boolean wasPackedScanned = false;
            while (hasMoreUpcomingFirewallScanner()) {
                nextPicoSecond();
                if (isPacketScanned()) {
                    wasPackedScanned = true;
                    break;
                }
            }
            if (!wasPackedScanned) {
                return delay;
            }
        }
    }

    public void reset() {
        layerToFirewallScannerMap.values().forEach(FirewallScanner::reset);
        picoseconds = 0;
        severitySum = 0;
    }


    public int calculatePacketSeverity() {
        while (hasMoreUpcomingFirewallScanner()) {
            nextPicoSecond();
        }
        return severitySum;
    }

    public boolean hasMoreUpcomingFirewallScanner() {
        FirewallScanner lastFirewallScanner = layerToFirewallScannerMap.lastEntry().getValue();
        return getCurrentLayerPosition() <= lastFirewallScanner.getLayer();
    }


    public void nextPicoSecond() {
        FirewallScanner firewallScanner = layerToFirewallScannerMap.get(getCurrentLayerPosition());
        if (isPacketScanned()) {
            severitySum += firewallScanner.getSeverity();
        }
        picoseconds++;
        layerToFirewallScannerMap.values().forEach(FirewallScanner::nextScanStep);
    }


    private boolean isPacketScanned() {
        FirewallScanner firewallScanner = layerToFirewallScannerMap.get(getCurrentLayerPosition());
        return firewallScanner != null && firewallScanner.getPosition() == 0;
    }


    public int getSeveritySum() {
        return severitySum;
    }

    public int getCurrentLayerPosition() {
        return picoseconds - packetDelay;
    }


    public void setPacketDelay(int packetDelay) {
        this.packetDelay = packetDelay;
    }

    public int getPacketDelay() {
        return packetDelay;
    }

    public int getPicoseconds() {
        return picoseconds;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Picosecond ").append(picoseconds).append(":\n");
        int lastLayerWithFirewallScanner = layerToFirewallScannerMap.lastKey();
        for (int layer = 0; layer <= lastLayerWithFirewallScanner; layer++) {
            sb.append(" ").append(layer).append("  ");
        }
        sb.append("\n");

        OptionalInt maxRange = layerToFirewallScannerMap.values().stream().mapToInt(FirewallScanner::getRange).max();
        for (int range = 0; range < maxRange.orElse(0); range++) {
            for (int layer = 0; layer <= lastLayerWithFirewallScanner; layer++) {
                FirewallScanner firewallScanner = layerToFirewallScannerMap.get(layer);
                if (firewallScanner != null) {
                    boolean isCurrentLayerAtRange0 = layer == getCurrentLayerPosition() && range == 0;
                    if (isCurrentLayerAtRange0 && firewallScanner.getPosition() == 0) {
                        sb.append("(S) ");
                    } else if (isCurrentLayerAtRange0) {
                        sb.append("( ) ");
                    } else if (firewallScanner.getPosition() == range) {
                        sb.append("[S] ");
                    } else if (firewallScanner.getRange() > range) {
                        sb.append("[ ] ");
                    } else {
                        sb.append("    ");
                    }
                } else if (range == 0 && layer == getCurrentLayerPosition()) {
                    sb.append("(.) ");
                } else if (range == 0) {
                    sb.append("... ");
                } else {
                    sb.append("    ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
