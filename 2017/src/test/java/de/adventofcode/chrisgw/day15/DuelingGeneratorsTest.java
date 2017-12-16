package de.adventofcode.chrisgw.day15;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class DuelingGeneratorsTest {

    @Test
    public void duelingGeneratorA_startValue_65() {
        List<Integer> expectedGeneratedValues = Arrays.asList(1092455, 1181022009, 245556042, 1744312007, 1352636452);

        DuelingGenerator duelingGeneratorA = newDuelingGenerators_part1().getDuelingGeneratorA();
        for (int i = 0; i < expectedGeneratedValues.size(); i++) {
            int nextGeneratedValue = duelingGeneratorA.generateNextValue();
            int expectedGeneratedValue = expectedGeneratedValues.get(i);
            Assert.assertEquals("Expect next generated value " + i, expectedGeneratedValue, nextGeneratedValue);
        }
    }

    @Test
    public void duelingGeneratorB_startValue_8921() {
        List<Integer> expectedGeneratedValues = Arrays.asList(430625591, 1233683848, 1431495498, 137874439, 285222916);

        DuelingGenerator duelingGeneratorB = newDuelingGenerators_part1().getDuelingGeneratorB();
        for (int i = 0; i < expectedGeneratedValues.size(); i++) {
            int nextGeneratedValue = duelingGeneratorB.generateNextValue();
            int expectedGeneratedValue = expectedGeneratedValues.get(i);
            Assert.assertEquals("Expect next generated value " + i, expectedGeneratedValue, nextGeneratedValue);
        }
    }


    @Test
    public void duelingGenerators_expect_totalTairCount_588() {
        long rounds = 40_000_000;
        int expectedPairCount = 588;

        DuelingGenerators duelingGenerators = newDuelingGenerators_part1();
        for (long round = 1; round <= rounds; round++) {
            duelingGenerators.nextRound();
        }

        long matchCount = duelingGenerators.getMatchCount();
        Assert.assertEquals("expect pair count after " + rounds, expectedPairCount, matchCount);
    }

    @Test
    public void duelingGenerators_myTask() {
        long rounds = 40_000_000;
        long startValueA = 516;
        long startValueB = 190;
        int expectedPairCount = 597;

        DuelingGenerator duelingGeneratorA = DuelingGenerator.newDuelingGeneratorA(startValueA, (value) -> true);
        DuelingGenerator duelingGeneratorB = DuelingGenerator.newDuelingGeneratorB(startValueB, (value) -> true);
        DuelingGenerators duelingGenerators = new DuelingGenerators(duelingGeneratorA, duelingGeneratorB);
        for (long round = 1; round <= rounds; round++) {
            duelingGenerators.nextRound();
        }

        long matchCount = duelingGenerators.getMatchCount();
        Assert.assertEquals("expect pair count after " + rounds, expectedPairCount, matchCount);
    }


    // --- part 2

    @Test
    public void duelingGeneratorA_startValue_65_part2() {
        List<Integer> expectedGeneratedValues = Arrays.asList(1352636452, 1992081072, 530830436, 1980017072, 740335192);

        DuelingGenerator duelingGeneratorA = newDuelingGenerators_part2().getDuelingGeneratorA();
        for (int i = 0; i < expectedGeneratedValues.size(); i++) {
            int nextGeneratedValue = duelingGeneratorA.generateNextValue();
            int expectedGeneratedValue = expectedGeneratedValues.get(i);
            Assert.assertEquals("Expect next generated value " + i, expectedGeneratedValue, nextGeneratedValue);
        }
    }

    @Test
    public void duelingGeneratorB_startValue_8921_part2() {
        List<Integer> expectedGeneratedValues = Arrays.asList(1233683848, 862516352, 1159784568, 1616057672, 412269392);

        DuelingGenerator duelingGeneratorB = newDuelingGenerators_part2().getDuelingGeneratorB();
        for (int i = 0; i < expectedGeneratedValues.size(); i++) {
            int nextGeneratedValue = duelingGeneratorB.generateNextValue();
            int expectedGeneratedValue = expectedGeneratedValues.get(i);
            Assert.assertEquals("Expect next generated value " + i, expectedGeneratedValue, nextGeneratedValue);
        }
    }


    @Test
    public void duelingGenerators_part2_expect_totalTairCount_309() {
        long rounds = 5_000_000;
        int expectedPairCount = 309;

        DuelingGenerators duelingGenerators = newDuelingGenerators_part2();
        for (long round = 1; round <= rounds; round++) {
            duelingGenerators.nextRound();
        }

        long matchCount = duelingGenerators.getMatchCount();
        Assert.assertEquals("expect pair count after " + rounds, expectedPairCount, matchCount);
    }

    @Test
    public void duelingGenerators_myTask_part2() {
        long rounds = 5_000_000;
        long startValueA = 516;
        long startValueB = 190;
        int expectedPairCount = 303;

        DuelingGenerator duelingGeneratorA = DuelingGenerator.newDuelingGeneratorA(startValueA,
                (value) -> value % 4 == 0);
        DuelingGenerator duelingGeneratorB = DuelingGenerator.newDuelingGeneratorB(startValueB,
                (value) -> value % 8 == 0);
        DuelingGenerators duelingGenerators = new DuelingGenerators(duelingGeneratorA, duelingGeneratorB);
        for (long round = 1; round <= rounds; round++) {
            duelingGenerators.nextRound();
        }

        long matchCount = duelingGenerators.getMatchCount();
        Assert.assertEquals("expect pair count after " + rounds, expectedPairCount, matchCount);
    }


    public static DuelingGenerators newDuelingGenerators_part1() {
        DuelingGenerator duelingGeneratorA = DuelingGenerator.newDuelingGeneratorA(65, (value) -> true);
        DuelingGenerator duelingGeneratorB = DuelingGenerator.newDuelingGeneratorB(8921, (value) -> true);
        return new DuelingGenerators(duelingGeneratorA, duelingGeneratorB);
    }

    public static DuelingGenerators newDuelingGenerators_part2() {
        DuelingGenerator duelingGeneratorA = DuelingGenerator.newDuelingGeneratorA(65, (value) -> value % 4 == 0);
        DuelingGenerator duelingGeneratorB = DuelingGenerator.newDuelingGeneratorB(8921, (value) -> value % 8 == 0);
        return new DuelingGenerators(duelingGeneratorA, duelingGeneratorB);
    }


}