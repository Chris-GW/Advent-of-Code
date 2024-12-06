package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;

import static de.adventofcode.chrisgw.day04.AdventOfCodeDay04.WordDirection.DIAGONAL;
import static de.adventofcode.chrisgw.day04.AdventOfCodeDay04.WordDirection.DIAGONAL_2;
import static de.adventofcode.chrisgw.day04.AdventOfCodeDay04.WordDirection.values;


/**
 * <a href="https://adventofcode.com/2024/day/4">Advent of Code - day 4</a>
 */
public class AdventOfCodeDay04 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay04(List<String> inputLines) {
        super(Year.of(2024), 4, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        int wordCounter = 0;
        for (int y = 0; y < getInputLines().size(); y++) {
            String line = getInputLines().get(y);

            for (int x = 0; x < line.length(); x++) {
                for (WordDirection direction : values()) {
                    boolean xmas = isWord(x, y, direction, "XMAS");
                    if (xmas || isWord(x, y, direction, "SAMX")) {
                        wordCounter++;
                    }
                }
            }
        }
        return wordCounter;
    }


    private boolean isWord(int x, int y, WordDirection direction, String word) {
        for (int i = 0; i < word.length(); i++) {
            char searchedLetter = word.charAt(i);
            char letter = charAt(x, y);
            if (searchedLetter != letter) {
                return false;
            }
            x += direction.dx;
            y += direction.dy;
        }
        return true;
    }

    private char charAt(int x, int y) {
        if (y < 0 || getInputLines().size() <= y
                || x < 0 || getInputLines().get(y).length() <= x) {
            return ' ';
        }
        return getInputLines().get(y).charAt(x);
    }


    @Override
    public Integer solveSecondPart() {
        int wordCounter = 0;
        for (int y = 0; y < getInputLines().size(); y++) {
            String line = getInputLines().get(y);
            for (int x = 0; x < line.length(); x++) {
                if (isXWord(x, y)) {
                    wordCounter++;
                }
            }
        }
        return wordCounter;
    }

    private boolean isXWord(int x, int y) {
        int x1 = x - 1;
        int x2 = x + 1;
        boolean firstDiagonal = isWord(x1, y, DIAGONAL, "MAS") || isWord(x1, y, DIAGONAL, "SAM");
        return firstDiagonal && (isWord(x2, y, DIAGONAL_2, "MAS") || isWord(x2, y, DIAGONAL_2, "SAM"));
    }


    public enum WordDirection {
        HORIZONTAL(1, 0),
        VERTICAL(0, 1),
        DIAGONAL(1, -1),
        DIAGONAL_2(-1, -1);

        final int dx;
        final int dy;

        WordDirection(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

    }

}
