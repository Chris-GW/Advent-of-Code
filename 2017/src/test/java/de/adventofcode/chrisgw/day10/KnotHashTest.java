package de.adventofcode.chrisgw.day10;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class KnotHashTest {

    @Test
    public void knotHash_example() {
        List<Integer> lenghts = Arrays.asList(3, 4, 1, 5);

        List<List<Integer>> expectedValuesPerStep = new ArrayList<>();
        expectedValuesPerStep.add(Arrays.asList(2, 1, 0, 3, 4));
        expectedValuesPerStep.add(Arrays.asList(4, 3, 0, 1, 2));
        expectedValuesPerStep.add(Arrays.asList(4, 3, 0, 1, 2));
        expectedValuesPerStep.add(Arrays.asList(3, 4, 2, 1, 0));
        int expectedFirstTwoElementProduct = 3 * 4;


        KnotHash knotHash = new KnotHash(4);
        for (int i = 0; i < lenghts.size(); i++) {
            knotHash.step(lenghts.get(i));
            List<Integer> expectedValues = expectedValuesPerStep.get(i);
            Assert.assertEquals("Expect values after " + i + " step", expectedValues, knotHash.getValues());
        }
        int firstTwoElementProduct = knotHash.multiplyFirstTwoElements();
        Assert.assertEquals("Expect product of two rist elements", expectedFirstTwoElementProduct,
                firstTwoElementProduct);
    }


    @Test
    public void knotHash_myTask() {
        List<Integer> lenghts = Arrays.asList(199, 0, 255, 136, 174, 254, 227, 16, 51, 85, 1, 2, 22, 17, 7, 192);
        int expectedFirstTwoElementProduct = 65 * 58;

        KnotHash knotHash = new KnotHash(255);
        knotHash.step(lenghts);
        int firstTwoElementProduct = knotHash.multiplyFirstTwoElements();
        Assert.assertEquals("Expect product of two rist elements", expectedFirstTwoElementProduct,
                firstTwoElementProduct);
    }

}
