package de.adventofcode.chrisgw.day04;

import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay04Test {

    @Test
    public void myPuzzleInput_part01() {
        long expectedPossiblePasswords = 966;
        int rangeFrom = 264793;
        int rangeTo = 803935;
        long possiblePasswords = AdventOfCodeDay04.countPossiblePasswordsPart01(rangeFrom, rangeTo);
        assertEquals("possiblePasswords", expectedPossiblePasswords, possiblePasswords);
    }


    // part 02

    @Test
    public void example01_part02() {
        int password = 112233;
        boolean validPassword = AdventOfCodeDay04.isValidPasswordPart02(password);
        assertTrue("validPassword", validPassword);
    }

    @Test
    public void example02_part02() {
        int password = 123444;
        boolean validPassword = AdventOfCodeDay04.isValidPasswordPart02(password);
        assertFalse("validPassword", validPassword);
    }

    @Test
    public void example03_part02() {
        int password = 111122;
        boolean validPassword = AdventOfCodeDay04.isValidPasswordPart02(password);
        assertTrue("validPassword", validPassword);
    }


    @Test
    public void myPuzzleInput_part02() {
        long expectedPossiblePasswords = 628;
        int rangeFrom = 264793;
        int rangeTo = 803935;
        long possiblePasswords = AdventOfCodeDay04.countPossiblePasswordsPart02(rangeFrom, rangeTo);
        assertEquals("possiblePasswords", expectedPossiblePasswords, possiblePasswords);
    }

}
