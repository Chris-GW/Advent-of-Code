package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;


public class AlchemicalReductionTest {


    @Test
    public void alchemicalReduction_example_01() {
        String polymerString = "aA";
        String expectedPolymerString = "";

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        AlchemicalReduction afterAlchemicalReduction = alchemicalReduction.triggerAllReactions();
        System.out.println(afterAlchemicalReduction);
        String actualPolymerString = afterAlchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }

    @Test
    public void alchemicalReduction_example_02() {
        String polymerString = "abBA";
        String expectedPolymerString = "";

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        AlchemicalReduction afterAlchemicalReduction = alchemicalReduction.triggerAllReactions();
        System.out.println(afterAlchemicalReduction);
        String actualPolymerString = afterAlchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }

    @Test
    public void alchemicalReduction_example_03() {
        String polymerString = "abAB";
        String expectedPolymerString = "abAB";

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        AlchemicalReduction afterAlchemicalReduction = alchemicalReduction.triggerAllReactions();
        System.out.println(afterAlchemicalReduction);
        String actualPolymerString = afterAlchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }

    @Test
    public void alchemicalReduction_example_04() {
        String polymerString = "aabAAB";
        String expectedPolymerString = "aabAAB";

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        AlchemicalReduction afterAlchemicalReduction = alchemicalReduction.triggerAllReactions();
        System.out.println(afterAlchemicalReduction);
        String actualPolymerString = afterAlchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }

    @Test
    public void alchemicalReduction_example_05() {
        String polymerString = "dabAcCaCBAcCcaDA";
        String expectedPolymerString = "dabCBAcaDA";

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        AlchemicalReduction afterAlchemicalReduction = alchemicalReduction.triggerAllReactions();
        System.out.println(afterAlchemicalReduction);
        String actualPolymerString = afterAlchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }


    @Test
    public void alchemicalReduction_myPuzzleInput() throws Exception {
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day05/myPuzzleInput.txt");
        String polymerString = Files.lines(myPuzzleInputFile).findFirst().orElse("");
        int expectedPolymerLength = 9202;

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        AlchemicalReduction afterAlchemicalReduction = alchemicalReduction.triggerAllReactions();
        System.out.println(afterAlchemicalReduction);
        int polymerLength = afterAlchemicalReduction.polymerLength();
        assertEquals("expectedPolymerLength after triggerAllReactions", expectedPolymerLength, polymerLength);
    }


    // part 02

    @Test
    public void alchemicalReduction_withoutBlockingUnit_example_05() {
        String polymerString = "dabAcCaCBAcCcaDA";
        int expectedPolymerLength = 4;

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        AlchemicalReduction afterAlchemicalReduction = alchemicalReduction.triggerAllReactionsWithoutBlockingUnit();
        System.out.println(afterAlchemicalReduction);
        int polymerLength = afterAlchemicalReduction.polymerLength();
        assertEquals("expectedPolymerLength after triggerAllReactions with removing blockingUnit",
                expectedPolymerLength, polymerLength);
    }

    @Test
    public void alchemicalReduction_withoutBlockingUnit_myPuzzleInput() throws Exception {
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day05/myPuzzleInput.txt");
        String polymerString = Files.lines(myPuzzleInputFile).findFirst().orElse("");
        int expectedPolymerLength = 6394;

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        AlchemicalReduction afterAlchemicalReduction = alchemicalReduction.triggerAllReactionsWithoutBlockingUnit();
        System.out.println(afterAlchemicalReduction);
        int polymerLength = afterAlchemicalReduction.polymerLength();
        System.out.println(polymerLength);
        assertEquals("expectedPolymerLength after triggerAllReactions with removing blockingUnit",
                expectedPolymerLength, polymerLength);
    }

    @Test
    public void alchemicalReduction_withoutBlockingUnit_myPuzzleInput02() throws Exception {
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day05/myPuzzleInput02.txt");
        String polymerString = Files.lines(myPuzzleInputFile).findFirst().orElse("");
        int expectedPolymerLength = 4552;

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        AlchemicalReduction afterAlchemicalReduction = alchemicalReduction.triggerAllReactionsWithoutBlockingUnit();
        System.out.println(afterAlchemicalReduction);
        int polymerLength = afterAlchemicalReduction.polymerLength();
        System.out.println(polymerLength);
        assertEquals("expectedPolymerLength after triggerAllReactions with removing blockingUnit",
                expectedPolymerLength, polymerLength);
    }

}
