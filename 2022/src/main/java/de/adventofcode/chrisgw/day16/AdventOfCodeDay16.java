package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


/**
 * <a href="https://adventofcode.com/2022/day/16">Advent of Code - day 16</a>
 */
public class AdventOfCodeDay16 extends AdventOfCodePuzzleSolver {


    public AdventOfCodeDay16(List<String> inputLines) {
        super(Year.of(2022), 16, inputLines);
    }


    public Integer solveFirstPart() {
        Map<String, VolcanicValve> valvesByLabel = parseValves();
        VolcanicValve startValve = valvesByLabel.get("AA");

        // new VolcanicValveOpeningProcedure(startValve)
        //         .moveTo(valvesByLabel.get("DD"))
        //         .openValve()
        //         .moveTo(valvesByLabel.get("CC"))
        //         .moveTo(valvesByLabel.get("BB"))
        //         .openValve()
        //         .moveTo(valvesByLabel.get("AA"))
        //         .moveTo(valvesByLabel.get("II"))
        //         .moveTo(valvesByLabel.get("JJ"))
        //         .openValve();

        Map<VolcanicValve, Map<VolcanicValve, Integer>> dist = warshall(valvesByLabel);
        List<VolcanicValve> uselessValves = valvesByLabel.values().stream().filter(valve -> valve.getFlowRate() == 0 && !valve.getLabel().equals("AA")).toList();
        uselessValves.forEach(dist::remove);
        dist.values().forEach(volcanicValveIntegerMap -> uselessValves.forEach(volcanicValveIntegerMap::remove));

        List<VolcanicValveOpeningProcedure> valveOpenings = bfs(dist, new VolcanicValveOpeningProcedure(startValve));
        Optional<VolcanicValveOpeningProcedure> bestPath = valveOpenings.stream()
                .map(currentPath -> {
                    while (!currentPath.isFinish()) {
                        currentPath = currentPath.moveTo(currentPath.currentValve());
                    }
                    return currentPath;
                })
                .max(VolcanicValveOpeningProcedure::compareTo);
        return bestPath
                .map(VolcanicValveOpeningProcedure::getTotalReleasedPressure)
                .orElse(0);
    }

    private Map<String, VolcanicValve> parseValves() {
        Pattern pattern = Pattern.compile("Valve ([A-Z]{2}) has flow rate=(\\d+); (?:tunnel leads to valve|tunnels lead to valves) (((?:, )?[A-Z]{2})+)");
        List<String> inputLines = getInputLines();
        Map<String, VolcanicValve> valvesByLabel = new HashMap<>(inputLines.size());

        for (String inputLine : inputLines) {
            Matcher matcher = pattern.matcher(inputLine);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("expect line matching " + pattern + ", but was: " + inputLine);
            }

            String label = matcher.group(1);
            int flowRate = Integer.parseInt(matcher.group(2));
            VolcanicValve volcanicValve = new VolcanicValve(label, flowRate);
            valvesByLabel.put(label, volcanicValve);
        }

        for (String inputLine : inputLines) {
            Matcher matcher = pattern.matcher(inputLine);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("expect line matching " + pattern + ", but was: " + inputLine);
            }

            String label = matcher.group(1);
            VolcanicValve volcanicValve = valvesByLabel.get(label);
            Arrays.stream(matcher.group(3).split(", ")).map(valvesByLabel::get).forEach(volcanicValve::connectWithValve);
        }
        return valvesByLabel;
    }

    private HashMap<VolcanicValve, Map<VolcanicValve, Integer>> warshall(Map<String, VolcanicValve> valvesByLabel) {

        // let dist be a |V| × |V| array of minimum distances initialized to ∞ (infinity)
        var dist = new HashMap<VolcanicValve, Map<VolcanicValve, Integer>>();

        // for each edge (u, v) do
        //     dist[u][v] ← w(u, v)  // The weight of the edge (u, v)
        for (VolcanicValve valve : valvesByLabel.values()) {
            valve.connectedValves().forEach(connectedVavle -> {
                int weight = 1;
                dist.computeIfAbsent(valve, volcanicValveVolcanicValveMap -> new HashMap<>()).put(connectedVavle, weight);
            });
        }

        // for each vertex v do
        //     dist[v][v] ← 0
        for (VolcanicValve valve : valvesByLabel.values()) {
            dist.computeIfAbsent(valve, volcanicValveVolcanicValveMap -> new HashMap<>()).put(valve, 0);
        }

        /*
        for k from 1 to |V|
            for i from 1 to |V|
                for j from 1 to |V|
                    if dist[i][j] > dist[i][k] + dist[k][j]
                        dist[i][j] ← dist[i][k] + dist[k][j]
                    end if
         */
        for (VolcanicValve k : valvesByLabel.values()) {
            for (VolcanicValve i : valvesByLabel.values()) {
                for (VolcanicValve j : valvesByLabel.values()) {
                    long ij = dist.get(i).getOrDefault(j, Integer.MAX_VALUE);
                    long ik = dist.get(i).getOrDefault(k, Integer.MAX_VALUE);
                    long kj = dist.get(k).getOrDefault(j, Integer.MAX_VALUE);
                    if (ij > ik + kj) {
                        dist.get(i).put(j, Math.toIntExact(ik + kj));
                    }
                }
            }
        }
        return dist;
    }

    private List<VolcanicValveOpeningProcedure> bfs(Map<VolcanicValve, Map<VolcanicValve, Integer>> dist, VolcanicValveOpeningProcedure currentPath) {
        if (currentPath.isFinish() || currentPath.getPath().containsAll(dist.keySet())) {
            return List.of(currentPath.copy());
        }

        List<VolcanicValveOpeningProcedure> volcanicValveOpeningProcedures = new ArrayList<>();
        Map<VolcanicValve, Integer> possibleValves = dist.get(currentPath.currentValve());
        for (VolcanicValve possibleNextValve : possibleValves.keySet()) {
            if (currentPath.contains(possibleNextValve)) {
                continue;
            }
            int cost = possibleValves.get(possibleNextValve);
            IntStream.range(1, cost).forEach(value -> {
                currentPath.moveTo(currentPath.currentValve());
            });
            currentPath.moveTo(possibleNextValve);
            currentPath.openValve();
            volcanicValveOpeningProcedures.addAll(bfs(dist, currentPath));
            currentPath.undoLastStep();
            currentPath.undoLastStep();
            IntStream.range(1, cost).forEach(value -> {
                currentPath.undoLastStep();
            });
        }
        return volcanicValveOpeningProcedures;
    }


    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


}
