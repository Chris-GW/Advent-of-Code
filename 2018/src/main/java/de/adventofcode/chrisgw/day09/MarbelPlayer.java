package de.adventofcode.chrisgw.day09;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


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

    private List<Marbel> takenMarbels = new ArrayList<>();

    @Getter
    @Setter
    private BigInteger score;


    public MarbelPlayer(int playerId) {
        this.playerId = playerId;
        this.nextPlayer = this;
    }


    public void addMarbel(Marbel marbel) {
        takenMarbels.add(marbel);
    }


    public Stream<Marbel> takenMarbels() {
        return takenMarbels.stream();
    }

    public Marbel lastTakenMarbel() {
        return takenMarbels.get(takenMarbels.size() - 1);
    }

}
