package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import lombok.Data;

import java.time.Year;
import java.util.*;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day15.AdventOfCodeDay15.Cave.MAX_RISK_LEVEL;


/**
 * https://adventofcode.com/2021/day/15
 */
public class AdventOfCodeDay15 extends AdventOfCodePuzzleSolver<Integer> {

    private Cave[][] caveMap;


    public AdventOfCodeDay15(List<String> inputLines) {
        super(Year.of(2021), 15, inputLines);

        caveMap = new Cave[inputLines.size()][];
        for (int y = 0; y < inputLines.size(); y++) {
            String row = inputLines.get(y);
            caveMap[y] = new Cave[inputLines.size()];

            for (int x = 0; x < row.length(); x++) {
                int riskLevel = Integer.parseInt(row, x, x + 1, 10);
                caveMap[y][x] = new Cave(x, y, riskLevel);
            }
        }
    }


    public AdventOfCodeDay15 duplicateTile(int times) {
        AdventOfCodeDay15 large = new AdventOfCodeDay15(this.getInputLines());
        int largeSize = size() * times;
        Cave[][] largeCaveMap = new Cave[largeSize][largeSize];
        for (int y = 0; y < largeSize; y++) {
            for (int x = 0; x < largeSize; x++) {
                Cave cave = caveAt(x % size(), y % size());
                int additionalRiskLevel = x / size() + y / size();
                int riskLevel = cave.getRiskLevel() + additionalRiskLevel;
                if (riskLevel > MAX_RISK_LEVEL) {
                    riskLevel -= MAX_RISK_LEVEL;
                }
                largeCaveMap[y][x] = large.new Cave(x, y, riskLevel);
            }
        }

        large.caveMap = largeCaveMap;
        return large;
    }


    public Cave caveAt(int x, int y) {
        if (hasCaveAt(x, y)) {
            return caveMap[y][x];
        }
        return null;
    }

    public boolean hasCaveAt(int x, int y) {
        return 0 <= y && y < size() && //
                0 <= x && x < size();
    }

    private int size() {
        return caveMap.length;
    }


    public Cave endCave() {
        int y = size() - 1;
        int x = size() - 1;
        return caveAt(x, y);
    }

    public Cave startCave() {
        return caveAt(0, 0);
    }


    public Stream<Cave> allCaves() {
        return Arrays.stream(caveMap).flatMap(Arrays::stream);
    }


    private List<Cave> dijkstraPathToEndCave() {
        // create vertex set Q
        Map<Cave, Integer> dist = new HashMap<>();
        Map<Cave, Cave> prev = new HashMap<>();
        List<Cave> q = new ArrayList<>();

        // for each vertex v in Graph.Vertices:
        allCaves().forEach(v -> {
            // dist[v] ← INFINITY
            dist.put(v, Integer.MAX_VALUE);
            // prev[v] ← UNDEFINED
            prev.put(v, null);
            // add v to Q
            q.add(v);
        });
        // dist[source] ← 0
        dist.put(startCave(), 0);

        // while Q is not empty:
        while (!q.isEmpty()) {
            // u ← vertex in Q with min dist[u]
            Cave u = q.stream().min(Comparator.comparingInt(dist::get)).orElseThrow();
            // remove u from Q
            q.remove(u);

            // for each neighbor v of u still in Q:
            u.adjacentCaves().filter(q::contains).forEach(v -> {
                // alt ← dist[u] + Graph.Edges(u, v)
                int alt = dist.get(u) + v.getRiskLevel();
                // if alt < dist[v]:
                if (alt < dist.get(v)) {
                    // dist[v] ← alt
                    dist.put(v, alt);
                    // prev[v] ← u
                    prev.put(v, u);
                }
            });
        }

        // path ← empty sequence
        List<Cave> path = new ArrayList<>();
        // u ← target
        Cave u = endCave();
        // while u is defined:
        while (u != null) {
            // insert u at the beginning of S
            path.add(0, u);
            // u ← prev[u]
            u = prev.get(u);
        }
        return path;
    }


    public Integer solveFirstPart() {
        List<Cave> path = dijkstraPathToEndCave();
        return calculatePathRiskLevelSum(path);
    }

    public Integer solveSecondPart() {
        List<Cave> path = duplicateTile(5).dijkstraPathToEndCave();
        return calculatePathRiskLevelSum(path);
    }

    private int calculatePathRiskLevelSum(List<Cave> path) {
        return path.stream().skip(1).mapToInt(Cave::getRiskLevel).sum();
    }


    @Data
    public class Cave {

        public static final int MAX_RISK_LEVEL = 9;

        private final int x;
        private final int y;
        private final int riskLevel;


        public Stream<Cave> adjacentCaves() {
            Cave northCave = caveAt(x, y + 1);
            Cave southCave = caveAt(x, y - 1);
            Cave westCave = caveAt(x - 1, y);
            Cave eastCave = caveAt(x + 1, y);
            return Stream.of( southCave, eastCave, westCave, northCave).filter(Objects::nonNull);
        }


        @Override
        public String toString() {
            return "(%d;%d) = %d".formatted(getX(), getY(), getRiskLevel());
        }

    }

}
