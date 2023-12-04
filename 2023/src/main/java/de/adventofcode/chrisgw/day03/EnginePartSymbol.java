package de.adventofcode.chrisgw.day03;

public record EnginePartSymbol(int x, int y, char symbol) {


    public boolean isGear() {
        return symbol() == '*';
    }

}
