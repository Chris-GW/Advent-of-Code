package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2022/day/8">Advent of Code - day 8</a>
 */
public class TreeHeightMap extends AdventOfCodePuzzleSolver<Integer> {

    private final TreeMapPoint[][] treeHeightMap;

    public TreeHeightMap(List<String> inputLines) {
        super(Year.of(2022), 8, inputLines);
        this.treeHeightMap = parseTreeHeightMap(inputLines);
    }

    private TreeMapPoint[][] parseTreeHeightMap(List<String> treeHeightMapLines) {
        TreeMapPoint[][] treeHeightMap = new TreeMapPoint[treeHeightMapLines.size()][];
        for (int y = 0; y < treeHeightMapLines.size(); y++) {
            String row = treeHeightMapLines.get(y);
            treeHeightMap[y] = new TreeMapPoint[row.length()];

            for (int x = 0; x < row.length(); x++) {
                int treeHeight = row.charAt(x) - '0';
                treeHeightMap[y][x] = new TreeMapPoint(this, x, y, treeHeight);
            }
        }
        return treeHeightMap;
    }


    public Integer solveFirstPart() {
        List<TreeMapPoint> visibleTrees = allTrees().filter(TreeMapPoint::isVisible).toList();
        return visibleTrees.size();
    }

    public Integer solveSecondPart() {
        return allTrees().mapToInt(TreeMapPoint::scenicScore).max().orElse(0);
    }


    public Stream<TreeMapPoint> allTrees() {
        return Arrays.stream(treeHeightMap).flatMap(Arrays::stream);
    }


    public int height() {
        return treeHeightMap.length;
    }

    public int width() {
        return treeHeightMap[0].length;
    }


    public TreeMapPoint treeAt(int x, int y) {
        if (!containsTreeAt(x, y)) {
            return null;
        }
        return treeHeightMap[y][x];
    }


    public boolean containsTreeAt(int x, int y) {
        return 0 <= x && x < width() //
                && 0 <= y && y < height();
    }


}
