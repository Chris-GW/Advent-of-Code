package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;


/**
 * <a href="https://adventofcode.com/2023/day/6">Advent of Code - day 6</a>
 */
public class AdventOfCodeDay06 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay06(List<String> inputLines) {
        super(Year.of(2023), 6, inputLines);
    }


    @Override
    public Long solveFirstPart() {
        List<ToyBoatRace> races = parseToyBoatRaces(getInputLines());
        return races.stream()
                .mapToLong(ToyBoatRace::countPossibleWaysOfWinning)
                .reduce((left, right) -> left * right)
                .orElse(0L);
    }


    @Override
    public Long solveSecondPart() {
        List<ToyBoatRace> races = parseToyBoatRaces(getInputLines());
        ToyBoatRace onlyRace = races.stream().reduce(this::fixKerningProblem).orElseThrow();
        return onlyRace.countPossibleWaysOfWinning();
    }

    private ToyBoatRace fixKerningProblem(ToyBoatRace leftRace, ToyBoatRace rightRace) {
        long raceDuration = Long.parseLong(leftRace.raceDuration() + "" + rightRace.raceDuration());
        long recordDistance = Long.parseLong(leftRace.recordDistance() + "" + rightRace.recordDistance());
        return new ToyBoatRace(raceDuration, recordDistance);
    }


    private List<ToyBoatRace> parseToyBoatRaces(List<String> inputLines) {
        int[] raceTimes = new int[0];
        int[] recordDistances = new int[0];
        for (String line : inputLines) {
            if (line.startsWith("Time:")) {
                raceTimes = Arrays.stream(line.substring("Time:".length()).trim().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } else if (line.startsWith("Distance:")) {
                recordDistances = Arrays.stream(line.substring("Distance:".length()).trim().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }

        List<ToyBoatRace> races = new ArrayList<>(raceTimes.length);
        for (int i = 0; i < raceTimes.length; i++) {
            int raceDuration = raceTimes[i];
            int recordDistance = recordDistances[i];
            races.add(new ToyBoatRace(raceDuration, recordDistance));
        }
        return races;
    }


    public record ToyBoatRace(long raceDuration, long recordDistance) {

        public long countPossibleWaysOfWinning() {
            return LongStream.range(1, raceDuration).filter(this::isWinningCharge).count();
        }

        private boolean isWinningCharge(long chargeDuration) {
            long movingDuration = raceDuration - chargeDuration;
            long distance = movingDuration * chargeDuration;
            return distance > recordDistance;
        }

    }

}
