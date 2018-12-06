package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.day06.PlaceCoordinate.Coordinate;
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

    private Map<Integer, PlaceCoordinate> placeCoordinates;

    private int[][] placeCoordinateArea;

    @Getter
    private int dx;

    @Getter
    private int dy;


    private ChronalCoordinates(List<PlaceCoordinate> placeCoordinates) {
        if (placeCoordinates.isEmpty()) {
            this.placeCoordinates = Collections.emptyMap();
            this.placeCoordinateArea = new int[0][0];
        }
        this.placeCoordinates = placeCoordinates.stream()
                .collect(Collectors.toMap(PlaceCoordinate::getId, Function.identity()));
        this.dx = -findMinPlaceCoordinateBy(PlaceCoordinate::getX) + 1;
        this.dy = -findMinPlaceCoordinateBy(PlaceCoordinate::getY) + 1;
        this.placeCoordinateArea = createPlaceCoordinateArea();
        calculatePlaceCoordinateArea();
    }

    public static ChronalCoordinates parsePlaceCoordinates(Path placeCoordinateFile) throws IOException {
        return parsePlaceCoordinates(Files.lines(placeCoordinateFile));
    }

    public static ChronalCoordinates parsePlaceCoordinates(Stream<String> placeCoordinateLines) {
        List<PlaceCoordinate> placeCoordinates = new ArrayList<>();
        Iterator<String> placeCordinateLineIterator = placeCoordinateLines.iterator();
        for (int id = 1; placeCordinateLineIterator.hasNext(); id++) {
            String readLine = placeCordinateLineIterator.next();
            PlaceCoordinate placeCoordinate = parsePlaceCoordinate(id, readLine);
            placeCoordinates.add(placeCoordinate);
        }
        return new ChronalCoordinates(placeCoordinates);
    }

    private static PlaceCoordinate parsePlaceCoordinate(int id, String placeCoordinateStr) {
        String[] split = placeCoordinateStr.split("\\s*,\\s*");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        return new PlaceCoordinate(id, x, y);
    }


    private int[][] createPlaceCoordinateArea() {
        if (placeCoordinates.isEmpty()) {
            return new int[0][0];
        }
        int maxX = findMaxPlaceCoordinateBy(PlaceCoordinate::getX);
        int maxY = findMaxPlaceCoordinateBy(PlaceCoordinate::getY);
        Coordinate maxCoordinate = mapToArea(new Coordinate(maxX, maxY));
        int xLength = maxCoordinate.getX() + 3;
        int yLength = maxCoordinate.getY() + 3;
        return new int[yLength][xLength];
    }


    private void calculatePlaceCoordinateArea() {
        int areaSize = placeCoordinateArea.length * placeCoordinateArea[0].length;
        Set<Coordinate> setCoordinates = new HashSet<>(areaSize);

        for (int distance = 0; setCoordinates.size() < areaSize; distance++) {
            Set<Coordinate> newCoordinates = new HashSet<>();
            for (PlaceCoordinate placeCoordinate : placeCoordinates.values()) {
                placeCoordinate.coordinatesWithinDistance(distance)
                        .filter(this::isWithingArea)
                        .filter(coordinate -> !setCoordinates.contains(coordinate))
                        .forEach(coordinate -> {
                            setClosestPlaceCoordinate(placeCoordinate, coordinate);
                            newCoordinates.add(coordinate);
                        });
            }
            setCoordinates.addAll(newCoordinates);
            //System.out.println(this);
        }
    }


    private int findMinPlaceCoordinateBy(ToIntFunction<PlaceCoordinate> getPlaceCoordinate) {
        PlaceCoordinate placeCoordinate = placeCoordinates().min(Comparator.comparingInt(getPlaceCoordinate))
                .orElseThrow(IllegalStateException::new);
        return getPlaceCoordinate.applyAsInt(placeCoordinate);
    }


    private int findMaxPlaceCoordinateBy(ToIntFunction<PlaceCoordinate> getPlaceCoordinate) {
        PlaceCoordinate placeCoordinate = placeCoordinates().max(Comparator.comparingInt(getPlaceCoordinate))
                .orElseThrow(IllegalStateException::new);
        return getPlaceCoordinate.applyAsInt(placeCoordinate);
    }


    private void setClosestPlaceCoordinate(PlaceCoordinate placeCoordinate, Coordinate coordinate) {
        coordinate = mapToArea(coordinate);
        int placeCoordinateId = placeCoordinate.getId();
        int currentPlaceCoordinateId = placeCoordinateArea[coordinate.getY()][coordinate.getX()];
        if (currentPlaceCoordinateId > 0) {
            placeCoordinateArea[coordinate.getY()][coordinate.getX()] = -1;
        } else if (currentPlaceCoordinateId != -1) {
            placeCoordinateArea[coordinate.getY()][coordinate.getX()] = placeCoordinateId;
        }
    }

    private boolean isWithingArea(Coordinate coordinate) {
        coordinate = mapToArea(coordinate);
        return 0 <= coordinate.getX() && coordinate.getX() < placeCoordinateArea[0].length && //
                0 <= coordinate.getY() && coordinate.getY() < placeCoordinateArea.length;
    }


    private Coordinate mapToArea(Coordinate coordinate) {
        int x = coordinate.getX() + dx;
        int y = coordinate.getY() + dy;
        return new Coordinate(x, y);
    }


    private Stream<PlaceCoordinate> placeCoordinates() {
        return placeCoordinates.values().stream();
    }


    public int largestAreaSize() {
        return placeCoordinates().filter(placeCoordinate -> !isBoarderPlaceCoordinate(placeCoordinate))
                .mapToInt(this::placeAreaSize)
                .max()
                .orElse(0);
    }

    private int placeAreaSize(PlaceCoordinate placeCoordinate) {
        return (int) Arrays.stream(placeCoordinateArea)
                .flatMapToInt(Arrays::stream)
                .filter(placeId -> placeId == placeCoordinate.getId())
                .count();
    }


    public boolean isBoarderPlaceCoordinate(PlaceCoordinate placeCoordinate) {
        for (int y = 0; y < placeCoordinateArea.length; y++) {
            for (int x = 0; x < placeCoordinateArea[y].length; x++) {
                if (y == 0 || y == placeCoordinateArea.length - 1 || //
                        x == 0 || x == placeCoordinateArea[y].length - 1) {
                    if (placeCoordinate.getId() == placeCoordinateArea[y][x]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        if (placeCoordinates.isEmpty()) {
            return "ChronalCoordinates empty";
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < placeCoordinateArea.length; y++) {
            for (int x = 0; x < placeCoordinateArea[y].length; x++) {
                int coordinateId = placeCoordinateArea[y][x];
                PlaceCoordinate placeCoordinate = placeCoordinates.get(coordinateId);
                char placeCoordinateIdLetter;
                if (placeCoordinate == null && coordinateId == -1) {
                    placeCoordinateIdLetter = '.';
                } else if (placeCoordinate == null) {
                    placeCoordinateIdLetter = ' ';
                } else if (placeCoordinate.isAtCoordinate(x - dx, y - dy)) {
                    placeCoordinateIdLetter = (char) ('A' + placeCoordinate.getId() - 1);
                } else {
                    placeCoordinateIdLetter = (char) ('a' + placeCoordinate.getId() - 1);
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
    }

}
