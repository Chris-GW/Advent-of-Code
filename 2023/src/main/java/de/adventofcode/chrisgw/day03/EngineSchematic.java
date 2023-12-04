package de.adventofcode.chrisgw.day03;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EngineSchematic {

    private Map<Integer, Map<Integer, EnginePartNumber>> partNumberMap = new HashMap<>();
    private Map<Integer, Map<Integer, EnginePartSymbol>> partSymbolMap = new HashMap<>();


    private EngineSchematic() {

    }

    public static EngineSchematic parseEngineSchematic(List<String> inputLines) {
        EngineSchematic engineSchematic = new EngineSchematic();
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
                .filter(this::hasAdjacentSymbol)
                .mapToInt(EnginePartNumber::partNumber)
                .sum();
    }

    private boolean hasAdjacentSymbol(EnginePartNumber enginePartNumber) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= enginePartNumber.partNumberLength(); dx++) {
                int y = enginePartNumber.y() + dy;
                int x = enginePartNumber.x() + dx;
                if (getEnginePartSymbol(x, y) != null) {
                    return true;
                }
            }
        }
        return false;
    }


    public EnginePartNumber getEnginePartNumber(int x, int y) {
        return partNumberMap.getOrDefault(x, Collections.emptyMap()).get(y);
    }

    public EnginePartSymbol getEnginePartSymbol(int x, int y) {
        return partSymbolMap.getOrDefault(x, Collections.emptyMap()).get(y);
    }


    public void putEnginePartNumber(EnginePartNumber enginePartNumber) {
        int x = enginePartNumber.x();
        int y = enginePartNumber.y();
        partNumberMap.computeIfAbsent(x, k -> new HashMap<>()).put(y, enginePartNumber);
    }

    public void putEnginePartSymbol(EnginePartSymbol enginePartSymbol) {
        int x = enginePartSymbol.x();
        int y = enginePartSymbol.y();
        partSymbolMap.computeIfAbsent(x, k -> new HashMap<>()).put(y, enginePartSymbol);
    }


}
