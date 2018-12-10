package de.adventofcode.chrisgw.day09;

import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MarbleMania {

    @Getter
    private int marbelCount;

    @Getter
    private int playerCount;


    private Iterator<Marbel> marbelsToPlace;

    private final Marbel firstMarbel;

    @Getter
    private Marbel currentMarbel;

    @Getter
    private MarbelPlayer currentPlayer;


    public MarbleMania(int marbelCount, int playerCount) {
        this.marbelCount = marbelCount;
        this.playerCount = playerCount;

        this.firstMarbel = new Marbel(0);
        this.currentMarbel = firstMarbel;
        this.currentPlayer = createMarbelPlayers(playerCount);
        this.marbelsToPlace = IntStream.rangeClosed(1, marbelCount).mapToObj(Marbel::new).iterator();
    }

    public static MarbleMania fromMarbleManiaDescription(String marbleManiaDescription) {
        Pattern marbelManiaPattern = Pattern.compile("(\\d+) players; last marble is worth (\\d+) points?",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = marbelManiaPattern.matcher(marbleManiaDescription);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect marbleManiaDescription matching pattern: " + marbelManiaPattern);
        }
        int playerCount = Integer.parseInt(matcher.group(1));
        int marbelCount = Integer.parseInt(matcher.group(2));
        return new MarbleMania(marbelCount, playerCount);
    }


    private MarbelPlayer createMarbelPlayers(int playerCount) {
        MarbelPlayer firstMarbelPlayer = new MarbelPlayer(1);
        MarbelPlayer currentPlayer = firstMarbelPlayer;
        for (int player = 2; player <= playerCount; player++) {
            MarbelPlayer marbelPlayer = new MarbelPlayer(player);
            currentPlayer.setNextPlayer(marbelPlayer);
            currentPlayer = marbelPlayer;
        }
        currentPlayer.setNextPlayer(firstMarbelPlayer);
        return firstMarbelPlayer;
    }


    public void nextPlayerPlaceNextMarbel() {
        if (!marbelsToPlace.hasNext()) {
            throw new IllegalStateException("This MarbleMania is finished");
        }
        int scoreForMarbelPlaceing;
        Marbel nextMarbel = marbelsToPlace.next();
        if (nextMarbel.isDivisibleBy(23)) {
            Marbel removedMarbel = currentMarbel.removeMarbel(-7);
            scoreForMarbelPlaceing = nextMarbel.getValue() + removedMarbel.getValue();
            currentMarbel = removedMarbel.getClockwiseMarbel();
        } else {
            currentMarbel.placeMarbelBetween(nextMarbel, 1, 2);
            currentMarbel = nextMarbel;
            scoreForMarbelPlaceing = 0;
        }

        currentPlayer.addScore(scoreForMarbelPlaceing);
        currentPlayer = currentPlayer.getNextPlayer();
    }


    public boolean isFinished() {
        return !marbelsToPlace.hasNext();
    }

    public MarbelPlayer bestPlayer() {
        return marbelPlayers().max(Comparator.comparingInt(MarbelPlayer::getScore)).orElse(null);
    }


    public Stream<MarbelPlayer> marbelPlayers() {
        return Stream.iterate(currentPlayer, MarbelPlayer::getNextPlayer).limit(playerCount);
    }

    public Stream<Marbel> marbels() {
        return Stream.iterate(firstMarbel, Marbel::getClockwiseMarbel)
                .limit(Math.min(currentMarbelValue() + 1, marbelCount));
    }

    private int currentMarbelValue() {
        return currentMarbel.getValue();
    }


    @Override
    public String toString() {
        int playerId = currentPlayer.getPlayerId();
        String joinedMarbelStr = marbels().map(marbel -> {
            if (marbel.equals(currentMarbel)) {
                return String.format("(%2d)", marbel.getValue());
            } else {
                return String.format(" %2d ", marbel.getValue());
            }
        }).collect(Collectors.joining(""));
        return String.format("[%2d] ", playerId) + joinedMarbelStr;
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);

    }

}
