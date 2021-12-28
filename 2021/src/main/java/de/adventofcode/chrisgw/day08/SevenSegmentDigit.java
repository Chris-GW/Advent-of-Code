package de.adventofcode.chrisgw.day08;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

import static de.adventofcode.chrisgw.day08.SevenSegment.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;

public enum SevenSegmentDigit {

    _0(0, EnumSet.of(a, b, c, e, f, g)),
    _1(1, EnumSet.of(c, f)),
    _2(2, EnumSet.of(a, c, d, e, g)),
    _3(3, EnumSet.of(a, c, d, f, g)),
    _4(4, EnumSet.of(b, c, d, f)),
    _5(5, EnumSet.of(a, b, d, f, g)),
    _6(6, EnumSet.of(a, b, d, e, f, g)),
    _7(7, EnumSet.of(a, c, f)),
    _8(8, EnumSet.of(a, b, c, d, e, f, g)),
    _9(9, EnumSet.of(a, b, c, d, f, g)),
    ;


    private final int number;
    private final EnumSet<SevenSegment> shownSegments;


    SevenSegmentDigit(int number, EnumSet<SevenSegment> shownSegments) {
        this.number = number;
        this.shownSegments = shownSegments;
    }


    public int number() {
        return number;
    }

    public EnumSet<SevenSegment> shownSegments() {
        return shownSegments;
    }

    public int segmentsCount() {
        return shownSegments.size();
    }


    public static Map<Integer, EnumSet<SevenSegmentDigit>> groupedByUsedSegments() {
        return Arrays.stream(SevenSegmentDigit.values())
                .collect(groupingBy(SevenSegmentDigit::segmentsCount,
                        TreeMap::new,
                        toCollection(() -> EnumSet.noneOf(SevenSegmentDigit.class))));
    }

}
