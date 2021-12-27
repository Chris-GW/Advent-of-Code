package de.adventofcode.chrisgw.day08;

import lombok.Data;

import java.util.BitSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;

@Data
public class SignalPattern {

    private final BitSet bitSet = new BitSet(8);


    public static SignalPattern parseSignalPattern(String signalPattern) {
        SignalPattern signal = new SignalPattern();
        signalPattern.chars()
                .map(Character::toLowerCase)
                .map(letter -> letter - 'a')
                .forEach(signal.bitSet::set);
        return signal;
    }


    public int shownSegments() {
        return (int) IntStream.range(0, bitSet.size()).filter(bitSet::get).count();
    }

    public Optional<SevenSegmentDigit> easySegmentDigit() {
        List<SevenSegmentDigit> possibleDigits = SevenSegmentDigit.groupedByUsedSegments()
                .getOrDefault(shownSegments(), emptyList());
        if (possibleDigits.size() == 1) {
            return Optional.of(possibleDigits.get(0));
        }
        return Optional.empty();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(bitSet.size());
        for (int i = 0; i < bitSet.size(); i++) {
            if (bitSet.get(i)) {
                char segmentLetter = (char) ('a' + i);
                sb.append(segmentLetter);
            }
        }
        return sb.toString();
    }

}
