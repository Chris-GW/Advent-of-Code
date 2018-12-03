package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.day03.CutRectangle.SquarePositon;
import lombok.Builder;
import lombok.Value;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


@Value
@Builder
public class CutRectangle implements Iterable<SquarePositon> {

    public static final Pattern RECTANGLE_SPECIFICATION_PATTERN = Pattern.compile(
            "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

    final long id;
    final int distanceLeft;
    final int distanceTop;
    final int wide;
    final int tall;


    public static CutRectangle parse(String rectangleSpecificationString) {
        Matcher matcher = RECTANGLE_SPECIFICATION_PATTERN.matcher(rectangleSpecificationString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Expect rectangleSpecification to match Pattern: " + RECTANGLE_SPECIFICATION_PATTERN);
        }
        long id = Long.parseLong(matcher.group(1));
        int distanceLeft = Integer.parseInt(matcher.group(2));
        int distanceTop = Integer.parseInt(matcher.group(3));
        int wide = Integer.parseInt(matcher.group(4));
        int tall = Integer.parseInt(matcher.group(5));
        return CutRectangle.builder()
                .id(id)
                .distanceLeft(distanceLeft)
                .distanceTop(distanceTop)
                .wide(wide)
                .tall(tall)
                .build();
    }

    public SquarePositon topLeft() {
        return new SquarePositon(distanceLeft, distanceTop);
    }

    public SquarePositon bottomRight() {
        return new SquarePositon(distanceLeft + wide, distanceTop + tall);
    }

    public int squares() {
        return wide * tall;
    }


    public Stream<SquarePositon> squarePositons() {
        Stream.Builder<SquarePositon> squarePositionStreamBuilder = Stream.builder();
        for (int x = distanceLeft; x < distanceLeft + wide; x++) {
            for (int y = distanceTop; y < distanceTop + tall; y++) {
                squarePositionStreamBuilder.add(new SquarePositon(x, y));
            }
        }
        return squarePositionStreamBuilder.build();
    }

    @Override
    public Iterator<SquarePositon> iterator() {
        return squarePositons().iterator();
    }


    public char rectangleLetter() {
        if (id < 10) {
            return (char) ('0' + id);
        } else {
            return (char) ('A' + id - 10);
        }
    }


    @Value
    public static class SquarePositon {

        public final int x;
        public final int y;


        public String toString() {
            return "(" + x + ";" + y + ")";
        }

    }

}
