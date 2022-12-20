package de.adventofcode.chrisgw.day16;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class VolcanicValve {

    @Getter
    private final String label;
    @Getter
    private final int flowRate;

    private final List<VolcanicValve> connectedValves = new ArrayList<>();


    public VolcanicValve(String label, int flowRate) {
        this.label = label;
        this.flowRate = flowRate;
    }

    public void connectWithValve(VolcanicValve volcanicValve) {
        volcanicValve.connectedValves.add(this);
        this.connectedValves.add(volcanicValve);
    }


    public Stream<VolcanicValve> connectedValves() {
        return connectedValves.stream();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolcanicValve that = (VolcanicValve) o;
        return new EqualsBuilder().append(flowRate, that.flowRate).append(label, that.label).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(label).append(flowRate).toHashCode();
    }

    @Override
    public String toString() {
        return getLabel();
    }

}
