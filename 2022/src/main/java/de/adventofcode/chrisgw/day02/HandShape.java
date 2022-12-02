package de.adventofcode.chrisgw.day02;

import lombok.Getter;

import java.util.Arrays;


@Getter
public enum HandShape {

    ROCK(1, 'A', 'X'), //
    PAPER(2, 'B', 'Y'), //
    SCISSORS(3, 'C', 'Z');

    private final char opponentHandShapeCode;
    private final char playerHandShapeCode;
    private final int shapeScore;


    HandShape(int shapeScore, char opponentHandShapeCode, char playerHandShapeCode) {
        this.shapeScore = shapeScore;
        this.opponentHandShapeCode = opponentHandShapeCode;
        this.playerHandShapeCode = playerHandShapeCode;
    }


    public static HandShape valueOfCode(char code) {
        return Arrays.stream(HandShape.values())
                .filter(handShape -> handShape.opponentHandShapeCode == code || handShape.playerHandShapeCode == code)
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
