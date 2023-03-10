package de.adventofcode.chrisgw.day18;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


/**
 * <a href="https://adventofcode.com/2022/day/18">Advent of Code - day 18</a>
 */
public class AdventOfCodeDay18 extends AdventOfCodePuzzleSolver {


    public AdventOfCodeDay18(List<String> inputLines) {
        super(Year.of(2022), 18, inputLines);
    }


    public Integer solveFirstPart() {
        Set<LavaDroplet> lavaDroplets = inputAsLavaDroplets();
        int exposedSides = lavaDroplets.size() * 6;

        for (LavaDroplet lavaDroplet : lavaDroplets) {
            long coveredSides = lavaDroplet.neighborLavaDroplets().filter(lavaDroplets::contains).count();
            exposedSides -= coveredSides;
        }

        return exposedSides;
    }

    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    private Set<LavaDroplet> inputAsLavaDroplets() {
        return inputLines().map(LavaDroplet::parseLavaDroplet).collect(Collectors.toSet());
    }


    public record LavaDroplet(int x, int y, int z) {

        public static LavaDroplet parseLavaDroplet(String positionStr) {
            String[] split = positionStr.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int z = Integer.parseInt(split[2]);
            return new LavaDroplet(x, y, z);
        }


        public Stream<LavaDroplet> neighborLavaDroplets() {
            Builder<LavaDroplet> neighborLavaDroplets = Stream.builder();

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        int changeAbsolute = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
                        if (changeAbsolute != 1) {
                            continue;
                        }
                        int x = this.x() + dx;
                        int y = this.y() + dy;
                        int z = this.z() + dz;
                        var neighborLavaDroplet = new LavaDroplet(x, y, z);
                        neighborLavaDroplets.add(neighborLavaDroplet);
                    }
                }
            }
            return neighborLavaDroplets.build();
        }

    }


}
