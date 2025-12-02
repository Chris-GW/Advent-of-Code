package de.adventofcode.chrisgw.day02;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.LongStream;
import java.util.stream.Stream;


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


    public long invalidProductIdSum() {
        return productIds().filter(Predicate.not(ProductId::isValid)).mapToLong(ProductId::id).sum();
    }


    public Stream<ProductId> productIds() {
        return LongStream.rangeClosed(start, end).mapToObj(ProductId::new);
    }


    @Override
    public String toString() {
        return "%d-%d".formatted(start, end);
    }

}
