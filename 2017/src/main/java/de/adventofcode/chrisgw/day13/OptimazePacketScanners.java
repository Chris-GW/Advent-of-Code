package de.adventofcode.chrisgw.day13;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OptimazePacketScanners {

    private static final Pattern FIREWALL_SCAN_LAYER_LINE_PATTERN = Pattern.compile("(\\d+)\\s*:\\s*(\\d+)");

    private OptimazePacketScanner[] firewallScanners;


    private OptimazePacketScanners(OptimazePacketScanner[] firewallScanners) {
        this.firewallScanners = firewallScanners;
    }

    public static OptimazePacketScanners parseFirewall(List<String> firewallLayerLines) {
        List<OptimazePacketScanner> parsedFirewallScanners = new LinkedList<>();
        for (String firewallLayerLine : firewallLayerLines) {
            Matcher matcher = FIREWALL_SCAN_LAYER_LINE_PATTERN.matcher(firewallLayerLine);
            if (matcher.matches()) {
                int layer = Integer.parseInt(matcher.group(1));
                int range = Integer.parseInt(matcher.group(2));
                OptimazePacketScanner firewallScanner = new OptimazePacketScanner(layer, range);
                parsedFirewallScanners.add(firewallScanner);
            }
        }

        int maxLayer = parsedFirewallScanners.stream().mapToInt(OptimazePacketScanner::getLayer).max().orElse(-1) + 1;
        OptimazePacketScanner[] firewallScanners = new OptimazePacketScanner[maxLayer];
        for (OptimazePacketScanner firewallScanner : parsedFirewallScanners) {
            firewallScanners[firewallScanner.getLayer()] = firewallScanner;
        }
        return new OptimazePacketScanners(firewallScanners);
    }


    public int calculateSeverity() {
        int severity = 0;
        for (int picoseconds = 0; picoseconds < firewallScanners.length; picoseconds++) {
            System.out.println(toString(picoseconds, 0));
            OptimazePacketScanner firewallScanner = firewallScanners[picoseconds];
            if (firewallScanner != null && firewallScanner.isOnTop(picoseconds)) {
                severity += firewallScanner.getSeverity();
            }
        }
        return severity;
    }

    public int calculateDelay() {
        for (int delay = 0; true; delay++) {
            boolean isPackedUnscanned = true;
            for (int picoseconds = 0; picoseconds < firewallScanners.length; picoseconds++) {
                OptimazePacketScanner firewallScanner = firewallScanners[picoseconds];
                if (firewallScanner != null && firewallScanner.isOnTop(picoseconds + delay)) {
                    isPackedUnscanned = false;
                    break;
                }
            }
            if (isPackedUnscanned) {
                return delay;
            }
        }
    }


    public OptimazePacketScanner[] getFirewallScanners() {
        return firewallScanners;
    }

    public String toString(int picoseconds, int delay) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Picosecond ").append(picoseconds).append(" (delay = ").append(delay).append("):\n");
        int lastLayerWithFirewallScanner = firewallScanners.length - 1;
        for (int layer = 0; layer <= lastLayerWithFirewallScanner; layer++) {
            sb.append(" ").append(layer).append("  ");
        }
        sb.append("\n");

        int currentPosition = picoseconds - delay;
        OptionalInt maxRange = Arrays.stream(firewallScanners)
                .filter(Objects::nonNull)
                .mapToInt(OptimazePacketScanner::getRange)
                .max();
        for (int range = 0; range < maxRange.orElse(0); range++) {
            for (int layer = 0; layer <= lastLayerWithFirewallScanner; layer++) {
                OptimazePacketScanner firewallScanner = firewallScanners[layer];
                if (firewallScanner != null) {
                    boolean isCurrentLayerAtRange0 = layer == currentPosition && range == 0;
                    if (isCurrentLayerAtRange0 && firewallScanner.isOnTop(picoseconds)) {
                        sb.append("(S) ");
                    } else if (isCurrentLayerAtRange0) {
                        sb.append("( ) ");
                    } else if (firewallScanner.getPosition(picoseconds) == range) {
                        sb.append("[S] ");
                    } else if (firewallScanner.getRange() > range) {
                        sb.append("[ ] ");
                    } else {
                        sb.append("    ");
                    }
                } else if (range == 0 && layer == currentPosition) {
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

    @Override
    public String toString() {
        return toString(0, 0);
    }

}
