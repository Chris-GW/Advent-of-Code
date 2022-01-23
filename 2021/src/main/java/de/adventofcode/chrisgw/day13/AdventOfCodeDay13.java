package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://adventofcode.com/2021/day/13
 */
public class AdventOfCodeDay13 extends AdventOfCodePuzzleSolver<Integer> {

    private final Set<PaperDot> dots;
    private final List<PaperFoldInstruction> paperFoldInstructions;

    public AdventOfCodeDay13(List<String> inputLines) {
        super(Year.of(2021), 13, inputLines);
        dots = inputLines.stream()
                .takeWhile(StringUtils::isNotBlank)
                .map(PaperDot::parseDot)
                .collect(Collectors.toSet());
        paperFoldInstructions = inputLines.stream()
                .dropWhile(StringUtils::isNotBlank)
                .skip(1) // skip empty line
                .map(PaperFoldInstruction::parsePaperFoldInstruction)
                .toList();
    }


    public Integer solveFirstPart() {
        PaperFoldInstruction firstFoldInstruction = paperFoldInstructions.get(0);
        applyFoldInstruction(firstFoldInstruction);
        return dots.size();
    }

    private void applyFoldInstruction(PaperFoldInstruction foldInstruction) {
        Set<PaperDot> visibleDots = dots().map(foldInstruction).collect(Collectors.toSet());
        dots.clear();
        dots.addAll(visibleDots);
    }

    
    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }

    public Stream<PaperDot> dots() {
        return dots.stream();
    }


    public boolean hasDotAt(int x, int y) {
        return hasDotAt(new PaperDot(x, y));
    }

    public boolean hasDotAt(PaperDot dot) {
        return dots.contains(dot);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int maxX = dots().mapToInt(PaperDot::x).max().orElse(10);
        int maxY = dots().mapToInt(PaperDot::y).max().orElse(10);
        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                if (hasDotAt(x, y)) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
