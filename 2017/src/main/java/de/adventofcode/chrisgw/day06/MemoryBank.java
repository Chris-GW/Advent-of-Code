package de.adventofcode.chrisgw.day06;

import java.util.Comparator;


public class MemoryBank implements Comparable<MemoryBank> {

    public int memoryBank = 0;
    public int occupiedMemoryBlocks = 0;


    public MemoryBank(int memoryBank, int occupiedMemoryBlocks) {
        this.memoryBank = memoryBank;
        this.occupiedMemoryBlocks = occupiedMemoryBlocks;
    }

    public MemoryBank(MemoryBank memoryBank) {
        this(memoryBank.memoryBank, memoryBank.occupiedMemoryBlocks);
    }


    @Override
    public int compareTo(MemoryBank otherMemoryBank) {
        return Integer.compare(memoryBank, otherMemoryBank.memoryBank);
    }

    public static Comparator<MemoryBank> compareByOccupiedMemoryBlocks() {
        return Comparator.comparingInt((MemoryBank o) -> o.occupiedMemoryBlocks)
                .thenComparing(Comparator.comparingInt((MemoryBank o) -> o.memoryBank).reversed());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MemoryBank that = (MemoryBank) o;

        if (memoryBank != that.memoryBank)
            return false;
        return occupiedMemoryBlocks == that.occupiedMemoryBlocks;
    }

    @Override
    public int hashCode() {
        int result = memoryBank;
        result = 31 * result + occupiedMemoryBlocks;
        return result;
    }

    @Override
    public String toString() {
        return "(" + memoryBank + ")=" + occupiedMemoryBlocks;
    }

}
