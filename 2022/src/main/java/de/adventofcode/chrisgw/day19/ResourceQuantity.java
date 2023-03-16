package de.adventofcode.chrisgw.day19;

public record ResourceQuantity(Resource resource, int quantity) {

    @Override
    public String toString() {
        return quantity + " " + resource;
    }

}
