package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.day12.AdventOfCodeDay12.PlantPlot;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


record PlantRegion(Set<PlantPlot> plantPlots) {

    public int calculateFencePrice() {
        return area() * perimeter();
    }

    private int area() {
        return plantPlots.size();
    }

    private int perimeter() {
        return plantPlots.stream()
                .map(PlantPlot::plotsInPerimeter)
                .mapToLong(Stream::count)
                .mapToInt(Math::toIntExact)
                .sum();
    }


    public int calculateBulkFencePrice() {
        int area = area();
        int sides = sides();
        int price = area * sides;
        char plantType = plantPlots.stream().findAny().orElseThrow().plantType;
        System.out.printf("%s: %d * %d = %d%n", plantType, area, sides, price);
        return price;
    }

    private int sides() {
        int sides = 0;
        List<PlantPlot> perimeterPlots = plantPlots.stream()
                .flatMap(PlantPlot::plotsInPerimeter)
                .collect(Collectors.toCollection(LinkedList::new));

        while (!perimeterPlots.isEmpty()) {
            PlantPlot plantPlot = perimeterPlots.getFirst();
            sides++;

            List<PlantPlot> verticalSide = GardenDirection.verticalDirections()
                    .flatMap(direction -> Stream.iterate(plantPlot,
                            perimeterPlots::contains,
                            plantPlot1 -> plantPlot1.plotInDirection(direction)))
                    .distinct()
                    .toList();
            List<PlantPlot> horizontalSide = GardenDirection.horizontalDirections()
                    .flatMap(direction -> Stream.iterate(
                            plantPlot,
                            perimeterPlots::contains,
                            plantPlot1 -> plantPlot1.plotInDirection(direction)))
                    .distinct()
                    .toList();

            if(verticalSide.size() > horizontalSide.size()) {
                verticalSide.forEach(perimeterPlots::remove);
            } else {
                horizontalSide.forEach(perimeterPlots::remove);
            }
        }
        return sides;
    }

}
