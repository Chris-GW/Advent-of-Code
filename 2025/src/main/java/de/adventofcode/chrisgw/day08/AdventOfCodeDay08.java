package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <a href="https://adventofcode.com/2025/day/8">Advent of Code - day 8</a>
 */
public class AdventOfCodeDay08 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay08(List<String> inputLines) {
        super(Year.of(2025), 8, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<Set<JunctionBox>> circutes = inputLines()
                .map(JunctionBox::parse)
                .map(junctionBox -> {
                    Set<JunctionBox> circute = new HashSet<>();
                    circute.add(junctionBox);
                    return circute;
                })
                .toList();

        int connectionCount = getInputLines().size() > 20 ? 1000 : 10;
        for (int i = 0; i < connectionCount; i++) {
            makeClosestConnection(circutes);
        }
        return 0;
    }

    private void makeClosestConnection(List<Set<JunctionBox>> circutes) {
        for (int i = 0; i < circutes.size(); i++) {
            for (int k = i + 1; k < circutes.size(); k++) {
                Set<JunctionBox> left = circutes.get(i);
                Set<JunctionBox> right = circutes.get(k);

            }
        }
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


}
