package de.adventofcode.chrisgw.day02;

import lombok.Getter;

import java.util.Arrays;


public enum GameOutcome {

    LOSE(0, 'X'), //
    DRAW(3, 'Y'), //
    WIN(6, 'Z');

    @Getter
    private final int outcomeScore;
    @Getter
    private final char outcomeCode;

    GameOutcome(int outcomeScore, char outcomeCode) {
        this.outcomeScore = outcomeScore;
        this.outcomeCode = outcomeCode;
    }

    public static GameOutcome valueOfCode(char outcomeCode) {
        return Arrays.stream(GameOutcome.values())
                .filter(gameOutcome -> gameOutcome.getOutcomeCode() == outcomeCode)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Could not find GameOutcome for code: " + outcomeCode));
    }

}
