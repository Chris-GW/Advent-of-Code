package de.adventofcode.chrisgw.day02;

import java.util.regex.Pattern;
import java.util.stream.LongStream;


public record ProductIdRange(long start, long end) {

    public static final Pattern PRODUCT_ID_RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");


    public static ProductIdRange parse(String productIdRangeStr) {
        var matcher = PRODUCT_ID_RANGE_PATTERN.matcher(productIdRangeStr);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Could not parse product id range from: " + productIdRangeStr);
        }
        long start = Long.parseLong(matcher.group(1));
        long end = Long.parseLong(matcher.group(2));
        return new ProductIdRange(start, end);
    }


    public LongStream productIds() {
        return LongStream.rangeClosed(start, end);
    }


    @Override
    public String toString() {
        return "%d-%d".formatted(start, end);
    }

}
