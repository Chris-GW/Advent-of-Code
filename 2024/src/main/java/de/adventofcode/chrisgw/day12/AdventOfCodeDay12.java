package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Year;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2024/day/12">Advent of Code - day 12</a>
 */
public class AdventOfCodeDay12 extends AdventOfCodePuzzleSolver {

    private PlantPlot[][] plotMap;


    public AdventOfCodeDay12(List<String> inputLines) {
        super(Year.of(2024), 12, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        plotMap = parsePlantPlotMap();
        List<PlantRegion> plantRegions = findPlantRegions(plotMap);
        return plantRegions.stream()
                .mapToInt(PlantRegion::calculateFencePrice)
                .sum();
    }

    private PlantPlot[][] parsePlantPlotMap() {
        PlantPlot[][] map = new PlantPlot[getInputLines().size()][];
        for (int y = 0; y < getInputLines().size(); y++) {
            String line = getInputLines().get(y);
            map[y] = new PlantPlot[line.length()];

            for (int x = 0; x < line.length(); x++) {
                char plantType = line.charAt(x);
                var plantPlot = new PlantPlot(x, y, plantType);
                map[y][x] = plantPlot;
            }
        }
        return map;
    }


    private List<PlantRegion> findPlantRegions(PlantPlot[][] plotMap) {
        List<PlantRegion> plantRegions = new ArrayList<>();
        Set<PlantPlot> visitedPlantPlots = new HashSet<>();

        for (PlantPlot[] plantPlots : plotMap) {
            for (PlantPlot plot : plantPlots) {
                if (visitedPlantPlots.add(plot)) {
                    Set<PlantPlot> plotsInRegion = collectPlotsInRegion(plot);
                    visitedPlantPlots.addAll(plotsInRegion);
                    plantRegions.add(new PlantRegion(plotsInRegion));
                }
            }
        }
        return plantRegions;
    }

    private Set<PlantPlot> collectPlotsInRegion(PlantPlot startPlot) {
        Set<PlantPlot> plotsInRegion = new HashSet<>();
        Deque<PlantPlot> connectedPlots = new ArrayDeque<>();
        connectedPlots.add(startPlot);

        while (!connectedPlots.isEmpty()) {
            PlantPlot plantPlot = connectedPlots.pop();
            if (plotsInRegion.add(plantPlot)) {
                List<PlantPlot> connections = Arrays.stream(GardenDirection.values())
                        .map(plantPlot::plotInDirection)
                        .filter(plantPlot::isSamePlantType)
                        .toList();
                connections.forEach(connectedPlots::offer);
            }
        }
        return plotsInRegion;
    }


    @Override
    public Integer solveSecondPart() {
        plotMap = parsePlantPlotMap();
        List<PlantRegion> plantRegions = findPlantRegions(plotMap);
        return plantRegions.stream()
                .mapToInt(PlantRegion::calculateBulkFencePrice)
                .sum();
    }


    class PlantPlot {

        final int x;
        final int y;
        final char plantType;

        PlantPlot(int x, int y, char plantType) {
            this.x = x;
            this.y = y;
            this.plantType = plantType;
        }


        public PlantPlot plotInDirection(GardenDirection direction) {
            int x2 = this.x + direction.dx;
            int y2 = this.y + direction.dy;
            if (0 > y2 || y2 >= plotMap.length || 0 > x2 || x2 >= plotMap[y2].length) {
                return new PlantPlot(x2, y2, '.');
            }
            return plotMap[y2][x2];
        }

        public boolean isConnected(PlantPlot otherPlot) {
            return Arrays.stream(GardenDirection.values())
                    .map(this::plotInDirection)
                    .anyMatch(otherPlot::equals);
        }


        public Stream<PlantPlot> plotsInPerimeter() {
            return Arrays.stream(GardenDirection.values())
                    .map(this::plotInDirection)
                    .filter(Predicate.not(this::isSamePlantType));
        }

        public boolean isSamePlantType(PlantPlot otherPlantPlot) {
            return this.plantType == otherPlantPlot.plantType;
        }


        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof PlantPlot plantPlot)) {
                return false;
            }
            return new EqualsBuilder().append(x, plantPlot.x)
                    .append(y, plantPlot.y)
                    .append(plantType, plantPlot.plantType)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(x).append(y).append(plantType).toHashCode();
        }

        @Override
        public String toString() {
            return "(%d;%d) %s".formatted(x, y, plantType);
        }

    }

}
