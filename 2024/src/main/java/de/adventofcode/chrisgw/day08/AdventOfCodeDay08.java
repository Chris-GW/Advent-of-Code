package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <a href="https://adventofcode.com/2024/day/8">Advent of Code - day 8</a>
 */
public class AdventOfCodeDay08 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay08(List<String> inputLines) {
        super(Year.of(2024), 8, inputLines);
    }


    private int mapWidth;
    private int mapHeight;
    private final Map<Character, List<Antenna>> antennaByFrequency = new HashMap<>();
    private final Map<Integer, Map<Integer, Antinode>> antinodeMap = new HashMap<>();


    @Override
    public Integer solveFirstPart() {
        parseAntennaMap();
        antennaByFrequency.values().forEach(this::markPairedAntinodes);
        return antinodeMap.values()
                .stream()
                .mapToInt(Map::size)
                .sum();
    }


    private void parseAntennaMap() {
        mapHeight = getInputLines().size();
        mapWidth = getInputLines().get(0).length();

        for (int y = 0; y < getInputLines().size(); y++) {
            String line = getInputLines().get(y);

            for (int x = 0; x < line.length(); x++) {
                char frequency = line.charAt(x);
                if (frequency == '.') {
                    continue;
                }
                var antenna = new Antenna(x, y, frequency);
                addAntenna(antenna);
            }
        }
    }

    private boolean addAntenna(Antenna antenna) {
        char frequency = antenna.frequency;
        return antennaByFrequency.computeIfAbsent(frequency, c -> new ArrayList<>()).add(antenna);
    }


    private void markPairedAntinodes(List<Antenna> antennas) {
        for (int i = 0; i < antennas.size(); i++) {
            Antenna firstAntenna = antennas.get(i);

            for (int k = i + 1; k < antennas.size(); k++) {
                Antenna secondAntenna = antennas.get(k);
                markAntinodeFor(firstAntenna, secondAntenna);
                markAntinodeFor(secondAntenna, firstAntenna);
            }
        }
    }

    private void markAntinodeFor(Antenna firstAntenna, Antenna secondAntenna) {
        int dx = secondAntenna.x - firstAntenna.x;
        int dy = secondAntenna.y - firstAntenna.y;
        int x = firstAntenna.x + dx * 2;
        int y = firstAntenna.y + dy * 2;
        var antinode = new Antinode(x, y, firstAntenna.frequency);
        if (isInsideMap(antinode)) {
            antinodeMap.computeIfAbsent(x, i -> new HashMap<>()).put(y, antinode);
        }
    }

    private boolean isInsideMap(Antinode antinode) {
        return 0 <= antinode.x && antinode.x < mapWidth
                && 0 <= antinode.y && antinode.y < mapHeight;
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    record Antenna(int x, int y, char frequency) {

    }

    record Antinode(int x, int y, char frequency) {

    }


}
