package de.adventofcode.chrisgw.day16;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VolcanicValveOpeningProcedure implements Comparable<VolcanicValveOpeningProcedure> {


    @Getter
    private int totalReleasedPressure = 0;
    private final List<VolcanicValve> path = new ArrayList<>();


    public VolcanicValveOpeningProcedure(VolcanicValve startValve) {
        path.add(startValve);
    }


    private VolcanicValveOpeningProcedure(List<VolcanicValve> valves, int totalReleasedPressure) {
        this.totalReleasedPressure = totalReleasedPressure;
        this.path.addAll(valves);
    }

    public VolcanicValveOpeningProcedure copy() {
        return new VolcanicValveOpeningProcedure(this.path, this.totalReleasedPressure);
    }


    public boolean isFinish() {
        return minute() >= 30;
    }


    public boolean contains(VolcanicValve valve) {
        return path.contains(valve);
    }


    public VolcanicValveOpeningProcedure moveTo(VolcanicValve valve) {
        increaseMinute();
        // System.out.println("You move to valve " + valve);
        path.add(valve);
        return this;
    }


    public boolean canOpenValve() {
        VolcanicValve currentValve = currentValve();
        return currentValve.getFlowRate() > 0;
    }

    public VolcanicValveOpeningProcedure openValve() {
        if (!canOpenValve()) {
            throw new IllegalStateException("can not open valve");
        }
        increaseMinute();
        VolcanicValve valve = currentValve();
        // System.out.println("You open valve " + valve);
        path.add(valve);
        return this;
    }


    private void increaseMinute() {
        totalReleasedPressure += currentlyReleasingPressure();
        // System.out.println(printCurrentMinute());
    }

    private String printCurrentMinute() {
        String openValvesString = currentOpenValves().stream().map(VolcanicValve::getLabel).collect(Collectors.joining(", "));
        return String.format("\n== Minute %d ==\nValve %s are open, releasing %d pressure.", minute(), openValvesString, currentlyReleasingPressure());
    }


    public void undoLastStep() {
        if (path.size() > 1) {
            totalReleasedPressure -= currentlyReleasingPressure();
            path.remove(path.size() - 1);
        }
    }


    public Set<VolcanicValve> currentOpenValves() {
        Set<VolcanicValve> openValves = new HashSet<>();
        for (int i = 1; i < path.size(); i++) {
            VolcanicValve valve = path.get(i);
            if (valve.equals(path.get(i - 1))) {
                openValves.add(valve);
            }
        }
        return openValves;
    }

    public int currentlyReleasingPressure() {
        return currentOpenValves().stream().mapToInt(VolcanicValve::getFlowRate).sum();
    }


    public VolcanicValve currentValve() {
        return path.get(path.size() - 1);
    }

    public Stream<VolcanicValve> possibleNextSteps() {
        return currentValve().connectedValves();
    }


    public List<VolcanicValve> getPath() {
        return path;
    }

    public int minute() {
        return path.size() - 1;
    }


    @Override
    public int compareTo(VolcanicValveOpeningProcedure other) {
        return Integer.compare(this.getTotalReleasedPressure(), other.getTotalReleasedPressure());
    }

}
