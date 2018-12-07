package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.day06.Place.Coordinate;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ChronalCoordinates {

    private Map<Integer, Place> placesById;

    private PlaceDistance[][] placeArea;

    @Getter
    private int dx;

    @Getter
    private int dy;


    private ChronalCoordinates(List<Place> places) {
        if (places.isEmpty()) {
            this.placesById = Collections.emptyMap();
            this.placeArea = new PlaceDistance[0][0];
        }
        this.placesById = places.stream().collect(Collectors.toMap(Place::getId, Function.identity()));
        this.dx = -findMinPlaceCoordinateBy(Place::getX) + 1;
        this.dy = -findMinPlaceCoordinateBy(Place::getY) + 1;
        this.placeArea = createPlaceCoordinateArea();
    }

    public static ChronalCoordinates parsePlaceCoordinates(Path placeCoordinateFile) throws IOException {
        return parsePlaceCoordinates(Files.lines(placeCoordinateFile));
    }

    public static ChronalCoordinates parsePlaceCoordinates(Stream<String> placeCoordinateLines) {
        List<Place> places = new ArrayList<>();
        Iterator<String> placeCordinateLineIterator = placeCoordinateLines.iterator();
        for (int id = 1; placeCordinateLineIterator.hasNext(); id++) {
            String readLine = placeCordinateLineIterator.next();
            Place place = parsePlaceCoordinate(id, readLine);
            places.add(place);
        }
        return new ChronalCoordinates(places);
    }

    private static Place parsePlaceCoordinate(int id, String placeCoordinateStr) {
        String[] split = placeCoordinateStr.split("\\s*,\\s*");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        return new Place(id, x, y);
    }


    private PlaceDistance[][] createPlaceCoordinateArea() {
        int maxX = findMaxPlaceCoordinateBy(Place::getX);
        int maxY = findMaxPlaceCoordinateBy(Place::getY);
        Coordinate maxCoordinate = mapToArea(new Coordinate(maxX, maxY));
        int xLength = maxCoordinate.getX() + 2;
        int yLength = maxCoordinate.getY() + 2;

        PlaceDistance[][] placeArea = new PlaceDistance[yLength][xLength];
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                Coordinate currentCoordinate = new Coordinate(x, y);
                PlaceDistance placeDistance = new PlaceDistance(currentCoordinate);
                for (Place place : placesById.values()) {
                    int distance = place.distanceTo(currentCoordinate);
                    placeDistance.putDistanceTo(place, distance);
                }
                placeArea[y][x] = placeDistance;
            }
        }
        return placeArea;
    }


    private int findMinPlaceCoordinateBy(ToIntFunction<Place> getPlaceCoordinate) {
        Place place = places().min(Comparator.comparingInt(getPlaceCoordinate)).orElseThrow(IllegalStateException::new);
        return getPlaceCoordinate.applyAsInt(place);
    }


    private int findMaxPlaceCoordinateBy(ToIntFunction<Place> getPlaceCoordinate) {
        Place place = places().max(Comparator.comparingInt(getPlaceCoordinate)).orElseThrow(IllegalStateException::new);
        return getPlaceCoordinate.applyAsInt(place);
    }


    private Coordinate mapToArea(Coordinate coordinate) {
        int x = coordinate.getX() + dx;
        int y = coordinate.getY() + dy;
        return new Coordinate(x, y);
    }


    private Stream<Place> places() {
        return placesById.values().stream();
    }


    public int largestAreaSize() {
        return places().filter(place -> !isBoarderPlaceCoordinate(place)).mapToInt(this::placeAreaSize).max().orElse(0);
    }

    private int placeAreaSize(Place place) {
        return (int) Arrays.stream(placeArea)
                .flatMap(Arrays::stream)
                .filter(placeDistance -> placeDistance.isPlaceWithMinDistance(place))
                .mapToInt(placeDistance -> placeDistance.getDistanceTo(place))
                .count();
    }

    public int closestAreaSize(int maxDistance) {
        return (int) Arrays.stream(placeArea)
                .flatMap(Arrays::stream)
                .filter(placeDistance -> placeDistance.isToatalDistanceSmallerThan(maxDistance))
                .count();
    }


    public boolean isBoarderPlaceCoordinate(Place place) {
        for (int y = 0; y < placeArea.length; y++) {
            for (int x = 0; x < placeArea[y].length; x++) {
                if (y == 0 || y == placeArea.length - 1 || //
                        x == 0 || x == placeArea[y].length - 1) {
                    PlaceDistance boarderPlaceDistance = placeArea[y][x];
                    if (boarderPlaceDistance.isPlaceWithMinDistance(place)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        if (placesById.isEmpty()) {
            return "ChronalCoordinates empty";
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < placeArea.length; y++) {
            for (int x = 0; x < placeArea[y].length; x++) {
                PlaceDistance placeDistance = placeArea[y][x];
                List<Place> minDistances = placeDistance.placesWithMinDistance().collect(Collectors.toList());
                char placeCoordinateIdLetter;
                if (minDistances.size() > 1) {
                    placeCoordinateIdLetter = '.';
                } else if (minDistances.isEmpty()) {
                    placeCoordinateIdLetter = ' ';
                } else if (minDistances.get(0).isAtCoordinate(x - dx, y - dy)) {
                    placeCoordinateIdLetter = (char) ('A' + minDistances.get(0).getId() - 1);
                } else {
                    placeCoordinateIdLetter = (char) ('a' + minDistances.get(0).getId() - 1);
                }
                sb.append(placeCoordinateIdLetter);
            }
            sb.append("\n");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);
        ChronalCoordinates chronalCoordinates = ChronalCoordinates.parsePlaceCoordinates(puzzleInputFile);
        int largestAreaSize = chronalCoordinates.largestAreaSize();
        System.out.println("largestAreaSize: " + largestAreaSize);

        int closestAreaSize = chronalCoordinates.closestAreaSize(10_000);
        System.out.println(closestAreaSize);
    }

}
