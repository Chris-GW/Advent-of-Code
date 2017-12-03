package de.adventofcode.chrisgw.day03;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SpiralMemoryTest {

    private SpiralMemory spiralMemory;


    @Before
    public void setUp() throws Exception {
        spiralMemory = new SpiralMemory();
    }


    @Test
    public void calculateAccessSteps_1() {
        int value = 1;
        int expectedAccessStepCount = 0;

        int accessStepCount = spiralMemory.calculateAccessStepsForMemoryDataValue(value);

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }


    @Test
    public void calculateAccessSteps_12() {
        int value = 12;
        int expectedAccessStepCount = 3;

        int accessStepCount = spiralMemory.calculateAccessStepsForMemoryDataValue(value);

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }

    @Test
    public void calculateAccessSteps_23() {
        int value = 23;
        int expectedAccessStepCount = 2;

        int accessStepCount = spiralMemory.calculateAccessStepsForMemoryDataValue(value);

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }

    @Test
    public void calculateAccessSteps_1024() {
        int value = 1024;
        int expectedAccessStepCount = 31;

        int accessStepCount = spiralMemory.calculateAccessStepsForMemoryDataValue(value);

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }

    @Test
    public void calculateAccessSteps_312051() {
        int value = 312051;
        int expectedAccessStepCount = 430;

        int accessStepCount = spiralMemory.calculateAccessStepsForMemoryDataValue(value);

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }

}