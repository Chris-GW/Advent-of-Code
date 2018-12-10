package de.adventofcode.chrisgw.day09;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class MarbleManiaTest {

    @Test
    public void marbleMania_example01() {
        int playerCound = 9;
        int marbelCount = 25;
        int expectedBestPlayer = 5;
        int expectedBestPlayerScore = 23 + 9;

        MarbleMania marbleMania = new MarbleMania(marbelCount, playerCound);
        while (!marbleMania.isFinished()) {
            marbleMania.nextPlayerPlaceNextMarbel();
            System.out.println(marbleMania);
        }
        MarbelPlayer bestPlayer = marbleMania.bestPlayer();
        assertEquals("bestPlayer", expectedBestPlayer, bestPlayer.getPlayerId());
        assertEquals("bestPlayerScore", expectedBestPlayerScore, bestPlayer.getScore());
    }


    @Test
    public void marbleMania_example02() {
        String marbelManiaDescription = "10 players; last marble is worth 1618 points";
        int expectedBestPlayerScore = 8317;

        MarbleMania marbleMania = MarbleMania.fromMarbleManiaDescription(marbelManiaDescription);
        while (!marbleMania.isFinished()) {
            marbleMania.nextPlayerPlaceNextMarbel();
        }
        MarbelPlayer bestPlayer = marbleMania.bestPlayer();
        assertEquals("bestPlayerScore", expectedBestPlayerScore, bestPlayer.getScore());
    }

    @Test
    public void marbleMania_example03() {
        String marbelManiaDescription = "13 players; last marble is worth 7999 points";
        int expectedBestPlayerScore = 146373;

        MarbleMania marbleMania = MarbleMania.fromMarbleManiaDescription(marbelManiaDescription);
        while (!marbleMania.isFinished()) {
            marbleMania.nextPlayerPlaceNextMarbel();
        }
        MarbelPlayer bestPlayer = marbleMania.bestPlayer();
        assertEquals("bestPlayerScore", expectedBestPlayerScore, bestPlayer.getScore());
    }

    @Test
    public void marbleMania_example04() {
        String marbelManiaDescription = "17 players; last marble is worth 1104 points";
        int expectedBestPlayerScore = 2764;

        MarbleMania marbleMania = MarbleMania.fromMarbleManiaDescription(marbelManiaDescription);
        while (!marbleMania.isFinished()) {
            marbleMania.nextPlayerPlaceNextMarbel();
        }
        MarbelPlayer bestPlayer = marbleMania.bestPlayer();
        assertEquals("bestPlayerScore", expectedBestPlayerScore, bestPlayer.getScore());
    }

    @Test
    public void marbleMania_example05() {
        String marbelManiaDescription = "21 players; last marble is worth 6111 points";
        int expectedBestPlayerScore = 54718;

        MarbleMania marbleMania = MarbleMania.fromMarbleManiaDescription(marbelManiaDescription);
        while (!marbleMania.isFinished()) {
            marbleMania.nextPlayerPlaceNextMarbel();
        }
        MarbelPlayer bestPlayer = marbleMania.bestPlayer();
        assertEquals("bestPlayerScore", expectedBestPlayerScore, bestPlayer.getScore());
    }

    @Test
    public void marbleMania_example06() {
        String marbelManiaDescription = "30 players; last marble is worth 5807 points";
        int expectedBestPlayerScore = 37305;

        MarbleMania marbleMania = MarbleMania.fromMarbleManiaDescription(marbelManiaDescription);
        while (!marbleMania.isFinished()) {
            marbleMania.nextPlayerPlaceNextMarbel();
        }
        MarbelPlayer bestPlayer = marbleMania.bestPlayer();
        assertEquals("bestPlayerScore", expectedBestPlayerScore, bestPlayer.getScore());
    }


    @Test
    public void marbleMania_myPuzzleInput() {
        String marbelManiaDescription = "458 players; last marble is worth 71307 points";
        int expectedBestPlayerScore = 398048;

        MarbleMania marbleMania = MarbleMania.fromMarbleManiaDescription(marbelManiaDescription);
        while (!marbleMania.isFinished()) {
            marbleMania.nextPlayerPlaceNextMarbel();
        }
        MarbelPlayer bestPlayer = marbleMania.bestPlayer();
        assertEquals("bestPlayerScore", expectedBestPlayerScore, bestPlayer.getScore());
    }

}
