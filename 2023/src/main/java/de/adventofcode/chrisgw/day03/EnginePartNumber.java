package de.adventofcode.chrisgw.day03;

public record EnginePartNumber(int x, int y, int partNumber) {

    public int partNumberLength() {
        return String.valueOf(partNumber).length();
    }

}
