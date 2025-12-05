package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.stream.IntStream;


/**
 * <a href="https://adventofcode.com/2015/day/2">Advent of Code - day 2</a>
 */
public class AdventOfCodeDay02 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay02(List<String> inputLines) {
        super(Year.of(2015), 2, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        return inputLines()
                .map(PresentBox::parse)
                .mapToInt(PresentBox::calculateNeededPaper)
                .sum();
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    public record PresentBox(int l, int w, int h) {

        public static PresentBox parse(String line) {
            String[] split = line.split("x");
            int l = Integer.parseInt(split[0]);
            int w = Integer.parseInt(split[1]);
            int h = Integer.parseInt(split[2]);
            return new PresentBox(l, w, h);
        }


        public int calculateSurfaceArea() {
            return (2 * l * w) + (2 * w * h) + (2 * h * l);
        }


        public int calculateNeededPaper() {
            int extraPaper = IntStream.builder()
                    .add(l * w)
                    .add(w * h)
                    .add(h * l)
                    .build()
                    .min()
                    .orElse(0);
            return calculateSurfaceArea() + extraPaper;
        }

    }

}
