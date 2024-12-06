package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2024/day/4">Advent of Code - day 4</a>
 */
public class AdventOfCodeDay04 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay04(List<String> inputLines) {
        super(Year.of(2024), 4, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<WordMatch> wordMatches = new ArrayList<>();
        for (int y = 0; y < getInputLines().size(); y++) {
            String line = getInputLines().get(y);
            for (int x = 0; x < line.length(); x++) {
                wordMatches.addAll(findWordMatches(x, y));
            }
        }
        return wordMatches.size();
    }

    private Collection<WordMatch> findWordMatches(int x, int y) {
        List<WordMatch> wordMatches = new ArrayList<>();
        for (WordDirection direction : WordDirection.values()) {
            if (isWord(x, y, direction)) {
                wordMatches.add(new WordMatch(x, y, direction));
            }
        }
        return wordMatches;
    }

    private boolean isWord(int x, int y, WordDirection direction) {
        String word = "XMAS";
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
        // TODO solveSecondPart
        return 0;
    }


    public record WordMatch(int x, int y, WordDirection direction) {

    }


    public enum WordDirection {
        LEFT_RIGHT(1, 0),
        RIGHT_LEFT(-1, 0),
        UP_DOWN(0, 1),
        DOWN_UP(0, -1),
        RIGHT_UP(1, 1),
        RIGHT_DOWN(1, -1),
        LEFT_UP(-1, 1),
        LEFT_DOWN(-1, -1);

        final int dx;
        final int dy;

        WordDirection(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

    }

}
