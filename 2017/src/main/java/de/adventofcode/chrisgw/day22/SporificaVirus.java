package de.adventofcode.chrisgw.day22;

/**
 * <h1><a href="https://adventofcode.com/2017/day/22>Day 22: Sporifica Virus</a></h1>
 * <pre>
 * </pre>
 */
public class SporificaVirus {

    private int x = 0;
    private int y = 0;
    private VirusDirection direction = VirusDirection.UP;

    private GridComputingCluster gridComputingCluster;
    private long infectionCount = 0;


    public SporificaVirus(GridComputingCluster gridComputingCluster) {
        this.gridComputingCluster = gridComputingCluster;
    }


    public void nextVirusBurst() {
        boolean currentNodeStatus = gridComputingCluster.getNodeStatus(x, y);
        if (currentNodeStatus) {
            nextBurstOnInfectedNode();
        } else {
            nextBurstOnCleanNode();
        }
        moveForwardInDirection();
    }

    private void nextBurstOnInfectedNode() {
        direction = direction.getDirectionToRight();
        gridComputingCluster.setNodeStatus(x, y, false);
    }

    private void nextBurstOnCleanNode() {
        direction = direction.getDirectionToLeft();
        gridComputingCluster.setNodeStatus(x, y, true);
        infectionCount++;
    }

    private void moveForwardInDirection() {
        switch (direction) {
        case UP:
            y++;
            break;
        case DOWN:
            y--;
            break;
        case LEFT:
            x--;
            break;
        case RIGHT:
            x++;
            break;
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public long getInfectionCount() {
        return infectionCount;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 4; y >= -4; y--) {
            for (int x = -4; x <= 4; x++) {
                boolean infectedNode = gridComputingCluster.getNodeStatus(x, y);
                boolean virusPosition = this.y == y && this.x == x;
                if (infectedNode && virusPosition) {
                    sb.append('+');
                } else if (virusPosition) {
                    sb.append('V');
                } else if (infectedNode) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    private enum VirusDirection {
        RIGHT, DOWN, LEFT, UP; // in clockwise ordinal position


        public VirusDirection getDirectionToRight() {
            VirusDirection[] virusDirections = VirusDirection.values();
            int ordinal = this.ordinal() + 1;
            if (ordinal >= virusDirections.length) {
                ordinal = 0;
            }
            return virusDirections[ordinal];

        }

        public VirusDirection getDirectionToLeft() {
            VirusDirection[] virusDirections = VirusDirection.values();
            int ordinal = this.ordinal() - 1;
            if (ordinal < 0) {
                ordinal = virusDirections.length - 1;
            }
            return virusDirections[ordinal];
        }

    }

}
