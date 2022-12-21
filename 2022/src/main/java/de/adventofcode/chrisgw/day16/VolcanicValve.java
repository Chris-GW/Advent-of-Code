package de.adventofcode.chrisgw.day16;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class VolcanicValve {

    @Getter
    private final String label;
    @Getter
    private final int flowRate;

    private List<VolcanicValve> connectedValves = new ArrayList<>();
    private Map<VolcanicValve, Integer> distance = new HashMap<>();
    private Map<VolcanicValve, VolcanicValve> next = new HashMap<>();


    public VolcanicValve(String label, int flowRate) {
        this.label = label;
        this.flowRate = flowRate;

        //    for each vertex u do
        //        dist[u][u] ← 0
        //        next[u][u] ← u
        this.putDistance(this, 0);
        this.putNext(this, this);
    }

    public void connectWithValve(VolcanicValve v) {
        //  for each edge (u, v) do
        //        dist[u][v] ← w(u, v)  // The weight of the edge (u, v)
        //        next[u][v] ← v
        VolcanicValve u = this;
        u.putDistance(v, 1);
        u.putNext(v, v);
        u.connectedValves.add(v);

        v.putDistance(u, 1);
        v.putNext(u, u);
        v.connectedValves.add(u);
    }


    public Stream<VolcanicValve> connectedValves() {
        return connectedValves.stream();
    }

    public int distanceTo(VolcanicValve otherValve) {
        return distance.getOrDefault(otherValve, Integer.MAX_VALUE);
    }


    public void putDistance(VolcanicValve j, int newDistance) {
        this.distance.put(j, newDistance);
    }

    public void putNext(VolcanicValve j, VolcanicValve k) {
        this.next.put(j, k);
    }

    public VolcanicValve getNext(VolcanicValve k) {
        return this.next.get(k);
    }


    public boolean hasFlowRate() {
        return this.getFlowRate() > 0;
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
