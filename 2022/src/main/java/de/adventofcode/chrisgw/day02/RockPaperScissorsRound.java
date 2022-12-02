package de.adventofcode.chrisgw.day02;


import java.util.Arrays;


public record RockPaperScissorsRound(int round, HandShape opponentHandShape, char strategyCode) {


    public int scoreAccordingToFixedHandShape() {
        HandShape playerHandShape = HandShape.valueOfCode(strategyCode);
        GameOutcome gameOutcome = playerHandShape.outcomeVersus(opponentHandShape);
        int shapeScore = playerHandShape.getShapeScore();
        int gameOutcomeScore = gameOutcome.getOutcomeScore();
        return shapeScore + gameOutcomeScore;
    }

    public int scoreAccordingToFixedOutcome() {
        GameOutcome gameOutcome = GameOutcome.valueOfCode(strategyCode);
        int shapeScore = handShapeForGameOutcome(gameOutcome).getShapeScore();
        int gameOutcomeScore = gameOutcome.getOutcomeScore();
        return shapeScore + gameOutcomeScore;
    }

    private HandShape handShapeForGameOutcome(GameOutcome gameOutcome) {
        return Arrays.stream(HandShape.values())
                .filter(handShape -> handShape.outcomeVersus(opponentHandShape).equals(gameOutcome))
                .findAny()
                .orElseThrow(() -> new RuntimeException("could not find a HandShape with GameOutcome: " + gameOutcome));
    }

}
