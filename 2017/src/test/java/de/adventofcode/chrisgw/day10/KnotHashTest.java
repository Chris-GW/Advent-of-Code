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


    // --- part 2

    @Test
    public void knotHash_part2_emptyString() {
        String input = "";
        String expectedKnotHash = "a2582a3a0e66e6e86e3812dcb672a272";

        String knotHash = KnotHash.calculateKnotHash(input, 64);
        Assert.assertEquals("Expect knotHash for input: " + input, expectedKnotHash, knotHash);
    }

    @Test
    public void knotHash_part2_AoC_2017() {
        String input = "AoC 2017";
        String expectedKnotHash = "33efeb34ea91902bb2f59c9920caa6cd";

        String knotHash = KnotHash.calculateKnotHash(input, 64);
        Assert.assertEquals("Expect knotHash for input: " + input, expectedKnotHash, knotHash);
    }

    @Test
    public void knotHash_part2_1_2_3() {
        String input = "1,2,3";
        String expectedKnotHash = "3efbe78a8d82f29979031a4aa0b16a9d";

        String knotHash = KnotHash.calculateKnotHash(input, 64);
        Assert.assertEquals("Expect knotHash for input: " + input, expectedKnotHash, knotHash);
    }

    @Test
    public void knotHash_part2_1_2_4() {
        String input = "1,2,4";
        String expectedKnotHash = "63960835bcdc130f0b66d7ff4f6a5a8e";

        String knotHash = KnotHash.calculateKnotHash(input, 64);
        Assert.assertEquals("Expect knotHash for input: " + input, expectedKnotHash, knotHash);
    }

    @Test
    public void knotHash_part2_myTask() {
        String input = "199,0,255,136,174,254,227,16,51,85,1,2,22,17,7,192";
        String expectedKnotHash = "a9d0e68649d0174c8756a59ba21d4dc6";

        String knotHash = KnotHash.calculateKnotHash(input, 64);
        Assert.assertEquals("Expect knotHash for input: " + input, expectedKnotHash, knotHash);
    }

}
