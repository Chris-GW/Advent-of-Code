package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2025/day/7">Advent of Code - day 7</a>
 */
public class AdventOfCodeDay07 extends AdventOfCodePuzzleSolver {

    private Set<Location> splitters = new HashSet<>();
    private Set<Location> beamLocations = new HashSet<>();
    private Location startLocation;


    public AdventOfCodeDay07(List<String> inputLines) {
        super(Year.of(2025), 7, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        parseDiagram();

        int beamCount = 0;
        Queue<Location> beamStartLocations = new PriorityQueue<>(Comparator.comparingInt(Location::y));
        beamStartLocations.offer(startLocation);

        while (!beamStartLocations.isEmpty()) {
            Location beamStartPosition = beamStartLocations.poll();
            if (!beamLocations.add(beamStartPosition)) {
                continue;
            }
            List<Location> locations = moveBeamDownward(beamStartPosition).toList();
            Location splitterLocation = locations.getLast().down();
            if (isSplitterAt(splitterLocation)) {
                splitterLocation.split().forEach(beamStartLocations::offer);
                beamCount++;
            }
            beamLocations.addAll(locations);
        }
        return beamCount;
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    private void parseDiagram() {
        for (int y = 0; y < getInputLines().size(); y++) {
            String line = getInputLines().get(y);
            for (int x = 0; x < line.length(); x++) {
                char diagramSign = line.charAt(x);
                if (diagramSign == 'S') {
                    startLocation = new Location(x, y);
                } else if (diagramSign == '^') {
                    splitters.add(new Location(x, y));
                }
            }
        }
    }


    public boolean isSplitterAt(Location location) {
        return splitters.contains(location);
    }

    public boolean isInside(Location location) {
        int x = location.x();
        int y = location.y();
        return 0 <= y && y < getInputLines().size() &&
                0 <= x && x < getInputLines().get(y).length();
    }


    public Stream<Location> moveBeamDownward(Location startLocation) {
        return Stream.iterate(startLocation, this::canMoveDownward, Location::down);
    }

    public boolean canMoveDownward(Location location) {
        return isInside(location) && !isSplitterAt(location);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < getInputLines().size(); y++) {
            for (int x = 0; x < getInputLines().get(y).length(); x++) {
                var location = new Location(x, y);
                sb.append(toDiagramSign(location));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private char toDiagramSign(Location location) {
        if (startLocation.equals(location)) {
            return 'S';
        } else if (isSplitterAt(location)) {
            return '^';
        } else if (beamLocations.contains(location)) {
            return '|';
        } else {
            return '.';
        }
    }

}
