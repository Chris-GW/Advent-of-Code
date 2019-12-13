package de.adventofcode.chrisgw.day13;

/**
 * 2019 Day 13: Care Package
 * https://adventofcode.com/2019/day/13
 */
public class AdventOfCodeDay13 {

    private AdventOfCodeDay13() {

    }

    public static long countBlockTilesOnScreen(ArcadeCabinet arcadeCabinet) {
        arcadeCabinet.startGame();
        return arcadeCabinet.countTiles(ScreenTileType.BLOCK);
    }

}
