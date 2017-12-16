package de.adventofcode.chrisgw.day16;

import java.util.Objects;


public class DancingProgram {

    private final String name;
    private int position;

    private DancingProgram[] dancingProgramLine;


    public DancingProgram(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public void addToDancingProgramLine(DancingProgram[] dancingProgramLine, int startPosition) {
        this.dancingProgramLine = dancingProgramLine;
        this.dancingProgramLine[startPosition] = this;
        this.position = startPosition;
    }


    public void spin() {
        while (position != 0) {
            exchange(position - 1);
        }
    }

    public void exchange(int position) {
        DancingProgram withDancingProgram = dancingProgramLine[position];
        partner(withDancingProgram);
    }

    public void partner(DancingProgram withDancingProgram) {
        int partnerPosition = withDancingProgram.position;
        dancingProgramLine[partnerPosition] = this;
        dancingProgramLine[this.position] = withDancingProgram;

        withDancingProgram.position = this.position;
        this.position = partnerPosition;
    }


    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DancingProgram))
            return false;

        DancingProgram that = (DancingProgram) o;

        if (getPosition() != that.getPosition())
            return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getPosition();
        return result;
    }

    @Override
    public String toString() {
        return name + "[" + position + "]";
    }

}
