package de.adventofcode.chrisgw.day06;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MemoryReallocationTest {

    @Test
    public void relocateMemoryBlocksTillInfiniteLoopDetected_example() {
        String startingMemoryBankStr = "0 2 7 0";
        int expectedRelocationSteps = 5;
        int expectedLoopSize = 4;
        List<MemoryMemento> expectedMemoryMementos = new ArrayList<>();
        expectedMemoryMementos.add(parseMemoryMenento("2 4 1 2"));
        expectedMemoryMementos.add(parseMemoryMenento("3 1 2 3"));
        expectedMemoryMementos.add(parseMemoryMenento("0 2 3 4"));
        expectedMemoryMementos.add(parseMemoryMenento("1 3 4 1"));
        expectedMemoryMementos.add(parseMemoryMenento("2 4 1 2"));

        MemoryReallocation memoryReallocation = MemoryReallocation.parseMemoryBanks(startingMemoryBankStr);
        System.out.println(memoryReallocation);
        for (int step = 0; step < expectedMemoryMementos.size(); step++) {
            memoryReallocation.relocateHighestOccupiedMemoryBank();
            System.out.println(memoryReallocation);
            Assert.assertEquals("Expect memory bank memento", expectedMemoryMementos.get(step),
                    memoryReallocation.saveMemoryMemento());
        }
        int actualRelocationSteps = memoryReallocation.getNeededRelocationStepsTillInfinteLoop();
        Assert.assertEquals("Expect relocations till infinte loop", expectedRelocationSteps, actualRelocationSteps);
        Assert.assertTrue("Expect infinte loop", memoryReallocation.isInfiniteRelocateLoopDetected());
        int actialLoopSize = memoryReallocation.getSizeOfInfinteRelocateLoop();
        Assert.assertEquals("Expect relocations infinte loop size", expectedLoopSize, actialLoopSize);
    }

    @Test
    public void relocateMemoryBlocksTillInfiniteLoopDetected_myTask() {
        String startingMemoryBankStr = "5\t1\t10\t0\t1\t7\t13\t14\t3\t12\t8\t10\t7\t12\t0\t6";
        int expectedRelocationSteps = 5042;
        int expectedLoopSize = 1086;

        MemoryReallocation memoryReallocation = MemoryReallocation.parseMemoryBanks(startingMemoryBankStr);
        System.out.println(memoryReallocation);
        memoryReallocation.relocateMemoryBlocksTillInfiniteLoopDetected();
        int actualRelocationSteps = memoryReallocation.getNeededRelocationStepsTillInfinteLoop();
        System.out.println(memoryReallocation);
        Assert.assertEquals("Expect relocations till infinte loop", expectedRelocationSteps, actualRelocationSteps);
        Assert.assertTrue("Expect infinte loop", memoryReallocation.isInfiniteRelocateLoopDetected());
        int actialLoopSize = memoryReallocation.getSizeOfInfinteRelocateLoop();
        Assert.assertEquals("Expect relocations infinte loop size", expectedLoopSize, actialLoopSize);
    }


    private MemoryMemento parseMemoryMenento(String memoryBankStr) {
        MemoryReallocation memoryReallocation = MemoryReallocation.parseMemoryBanks(memoryBankStr);
        return memoryReallocation.saveMemoryMemento();
    }

}