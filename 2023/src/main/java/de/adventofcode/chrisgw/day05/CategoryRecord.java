package de.adventofcode.chrisgw.day05;

public record CategoryRecord(Category category, long number) {


    @Override
    public String toString() {
        return category.name().toLowerCase() + " " + number;
    }

}
