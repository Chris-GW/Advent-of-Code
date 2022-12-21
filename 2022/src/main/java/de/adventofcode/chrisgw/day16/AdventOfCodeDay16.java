package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <a href="https://adventofcode.com/2022/day/16">Advent of Code - day 16</a>
 */
public class AdventOfCodeDay16 extends AdventOfCodePuzzleSolver {


    private final Map<String, VolcanicValve> valvesByLabel;

    public AdventOfCodeDay16(List<String> inputLines) {
        super(Year.of(2022), 16, inputLines);
        this.valvesByLabel = parseValves();
    }


    public Integer solveFirstPart() {
        VolcanicValve startValve = valvesByLabel.get("AA");
        shortestPathFloydWarshall(valvesByLabel);
        List<VolcanicValveRoute> valveOpenings = bfs(new VolcanicValveRoute(startValve, valvesByLabel));
        return valveOpenings.stream()
                .mapToInt(route -> route.totalReleasedPressureTill(30))
                .max()
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

    private void shortestPathFloydWarshall(Map<String, VolcanicValve> valvesByLabel) {
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
                    long ij = i.distanceTo(j);
                    long ik = i.distanceTo(k);
                    long kj = k.distanceTo(j);
                    if (ij > ik + kj) {
                        // dist[i][j] ← dist[i][k] + dist[k][j]
                        // next[i][j] ← next[i][k]
                        i.putDistance(j, Math.toIntExact(ik + kj));
                        i.putNext(j, i.getNext(k));
                    }
                }
            }
        }
    }

    private List<VolcanicValveRoute> bfs(VolcanicValveRoute currentRoute) {
        if (currentRoute.connectsAllValvesWithFlowRate() || currentRoute.length() > 30) {
            return List.of(new VolcanicValveRoute(currentRoute));
        }

        List<VolcanicValveRoute> valveRoutes = new ArrayList<>();
        List<VolcanicValve> possibleNextValve = currentRoute.missingValvesWithFlowRate().toList();
        for (VolcanicValve nextValve : possibleNextValve) {
            currentRoute.openNextValve(nextValve);
            valveRoutes.addAll(bfs(currentRoute));
            currentRoute.undoOpenValve(nextValve);
        }
        return valveRoutes;
    }


    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


}
