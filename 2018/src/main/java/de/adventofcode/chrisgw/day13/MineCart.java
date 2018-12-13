package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.day13.MineCartMadness.MineCartTrack;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import static de.adventofcode.chrisgw.day13.MineCart.MineCartDirection.*;


@Data
@Setter(AccessLevel.PRIVATE)
public class MineCart {

    private final int id;

    private MineCartTrack currentTrack;
    private MineCartDirection direction;

    private int passedIntersectionCount = 0;

    @ToString.Exclude
    private List<UnaryOperator<MineCartDirection>> intersectionTransitions = Arrays.asList( //
            MineCartDirection::toLeft, // it turns left the first time
            direction -> direction, // goes straight the second time
            MineCartDirection::toRight); // turns right the third time


    public MineCart(int id, char mineCartLetter, MineCartTrack track) {
        this.id = id;
        this.direction = MineCartDirection.forDirectionLetter(mineCartLetter);
        this.currentTrack = track;
    }


    public void nextTick() {
        currentTrack = currentTrack.trackTo(direction);
        direction = adjustDirection();
    }

    private MineCartDirection adjustDirection() {
        if (currentTrack.isIntersection()) {
            int currentIntersectionTranstionIndex = (passedIntersectionCount++) % intersectionTransitions.size();
            return intersectionTransitions.get(currentIntersectionTranstionIndex).apply(direction);
        } else if (currentTrack.isCurve() && isLeftTurn()) {
            return direction.toLeft();
        } else if (currentTrack.isCurve()) {
            return direction.toRight();
        } else {
            return direction;
        }
    }

    private boolean isLeftTurn() {
        char currentTrackType = currentTrack.getTrackType();
        return (NORTH.equals(direction) && currentTrackType == '\\') //
                || (SOUTH.equals(direction) && currentTrackType == '\\') //
                || (EAST.equals(direction) && currentTrackType == '/') //
                || (WEST.equals(direction) && currentTrackType == '/');
    }


    public boolean isOnTrack(MineCartTrack track) {
        return currentTrack.equals(track);
    }

    public boolean collideWith(MineCart otherMineCart) {
        return this != otherMineCart && isOnTrack(otherMineCart.getCurrentTrack());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MineCart)) {
            return false;
        }

        MineCart mineCart = (MineCart) o;
        return new EqualsBuilder().append(getId(), mineCart.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
    }


    public enum MineCartDirection {

        NORTH(0, -1, '^'),  //
        EAST(1, 0, '>'), //
        SOUTH(0, 1, 'v'), //
        WEST(-1, 0, '<'); //

        @Getter
        private final int dx;

        @Getter
        private final int dy;

        @Getter
        private final char directionLetter;


        MineCartDirection(int dx, int dy, char directionLetter) {
            this.dx = dx;
            this.dy = dy;
            this.directionLetter = directionLetter;
        }

        public static MineCartDirection forDirectionLetter(char directionLetter) {
            for (MineCartDirection cartDirection : values()) {
                if (cartDirection.getDirectionLetter() == directionLetter) {
                    return cartDirection;
                }
            }
            throw new IllegalArgumentException("Unknown mineCartDirection with letter: " + directionLetter);
        }


        public MineCartDirection toRight() {
            MineCartDirection[] directions = values();
            if (ordinal() == directions.length - 1) {
                return directions[0];
            } else {
                return directions[ordinal() + 1];
            }
        }

        public MineCartDirection toLeft() {
            MineCartDirection[] directions = values();
            if (ordinal() == 0) {
                return directions[directions.length - 1];
            } else {
                return directions[ordinal() - 1];
            }
        }

        public MineCartDirection oppositeDirection() {
            MineCartDirection[] directions = values();
            return directions[(ordinal() + 2) % 4];
        }

    }

}
