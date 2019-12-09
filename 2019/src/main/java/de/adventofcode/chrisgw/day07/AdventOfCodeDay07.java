package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * 2019 Day 7: Amplification Circuit
 * https://adventofcode.com/2019/day/7
 */
public class AdventOfCodeDay07 {

    private List<ThrusterAmplifier> thrusterAmplifiers = new ArrayList<>(5);


    public AdventOfCodeDay07(IntCodeProgram controllerSoftware) {
        IntStream.range(0, 5).forEach(value -> thrusterAmplifiers.add(new ThrusterAmplifier(controllerSoftware)));
        for (int i = 1; i < thrusterAmplifiers.size(); i++) {
            ThrusterAmplifier outputAmplifier = thrusterAmplifiers.get(i);
            ThrusterAmplifier inputAmplifier = thrusterAmplifiers.get(i - 1);
            inputAmplifier.connectOutputTo(outputAmplifier);
        }
    }


    private void setThrusterPhaseSettings(int[] phaseSettings) {
        for (int i = 0; i < thrusterAmplifiers.size(); i++) {
            ThrusterAmplifier thrusterAmplifier = thrusterAmplifiers.get(i);
            thrusterAmplifier.withPhaseSetting(phaseSettings[i]);
        }
    }

    private long runThruster(int[] phaseSettings) {
        setThrusterPhaseSettings(phaseSettings);
        thrusterAmplifiers.get(0).addInputSignal(0);
        return runThruster();
    }

    private long runThruster() {
        long signal = 0;
        for (ThrusterAmplifier thrusterAmplifier : thrusterAmplifiers) {
            signal = thrusterAmplifier.runControllerSoftware();
        }
        return signal;
    }


    private List<int[]> possiblePhaseSettings(int[] phaseSettings, int index, Set<Integer> unusedPhaseSettings) {
        List<int[]> possiblePhaseSettings = new ArrayList<>();
        if (index >= thrusterAmplifiers.size()) {
            possiblePhaseSettings.add(Arrays.copyOf(phaseSettings, phaseSettings.length));
            return possiblePhaseSettings;
        }

        for (int phaseSetting : unusedPhaseSettings) {
            phaseSettings[index] = phaseSetting;
            Set<Integer> newUnusedPhaseSettings = new HashSet<>(unusedPhaseSettings);
            newUnusedPhaseSettings.remove(phaseSetting);
            possiblePhaseSettings.addAll(possiblePhaseSettings(phaseSettings, index + 1, newUnusedPhaseSettings));
        }
        return possiblePhaseSettings;
    }


    // part 01

    public long findHighestThrusterSignal() {
        int[] phaseSettings = new int[thrusterAmplifiers.size()];
        Set<Integer> unusedPhaseSettings = IntStream.range(0, thrusterAmplifiers.size())
                .boxed()
                .collect(Collectors.toSet());
        List<int[]> possibleThrusterPhaseSettings = possiblePhaseSettings(phaseSettings, 0, unusedPhaseSettings);
        return possibleThrusterPhaseSettings.stream().mapToLong(this::runThruster).max().orElseThrow();
    }


    // part 02

    public long findHighestThrusterSignalWithFeedbackLoop() {
        int[] phaseSettings = new int[thrusterAmplifiers.size()];
        Set<Integer> unusedPhaseSettings = IntStream.range(0, thrusterAmplifiers.size())
                .map(phaseSetting -> phaseSetting + 5)
                .boxed()
                .collect(Collectors.toSet());
        List<int[]> possibleThrusterPhaseSettings = possiblePhaseSettings(phaseSettings, 0, unusedPhaseSettings);
        return possibleThrusterPhaseSettings.stream().mapToLong(this::runThrusterInFeedbackLoopWith).max().orElseThrow();
    }

    public long runThrusterInFeedbackLoopWith(int[] phaseSettings) {
        setThrusterPhaseSettings(phaseSettings);
        thrusterAmplifiers.get(0).addInputSignal(0);

        // close loop
        ThrusterAmplifier lastThrusterAmplifier = thrusterAmplifiers.get(thrusterAmplifiers.size() - 1);
        ThrusterAmplifier firstThrusterAmplifier = thrusterAmplifiers.get(0);
        lastThrusterAmplifier.connectOutputTo(firstThrusterAmplifier);
        long previousSignal = runThruster();
        long signal = runThruster();
        while (previousSignal != signal) {
            previousSignal = signal;
            signal = runThruster();
        }
        return signal;
    }


}
