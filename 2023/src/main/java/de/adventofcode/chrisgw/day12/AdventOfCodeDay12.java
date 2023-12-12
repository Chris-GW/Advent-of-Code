package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.adventofcode.chrisgw.day12.AdventOfCodeDay12.SpringState.*;


/**
 * <a href="https://adventofcode.com/2023/day/12">Advent of Code - day 12</a>
 */
public class AdventOfCodeDay12 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay12(List<String> inputLines) {
        super(Year.of(2023), 12, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        return inputLines().map(SpringRecord::parse).mapToInt(SpringRecord::countArrangements).sum();
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    public record SpringRecord(SpringState[] springStates, int[] contiguousGroups) {

        public static SpringRecord parse(String line) {
            String[] split = line.split(" ");
            String springStateStr = split[0];
            SpringState[] springSates = springStateStr.chars()
                    .mapToObj(SpringState::valueOf)
                    .toArray(SpringState[]::new);
            int[] contiguousGroups = Arrays.stream(split[1].split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return new SpringRecord(springSates, contiguousGroups);
        }

        public int countArrangements() {
            return countArrangements(0);
        }

        private int countArrangements(int i) {
            if (i >= springStates.length) {
                return isValid() ? 1 : 0;
            }
            int count = 0;
            SpringState state = springStates[i];
            if (UNKNOWN.equals(state)) {
                springStates[i] = OPERATIONAL;
                count += countArrangements(i + 1);
                springStates[i] = DAMAGED;
                count += countArrangements(i + 1);
                springStates[i] = UNKNOWN;
            } else {
                count += countArrangements(i + 1);
            }
            return count;
        }

        private boolean isValid() {
            List<Integer> actualGroupSizes = new ArrayList<>();
            int groupSize = 0;
            for (int i = 0; i < springStates.length; i++) {
                if (DAMAGED.equals(springStates[i])) {
                    groupSize++;
                } else if (groupSize > 0) {
                    actualGroupSizes.add(groupSize);
                    groupSize = 0;
                } else {
                    groupSize = 0;
                }
            }
            if (groupSize > 0) {
                actualGroupSizes.add(groupSize);
            }
            if (actualGroupSizes.size() != contiguousGroups.length) {
                return false;
            }
            for (int i = 0; i < actualGroupSizes.size(); i++) {
                if (actualGroupSizes.get(i) != contiguousGroups[i]) {
                    return false;
                }
            }
            return true;
        }

    }


    public enum SpringState {
        OPERATIONAL('.'),
        DAMAGED('#'),
        UNKNOWN('?');

        private final char stateSymbol;

        SpringState(char stateSymbol) {
            this.stateSymbol = stateSymbol;
        }

        public static SpringState valueOf(int stateSymbol) {
            for (SpringState state : SpringState.values()) {
                if (state.getStateSymbol() == stateSymbol) {
                    return state;
                }
            }
            throw new IllegalArgumentException("Could not find SpringState for symbol: " + stateSymbol);
        }

        public char getStateSymbol() {
            return stateSymbol;
        }


        @Override
        public String toString() {
            return String.valueOf(stateSymbol);
        }

    }

}
