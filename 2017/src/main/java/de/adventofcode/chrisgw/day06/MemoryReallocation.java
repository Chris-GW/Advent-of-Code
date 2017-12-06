package de.adventofcode.chrisgw.day06;

import java.util.*;


/**
 * <pre>
 * --- Day 6: Memory Reallocation ---
 *
 * A debugger program here is having an issue: it is trying to repair a memoryBanks
 * reallocation routine, but it keeps getting stuck in an infinite loop.
 *
 * In this area, there are sixteen memoryBanks banks; each memoryBanks bank can hold any
 * number of blocks. The goal of the reallocation routine is to balance the
 * blocks between the memoryBanks banks.
 *
 * The reallocation routine operates in cycles. In each cycle, it finds the
 * memoryBanks bank with the most blocks (ties won by the lowest-numbered memoryBanks bank) and redistributes those blocks among the banks. To do this, it removes all of the blocks from the selected bank, then moves to the next (by index) memoryBanks bank and inserts one of the blocks. It continues doing this until it runs out of blocks; if it reaches the last memoryBanks bank, it wraps around to the first one.
 *
 * The debugger would like to know how many redistributions can be done before
 * a blocks-in-banks configuration is produced that has been seen before.
 *
 * For example, imagine a scenario with only four memoryBanks banks:
 * - The banks start with 0, 2, 7, and 0 blocks. The third bank has the most
 *   blocks, so it is chosen for redistribution.
 * - Starting with the next bank (the fourth bank) and then continuing to the
 *   first bank, the second bank, and so on, the 7 blocks are spread out over
 *   the memoryBanks banks. The fourth, first, and second banks get two blocks each,
 *   and the third bank gets one back. The final result looks like this: 2 4 1 2.
 * - Next, the second bank is chosen because it contains the most blocks (four).
 *   Because there are four memoryBanks banks, each gets one block. The result is: 3 1 2 3.
 * - Now, there is a tie between the first and fourth memoryBanks banks, both of which
 *   have three blocks. The first bank wins the tie, and its three blocks are
 *   distributed evenly over the other three banks, leaving it with none: 0 2 3 4.
 * - The fourth bank is chosen, and its four blocks are distributed such that each
 *   of the four banks receives one: 1 3 4 1.
 * - The third bank is chosen, and the same thing happens: 2 4 1 2.
 *
 * At this point, we've reached a state we've seen before: 2 4 1 2 was already seen.
 * The infinite loop is detected after the fifth block redistribution cycle, and so
 * the answer in this example is 5.
 *
 * Given the initial block counts in your puzzle input, how many redistribution
 * cycles must be completed before a configuration is produced that has been seen before?
 * </pre>
 */
public class MemoryReallocation {

    private List<MemoryBank> memoryBanks = new ArrayList<>(16);
    private Set<MemoryMemento> seenMemoryMementos = new LinkedHashSet<>();
    private boolean isInfiniteRelocateLoopDetected = false;


    public static MemoryReallocation parseMemoryBanks(String memoryBankStr) {
        String[] splitMemoryBlocks = memoryBankStr.split("\\s+");
        List<MemoryBank> memoryBank = new ArrayList<>(splitMemoryBlocks.length);
        for (int memoryBankIndex = 0; memoryBankIndex < splitMemoryBlocks.length; memoryBankIndex++) {
            int occupiedMemoryBlocks = Integer.parseInt(splitMemoryBlocks[memoryBankIndex]);
            memoryBank.add(new MemoryBank(memoryBankIndex, occupiedMemoryBlocks));
        }
        return new MemoryReallocation(memoryBank);
    }

    public MemoryReallocation(List<MemoryBank> memoryBanks) {
        this.memoryBanks = memoryBanks;
        Collections.sort(this.memoryBanks);
        seenMemoryMementos.add(saveMemoryMemento());
    }


    public void relocateMemoryBlocksTillInfiniteLoopDetected() {
        while (!isInfiniteRelocateLoopDetected) {
            relocateHighestOccupiedMemoryBank();
        }
    }

    public void relocateHighestOccupiedMemoryBank() {
        if (memoryBanks.isEmpty()) {
            return;
        }
        MemoryBank highestMemoryBank = findHighestOccupiedMemoryBank();
        int memoryBlocksToDistribute = highestMemoryBank.occupiedMemoryBlocks;
        highestMemoryBank.occupiedMemoryBlocks = 0;

        for (Iterator<MemoryBank> cycleMemoryBankIterator = getCycleMemoryBankIterator(highestMemoryBank);
             memoryBlocksToDistribute > 0; memoryBlocksToDistribute--) {
            MemoryBank nextMemoryBank = cycleMemoryBankIterator.next();
            nextMemoryBank.occupiedMemoryBlocks += 1;
        }
        isInfiniteRelocateLoopDetected = !seenMemoryMementos.add(saveMemoryMemento());
    }


    private MemoryBank findHighestOccupiedMemoryBank() {
        return memoryBanks.stream().max(MemoryBank.compareByOccupiedMemoryBlocks()).orElse(null);
    }

    public MemoryMemento saveMemoryMemento() {
        return new MemoryMemento(memoryBanks);
    }


    public boolean isInfiniteRelocateLoopDetected() {
        return isInfiniteRelocateLoopDetected;
    }

    public int getNeededRelocationStepsTillInfinteLoop() {
        return seenMemoryMementos.size();
    }


    public int getSizeOfInfinteRelocateLoop() {
        if (isInfiniteRelocateLoopDetected) {
            MemoryMemento currentMemoryMemento = saveMemoryMemento();
            int index = 0;
            for (MemoryMemento seenMemoryMemento : seenMemoryMementos) {
                if (seenMemoryMemento.equals(currentMemoryMemento)) {
                    break;
                }
                index++;
            }
            return seenMemoryMementos.size() - index;
        } else {
            relocateMemoryBlocksTillInfiniteLoopDetected();
            return getSizeOfInfinteRelocateLoop();
        }
    }


    private Iterator<MemoryBank> getCycleMemoryBankIterator(MemoryBank startingMemoryBank) {
        return new Iterator<MemoryBank>() {

            private int index = startingMemoryBank.memoryBank;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public MemoryBank next() {
                if (++index >= memoryBanks.size()) {
                    index = 0;
                }
                return memoryBanks.get(index);
            }
        };
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MemoryReallocation that = (MemoryReallocation) o;

        return memoryBanks != null ? memoryBanks.equals(that.memoryBanks) : that.memoryBanks == null;
    }

    @Override
    public int hashCode() {
        return memoryBanks != null ? memoryBanks.hashCode() : 0;
    }

    @Override
    public String toString() {
        return memoryBanks.toString();
    }

}
