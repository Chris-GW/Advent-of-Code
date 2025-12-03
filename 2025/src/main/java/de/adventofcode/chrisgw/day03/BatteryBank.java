package de.adventofcode.chrisgw.day03;

public record BatteryBank(String batteries) {


    public int turnOnLargestJoltageBatteries() {
        int maxJoltage = 0;
        for (int i = 0; i < batteries.length(); i++) {
            int firstJoltage = Character.getNumericValue(batteries.charAt(i));
            for (int k = i + 1; k < batteries.length(); k++) {
                int secondJoltage = Character.getNumericValue(batteries.charAt(k));
                int totalJoltage = firstJoltage * 10 + secondJoltage;
                maxJoltage = Math.max(totalJoltage, maxJoltage);
            }
        }
        return maxJoltage;
    }

}
