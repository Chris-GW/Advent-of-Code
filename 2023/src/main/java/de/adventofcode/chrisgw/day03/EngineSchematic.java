package de.adventofcode.chrisgw.day03;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EngineSchematic {

    private final Map<Integer, Map<Integer, EnginePartNumber>> partNumberMap;
    private final Map<Integer, Map<Integer, EnginePartSymbol>> partSymbolMap;


    private EngineSchematic() {
        partNumberMap = new HashMap<>();
        partSymbolMap = new HashMap<>();
    }

    public static EngineSchematic parseEngineSchematic(List<String> inputLines) {
        var engineSchematic = new EngineSchematic();
        Pattern engineSchematicPattern = Pattern.compile("(\\d+|[^.])");
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);
            Matcher matcher = engineSchematicPattern.matcher(line);

            while (matcher.find()) {
                int x = matcher.start();
                char firstSymbol = line.charAt(x);
                if (Character.isDigit(firstSymbol)) {
                    int number = Integer.parseInt(matcher.group());
                    var partNumber = new EnginePartNumber(x, y, number);
                    engineSchematic.putEnginePartNumber(partNumber);
                } else {
                    var partSymbol = new EnginePartSymbol(x, y, firstSymbol);
                    engineSchematic.putEnginePartSymbol(partSymbol);
                }
            }
        }
        return engineSchematic;
    }


    public int partNumberSum() {
        return partNumberMap.values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .distinct()
                .filter(this::hasAdjacentSymbol)
                .mapToInt(EnginePartNumber::partNumber)
                .sum();
    }

    private boolean hasAdjacentSymbol(EnginePartNumber enginePartNumber) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= enginePartNumber.partNumberLength(); dx++) {
                int y = enginePartNumber.y() + dy;
                int x = enginePartNumber.x() + dx;
                var enginePartSymbol = getEnginePartSymbol(x, y);
                if (enginePartSymbol != null) {
                    return true;
                }
            }
        }
        return false;
    }


    public int gearRatioSum() {
        return partSymbolMap.values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(EnginePartSymbol::isGear)
                .mapToInt(this::calculateGearRatio)
                .sum();
    }

    private int calculateGearRatio(EnginePartSymbol enginePartSymbol) {
        Set<EnginePartNumber> adjacentPartNumbers = new HashSet<>();
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int x = enginePartSymbol.x() + dx;
                int y = enginePartSymbol.y() + dy;
                EnginePartNumber enginePartNumber = getEnginePartNumber(x, y);
                if (enginePartNumber != null) {
                    adjacentPartNumbers.add(enginePartNumber);
                }
            }
        }

        if (adjacentPartNumbers.size() == 2) {
            return adjacentPartNumbers.stream()
                    .mapToInt(EnginePartNumber::partNumber)
                    .reduce(1, (left, right) -> left * right);
        }
        return 0;
    }


    public EnginePartNumber getEnginePartNumber(int x, int y) {
        return partNumberMap.getOrDefault(x, Collections.emptyMap()).get(y);
    }

    public EnginePartSymbol getEnginePartSymbol(int x, int y) {
        return partSymbolMap.getOrDefault(x, Collections.emptyMap()).get(y);
    }


    private void putEnginePartNumber(EnginePartNumber enginePartNumber) {
        int y = enginePartNumber.y();
        for (int dx = 0; dx < enginePartNumber.partNumberLength(); dx++) {
            int x = enginePartNumber.x() + dx;
            partNumberMap.computeIfAbsent(x, k -> new HashMap<>()).put(y, enginePartNumber);
        }
    }

    private void putEnginePartSymbol(EnginePartSymbol enginePartSymbol) {
        int x = enginePartSymbol.x();
        int y = enginePartSymbol.y();
        partSymbolMap.computeIfAbsent(x, k -> new HashMap<>()).put(y, enginePartSymbol);
    }


}
