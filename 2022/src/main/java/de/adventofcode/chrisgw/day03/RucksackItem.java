package de.adventofcode.chrisgw.day03;

public record RucksackItem(char itemType) {

    public int priority() {
        if (Character.isUpperCase(itemType())) {
            return this.itemType() - 'A' + 27;
        } else {
            return this.itemType() - 'a' + 1;
        }
    }

}
