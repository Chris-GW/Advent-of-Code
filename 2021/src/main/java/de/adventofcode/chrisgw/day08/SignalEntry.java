package de.adventofcode.chrisgw.day08;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public record SignalEntry(SignalPattern[] observedSignalPatterns,
                          SignalPattern[] outputSignalPatterns) {

    public static final Pattern signalEntryPattern = Pattern.compile("((?:[a-g]{1,8}\\s){10})\\|((?:\\s[a-g]{1,8}){4})");

    public static SignalEntry parseSignalEntry(String line) {
        Matcher matcher = signalEntryPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect SignalEntry line matching pattern " + signalEntryPattern + ", but was: " + line);
        }
        SignalPattern[] observedSignalPatterns = Arrays.stream(matcher.group(1).trim().split("\\s"))
                .map(SignalPattern::parseSignalPattern)
                .toArray(SignalPattern[]::new);
        SignalPattern[] outputSignalPatterns = Arrays.stream(matcher.group(2).trim().split("\\s"))
                .map(SignalPattern::parseSignalPattern)
                .toArray(SignalPattern[]::new);
        return new SignalEntry(observedSignalPatterns, outputSignalPatterns);
    }


    public Stream<SignalPattern> easyOutputDigits() {
        return Arrays.stream(outputSignalPatterns)
                .filter(signalPattern -> signalPattern.easySegmentDigit().isPresent());
    }


    @Override
    public String toString() {
        String observedSignals = Arrays.stream(observedSignalPatterns)
                .map(SignalPattern::toString)
                .collect(joining(" "));
        String outputSignals = Arrays.stream(outputSignalPatterns)
                .map(SignalPattern::toString)
                .collect(joining(" "));
        return observedSignals + " | " + outputSignals;
    }

}
