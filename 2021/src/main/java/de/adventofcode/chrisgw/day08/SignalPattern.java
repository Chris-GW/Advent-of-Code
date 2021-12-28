package de.adventofcode.chrisgw.day08;

import lombok.Data;

import java.util.EnumSet;
import java.util.Optional;

@Data
public class SignalPattern {

    private final EnumSet<SevenSegment> segments;


    public static SignalPattern parseSignalPattern(String signalPattern) {
        EnumSet<SevenSegment> segments = EnumSet.noneOf(SevenSegment.class);
        for (int i = 0; i < signalPattern.length(); i++) {
            char segmentLetter = signalPattern.charAt(i);
            segments.add(SevenSegment.valueOf(segmentLetter));
        }
        return new SignalPattern(segments);
    }


    public int shownSegments() {
        return segments.size();
    }

    public Optional<SevenSegmentDigit> easySegmentDigit() {
        EnumSet<SevenSegmentDigit> possibleDigits = possibleDigits();
        if (possibleDigits.size() == 1) {
            return possibleDigits.stream().findAny();
        }
        return Optional.empty();
    }

    public EnumSet<SevenSegmentDigit> possibleDigits() {
        return SevenSegmentDigit.groupedByUsedSegments()
                .getOrDefault(shownSegments(), EnumSet.noneOf(SevenSegmentDigit.class));
    }


    @Override
    public String toString() {
        return segments.toString();
    }

}
