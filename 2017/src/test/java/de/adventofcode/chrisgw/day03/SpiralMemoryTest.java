package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.day03.SpiralMemory.SpiralMemoryData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SpiralMemoryTest {


    @Test
    public void calculateAccessSteps_1() {
        int value = 1;
        int expectedAccessStepCount = 0;

        SpiralMemory spiralMemory = new SpiralMemory(SpiralMemory.increaseValueByOneCalculater());
        int accessStepCount = spiralMemory.getSpiralMemoryData(value).getDistanceToPort();

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }


    @Test
    public void calculateAccessSteps_12() {
        int value = 12;
        int expectedAccessStepCount = 3;

        SpiralMemory spiralMemory = new SpiralMemory(SpiralMemory.increaseValueByOneCalculater());
        SpiralMemoryData spiralMemoryData = spiralMemory.getSpiralMemoryData(value);
        int accessStepCount = spiralMemoryData.getDistanceToPort();

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }

    @Test
    public void calculateAccessSteps_23() {
        int value = 23;
        int expectedAccessStepCount = 2;

        SpiralMemory spiralMemory = new SpiralMemory(SpiralMemory.increaseValueByOneCalculater());
        int accessStepCount = spiralMemory.getSpiralMemoryData(value).getDistanceToPort();

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }

    @Test
    public void calculateAccessSteps_1024() {
        int value = 1024;
        int expectedAccessStepCount = 31;

        SpiralMemory spiralMemory = new SpiralMemory(SpiralMemory.increaseValueByOneCalculater());
        int accessStepCount = spiralMemory.getSpiralMemoryData(value).getDistanceToPort();

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }

    @Test
    public void calculateAccessSteps_312051() {
        int value = 312051;
        int expectedAccessStepCount = 430;

        SpiralMemory spiralMemory = new SpiralMemory(SpiralMemory.increaseValueByOneCalculater());
        int accessStepCount = spiralMemory.getSpiralMemoryData(value).getDistanceToPort();

        String message = String.format("Expect %d memory access steps, but was %d for value=%d",
                expectedAccessStepCount, accessStepCount, value);
        Assert.assertEquals(message, expectedAccessStepCount, accessStepCount);
    }

    @Test
    public void calculateFirstValueGreaterThan_312051() {
        int value = 312051;
        int expectedDataValue = 312453;

        SpiralMemory spiralMemory = new SpiralMemory(SpiralMemory.sumAdjustedDataValuesCalculater());
        SpiralMemoryData spiralMemoryData = spiralMemory.getSpiralMemoryData(value);
        int dataValue = spiralMemoryData.value;

        Assert.assertTrue(
                String.format("Expect greater equal dataValue than %d, but was %d", expectedDataValue, dataValue),
                dataValue >= value);
        Assert.assertEquals(
                String.format("Expect dataValue %d, but was %d for value >= %d", expectedDataValue, dataValue, value),
                expectedDataValue, dataValue);
    }

}