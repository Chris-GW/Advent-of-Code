package de.adventofcode.chrisgw.day03;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
public class GridDirection {

    public static final Pattern GRID_DIRECTION_PATTERN = Pattern.compile("([URDL])(\\d+)");

    private final Direction direction;
    private final int length;


    public static GridDirection parse(String gridDirectionStr) {
        Matcher matcher = GRID_DIRECTION_PATTERN.matcher(gridDirectionStr);
        if (matcher.matches()) {
            MatchResult matcherResult = matcher.toMatchResult();
            char directionLetter = matcherResult.group(1).charAt(0);
            Direction direction = Direction.forDirectionLetter(directionLetter);
            int length = Integer.parseInt(matcherResult.group(2));
            return new GridDirection(direction, length);
        } else {
            throw new IllegalArgumentException("Expect GridDirection matching: " + GRID_DIRECTION_PATTERN);
        }
    }


    public List<GridPoint> pathFrom(GridPoint startingPoint) {
        List<GridPoint> path = new ArrayList<>(length);
        GridPoint currentPoint = startingPoint;
        for (int i = 0; i < length; i++) {
            currentPoint = currentPoint.moveTo(direction);
            path.add(currentPoint);
        }
        return path;
    }


}
