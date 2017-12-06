package de.adventofcode.chrisgw.day06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MemoryMemento {

    private List<MemoryBank> memoryBanks;


    public MemoryMemento(List<MemoryBank> memoryBanks) {
        this.memoryBanks = new ArrayList<>(memoryBanks.size());
        for (MemoryBank memoryBank : memoryBanks) {
            this.memoryBanks.add(new MemoryBank(memoryBank));
        }
    }


    public List<MemoryBank> getMemoryBanks() {
        return Collections.unmodifiableList(memoryBanks);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MemoryMemento that = (MemoryMemento) o;
        return memoryBanks.equals(that.memoryBanks);
    }

    @Override
    public int hashCode() {
        return memoryBanks.hashCode();
    }

    @Override
    public String toString() {
        return memoryBanks.toString();
    }

}
