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
        assertTrue("should trigger a reaction", alchemicalReduction.triggerAllReactions());
        System.out.println(alchemicalReduction);
        String actualPolymerString = alchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }

    @Test
    public void alchemicalReduction_example_02() {
        String polymerString = "abBA";
        String expectedPolymerString = "";

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        assertTrue("should trigger a reaction", alchemicalReduction.triggerAllReactions());
        System.out.println(alchemicalReduction);
        String actualPolymerString = alchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }

    @Test
    public void alchemicalReduction_example_03() {
        String polymerString = "abAB";
        String expectedPolymerString = "abAB";

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        assertFalse("should trigger no reaction", alchemicalReduction.triggerAllReactions());
        System.out.println(alchemicalReduction);
        String actualPolymerString = alchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }

    @Test
    public void alchemicalReduction_example_04() {
        String polymerString = "aabAAB";
        String expectedPolymerString = "aabAAB";

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        assertFalse("should trigger no reaction", alchemicalReduction.triggerAllReactions());
        System.out.println(alchemicalReduction);
        String actualPolymerString = alchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }

    @Test
    public void alchemicalReduction_example_05() {
        String polymerString = "dabAcCaCBAcCcaDA";
        String expectedPolymerString = "dabCBAcaDA";

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        assertTrue("should trigger a reaction", alchemicalReduction.triggerAllReactions());
        System.out.println(alchemicalReduction);
        String actualPolymerString = alchemicalReduction.toString();
        assertEquals("expectedPolymerString after triggerAllReactions", expectedPolymerString, actualPolymerString);
    }


    @Test
    public void alchemicalReduction_myPuzzleInput() throws Exception {
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day05/myPuzzleInput.txt");
        String polymerString = Files.lines(myPuzzleInputFile).findFirst().orElse("");
        int expectedPolymerLength = 9202;

        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(polymerString);
        System.out.println(alchemicalReduction);
        assertTrue("should trigger a reaction", alchemicalReduction.triggerAllReactions());
        System.out.println(alchemicalReduction);
        int polymerLength = alchemicalReduction.polymerLength();
        assertEquals("expectedPolymerLength after triggerAllReactions", expectedPolymerLength, polymerLength);
    }

}
