package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Year;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * https://adventofcode.com/2021/day/11
 */
public class AdventOfCodeDay11 extends AdventOfCodePuzzleSolver<Integer> {

    public static final int FLASH_ENERGY_LEVEL = 10;

    private final Octopus[][] octopusGrid;


    public AdventOfCodeDay11(List<String> inputLines) {
        super(Year.of(2021), 11, inputLines);
        octopusGrid = parseOctopusGrid(inputLines);
    }

    private Octopus[][] parseOctopusGrid(List<String> inputLines) {
        Octopus[][] octopusGrid = new Octopus[inputLines.size()][];
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);
            octopusGrid[y] = new Octopus[line.length()];

            for (int x = 0; x < line.length(); x++) {
                int energyLevel = line.charAt(x) - '0';
                octopusGrid[y][x] = new Octopus(x, y, energyLevel);
            }
        }
        return octopusGrid;
    }


    @Override
    public Integer solveFirstPart() {
        // How many total flashes are there after 100 steps?
        int totalFlashCount = 0;
        for (int step = 0; step < 100; step++) {
            totalFlashCount += nextStepFlashCount();
        }
        return totalFlashCount;
    }

    private int nextStepFlashCount() {
        int flashCount = 0;
        // First, the energy level of each octopus increases by 1.
        octopuses().forEach(Octopus::increaseEnergyLevel);

        // Then, any octopus with an energy level greater than 9 flashes. This
        // increases the energy level of all adjacent octopuses by 1, including
        // octopuses that are diagonally adjacent. If this causes an octopus to
        // have an energy level greater than 9, it also flashes. This process
        // continues as long as new octopuses keep having their energy level
        // increased beyond 9. (An octopus can only flash at most once per step.)
        Queue<Octopus> flashingOctopuses = new ArrayDeque<>();
        octopuses().filter(Octopus::isFlashing).forEach(flashingOctopuses::offer);
        while (!flashingOctopuses.isEmpty()) {
            Octopus octopus = flashingOctopuses.poll();
            octopus.flash()
                    .filter(Predicate.not(flashingOctopuses::contains))
                    .forEach(flashingOctopuses::add);
            flashCount++;
        }

        // Finally, any octopus that flashed during this step has its energy
        // level set to 0, as it used all of its energy to flash.
        octopuses().filter(Octopus::hasFlashed).forEach(Octopus::resetEnergyLevel);
        return flashCount;
    }


    @Override
    public Integer solveSecondPart() {
        // What is the first step during which all octopuses flash?
        int step;
        for (step = 0; !allWithEnergyLevel(); step++) {
            nextStepFlashCount();
        }
        return step;
    }

    public boolean allWithEnergyLevel() {
        Integer anyEnergyLevel = octopuses().findAny()
                .map(Octopus::getEnergyLevel)
                .orElse(0);
        return octopuses().mapToInt(Octopus::getEnergyLevel)
                .allMatch(anyEnergyLevel::equals);
    }


    public Octopus octopusAt(int x, int y) {
        if (0 <= y && y < octopusGrid.length &&
                0 <= x && x < octopusGrid[y].length) {
            return octopusGrid[y][x];
        }
        return null;
    }

    public Stream<Octopus> octopuses() {
        return Arrays.stream(octopusGrid).flatMap(Arrays::stream);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < octopusGrid.length; y++) {
            for (int x = 0; x < octopusGrid[y].length; x++) {
                Octopus octopus = octopusAt(x, y);
                sb.append(octopus.getEnergyLevel());
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    @Data
    @AllArgsConstructor
    public class Octopus {

        private final int x;
        private final int y;
        private int energyLevel;


        public int increaseEnergyLevel() {
            return ++energyLevel;
        }

        public void resetEnergyLevel() {
            energyLevel = 0;
        }


        public boolean isFlashing() {
            return energyLevel == FLASH_ENERGY_LEVEL;
        }

        public boolean hasFlashed() {
            return energyLevel > FLASH_ENERGY_LEVEL;
        }


        public Stream<Octopus> flash() {
            adjacentOctopuses().forEach(Octopus::increaseEnergyLevel);
            return adjacentOctopuses().filter(Octopus::isFlashing);
        }

        public Stream<Octopus> adjacentOctopuses() {
            Stream.Builder<Octopus> adjacentOctopuses = Stream.builder();
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    Octopus octopus = octopusAt(x + dx, y + dy);
                    if (octopus != null) {
                        adjacentOctopuses.add(octopus);
                    }
                }
            }
            return adjacentOctopuses.build();
        }

    }

}
