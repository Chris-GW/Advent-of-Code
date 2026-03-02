package de.adventofcode.chrisgw.day10;

import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record MachineButton(int[] wiring) {

    public static MachineButton parseButtonWiring(String wiringString) {
        wiringString = wiringString.substring(1, wiringString.length() - 1);
        String[] split = wiringString.split(",");
        int[] wiring = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        return new MachineButton(wiring);
    }


    public void press(BitSet indicatorLights) {
        for (int lightIndex : wiring) {
            indicatorLights.flip(lightIndex);
        }
    }


    public int wiredButtonCount() {
        return wiring.length;
    }


    @Override
    public String toString() {
        return Arrays.stream(wiring)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",", "(", ")"));
    }

}
