package de.adventofcode.chrisgw.day02;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;


public record CubeSample(Map<CubeColor, Integer> cubeSample) {

    public static final Pattern CUBE_SAMPLE_PATTERN = Pattern.compile( //
            "(\\d+)\\s+(red|green|blue),?");


    public enum CubeColor {
        BLUE, RED, GREEN;

    }

    public static CubeSample parseCubeSample(String line) {
        Map<CubeColor, Integer> colorCountMap = new EnumMap<>(CubeColor.class);
        var matcher = CUBE_SAMPLE_PATTERN.matcher(line);
        while (matcher.find()) {
            int count = Integer.parseInt(matcher.group(1));
            var cubeColor = CubeColor.valueOf(matcher.group(2).toUpperCase());
            colorCountMap.put(cubeColor, count);
        }
        return new CubeSample(colorCountMap);
    }


    public int countFor(CubeColor color) {
        return cubeSample.getOrDefault(color, 0);
    }


    public boolean isSmallerEqualThen(CubeSample otherSample) {
        return Arrays.stream(CubeColor.values()).allMatch(color -> this.countFor(color) <= otherSample.countFor(color));
    }

}
