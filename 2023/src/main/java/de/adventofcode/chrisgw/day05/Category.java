package de.adventofcode.chrisgw.day05;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public enum Category {
    SEED, SOIL, FERTILIZER, WATER, LIGHT, TEMPERATURE, HUMIDITY, LOCATION;

    public Category destinationCategory() {
        return switch (this) {
            case SEED -> SOIL;
            case SOIL -> FERTILIZER;
            case FERTILIZER -> WATER;
            case WATER -> LIGHT;
            case LIGHT -> TEMPERATURE;
            case TEMPERATURE -> HUMIDITY;
            case HUMIDITY -> LOCATION;
            case LOCATION -> null;
        };
    }


    public static Pattern captureGroup() {
        return Pattern.compile(Arrays.stream(Category.values())
                .map(Enum::name)
                .map(String::toLowerCase)
                .collect(Collectors.joining("|", "(", ")")));
    }

}
