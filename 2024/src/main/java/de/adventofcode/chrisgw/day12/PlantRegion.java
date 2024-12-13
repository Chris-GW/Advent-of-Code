package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.day12.AdventOfCodeDay12.PlantPlot;

import java.util.Set;
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

}
