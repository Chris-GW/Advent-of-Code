package de.adventofcode.chrisgw.day02;


public record RockPaperScissorsRound(int round, HandShape opponentHandShape, HandShape playerHandShape) {


    public GameOutcome gameOutcome() {
        return playerHandShape.outcomeVersus(opponentHandShape);
    }

    public int score() {
        int shapeScore = playerHandShape.getShapeScore();
        int gameOutcomeScore = gameOutcome().getOutcomeScore();
        return shapeScore + gameOutcomeScore;
    }


}
