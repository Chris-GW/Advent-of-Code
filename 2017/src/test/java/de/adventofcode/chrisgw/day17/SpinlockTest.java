package de.adventofcode.chrisgw.day17;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class SpinlockTest {


    @Test
    public void spinlock_part1_example_step_3_after_9_spins() {
        int step = 3;
        int spins = 9;
        List<Integer> expectedSpinValues = Arrays.asList(0, (9), 5, 7, 2, 4, 3, 8, 6, 1);


        Spinlock spinlock = new Spinlock();
        spinlock.followSpin(step, spins);
        System.out.println(spinlock);

        Assert.assertEquals("expect spin values", expectedSpinValues, spinlock.getValues());
    }

    @Test
    public void spinlock_part1_example_step_3() {
        int step = 3;
        int spins = 2017;
        int expectedValue = 638;

        Spinlock spinlock = new Spinlock();
        spinlock.followSpin(step, spins);
        int valueAfterCurrentPosition = spinlock.getValueAfterCurrentPosition();
        System.out.println(spinlock);

        Assert.assertEquals("expect spin values", expectedValue, valueAfterCurrentPosition);
    }

    @Test
    public void spinlock_part1_myTask_step_329() {
        int step = 329;
        int spins = 2017;
        int expectedValue = 725;

        Spinlock spinlock = new Spinlock();
        spinlock.followSpin(step, spins);
        System.out.println(spinlock);
        int valueAfterCurrentPosition = spinlock.getValueAfterCurrentPosition();

        Assert.assertEquals("expect spin values", expectedValue, valueAfterCurrentPosition);
    }


    // --- part 2

    @Test
    public void spinlock_part2_example_step_3() {
        int step = 3;
        List<Integer> expectedValues = Arrays.asList(1, 2, 2, 2, 5, 5, 5, 5, 9);

        for (int i = 1; i <= expectedValues.size(); i++) {
            int value = Spinlock.calculateValueAfter0(step, i);
            int expectedValue = expectedValues.get(i - 1);
            Assert.assertEquals("expect value after 0", expectedValue, value);
        }
    }

    @Test
    public void spinlock_part2_myTask_step_329() {
        int step = 329;
        int spins = 50_000_000;
        int expectedValue = 27361412;

        int valueAfter0 = Spinlock.calculateValueAfter0(step, spins);
        Assert.assertEquals("expect spin values", expectedValue, valueAfter0);
    }


}