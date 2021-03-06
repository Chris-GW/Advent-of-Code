package de.adventofcode.chrisgw.day06;

import lombok.Value;

import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


@Value
public class Place {

    private final int id;
    private final Coordinate coordinate;


    public Place(int id, int x, int y) {
        this.id = id;
        this.coordinate = new Coordinate(x, y);
    }

    public boolean isAtCoordinate(Place otherPlace) {
        return isAtCoordinate(otherPlace.getCoordinate());
    }

    public boolean isAtCoordinate(Coordinate otherCoordinate) {
        return isAtCoordinate(otherCoordinate.getX(), otherCoordinate.getY());
    }

    public boolean isAtCoordinate(int x, int y) {
        return getX() == x && getY() == y;
    }


    public int distanceTo(Coordinate coordinate) {
        return this.coordinate.distanceTo(coordinate);
    }



    public int getX() {
        return getCoordinate().getX();
    }

    public int getY() {
        return getCoordinate().getY();
    }


    @Value
    public static class Coordinate {

        private final int x;
        private final int y;


        public int distanceTo(Coordinate coordinate) {
            int distanceX = Math.abs(this.x - coordinate.getX());
            int distanceY = Math.abs(this.y - coordinate.getY());
            return distanceX + distanceY;
        }


        @Override
        public String toString() {
            return String.format("(%2d;%2d)", x, y);
        }

    }

}
