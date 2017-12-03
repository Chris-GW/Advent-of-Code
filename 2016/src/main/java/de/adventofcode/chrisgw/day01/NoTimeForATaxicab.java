package main.java.de.adventofcode.chrisgw.day01;

import java.util.*;


public class NoTimeForATaxicab {


    private Set<Position> visitedPositions = new LinkedHashSet<>();
    private Set<Position> twiceVisitedPositions = new LinkedHashSet<>();

    private Direction direction = Direction.NORTH;
    private Position currentPosition = new Position(0, 0);


    public void followInsturctions(String insturction, String seperator) {
        List<String> insturctions = Arrays.asList(insturction.split(seperator));
        followInsturctions(insturctions);
    }

    public void followInsturctions(List<String> insturctions) {
        for (String insturction : insturctions) {
            followInstruction(insturction);
        }
    }

    public void followInstruction(String instruction) {
        char turnDirection = instruction.charAt(0);
        int blocksToMove = Integer.valueOf(instruction.substring(1));
        if (turnDirection == 'R') {
            direction = direction.turnRight();
            moveForward(blocksToMove);
        } else if (turnDirection == 'L') {
            direction = direction.turnLeft();
            moveForward(blocksToMove);
        } else {
            throw new IllegalArgumentException(
                    String.format("Unexpected direction '%s' in instruction: '%s'", turnDirection, instruction));
        }
    }


    private void moveForward(int blocksToMove) {
        for (int i = 0; i < blocksToMove; i++) {
            currentPosition = new Position(currentPosition);
            if (direction == Direction.NORTH) {
                currentPosition.y++;
            } else if (direction == Direction.SOUTH) {
                currentPosition.y--;
            } else if (direction == Direction.EAST) {
                currentPosition.x++;
            } else if (direction == Direction.WEST) {
                currentPosition.x--;
            } else {
                throw new IllegalStateException("Taxicab faces in unknown direction " + direction);
            }
            boolean newVisitedPosition = visitedPositions.add(currentPosition);
            if (!newVisitedPosition) {
                twiceVisitedPositions.add(currentPosition);
            }
        }
    }


    public int getBlocksAway() {
        return currentPosition.getBlocksAway();
    }

    public Set<Position> getTwiceVisitedPositions() {
        return twiceVisitedPositions;
    }


    public static class Position {

        int x;
        int y;


        public Position(Position position) {
            this(position.x, position.y);
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public int getBlocksAway() {
            return Math.abs(x) + Math.abs(y);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Position))
                return false;

            Position position = (Position) o;

            if (x != position.x)
                return false;
            return y == position.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "(" + x + ";" + y + ")";
        }
    }


    public enum Direction {
        NORTH, EAST, SOUTH, WEST;


        public Direction turnRight() {
            if (this.equals(WEST)) {
                return NORTH;
            }
            return Direction.values()[this.ordinal() + 1];
        }

        public Direction turnLeft() {
            if (this.equals(NORTH)) {
                return WEST;
            }
            return Direction.values()[this.ordinal() - 1];
        }

    }


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        String instructions = args[0];
        System.out.println(instructions);

        long startTime = System.nanoTime();
        NoTimeForATaxicab taxicab = new NoTimeForATaxicab();
        taxicab.followInsturctions(instructions, ", ");
        int blocksAway = taxicab.getBlocksAway();
        long duration = System.nanoTime() - startTime;
        System.out.printf("Our taxicab is '%d' blocks away from our starting position (%9d ns)", blocksAway, duration);
    }

}
