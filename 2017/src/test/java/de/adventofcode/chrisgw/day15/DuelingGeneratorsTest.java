package de.adventofcode.chrisgw.day15;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class DuelingGeneratorsTest {

    @Test
    public void duelingGeneratorA_startValue_65() {
        int startValue = 65;
        List<Long> expectedGeneratedValues = Arrays.asList(1092455L, 1181022009L, 245556042L, 1744312007L, 1352636452L);

        DuelingGenerator duelingGeneratorA = DuelingGenerator.newDuelingGeneratorA(startValue);
        for (int i = 0; i < expectedGeneratedValues.size(); i++) {
            long nextGeneratedValue = duelingGeneratorA.generateNextValue();
            long expectedGeneratedValue = expectedGeneratedValues.get(i);
            Assert.assertEquals("Expect next generated value " + i, expectedGeneratedValue, nextGeneratedValue);
        }
    }

    @Test
    public void duelingGeneratorB_startValue_8921() {
        int startValue = 8921;
        List<Long> expectedGeneratedValues = Arrays.asList(430625591L, 1233683848L, 1431495498L, 137874439L,
                285222916L);

        DuelingGenerator duelingGeneratorB = DuelingGenerator.newDuelingGeneratorB(startValue);
        for (int i = 0; i < expectedGeneratedValues.size(); i++) {
            long nextGeneratedValue = duelingGeneratorB.generateNextValue();
            long expectedGeneratedValue = expectedGeneratedValues.get(i);
            Assert.assertEquals("Expect next generated value " + i, expectedGeneratedValue, nextGeneratedValue);
        }
    }


    @Test
    public void duelingGenerators_expect_totalTairCount_588() {
        long rounds = 40_000_000;
        long startValueA = 65;
        long startValueB = 8921;
        int expectedPairCount = 588;

        DuelingGenerators duelingGenerators = DuelingGenerators.createDuelingGenerators(startValueA, startValueB);
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

        DuelingGenerators duelingGenerators = DuelingGenerators.createDuelingGenerators(startValueA, startValueB);
        for (long round = 1; round <= rounds; round++) {
            duelingGenerators.nextRound();
        }

        long matchCount = duelingGenerators.getMatchCount();
        Assert.assertEquals("expect pair count after " + rounds, expectedPairCount, matchCount);
    }

}