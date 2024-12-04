package de.adventofcode.chrisgw;

import java.time.Year;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;


public abstract class AdventOfCodePuzzleSolver {

    private final AdventOfCodePuzzle aocPuzzle;
    private final List<String> inputLines;


    protected AdventOfCodePuzzleSolver(Year year, int day, List<String> inputLines) {
        this(new AdventOfCodePuzzle(year, day), inputLines);
    }

    protected AdventOfCodePuzzleSolver(AdventOfCodePuzzle aocPuzzle, List<String> inputLines) {
        this.aocPuzzle = requireNonNull(aocPuzzle);
        this.inputLines = requireNonNull(inputLines);
    }


    public abstract Object solveFirstPart();

    public abstract Object solveSecondPart();


    public Stream<String> inputLines() {
        return getInputLines().stream();
    }

    public List<String> getInputLines() {
        return inputLines;
    }


    public AdventOfCodePuzzle getAocPuzzle() {
        return aocPuzzle;
    }

}
