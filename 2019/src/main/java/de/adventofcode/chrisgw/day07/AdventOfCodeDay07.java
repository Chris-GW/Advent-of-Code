package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.day02.IntCodeProgram;

import java.util.*;
import java.util.stream.IntStream;


/**
 * 2019 Day 7: Amplification Circuit
 * https://adventofcode.com/2019/day/7
 */
public class AdventOfCodeDay07 {

    private List<ThrusterAmplifier> thrusterAmplifiers = new ArrayList<>(5);


    public AdventOfCodeDay07(IntCodeProgram controllerSoftware) {
        IntStream.range(0, 5).forEach(value -> thrusterAmplifiers.add(new ThrusterAmplifier(controllerSoftware)));
    }


    public int findHighestThrusterSignal() {
        int[] phaseSettings = new int[5];
        List<int[]> possibleThrusterPhaseSettings = possiblePhaseSettings(phaseSettings, 0);
        return possibleThrusterPhaseSettings.stream().mapToInt(this::runThrusterWith).max().orElseThrow();
    }

    public List<int[]> possiblePhaseSettings(int[] phaseSettings, int index) {
        List<int[]> possiblePhaseSettings = new ArrayList<>();
        if (index >= thrusterAmplifiers.size()) {
            possiblePhaseSettings.add(Arrays.copyOf(phaseSettings, phaseSettings.length));
            return possiblePhaseSettings;
        }

        for (int phaseSetting = 0; phaseSetting <= 4; phaseSetting++) {
            if (isUsedPhaseSetting(phaseSettings, phaseSetting, index)) {
                continue;
            }
            phaseSettings[index] = phaseSetting;
            possiblePhaseSettings.addAll(possiblePhaseSettings(phaseSettings, index + 1));
        }
        return possiblePhaseSettings;
    }

    private static boolean isUsedPhaseSetting(int[] phaseSettings, int phaseSetting, int index) {
        return Arrays.stream(phaseSettings, 0, index).anyMatch(value -> value == phaseSetting);
    }


    public int runThrusterWith(int[] phaseSettings) {
        int signal = 0;
        for (int i = 0; i < thrusterAmplifiers.size(); i++) {
            ThrusterAmplifier thrusterAmplifier = thrusterAmplifiers.get(i);
            int phaseSetting = phaseSettings[i];
            thrusterAmplifier.setPhaseSetting(phaseSetting);
            thrusterAmplifier.setInputSignal(signal);
            signal = thrusterAmplifier.runControllerSoftware();
        }
        return signal;
    }

}
