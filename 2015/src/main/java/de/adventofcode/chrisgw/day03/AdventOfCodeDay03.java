package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * <a href="https://adventofcode.com/2015/day/3">Advent of Code - day 3</a>
 */
public class AdventOfCodeDay03 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay03(List<String> inputLines) {
        super(Year.of(2015), 3, inputLines);
    }

    private int x = 0;
    private int y = 0;
    private Map<Integer, Map<Integer, Integer>> visitedPositions = new HashMap<>();


    @Override
    public Integer solveFirstPart() {
        savePosition();
        String inputLine = getInputLines().getFirst();
        for (int i = 0; i < inputLine.length(); i++) {
            char directionSign = inputLine.charAt(i);
            var direction = Direction.fromSign(directionSign);
            walkDirection(direction);
        }
        return visitedPositions.values()
                .stream()
                .map(Map::keySet)
                .mapToInt(Set::size)
                .sum();
    }


    private void walkDirection(Direction direction) {
        x += direction.dx();
        y += direction.dy();
        savePosition();
    }

    private int savePosition() {
        return visitedPositions
                .computeIfAbsent(x, k -> new HashMap<>())
                .merge(y, 1, Integer::sum);
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    public enum Direction {
        NORTH('^', 0, 1),
        SOUTH('v', 0, -1),
        EAST('>', 1, 0),
        WEST('<', -1, 0);

        private final char sign;
        private final int dx;
        private final int dy;


        Direction(char sign, int dx, int dy) {
            this.sign = sign;
            this.dx = dx;
            this.dy = dy;
        }

        public static Direction fromSign(char sign) {
            return Arrays.stream(values())
                    .filter(direction -> direction.sign == sign)
                    .findAny()
                    .orElseThrow();
        }


        public char sign() {
            return sign;
        }

        public int dx() {
            return dx;
        }

        public int dy() {
            return dy;
        }

    }

}
