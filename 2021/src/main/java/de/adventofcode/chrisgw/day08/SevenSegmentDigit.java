package de.adventofcode.chrisgw.day08;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum SevenSegmentDigit {

    _0(0, SignalPattern.parseSignalPattern("abcefg")),
    _1(1, SignalPattern.parseSignalPattern("cf")),
    _2(2, SignalPattern.parseSignalPattern("acdeg")),
    _3(3, SignalPattern.parseSignalPattern("acdfg")),
    _4(4, SignalPattern.parseSignalPattern("bcdf")),
    _5(5, SignalPattern.parseSignalPattern("abdfg")),
    _6(6, SignalPattern.parseSignalPattern("abdefg")),
    _7(7, SignalPattern.parseSignalPattern("acf")),
    _8(8, SignalPattern.parseSignalPattern("abcdefg")),
    _9(9, SignalPattern.parseSignalPattern("abcdfg")),
    ;


    private final int number;
    private final SignalPattern signalPattern;


    SevenSegmentDigit(int number, SignalPattern signalPattern) {
        this.number = number;
        this.signalPattern = signalPattern;
    }


    public int number() {
        return number;
    }

    public SignalPattern signalPattern() {
        return signalPattern;
    }

    public int shownSegments() {
        return signalPattern.shownSegments();
    }


    public static Map<Integer, List<SevenSegmentDigit>> groupedByUsedSegments() {
        return Arrays.stream(SevenSegmentDigit.values()).collect(Collectors.groupingBy(SevenSegmentDigit::shownSegments));
    }

}
