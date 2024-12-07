package de.adventofcode.chrisgw.day06;

enum MapTileType {
    EMPTY('.'),
    OBSTACLE('#'),
    GUARD('^'),
    GUARD_PATH('X'),
    OUTSIDE(' ');


    private final char sign;

    MapTileType(char sign) {
        this.sign = sign;
    }

    public static MapTileType fromSign(char sign) {
        for (MapTileType mapTileType : MapTileType.values()) {
            if (mapTileType.sign == sign) {
                return mapTileType;
            }
        }
        throw new IllegalArgumentException("unknown type: " + sign);
    }


    public char getSign() {
        return sign;
    }


    @Override
    public String toString() {
        return String.valueOf(sign);
    }

}
