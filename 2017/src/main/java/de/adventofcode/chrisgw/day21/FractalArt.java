package de.adventofcode.chrisgw.day21;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * <h1><a href="https://adventofcode.com/2017/day/21>Day 21: Fractal Art</a></h1>
 * <pre>
 * You find a program trying to generate some art. It uses a strange process
 * that involves repeatedly enhancing the detail of an image through a set of
 * rules.
 *
 * The image consists of a two-dimensional square grid of pixels that are
 * either on (#) or off (.). The program always begins with this pattern:
 *
 * .#.
 * ..#
 * ###
 *
 * Because the pattern is both 3 pixels wide and 3 pixels tall, it is said to
 * have a size of 3.
 *
 * Then, the program repeats the following process:
 *
 * - If the size is evenly divisible by 2, break the pixels up into 2x2
 *   squares, and convert each 2x2 square into a 3x3 square by following
 *   the corresponding enhancement rule.
 * - Otherwise, the size is evenly divisible by 3; break the pixels up into
 *   3x3 squares, and convert each 3x3 square into a 4x4 square by
 *   following the corresponding enhancement rule.
 *
 * Because each square of pixels is replaced by a larger one, the image gains
 * pixels and so its size increases.
 *
 * The artist's book of enhancement rules is nearby (your puzzle input);
 * however, it seems to be missing rules. The artist explains that sometimes,
 * one must rotate or flip the input pattern to find a match. (Never rotate or
 * flip the output pattern, though.) Each pattern is written concisely: rows
 * are listed as single units, ordered top-down, and separated by slashes. For
 * example, the following rules correspond to the adjacent patterns:
 *
 * ../.#  =  ..
 *           .#
 *
 *                 .#.
 * .#./..#/###  =  ..#
 *                 ###
 *
 *                         #..#
 * #..#/..../#..#/.##.  =  ....
 *                         #..#
 *                         .##.
 *
 * When searching for a rule to use, rotate and flip the pattern as necessary.
 * For example, all of the following patterns match the same rule:
 *
 * .#.   .#.   #..   ###
 * ..#   #..   #.#   ..#
 * ###   ###   ##.   .#.
 *
 * Suppose the book contained the following two rules:
 *
 * ../.# => ##./#../...
 * .#./..#/### => #..#/..../..../#..#
 *
 * As before, the program begins with this pattern:
 *
 * .#.
 * ..#
 * ###
 *
 * The size of the grid (3) is not divisible by 2, but it is divisible by 3.
 * It divides evenly into a single square; the square matches the second rule,
 * which produces:
 *
 * #..#
 * ....
 * ....
 * #..#
 *
 * The size of this enhanced grid (4) is evenly divisible by 2, so that rule
 * is used. It divides evenly into four squares:
 *
 * #.|.#
 * ..|..
 * --+--
 * ..|..
 * #.|.#
 *
 * Each of these squares matches the same rule (../.# => ##./#../...), three
 * of which require some flipping and rotation to line up with the rule. The
 * output for the rule is the same in all four cases:
 *
 * ##.|##.
 * #..|#..
 * ...|...
 * ---+---
 * ##.|##.
 * #..|#..
 * ...|...
 *
 * Finally, the squares are joined into a new grid:
 *
 * ##.##.
 * #..#..
 * ......
 * ##.##.
 * #..#..
 * ......
 *
 * Thus, after 2 iterations, the grid contains 12 pixels that are on.
 *
 * How many pixels stay on after 5 iterations?
 * </pre>
 */
public class FractalArt {

    private ArtPixelPattern pixelGrid;
    private List<ArtPixelEnhancmentRule> pixelEnhancmentRules;


    public FractalArt(List<ArtPixelEnhancmentRule> pixelEnhancmentRules) {
        this.pixelEnhancmentRules = pixelEnhancmentRules;
        this.pixelGrid = ArtPixelPattern.parseArtPixelPattern(".#./..#/###");
    }


    public int size() {
        return pixelGrid.size();
    }


    public void doEnhancePixelGrid() {
        int pixelPatternSize = 3;
        if (size() % 2 == 0) {
            pixelPatternSize = 2;
        }
        List<List<ArtPixelPattern>> dividedPixelGrid = dividePixelGrid(pixelPatternSize);
        int newPixelPatternSize = pixelPatternSize + 1;
        int newPixelGridSize = dividedPixelGrid.size() * newPixelPatternSize;
        pixelGrid = new ArtPixelPattern(new boolean[newPixelGridSize][newPixelGridSize]);

        for (int y = 0; y < dividedPixelGrid.size(); y++) {
            List<ArtPixelPattern> pixelPatternRow = dividedPixelGrid.get(y);

            for (int x = 0; x < pixelPatternRow.size(); x++) {
                ArtPixelPattern pixelPatternPart = pixelPatternRow.get(x);
                ArtPixelPattern enhancePixelPattern = enhancePixelPattern(pixelPatternPart);
                setPixelPattern(x * newPixelPatternSize, y * newPixelPatternSize, enhancePixelPattern);
            }
        }
    }


    private List<List<ArtPixelPattern>> dividePixelGrid(int newSize) {
        List<List<ArtPixelPattern>> dividedPixelGrid = new ArrayList<>(size() / newSize);

        for (int y = 0; y < size(); y += newSize) {
            List<ArtPixelPattern> pixelPatternRow = new ArrayList<>(size() / newSize);
            for (int x = 0; x < size(); x += newSize) {
                ArtPixelPattern pixelPatternPart = getPixelPattern(x, y, newSize);
                pixelPatternRow.add(pixelPatternPart);
            }
            dividedPixelGrid.add(pixelPatternRow);
        }

        return dividedPixelGrid;
    }

    private ArtPixelPattern getPixelPattern(int startX, int startY, int size) {
        boolean[][] pixelGridPart = new boolean[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixelGridPart[y][x] = pixelGrid.getPixel(startX + x, startY + y);
            }
        }
        return new ArtPixelPattern(pixelGridPart);
    }


    private ArtPixelPattern enhancePixelPattern(ArtPixelPattern pixelPattern) {
        Optional<ArtPixelEnhancmentRule> firstMatchingPixelEnhancmentRule = pixelEnhancmentRules.stream()
                .filter(pixelEnhancmentRule -> pixelEnhancmentRule.matches(pixelPattern))
                .findFirst();
        if (firstMatchingPixelEnhancmentRule.isPresent()) {
            return firstMatchingPixelEnhancmentRule.get().getOutputPixelPatterns();
        } else {
            return pixelPattern; // unchanged
        }
    }

    private Optional<ArtPixelEnhancmentRule> findFirstMatchingPixelEnhancmentRule(ArtPixelPattern pixelPattern) {
        return pixelEnhancmentRules.stream()
                .filter(pixelEnhancmentRule -> pixelEnhancmentRule.matches(pixelPattern))
                .findFirst();
    }

    private void setPixelPattern(int startX, int startY, ArtPixelPattern pixelPattern) {
        for (int y = 0; y < pixelPattern.size(); y++) {
            for (int x = 0; x < pixelPattern.size(); x++) {
                boolean pixel = pixelPattern.getPixel(x, y);
                pixelGrid.setPixel(startX + x, startY + y, pixel);
            }
        }
    }


    public long countLitPixels() {
        return pixelGrid.countLitPixels();
    }


    public ArtPixelPattern getPixelGrid() {
        return pixelGrid;
    }


    @Override
    public String toString() {
        return pixelGrid.toString();
    }

}
