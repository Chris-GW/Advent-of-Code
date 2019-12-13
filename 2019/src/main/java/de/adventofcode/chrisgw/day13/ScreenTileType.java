package de.adventofcode.chrisgw.day13;

public enum ScreenTileType {

    EMPTY(0), //
    WALL(1), //
    BLOCK(2), //
    HORIZONTAL_PADDLE(3), //
    BALL(4);

    private final long tileId;

    ScreenTileType(long tileId) {
        this.tileId = tileId;
    }

    public static ScreenTileType fromTileId(long tileId) {
        for (ScreenTileType tile : values()) {
            if (tile.getTileId() == tileId) {
                return tile;
            }
        }
        throw new IllegalArgumentException("Unknonw tileId: " + tileId);
    }


    public long getTileId() {
        return tileId;
    }

}
