package de.adventofcode.chrisgw.day05;

import java.util.regex.Pattern;


public record RearrangementProcedure(int quantity, int fromStackIndex, int toStackIndex) {

    public static final Pattern REARRANGEMENT_PROCEDURE_PATTERN = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

    public static RearrangementProcedure parseRearrangementProcedure(String line) {
        var matcher = REARRANGEMENT_PROCEDURE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Expect line matching patter '%s', but was: '%s'".formatted(REARRANGEMENT_PROCEDURE_PATTERN, line));
        }
        int quantity = Integer.parseInt(matcher.group(1));
        int fromStackIndex = Integer.parseInt(matcher.group(2));
        int toStackIndex = Integer.parseInt(matcher.group(3));
        return new RearrangementProcedure(quantity, fromStackIndex, toStackIndex);
    }



    @Override
    public String toString() {
        return "move %d from %d to %d".formatted(quantity(), fromStackIndex(), toStackIndex());
    }

}
