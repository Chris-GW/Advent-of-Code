package de.adventofcode.chrisgw.day04;

import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay04Test {

    @Test
    public void myPuzzleInput() {
        long expectedPossiblePasswords = 966;
        int rangeFrom = 264793;
        int rangeTo = 803935;
        long possiblePasswords = AdventOfCodeDay04.countPossiblePasswords(rangeFrom, rangeTo);
        assertEquals("possiblePasswords", expectedPossiblePasswords, possiblePasswords);
    }

}
