package de.adventofcode.chrisgw.day03;

import java.math.BigInteger;

public record BatteryBank(String batteries) {


    public BigInteger findBestBatteryCombination(int k) {
        StringBuilder best = new StringBuilder("0".repeat(k));
        findBestBatteryCombination(0, k, new StringBuilder(k), best);
        return new BigInteger(best.toString());
    }


    private void findBestBatteryCombination(int start, int k, StringBuilder current, StringBuilder best) {
        if (k == 0) {
            if (current.compareTo(best) > 0) {
                best.setLength(0);
                best.append(current);
            }
            return;
        }

        for (int i = start; i < batteries.length(); i++) {
            current.append(batteries.charAt(i));
            if (current.compareTo(best) > 0) {
                findBestBatteryCombination(i + 1, k - 1, current, best);
            }
            current.deleteCharAt(current.length() - 1);
        }
    }

}
