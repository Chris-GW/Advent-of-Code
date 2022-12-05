package de.adventofcode.chrisgw.day05;

public record StackableCargoCrate(char crateCode) {


    @Override
    public String toString() {
        return String.valueOf(crateCode);
    }

}
