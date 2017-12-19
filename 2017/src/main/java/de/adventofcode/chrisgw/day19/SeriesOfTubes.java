package de.adventofcode.chrisgw.day19;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static de.adventofcode.chrisgw.day19.SeriesOfTubes.MoveDirection.*;


/**
 * <h1><a href="https://adventofcode.com/2017/day/19>Day 19: A Series of Tubes</a></h1>
 * <pre>
 * Somehow, a network packet got lost and ended up here. It's trying to follow
 * a routing diagram (your puzzle input), but it's confused about where to go.
 *
 * Its starting point is just off the top of the diagram. Lines (drawn with |
 * , -, and +) show the path it needs to take, starting by going down onto the
 * only line connected to the top of the diagram. It needs to follow this path
 * until it reaches the end (located somewhere within the diagram) and stop there.
 *
 * Sometimes, the lines cross over each other; in these cases, it needs to
 * continue going the same direction, and only turn left or right when there's
 * no other option. In addition, someone has left letters on the line; these
 * also don't change its direction, but it can use them to keep track of where
 * it's been. For example:
 *
 *     |
 *     |  +--+
 *     A  |  C
 * F---|----E|--+
 *     |  |  |  D
 *     +B-+  +--+
 *
 * Given this diagram, the packet needs to take the following path:
 *
 * - Starting at the only line touching the top of the diagram, it must go
 *   down, pass through A, and continue onward to the first +.
 * - Travel right, up, and right, passing through B in the process.
 * - Continue down (collecting C), right, and up (collecting D).
 * - Finally, go all the way left through E and stopping at F.
 *
 * Following the path to the end, the letters it sees on its path are ABCDEF.
 *
 * The little packet looks up at you, hoping you can help it find the way.
 * What letters will it see (in the order it would see them) if it follows the
 * path? (The routing diagram is very wide; make sure you view it without line
 * wrapping.)
 * --- Part Two ---
 * The packet is curious how many steps it needs to go.
 *
 * For example, using the same routing diagram from the example above...
 *
 *     |
 *     |  +--+
 *     A  |  C
 * F---|--|-E---+
 *     |  |  |  D
 *     +B-+  +--+
 *
 * ...the packet would go:
 *
 * - 6 steps down (including the first line at the top of the diagram).
 * - 3 steps right.
 * - 4 steps up.
 * - 3 steps right.
 * - 4 steps down.
 * - 3 steps right.
 * - 2 steps up.
 * - 13 steps left (including the F it stops on).
 *
 * This would result in a total of 38 steps.
 *
 * How many steps does the packet need to go?
 * </pre>
 */
public class SeriesOfTubes {

    private List<List<Character>> network;

    private int x;
    private int y;
    private MoveDirection moveDirection;

    private List<String> letters;
    private int stepCount;


    public SeriesOfTubes(String networkStr) {
        this(parseNetwork(networkStr));
    }

    public SeriesOfTubes(List<List<Character>> network) {
        this.network = network;
        this.y = 0;
        this.x = findStartingPosition();
        this.moveDirection = DOWN;

        this.letters = new LinkedList<>();
        this.stepCount = 1;
    }

    private int findStartingPosition() {
        List<Character> firstNetworkRow = network.get(0);
        for (int x = 0; x < firstNetworkRow.size(); x++) {
            char firstLineConnection = firstNetworkRow.get(x);
            if (firstLineConnection == '|') {
                return x;
            }
        }
        throw new IllegalArgumentException("no starting position at top found");
    }

    private static List<List<Character>> parseNetwork(String networkStr) {
        String[] splitNetworkRows = networkStr.split("\n");
        List<List<Character>> network = new ArrayList<>(splitNetworkRows.length);
        for (int y = 0; y < splitNetworkRows.length; y++) {
            String splitNetworkRow = splitNetworkRows[y];
            List<Character> networkRow = new ArrayList<>(splitNetworkRow.length());
            for (int x = 0; x < splitNetworkRow.length(); x++) {
                networkRow.add(splitNetworkRow.charAt(x));
            }
            network.add(networkRow);
        }
        return network;
    }


