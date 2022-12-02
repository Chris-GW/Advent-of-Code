package de.adventofcode.chrisgw.day02;

import lombok.Getter;

import java.util.Arrays;


@Getter
public enum HandShape {

    ROCK(1, 'A', 'X'), //
    PAPER(2, 'B', 'Y'), //
    SCISSORS(3, 'C', 'Z');

    private final char firstPlayerCode;
    private final char secondPlayerCode;
    private final int shapeScore;


    HandShape(int shapeScore, char firstPlayerCode, char secondPlayerCode) {
        this.shapeScore = shapeScore;
        this.firstPlayerCode = firstPlayerCode;
        this.secondPlayerCode = secondPlayerCode;
    }


    public boolean hasCode(char code) {
        return firstPlayerCode == code || secondPlayerCode == code;
    }


    public static HandShape valueOf(char code) {
        return Arrays.stream(HandShape.values())
                .filter(handShape -> handShape.hasCode(code))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Could not find HandShape for code: " + code));
    }


    public GameOutcome outcomeVersus(HandShape otherHandShape) {
        if (this.equals(otherHandShape)) {
            return GameOutcome.DRAW;
        } else if (isWinningOutcomeVersus(otherHandShape)) {
            return GameOutcome.WIN;
        } else {
            return GameOutcome.LOSE;
        }
    }

    private boolean isWinningOutcomeVersus(HandShape otherHandShape) {
        return switch (this) {
            case ROCK -> SCISSORS.equals(otherHandShape);
            case PAPER -> ROCK.equals(otherHandShape);
            case SCISSORS -> PAPER.equals(otherHandShape);
        };
    }


}
