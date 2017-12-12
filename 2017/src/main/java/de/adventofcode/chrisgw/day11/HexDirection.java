package de.adventofcode.chrisgw.day11;

public enum HexDirection {

    NORTH("n"), NORTH_WEST("nw"), NORTH_EAST("ne"), //
    SOUTH("s"), SOUTH_WEST("sw"), SOUTH_EAST("se");

    private String shortName;


    HexDirection(String shortName) {
        this.shortName = shortName;
    }


    public static HexDirection fromShortName(String shortName) {
        for (HexDirection hexDirection : HexDirection.values()) {
            if (hexDirection.shortName.equalsIgnoreCase(shortName)) {
                return hexDirection;
            }
        }
        throw new IllegalArgumentException("Unknown shortName " + shortName);
    }

    public String getShortName() {
        return shortName;
    }

}
