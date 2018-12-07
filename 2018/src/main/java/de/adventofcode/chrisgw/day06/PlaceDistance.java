package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.day06.Place.Coordinate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;


@Value
public class PlaceDistance {


    private final Coordinate coordinate;

    @Getter(AccessLevel.PRIVATE)
    private final Map<Place, Integer> distanceToPlaces = new HashMap<>();


    public Integer putDistanceTo(Place place, int distance) {
        return distanceToPlaces.merge(place, distance, Math::min);
    }

    public int getDistanceTo(Place place) {
        return distanceToPlaces.getOrDefault(place, Integer.MAX_VALUE);
    }


    public Stream<Place> places() {
        return distanceToPlaces.keySet().stream();
    }

    public boolean isToatalDistanceSmallerThan(int maxDistance) {
        return places().mapToInt(distanceToPlaces::get).sum() < maxDistance;
    }

    public Stream<Place> placesWithMinDistance() {
        int minDistance = distanceToPlaces.values().stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
        return places().filter(place -> getDistanceTo(place) == minDistance);
    }

    public boolean isPlaceWithMinDistance(Place place) {
        return placesWithMinDistance().allMatch(place::equals);
    }


}
