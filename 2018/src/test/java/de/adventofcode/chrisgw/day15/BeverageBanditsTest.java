package de.adventofcode.chrisgw.day15;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class BeverageBanditsTest {


    @Test
    public void moveIntoRangeOfNearestEnemyUnit_example01() {
        List<String> gameAreaLines = Arrays.asList( //
                "#######", //
                "#E..G.#", //
                "#...#.#", //
                "#.G.#G#", //
                "#######");
        String expectedGameStr = "" //
                + "#######\n" //
                + "#.E.G.#\n" //
                + "#...#.#\n" //
                + "#.G.#G#\n" //
                + "#######";

        BeverageBandits game = new BeverageBandits(gameAreaLines);
        System.out.println(game);
        System.out.println();
        game.nextTurn();

        String gameAreaStr = game.toString();
        System.out.println(gameAreaStr);
        Assert.assertEquals("gameAreaStr", expectedGameStr, gameAreaStr);
    }


    @Test
    public void moveIntoRangeOfNearestEnemyUnit_example02() {
        List<String> gameAreaLines = Arrays.asList( //
                "#######", //
                "#.E...#", //
                "#.....#", //
                "#...G.#", //
                "#######");
        String expectedGameStr = "" //
                + "#######\n" //
                + "#..E..#\n" //
                + "#.....#\n" //
                + "#...G.#\n" //
                + "#######";

        BeverageBandits game = new BeverageBandits(gameAreaLines);
        System.out.println(game);
        System.out.println();
        game.nextTurn();

        String gameAreaStr = game.toString();
        System.out.println(gameAreaStr);
        Assert.assertEquals("gameAreaStr", expectedGameStr, gameAreaStr);
    }


    @Test
    public void nextTurn_example01() {
        List<String> gameAreaLines = Arrays.asList( //
                "#########", //
                "#G..G..G#", //
                "#.......#", //
                "#.......#", //
                "#G..E..G#", //
                "#.......#", //
                "#.......#", //
                "#G..G..G#", //
                "#########");

        List<String> expectedGameTurnStr = Arrays.asList( //
                "" // round 00
                        + "#########\n" //
                        + "#G..G..G#\n" //
                        + "#.......#\n" //
                        + "#.......#\n" //
                        + "#G..E..G#\n" //
                        + "#.......#\n" //
                        + "#.......#\n" //
                        + "#G..G..G#\n" //
                        + "#########", //
                "" // round 01
                        + "#########\n" //
                        + "#.G...G.#\n" //
                        + "#...G...#\n" //
                        + "#...E..G#\n" //
                        + "#.G.....#\n" //
                        + "#.......#\n" //
                        + "#G..G..G#\n" //
                        + "#.......#\n" //
                        + "#########", //
                "" // round 02
                        + "#########\n" //
                        + "#..G.G..#\n" //
                        + "#...G...#\n" //
                        + "#.G.E.G.#\n" //
                        + "#.......#\n" //
                        + "#G..G..G#\n" //
                        + "#.......#\n" //
                        + "#.......#\n" //
                        + "#########", //
                "" // round 03
                        + "#########\n" //
                        + "#.......#\n" //
                        + "#..GGG..#\n" //
                        + "#..GEG..#\n" //
                        + "#G..G...#\n" //
                        + "#......G#\n" //
                        + "#.......#\n" //
                        + "#.......#\n" //
                        + "#########");

        BeverageBandits game = new BeverageBandits(gameAreaLines);

        for (int trun = 0; trun < expectedGameTurnStr.size(); trun++) {
            String gameAreaStr = game.toString();
            System.out.println(gameAreaStr);
            Assert.assertEquals("gameAreaStr", expectedGameTurnStr.get(trun), gameAreaStr);
            game.nextBattleRound();
        }
    }

}
