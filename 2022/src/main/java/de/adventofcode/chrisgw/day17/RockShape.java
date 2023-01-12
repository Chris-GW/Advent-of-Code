package de.adventofcode.chrisgw.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public enum RockShape {

    HORIZONTAL_LINE_ROCK(new String[] { "####" }), //
    CROSS_ROCK(new String[] { //
            ".#.", //
            "###", //
            ".#." //
    }), //
    L_ROCK(new String[] { //
            "###", //
            "..#", //
            "..#" //
    }), //
    VERTICAL_LINE_ROCK(new String[] { //
            "#", //
            "#", //
            "#", //
            "#" //
    }), //
    BLOCK_ROCK(new String[] { //
            "##", //
            "##", //
    });

    private final List<RockCoordinate> rockCoordinates = new ArrayList<>();


    RockShape(String[] rockShape) {
        for (int y = 0; y < rockShape.length; y++) {
            String line = rockShape[y];
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#') {
                    rockCoordinates.add(new RockCoordinate(x, y));
                }
            }
        }
    }


    public Stream<RockCoordinate> rockCoordinates() {
        return rockCoordinates.stream();
    }


}
