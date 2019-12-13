package de.adventofcode.chrisgw.day12;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.Combinations;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * 2019 Day 12: The N-Body Problem
 * https://adventofcode.com/2019/day/12
 */
public class AdventOfCodeDay12 {

    private long timeSteps = 0;
    private List<Pair<Moon, Moon>> moonPairs;
    private final List<Moon> moons;


    public AdventOfCodeDay12(List<Moon> moons) {
        this.moons = new ArrayList<>(moons);
        this.moonPairs = moonPairs();
    }

    public static AdventOfCodeDay12 parseMoonPositions(List<String> moonPositions) {
        List<Moon> moons = moonPositions.stream().map(Moon::parseMoonPosition).collect(Collectors.toList());
        return new AdventOfCodeDay12(moons);
    }


    public void nextTimeStep() {
        timeSteps++;
        moonPairs.forEach(moonMoonPair -> moonMoonPair.getFirst().applyGravity(moonMoonPair.getSecond()));
        moons.forEach(Moon::applyVelocity);
    }

    private List<Pair<Moon, Moon>> moonPairs() {
        Combinations moonCombinations = new Combinations(this.moons.size(), 2);
        return StreamSupport.stream(moonCombinations.spliterator(), false).map(moonCombination -> {
            Moon firstMoon = this.moons.get(moonCombination[0]);
            Moon secondMoon = this.moons.get(moonCombination[1]);
            return Pair.create(firstMoon, secondMoon);
        }).collect(Collectors.toList());
    }


    public int totalEnergyInSystem() {
        return moons().mapToInt(Moon::totalEnergy).sum();
    }


    public long findNeededStepsForSystemSameState() {
        long[] periodsForCoordinate = new long[3];

        while (!Arrays.stream(periodsForCoordinate).allMatch(period -> period > 0)) {
            this.nextTimeStep();
            for (int i = 0; i < 3; i++) {
                if (periodsForCoordinate[i] == 0 && allMoonsAtStartPositionForCoordinate(i)) {
                    periodsForCoordinate[i] = getTimeSteps();
                }
            }
        }

        return Arrays.stream(periodsForCoordinate).reduce(ArithmeticUtils::lcm).orElseThrow();
    }

    private boolean allMoonsAtStartPositionForCoordinate(int i) {
        return moons().allMatch(moon -> moon.isAtStartPoisitionForCoordinate(i));
    }


    public Stream<Moon> moons() {
        return moons.stream();
    }

    public long getTimeSteps() {
        return timeSteps;
    }


    @Override
    public String toString() {
        String stepHeader = String.format("After %2d steps:%n", timeSteps);
        String moonsStr = moons().map(Moon::toString).collect(Collectors.joining("\n"));
        return stepHeader + moonsStr;
    }

}
