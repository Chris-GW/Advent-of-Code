package de.adventofcode.chrisgw.day12;

import java.util.HashSet;
import java.util.Set;


public class DigitalProgram {

    public int id;
    public Set<DigitalProgram> connectedDigitalPrograms = new HashSet<>();


    public DigitalProgram(int id) {
        this.id = id;
    }


    public Set<DigitalProgram> getAllReachableDigitalPrograms() {
        return getAllReachableDigitalPrograms(new HashSet<>());
    }

    private Set<DigitalProgram> getAllReachableDigitalPrograms(Set<DigitalProgram> allConnectedDigitalPrograms) {
        for (DigitalProgram connectedDigitalProgram : connectedDigitalPrograms) {
            boolean isNewConnectedDigitalProgram = allConnectedDigitalPrograms.add(connectedDigitalProgram);
            if (isNewConnectedDigitalProgram) {
                connectedDigitalProgram.getAllReachableDigitalPrograms(allConnectedDigitalPrograms);
            }
        }
        return allConnectedDigitalPrograms;
    }


    public void addConnectedDigitalProgam(DigitalProgram digitalProgram) {
        connectedDigitalPrograms.add(digitalProgram);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DigitalProgram))
            return false;

        DigitalProgram that = (DigitalProgram) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
