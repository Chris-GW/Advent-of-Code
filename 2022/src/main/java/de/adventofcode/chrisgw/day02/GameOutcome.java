package de.adventofcode.chrisgw.day02;

import lombok.Getter;


public enum GameOutcome {

    LOSE(0), //
    DRAW(3), //
    WIN(6);

    @Getter
    private final int outcomeScore;

    GameOutcome(int outcomeScore) {
        this.outcomeScore = outcomeScore;
    }

}
