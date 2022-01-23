package de.adventofcode.chrisgw.day13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record PaperDot(int x, int y) {

    public static final Pattern DOT_PATTERN = Pattern.compile("(\\d+),(\\d+)");


    public static PaperDot parseDot(String line) {
        Matcher matcher = DOT_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matches '%s', but was: %s"
                    .formatted(DOT_PATTERN, line));
        }
        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));
        return new PaperDot(x, y);
    }

}
