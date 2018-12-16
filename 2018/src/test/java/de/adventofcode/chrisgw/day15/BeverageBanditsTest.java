package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day15.BeverageBandits.GameUnit;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


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

        String gameAreaStr = withoutUnitStatus(game);
        System.out.println(gameAreaStr);
        assertEquals("gameAreaStr", expectedGameStr, gameAreaStr);
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

        String gameAreaStr = withoutUnitStatus(game);
        System.out.println(gameAreaStr);
        assertEquals("gameAreaStr", expectedGameStr, gameAreaStr);
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
            System.out.println(game);
            assertEquals("gameAreaStr", expectedGameTurnStr.get(trun), withoutUnitStatus(game));
            game.nextBattleRound();
        }
    }


    @Test
    public void nextTurn_attack_example01() {
        List<String> gameAreaLines = Arrays.asList( //
                "#######", //
                "#G....#", //
                "#..G..#", //
                "#..EG.#", //
                "#..G..#", //
                "#...G.#", //
                "#######");

        BeverageBandits game = new BeverageBandits(gameAreaLines);
        game.fieldAt(1, 1).getUnit().setHitPoints(9);
        game.fieldAt(3, 2).getUnit().setHitPoints(4);
        game.fieldAt(4, 3).getUnit().setHitPoints(2);
        game.fieldAt(3, 4).getUnit().setHitPoints(2);
        game.fieldAt(4, 5).getUnit().setHitPoints(1);

        System.out.println(game);
        game.nextBattleRound();
        System.out.println(game);
        GameUnit unit = game.fieldAt(4, 3).getUnit();
        assertNull("Goblin (4;3) is dead", unit);
    }


    @Test
    public void nextBattleround_example01() {
        List<String> gameAreaLines = Arrays.asList( //
                "#######", //
                "#.G...#", //
                "#...EG#", //
                "#.#.#G#", //
                "#..G#E#", //
                "#.....#", //
                "#######");

        Map<Integer, String> expectedGameRoundStr = new HashMap<>();
        expectedGameRoundStr.put(0, "" // round 00
                + "#######   \n" //
                + "#.G...#   G(200)\n" //
                + "#...EG#   E(200), G(200)\n" //
                + "#.#.#G#   G(200)\n" //
                + "#..G#E#   G(200), E(200)\n" //
                + "#.....#   \n" //
                + "#######   ");
        expectedGameRoundStr.put(1, "" // round 01
                + "#######   \n" //
                + "#..G..#   G(200)\n" //
                + "#...EG#   E(197), G(197)\n" //
                + "#.#G#G#   G(200), G(197)\n" //
                + "#...#E#   E(197)\n" //
                + "#.....#   \n" //
                + "#######   ");
        expectedGameRoundStr.put(2, "" // round 02
                + "#######   \n" //
                + "#...G.#   G(200)\n" //
                + "#..GEG#   G(200), E(188), G(194)\n" //
                + "#.#.#G#   G(194)\n" //
                + "#...#E#   E(194)\n" //
                + "#.....#   \n" //
                + "#######   ");

        expectedGameRoundStr.put(23, "" // round 23
                + "#######   \n" //
                + "#...G.#   G(200)\n" //
                + "#..G.G#   G(200), G(131)\n" //
                + "#.#.#G#   G(131)\n" //
                + "#...#E#   E(131)\n" //
                + "#.....#   \n" //
                + "#######   ");
        expectedGameRoundStr.put(24, "" // round 24
                + "#######   \n" //
                + "#..G..#   G(200)\n" //
                + "#...G.#   G(131)\n" //
                + "#.#G#G#   G(200), G(128)\n" //
                + "#...#E#   E(128)\n" //
                + "#.....#   \n" //
                + "#######   ");
        expectedGameRoundStr.put(25, "" // round 25
                + "#######   \n" //
                + "#.G...#   G(200)\n" //
                + "#..G..#   G(131)\n" //
                + "#.#.#G#   G(125)\n" //
                + "#..G#E#   G(200), E(125)\n" //
                + "#.....#   \n" //
                + "#######   ");
        expectedGameRoundStr.put(26, "" // round 26
                + "#######   \n" //
                + "#G....#   G(200)\n" //
                + "#.G...#   G(131)\n" //
                + "#.#.#G#   G(122)\n" //
                + "#...#E#   E(122)\n" //
                + "#..G..#   G(200)\n" //
                + "#######   ");
        expectedGameRoundStr.put(27, "" // round 27
                + "#######   \n" //
                + "#G....#   G(200)\n" //
                + "#.G...#   G(131)\n" //
                + "#.#.#G#   G(119)\n" //
                + "#...#E#   E(119)\n" //
                + "#...G.#   G(200)\n" //
                + "#######   ");
        expectedGameRoundStr.put(28, "" // round 28
                + "#######   \n" //
                + "#G....#   G(200)\n" //
                + "#.G...#   G(131)\n" //
                + "#.#.#G#   G(116)\n" //
                + "#...#E#   E(113)\n" //
                + "#....G#   G(200)\n" //
                + "#######   ");

        expectedGameRoundStr.put(47, "" // round 47
                + "#######   \n" //
                + "#G....#   G(200)\n" //
                + "#.G...#   G(131)\n" //
                + "#.#.#G#   G(59)\n" //
                + "#...#.#   \n" //
                + "#....G#   G(200)\n" //
                + "#######   ");


        int expectedBattleRounds = 47;
        int expectedRemainingUnitHitPointSum = 200 + 131 + 59 + 200; // 590
        int expectedFinishingScore = expectedBattleRounds * expectedRemainingUnitHitPointSum; // 27730

        BeverageBandits game = new BeverageBandits(gameAreaLines);

        for (int rounds = 0; !game.isOnlyOneFactionAlive(); rounds++) {
            System.out.println("After " + rounds + ": ");
            System.out.println(game);
            System.out.println();
            if (expectedGameRoundStr.containsKey(rounds)) {
                String expectedGameStr = expectedGameRoundStr.get(rounds);
                assertEquals("game after " + rounds, expectedGameStr, game.toString());
            }
            game.nextBattleRound();
        }
        int battleRounds = game.getBattleRounds();
        int finishingGameScore = game.calculateFinishingGameScore();
        System.out.println("After " + battleRounds + ": " + finishingGameScore);
        System.out.println(game);

        assertEquals("expectedBattleRounds", expectedBattleRounds, battleRounds);
        assertEquals("expectedFinishingScore", expectedFinishingScore, finishingGameScore);
    }


    @Test
    public void nextBattleround_example02() {
        List<String> gameAreaLines = Arrays.asList( //
                "#######", //
                "#G..#E#", //
                "#E#E.E#", //
                "#G.##.#", //
                "#...#E#", //
                "#...E.#", //
                "#######");

        String expectedGameEndingStr = "" // round 37
                + "#######   \n" //
                + "#...#E#   E(200)\n" //
                + "#E#...#   E(197)\n" //
                + "#.E##.#   E(185)\n" //
                + "#E..#E#   E(200), E(200)\n" //
                + "#.....#   \n" //
                + "#######   ";

        int expectedBattleRounds = 37;
        int expectedRemainingUnitHitPointSum = 982;
        int expectedFinishingScore = expectedBattleRounds * expectedRemainingUnitHitPointSum; // 36334

        BeverageBandits game = new BeverageBandits(gameAreaLines);
        System.out.println(game);
        System.out.println();

        for (int rounds = 0; !game.isOnlyOneFactionAlive(); rounds++) {
            game.nextBattleRound();
        }
        int battleRounds = game.getBattleRounds();
        int finishingGameScore = game.calculateFinishingGameScore();
        System.out.println("After " + battleRounds + ": " + finishingGameScore);
        System.out.println(game);

        assertEquals("gameEnd", expectedGameEndingStr, game.toString());
        assertEquals("expectedBattleRounds", expectedBattleRounds, battleRounds);
        assertEquals("expectedFinishingScore", expectedFinishingScore, finishingGameScore);
    }


    @Test
    public void nextBattleround_example03() {
        List<String> gameAreaLines = Arrays.asList( //
                "#######", //
                "#E..EG#", //
                "#.#G.E#", //
                "#E.##E#", //
                "#G..#.#", //
                "#..E#.#", //
                "#######");

        String expectedGameEndingStr = "" // round 46
                + "#######   \n" //
                + "#.E.E.#   E(164), E(197)\n" //
                + "#.#E..#   E(200)\n" //
                + "#E.##.#   E(98)\n" //
                + "#.E.#.#   E(200)\n" //
                + "#...#.#   \n" //
                + "#######   ";

        int expectedBattleRounds = 46;
        int expectedRemainingUnitHitPointSum = 859;
        int expectedFinishingScore = expectedBattleRounds * expectedRemainingUnitHitPointSum; // 39514

        BeverageBandits game = new BeverageBandits(gameAreaLines);
        System.out.println(game);
        System.out.println();

        for (int rounds = 0; !game.isOnlyOneFactionAlive(); rounds++) {
            game.nextBattleRound();
        }
        int battleRounds = game.getBattleRounds();
        int finishingGameScore = game.calculateFinishingGameScore();
        System.out.println("After " + battleRounds + ": " + finishingGameScore);
        System.out.println(game);

        assertEquals("gameEnd", expectedGameEndingStr, game.toString());
        assertEquals("expectedBattleRounds", expectedBattleRounds, battleRounds);
        assertEquals("expectedFinishingScore", expectedFinishingScore, finishingGameScore);
    }


    @Test
    public void nextBattleround_example04() {
        List<String> gameAreaLines = Arrays.asList( //
                "#######", //
                "#E.G#.#", //
                "#.#G..#", //
                "#G.#.G#", //
                "#G..#.#", //
                "#...E.#", //
                "#######");

        String expectedGameEndingStr = "" // round 35
                + "#######   \n" //
                + "#G.G#.#   G(200), G(98)\n" //
                + "#.#G..#   G(200)\n" //
                + "#..#..#   \n" //
                + "#...#G#   G(95)\n" //
                + "#...G.#   G(200)\n" //
                + "#######   ";

        int expectedBattleRounds = 35;
        int expectedRemainingUnitHitPointSum = 793;
        int expectedFinishingScore = expectedBattleRounds * expectedRemainingUnitHitPointSum; // 27755

        BeverageBandits game = new BeverageBandits(gameAreaLines);
        System.out.println(game);
        System.out.println();

        for (int rounds = 0; !game.isOnlyOneFactionAlive(); rounds++) {
            game.nextBattleRound();
        }
        int battleRounds = game.getBattleRounds();
        int finishingGameScore = game.calculateFinishingGameScore();
        System.out.println("After " + battleRounds + ": " + finishingGameScore);
        System.out.println(game);

        assertEquals("gameEnd", expectedGameEndingStr, game.toString());
        assertEquals("expectedBattleRounds", expectedBattleRounds, battleRounds);
        assertEquals("expectedFinishingScore", expectedFinishingScore, finishingGameScore);
    }

    @Test
    public void nextBattleround_example05() {
        List<String> gameAreaLines = Arrays.asList( //
                "#######", //
                "#.E...#", //
                "#.#..G#", //
                "#.###.#", //
                "#E#G#G#", //
                "#...#G#", //
                "#######");

        String expectedGameEndingStr = "" // round 54
                + "#######   \n" //
                + "#.....#   \n" //
                + "#.#G..#   G(200)\n" //
                + "#.###.#   \n" //
                + "#.#.#.#   \n" //
                + "#G.G#G#   G(98), G(38), G(200)\n" //
                + "#######   ";

        int expectedBattleRounds = 54;
        int expectedRemainingUnitHitPointSum = 536;
        int expectedFinishingScore = expectedBattleRounds * expectedRemainingUnitHitPointSum; // 28944

        BeverageBandits game = new BeverageBandits(gameAreaLines);
        System.out.println(game);
        System.out.println();

        for (int rounds = 0; !game.isOnlyOneFactionAlive(); rounds++) {
            game.nextBattleRound();
        }
        int battleRounds = game.getBattleRounds();
        int finishingGameScore = game.calculateFinishingGameScore();
        System.out.println("After " + battleRounds + ": " + finishingGameScore);
        System.out.println(game);

        assertEquals("gameEnd", expectedGameEndingStr, game.toString());
        assertEquals("expectedBattleRounds", expectedBattleRounds, battleRounds);
        assertEquals("expectedFinishingScore", expectedFinishingScore, finishingGameScore);
    }


    @Test
    public void nextBattleround_example06() {
        List<String> gameAreaLines = Arrays.asList( //
                "#########", //
                "#G......#", //
                "#.E.#...#", //
                "#..##..G#", //
                "#...##..#", //
                "#...#...#", //
                "#.G...G.#", //
                "#.....G.#", //
                "#########");

        String expectedGameEndingStr = "" // round 20
                + "#########   \n" //
                + "#.G.....#   G(137)\n" //
                + "#G.G#...#   G(200), G(200)\n" //
                + "#.G##...#   G(200)\n" //
                + "#...##..#   \n" //
                + "#.G.#...#   G(200)\n" //
                + "#.......#   \n" //
                + "#.......#   \n" //
                + "#########   ";

        int expectedBattleRounds = 20;
        int expectedRemainingUnitHitPointSum = 937;
        int expectedFinishingScore = expectedBattleRounds * expectedRemainingUnitHitPointSum; // 18740

        BeverageBandits game = new BeverageBandits(gameAreaLines);
        System.out.println(game);
        System.out.println();

        for (int rounds = 0; !game.isOnlyOneFactionAlive(); rounds++) {
            game.nextBattleRound();
        }
        int battleRounds = game.getBattleRounds();
        int finishingGameScore = game.calculateFinishingGameScore();
        System.out.println("After " + battleRounds + ": " + finishingGameScore);
        System.out.println(game);

        assertEquals("gameEnd", expectedGameEndingStr, game.toString());
        assertEquals("expectedBattleRounds", expectedBattleRounds, battleRounds);
        assertEquals("expectedFinishingScore", expectedFinishingScore, finishingGameScore);
    }


    @Test
    public void nextBattleround_myPuzzleInput() throws Exception {
        Path myGameFile = TestUtils.getResourcePath("/day15/myPuzzleInput.txt");
        List<String> gameAreaLines = Files.readAllLines(myGameFile);

        int expectedBattleRounds = 71;
        int expectedRemainingUnitHitPointSum = 2775;
        int expectedFinishingScore = expectedBattleRounds * expectedRemainingUnitHitPointSum; // 197025

        BeverageBandits game = new BeverageBandits(gameAreaLines);
        System.out.println(game);
        System.out.println();

        for (int rounds = 0; !game.isOnlyOneFactionAlive(); rounds++) {
            game.nextBattleRound();
        }
        int battleRounds = game.getBattleRounds();
        int finishingGameScore = game.calculateFinishingGameScore();
        System.out.println("After " + battleRounds + ": " + finishingGameScore);
        System.out.println(game);

        assertEquals("expectedBattleRounds", expectedBattleRounds, battleRounds);
        assertEquals("expectedFinishingScore", expectedFinishingScore, finishingGameScore);
    }


    private static String withoutUnitStatus(BeverageBandits game) {
        String gameStr = game.toString();
        return gameStr.replaceAll("[ ]+(.*?)\n", "\n").trim();
    }

}
