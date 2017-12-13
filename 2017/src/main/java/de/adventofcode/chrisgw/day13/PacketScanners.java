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
    private int currentLayerPosition = 0;
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


    public int calculatePacketSeverity() {
        while (hasMoreUpcomingFirewallScanner()) {
            nextPicoSecond();
        }
        return severitySum;
    }

    public boolean hasMoreUpcomingFirewallScanner() {
        FirewallScanner lastFirewallScanner = layerToFirewallScannerMap.lastEntry().getValue();
        return currentLayerPosition <= lastFirewallScanner.getLayer();
    }


    public void nextPicoSecond() {
        FirewallScanner firewallScanner = layerToFirewallScannerMap.get(currentLayerPosition);
        if (isPacketScanned(firewallScanner)) {
            severitySum += firewallScanner.getSeverity();
        }
        currentLayerPosition++;
        layerToFirewallScannerMap.values().forEach(FirewallScanner::nextScanStep);
    }

    private boolean isPacketScanned(FirewallScanner firewallScanner) {
        return firewallScanner != null && firewallScanner.getPosition() == 0;
    }


    public int getSeveritySum() {
        return severitySum;
    }

    public int getCurrentLayerPosition() {
        return currentLayerPosition;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Picosecond ").append(currentLayerPosition).append(":\n");
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
                    boolean isCurrentLayerAtRange0 = layer == currentLayerPosition && range == 0;
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
                } else if (range == 0 && layer == currentLayerPosition) {
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
