package de.adventofcode.chrisgw.day22;

public class EvolvedSporificaVirus extends SporificaVirus {


    public EvolvedSporificaVirus(GridComputingCluster gridComputingCluster) {
        super(gridComputingCluster);
    }


    @Override
    protected void turnVirusDirection(ComputingNodeStatus currentNodeStatus) {
        if (ComputingNodeStatus.WEAKENED.equals(currentNodeStatus)) {
            // no direction change
        } else if (ComputingNodeStatus.FLAGGED.equals(currentNodeStatus)) {
            direction = direction.getReverseDirection();
        } else { // clean and infected as before
            super.turnVirusDirection(currentNodeStatus);
        }
    }

    @Override
    protected void modifyCurrenNode(ComputingNodeStatus currentNodeStatus) {
        switch (currentNodeStatus) {
        case CLEAN:
            gridComputingCluster.setNodeStatus(x, y, ComputingNodeStatus.WEAKENED);
            break;
        case INFECTED:
            gridComputingCluster.setNodeStatus(x, y, ComputingNodeStatus.FLAGGED);
            break;
        case WEAKENED:
            gridComputingCluster.setNodeStatus(x, y, ComputingNodeStatus.INFECTED);
            infectionCount++;
            break;
        case FLAGGED:
            gridComputingCluster.setNodeStatus(x, y, ComputingNodeStatus.CLEAN);
            break;
        }
    }

}
