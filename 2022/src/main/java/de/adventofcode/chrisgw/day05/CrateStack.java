package de.adventofcode.chrisgw.day05;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayDeque;
import java.util.Deque;


public class CrateStack {

    @Getter
    private final int stackNumber;
    private final Deque<StackableCargoCrate> crates = new ArrayDeque<>();


    public CrateStack(int stackNumber) {
        this.stackNumber = stackNumber;
    }


    public void addLastCrate(StackableCargoCrate crate) {
        crates.addLast(crate);
    }


    public StackableCargoCrate pollCrate() {
        return crates.poll();
    }

    public void pushCrate(StackableCargoCrate crate) {
        crates.push(crate);
    }

    public StackableCargoCrate peekCrate() {
        return crates.peek();
    }


    public boolean isEmpty() {
        return crates.isEmpty();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CrateStack that))
            return false;
        return new EqualsBuilder().append(getStackNumber(), that.getStackNumber())
                .append(crates, that.crates)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getStackNumber()).toHashCode();
    }

    @Override
    public String toString() {
        return crates.toString();
    }

}
