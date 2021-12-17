package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * https://adventofcode.com/2021/day/6
 */
public class AdventOfCodeDay06 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay06(List<String> inputLines) {
        super(Year.of(2021), 6, inputLines);
    }


    public Integer solveFirstPart() {
        List<Lanternfish> lanternfishs = new ArrayList<>(parseLanternfishs());
        for (int day = 1; day <= 80; day++) {
            List<Lanternfish> newLanternfishs = lanternfishs.stream()
                    .map(Lanternfish::nextDayWithBirth)
                    .flatMap(Optional::stream)
                    .toList();
            lanternfishs.addAll(newLanternfishs);
        }
        return lanternfishs.size();
    }


    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }


    private List<Lanternfish> parseLanternfishs() {
        return inputLines().flatMap(line -> Arrays.stream(line.split(",")))
                .mapToInt(Integer::parseInt)
                .mapToObj(Lanternfish::new)
                .toList();
    }


    public static class Lanternfish {

        private int internalTimer;


        public Lanternfish() {
            this(8);
        }

        public Lanternfish(int internalTimer) {
            if (0 < internalTimer && internalTimer <= 8) {
                this.internalTimer = internalTimer;
            } else {
                throw new IllegalArgumentException(
                        "internal birth timer must be between [0:9], but was: " + internalTimer);
            }
        }


        public Optional<Lanternfish> nextDayWithBirth() {
            if (internalTimer-- == 0) {
                internalTimer = 6;
                return Optional.of(new Lanternfish());
            }
            return Optional.empty();
        }


        @Override
        public String toString() {
            return String.valueOf(internalTimer);
        }

    }

}
