package de.adventofcode.chrisgw.day22;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class GridComputingCluster {


    private Map<Integer, Map<Integer, Boolean>> positionToComputingNode;


    private GridComputingCluster(Map<Integer, Map<Integer, Boolean>> positionToComputingNode) {
        this.positionToComputingNode = positionToComputingNode;
    }

    public static GridComputingCluster parseComputingNodeMap(List<String> computingNodeMapLines) {
        Map<Integer, Map<Integer, Boolean>> positionToComputingNode = new TreeMap<>();
        int originDistance = computingNodeMapLines.size() / 2;

        for (int row = 0; row < computingNodeMapLines.size(); row++) {
            String computingNodeMapLine = computingNodeMapLines.get(row);
            int y = originDistance - row;
            Map<Integer, Boolean> computingNodeRow = positionToComputingNode.computeIfAbsent(y, (p) -> new TreeMap<>());

            for (int column = 0; column < computingNodeMapLine.length(); column++) {
                char nodeInfectedLetter = computingNodeMapLine.charAt(column);
                int x = column - originDistance;
                computingNodeRow.put(x, nodeInfectedLetter == '#');
            }
        }
        return new GridComputingCluster(positionToComputingNode);
    }


    public boolean getNodeStatus(int x, int y) {
        Map<Integer, Boolean> computingNodeRow = getNodeRow(y);
        return computingNodeRow.getOrDefault(x, false);
    }

    public void setNodeStatus(int x, int y, boolean infected) {
        Map<Integer, Boolean> computingNodeRow = getNodeRow(y);
        computingNodeRow.put(x, infected);
    }

    private Map<Integer, Boolean> getNodeRow(int y) {
        return positionToComputingNode.computeIfAbsent(y, (p) -> new TreeMap<>());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 4; y >= -4; y--) {
            for (int x = -4; x <= 4; x++) {
                if (getNodeStatus(x, y)) {
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
