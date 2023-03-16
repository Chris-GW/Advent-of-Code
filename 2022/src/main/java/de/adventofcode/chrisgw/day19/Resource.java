package de.adventofcode.chrisgw.day19;

public enum Resource {
    ORE, CLAY, OBSIDIAN, GEODE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
