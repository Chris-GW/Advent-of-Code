package de.adventofcode.chrisgw.day18;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.emptyMap;


/**
 * <a href="https://adventofcode.com/2023/day/18">Advent of Code - day 18</a>
 */
public class AdventOfCodeDay18 extends AdventOfCodePuzzleSolver {
    private Map<Integer, Map<Integer, String>> digMap = new HashMap<>();
    private int x = 0;
    private int y = 0;

    public AdventOfCodeDay18(List<String> inputLines) {
        super(Year.of(2023), 18, inputLines);
        put(0, 0, "");
    }


    @Override
    public Integer solveFirstPart() {
        List<DigInstruction> digInstructions = inputLines().map(DigInstruction::parseDigInstruction).toList();
        digInstructions.forEach(this::executeDigInstruction);
        System.out.println(this);
        digOutInterior();
        System.out.println("digOutInterior");
        System.out.println(this);
        return digMap.values().stream().mapToInt(Map::size).sum();
    }

    private void executeDigInstruction(DigInstruction digInstruction) {
        for (int i = 0; i < digInstruction.length(); i++) {
            DigDirection direction = digInstruction.direction();
            x += direction.getDx();
            y += direction.getDy();
            put(x, y, digInstruction.colorhexCode());
        }
    }

    private void digOutInterior() {
        int minY = digMap.keySet().stream().min(Integer::compareTo).orElse(0);
        int maxY = digMap.keySet().stream().max(Integer::compareTo).orElse(0);
        for (int y = minY; y <= maxY; y++) {
            Map<Integer, String> rowMap = digMap.getOrDefault(y, emptyMap());
            int minX = rowMap.keySet().stream().min(Integer::compareTo).orElse(0);
            int maxX = rowMap.keySet().stream().max(Integer::compareTo).orElse(0);
            for (int x = minX; x <= maxX; x++) {
                if (isInside(x, y)) {
                    put(x, y, "");
                }
            }
        }
    }

    private boolean isInside(int x, int y) {
        // TODO flood fill algorithm?
        for (DigDirection direction : DigDirection.values()) {
            boolean found = false;
            for (int i = 0; i < 100000; i++) {
                int nexX = x + direction.getDx() * i;
                int nexY = y + direction.getDy() * i;
                if (contains(nexX, nexY)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private boolean contains(int x, int y) {
        return get(x, y) != null;
    }

    public String get(int x, int y) {
        return digMap.getOrDefault(y, emptyMap()).get(x);
    }

    public String put(int x, int y, String colorHexCode) {
        return digMap.computeIfAbsent(y, k -> new HashMap<>()).put(x, colorHexCode);
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    @Override
    public String toString() {
        int minY = digMap.keySet().stream().min(Integer::compareTo).orElse(0);
        int maxY = digMap.keySet().stream().max(Integer::compareTo).orElse(0);
        int minX = digMap.values()
                .stream()
                .map(Map::keySet)
                .flatMap(Collection::stream)
                .min(Integer::compareTo)
                .orElse(0);
        int maxX = digMap.values()
                .stream()
                .map(Map::keySet)
                .flatMap(Collection::stream)
                .max(Integer::compareTo)
                .orElse(0);

        StringBuilder sb = new StringBuilder();
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                String s = get(x, y);
                if (s != null) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public record DigInstruction(DigDirection direction, int length, String colorhexCode) {
        public static final Pattern DIG_INSTRUCTION_PATTERN = Pattern.compile("([UDLR]) (\\d+) \\((#[0-9a-f]{6})\\)");

        public static DigInstruction parseDigInstruction(String digInstructionLine) {
            Matcher matcher = DIG_INSTRUCTION_PATTERN.matcher(digInstructionLine);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Expect digInstruction matching pattern "
                        + DIG_INSTRUCTION_PATTERN + ", but was: " + digInstructionLine);
            }
            DigDirection direction = DigDirection.fromShortName(matcher.group(1));
            int length = Integer.parseInt(matcher.group(2));
            String colorHexCode = matcher.group(3);
            return new DigInstruction(direction, length, colorHexCode);
        }

    }

}
