package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    private final Map<Integer, Map<Integer, Antenna>> antinodeMap = new HashMap<>();


    @Override
    public Integer solveFirstPart() {
        parseAntennaMap();
        for (List<Antenna> antennas : antennaByFrequency.values()) {
            markPairedAntinodes(antennas, false);
        }
        System.out.println(this);
        return antinodeMap.values()
                .stream()
                .mapToInt(Map::size)
                .sum();
    }

    @Override
    public Integer solveSecondPart() {
        parseAntennaMap();
        for (List<Antenna> antennas : antennaByFrequency.values()) {
            markPairedAntinodes(antennas, true);
        }
        System.out.println(this);
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

    private void addAntenna(Antenna antenna) {
        char frequency = antenna.frequency;
        antennaByFrequency.computeIfAbsent(frequency, c -> new ArrayList<>()).add(antenna);
    }


    private void markPairedAntinodes(List<Antenna> antennas, boolean anyGridPosition) {
        for (int i = 0; i < antennas.size(); i++) {
            Antenna firstAntenna = antennas.get(i);

            for (int k = i + 1; k < antennas.size(); k++) {
                Antenna secondAntenna = antennas.get(k);
                markAntinodeFor(firstAntenna, secondAntenna, anyGridPosition);
                markAntinodeFor(secondAntenna, firstAntenna, anyGridPosition);
            }
        }
    }

    private void markAntinodeFor(Antenna firstAntenna, Antenna secondAntenna, boolean anyGridPosition) {
        int dx = secondAntenna.x - firstAntenna.x;
        int dy = secondAntenna.y - firstAntenna.y;

        for (int i = anyGridPosition ? 0 : 2; true; i++) {
            int x = firstAntenna.x + (dx * i);
            int y = firstAntenna.y + (dy * i);
            Antenna antinode = new Antenna(x, y, firstAntenna.frequency);
            if (!isInsideMap(antinode)) {
                break;
            }
            antinodeMap.computeIfAbsent(x, k -> new HashMap<>()).put(y, antinode);
            if (!anyGridPosition) {
                break;
            }
        }
    }

    private boolean isInsideMap(Antenna antinode) {
        return 0 <= antinode.x && antinode.x < mapWidth
                && 0 <= antinode.y && antinode.y < mapHeight;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                Optional<Antenna> antenna = findAntennaAt(x, y);
                if (antenna.isPresent()) {
                    sb.append(antenna.get().frequency);
                    continue;
                }

                Optional<Antenna> antinode = findAntinodeAt(x, y);
                if (antinode.isPresent()) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    private Optional<Antenna> findAntennaAt(int x, int y) {
        return antennaByFrequency.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(antenna1 -> antenna1.isAt(x, y))
                .findAny();
    }

    private Optional<Antenna> findAntinodeAt(int x, int y) {
        return antinodeMap.values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(antenna1 -> antenna1.isAt(x, y))
                .findAny();
    }


    record Antenna(int x, int y, char frequency) {

        public boolean isAt(int x, int y) {
            return this.x == x && this.y == y;
        }

    }

}
