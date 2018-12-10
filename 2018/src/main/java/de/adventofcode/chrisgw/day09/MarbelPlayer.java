package de.adventofcode.chrisgw.day09;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@EqualsAndHashCode
public class MarbelPlayer {

    @Getter
    private final int playerId;

    @Getter
    @Setter
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MarbelPlayer nextPlayer;

    @Getter
    private int score;


    public MarbelPlayer(int playerId) {
        this.playerId = playerId;
        this.nextPlayer = this;
        this.score = 0;
    }


    public int addScore(int addedScore) {
        this.score += addedScore;
        return this.score;
    }

}
