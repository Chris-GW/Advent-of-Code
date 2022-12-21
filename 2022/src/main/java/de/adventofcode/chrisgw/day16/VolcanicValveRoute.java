package de.adventofcode.chrisgw.day16;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VolcanicValveRoute {


    private final Map<String, VolcanicValve> valvesByLabel;

    private final Set<VolcanicValve> openValves = new HashSet<>();
    private final List<VolcanicValve> path = new ArrayList<>();


    public VolcanicValveRoute(VolcanicValve startValve, Map<String, VolcanicValve> valvesByLabel) {
        this.valvesByLabel = new HashMap<>(valvesByLabel);
        this.path.add(startValve);
    }

    public VolcanicValveRoute(VolcanicValveRoute otherValveRoute) {
        this.valvesByLabel = otherValveRoute.valvesByLabel;
        this.openValves.addAll(otherValveRoute.openValves);
        this.path.addAll(otherValveRoute.path);
    }


    public Stream<VolcanicValve> usefulValvesWithFlowRate() {
        return valvesByLabel.values().stream().filter(VolcanicValve::hasFlowRate);
    }

    public boolean connectsAllValvesWithFlowRate() {
        return usefulValvesWithFlowRate().allMatch(openValves::contains);
    }


    public VolcanicValveRoute openNextValve(VolcanicValve v) {
        // if next[u][v] = null then
        //     return []
        // path ← [u]
        // while u ≠ v
        //     u ← next[u][v]
        //     path.append(u)
        // return path

        // System.out.println("You move to v " + v);
        VolcanicValve u = currentValve();
        while (!u.equals(v)) {
            u = u.getNext(v);
            path.add(u);
        }
        path.add(v);
        openValves.add(v);
        return this;
    }


    public void undoOpenValve(VolcanicValve v) {
        path.remove(path.size() - 1);
        path.remove(path.size() - 1);
        for (int i = path.size() - 1; i > 0; i--) {
            if (path.get(i).equals(path.get(i - 1))) {
                break;
            }
            path.remove(i);
        }
        openValves.remove(v);
    }


    public VolcanicValve currentValve() {
        return path.get(path.size() - 1);
    }

    public Stream<VolcanicValve> missingValvesWithFlowRate() {
        return usefulValvesWithFlowRate().filter(Predicate.not(openValves::contains));
    }


    public int totalReleasedPressureTill(int maxMinute) {
        Set<VolcanicValve> openValves = new HashSet<>(Math.toIntExact(usefulValvesWithFlowRate().count()));
        int totalReleasedPressure = 0;
        for (int minute = 1; minute <= maxMinute; minute++) {
            int currentlyReleasedPressure = openValves.stream().mapToInt(VolcanicValve::getFlowRate).sum();
            totalReleasedPressure += currentlyReleasedPressure;

            if (minute >= length()) {
                continue;
            }
            VolcanicValve currentValve = path.get(minute);
            VolcanicValve previousValve = path.get(minute - 1);
            if (currentValve.equals(previousValve)) {
                openValves.add(currentValve);
            }
        }
        return totalReleasedPressure;
    }


    public List<VolcanicValve> getPath() {
        return path;
    }

    public int length() {
        return path.size();
    }


    @Override
    public String toString() {
        return path.stream().map(VolcanicValve::getLabel).collect(Collectors.joining(" -> "));
    }

}
