package de.adventofcode.chrisgw.day05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

record PagePrintOrderRule(int page, int beforePage) {

    public static PagePrintOrderRule parse(String line) {
        Pattern pattern = Pattern.compile("(\\d+)\\|(\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matching pattern " + pattern + " but was: " + line);
        }
        int page = Integer.parseInt(matcher.group(1));
        int pageBefore = Integer.parseInt(matcher.group(2));
        return new PagePrintOrderRule(page, pageBefore);
    }


    @Override
    public String toString() {
        return page + "|" + beforePage;
    }

}
