package de.adventofcode.chrisgw.day22;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GridComputingCluster {


    private Map<Integer, Map<Integer, ComputingNodeStatus>> positionToComputingNode;


    private GridComputingCluster(Map<Integer, Map<Integer, ComputingNodeStatus>> positionToComputingNode) {
        this.positionToComputingNode = positionToComputingNode;
    }

    public static GridComputingCluster parseComputingNodeMap(List<String> computingNodeMapLines) {
        Map<Integer, Map<Integer, ComputingNodeStatus>> positionToComputingNode = new HashMap<>();
        int originDistance = computingNodeMapLines.size() / 2;

        for (int row = 0; row < computingNodeMapLines.size(); row++) {
            String computingNodeMapLine = computingNodeMapLines.get(row);
            int y = originDistance - row;
            Map<Integer, ComputingNodeStatus> computingNodeRow = positionToComputingNode.computeIfAbsent(y,
                    (p) -> new HashMap<>());

            for (int column = 0; column < computingNodeMapLine.length(); column++) {
                char nodeInfectedLetter = computingNodeMapLine.charAt(column);
                int x = column - originDistance;
                if (nodeInfectedLetter == '#') {
                    computingNodeRow.put(x, ComputingNodeStatus.INFECTED);
                }
            }
        }
        return new GridComputingCluster(positionToComputingNode);
    }


    public ComputingNodeStatus getNodeStatus(int x, int y) {
        Map<Integer, ComputingNodeStatus> computingNodeRow = getNodeRow(y);
        return computingNodeRow.getOrDefault(x, ComputingNodeStatus.CLEAN);
    }

    public void setNodeStatus(int x, int y, ComputingNodeStatus nodeStatus) {
        Map<Integer, ComputingNodeStatus> computingNodeRow = getNodeRow(y);
        computingNodeRow.put(x, nodeStatus);
    }

    private Map<Integer, ComputingNodeStatus> getNodeRow(int y) {
        return positionToComputingNode.computeIfAbsent(y, (p) -> new HashMap<>());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 4; y >= -4; y--) {
            for (int x = -4; x <= 4; x++) {
                if (getNodeStatus(x, y).equals(ComputingNodeStatus.INFECTED)) {
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

}
