package de.adventofcode.chrisgw.day02;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public record CubeGameRecord(int id, List<CubeSample> cubeSamples) {

    public static final Pattern GAME_RECORD_PATTERN = Pattern.compile( //
            "Game\\s+(\\d+): ((.+)(;\\s*(.+)))");

    public static CubeGameRecord parseCubeGameRecord(String line) {
        var matcher = GAME_RECORD_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "expect game record matching pattern " + GAME_RECORD_PATTERN + ", but was: " + line);
        }
        int id = Integer.parseInt(matcher.group(1));
        List<CubeSample> cubeSamples = Arrays.stream(matcher.group(2).split(";"))
                .map(CubeSample::parseCubeSample)
                .toList();
        return new CubeGameRecord(id, cubeSamples);
    }

    public boolean isPossibleWith(CubeSample maxColorCount) {
        return cubeSamples.stream().allMatch(cubeSample -> cubeSample.isSmallerEqualThen(maxColorCount));
    }

}