    public void followNextConnectionTillEnd() {
        if (UP.equals(moveDirection)) {
            moveUpTillCorner();
        } else if (DOWN.equals(moveDirection)) {
            moveDownTillCorner();
        } else if (RIGHT.equals(moveDirection)) {
            moveRightTillCorner();
        } else if (LEFT.equals(moveDirection)) {
            moveLeftTillCorner();
        }
        moveDirection = findNextPossibleDirection();
    }


    private void moveUpTillCorner() {
        do {
            y--;
            stepCount++;
            checkIfPassedLetter();
        } while (canContinueInMoveDirection());
    }

    private void moveDownTillCorner() {
        do {
            y++;
            stepCount++;
            checkIfPassedLetter();
        } while (canContinueInMoveDirection());
    }


    private void moveRightTillCorner() {
        do {
            x++;
            stepCount++;
            checkIfPassedLetter();
        } while (canContinueInMoveDirection());
    }

    private void moveLeftTillCorner() {
        do {
            x--;
            stepCount++;
            checkIfPassedLetter();
        } while (canContinueInMoveDirection());
    }


    private boolean canContinueInMoveDirection() {
        return canMoveInDirection(moveDirection) && !isAtCorner();
    }

    private boolean isAtCorner() {
        return isCorner(getNetworkAt(x, y));
    }

    private boolean canMoveInDirection(MoveDirection moveDirection) {
        char upcommingConnection;
        if (UP.equals(moveDirection)) {
            upcommingConnection = getNetworkAt(x, y - 1);
        } else if (DOWN.equals(moveDirection)) {
            upcommingConnection = getNetworkAt(x, y + 1);
        } else if (LEFT.equals(moveDirection)) {
            upcommingConnection = getNetworkAt(x - 1, y);
        } else {
            upcommingConnection = getNetworkAt(x + 1, y);
        }
        return isMoveable(upcommingConnection);
    }


    private MoveDirection findNextPossibleDirection() {
        MoveDirection backtrackDirection = this.moveDirection.getOppositeDirection();

        for (MoveDirection moveDirection : MoveDirection.values()) {
            if (!moveDirection.equals(backtrackDirection) && canMoveInDirection(moveDirection)) {
                return moveDirection;
            }
        }
        return this.moveDirection;
    }


    private boolean checkIfPassedLetter() {
        char currentPosition = getNetworkAt(x, y);
        if (isLetter(currentPosition)) {
            letters.add(String.valueOf(currentPosition));
            return true;
        }
        return false;
    }


    public boolean isAtNetworkEnd() {
        MoveDirection backtrackDirection = this.moveDirection.getOppositeDirection();
        for (MoveDirection direction : MoveDirection.values()) {
            if (!direction.equals(backtrackDirection) && canMoveInDirection(direction)) {
                return false;
            }
        }
        return true;
    }


    private char getNetworkAt(int x, int y) {
        if (x < 0 || y < 0) {
            return ' ';
        }
        if (y >= network.size() || x >= network.get(y).size()) {
            return ' ';
        }
        return network.get(y).get(x);
    }


    private static boolean isLetter(char connection) {
        connection = Character.toUpperCase(connection);
        return 'A' <= connection && connection <= 'Z';
    }

    private static boolean isCorner(char connection) {
        return connection == '+';
    }

    private static boolean isMoveable(char upcommingConnection) {
        return upcommingConnection == '-' || upcommingConnection == '|' || isLetter(upcommingConnection) || isCorner(
                upcommingConnection);
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }


    public List<String> getLetters() {
        return letters;
    }

    public String getLettersAsString() {
        return String.join("", letters).toUpperCase();
    }


    public int getStepCount() {
        return stepCount;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < network.size(); y++) {
            List<Character> networkRow = network.get(y);
            for (int x = 0; x < networkRow.size(); x++) {
                if (this.y == y && this.x == x) {
                    sb.append("X");
                } else {
                    sb.append(getNetworkAt(x, y));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public enum MoveDirection {

        UP, RIGHT, DOWN, LEFT;


        public MoveDirection getOppositeDirection() {
            MoveDirection oppositeDirection;
            if (UP.equals(this)) {
                oppositeDirection = DOWN;
            } else if (DOWN.equals(this)) {
                oppositeDirection = UP;
            } else if (LEFT.equals(this)) {
                oppositeDirection = RIGHT;
            } else {
                oppositeDirection = LEFT;
            }
            return oppositeDirection;
        }

    }

}
