package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay02Test {

    @Test
    public void example01() {
        String intCodeProgrammStr = "1,9,10,3,2,3,11,0,99,30,40,50";
        List<Integer> expectedIntCodeProgram = List.of(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50);
        int expectedFirstValue = 3500;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        while (!adventOfCodeDay02.execute()) {
            System.out.println(adventOfCodeDay02);
        }

        List<Integer> intCodeProgram = adventOfCodeDay02.getIntCodeProgram();
        assertEquals("intCodeProgram", expectedIntCodeProgram, intCodeProgram);
        int firstValue = adventOfCodeDay02.getFirstValue();
        assertEquals("firstValue", expectedFirstValue, firstValue);
    }

    @Test
    public void example02() {
        String intCodeProgrammStr = "1,0,0,0,99";
        List<Integer> expectedIntCodeProgram = List.of(2, 0, 0, 0, 99);
        int expectedFirstValue = 2;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        while (!adventOfCodeDay02.execute()) {
            System.out.println(adventOfCodeDay02);
        }

        List<Integer> intCodeProgram = adventOfCodeDay02.getIntCodeProgram();
        assertEquals("intCodeProgram", expectedIntCodeProgram, intCodeProgram);
        int firstValue = adventOfCodeDay02.getFirstValue();
        assertEquals("firstValue", expectedFirstValue, firstValue);
    }

    @Test
    public void example03() {
        String intCodeProgrammStr = "2,3,0,3,99";
        List<Integer> expectedIntCodeProgram = List.of(2, 3, 0, 6, 99);
        int expectedFirstValue = 2;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        while (!adventOfCodeDay02.execute()) {
            System.out.println(adventOfCodeDay02);
        }

        List<Integer> intCodeProgram = adventOfCodeDay02.getIntCodeProgram();
        assertEquals("intCodeProgram", expectedIntCodeProgram, intCodeProgram);
        int firstValue = adventOfCodeDay02.getFirstValue();
        assertEquals("firstValue", expectedFirstValue, firstValue);
    }

    @Test
    public void example04() {
        String intCodeProgrammStr = "2,4,4,5,99,0";
        List<Integer> expectedIntCodeProgram = List.of(2, 4, 4, 5, 99, 9801);
        int expectedFirstValue = 2;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        while (!adventOfCodeDay02.execute()) {
            System.out.println(adventOfCodeDay02);
        }

        List<Integer> intCodeProgram = adventOfCodeDay02.getIntCodeProgram();
        assertEquals("intCodeProgram", expectedIntCodeProgram, intCodeProgram);
        int firstValue = adventOfCodeDay02.getFirstValue();
        assertEquals("firstValue", expectedFirstValue, firstValue);
    }

    @Test
    public void example05() {
        String intCodeProgrammStr = "1,1,1,4,99,5,6,0,99";
        List<Integer> expectedIntCodeProgram = List.of(30, 1, 1, 4, 2, 5, 6, 0, 99);
        int expectedFirstValue = 30;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        while (!adventOfCodeDay02.execute()) {
            System.out.println(adventOfCodeDay02);
        }

        List<Integer> intCodeProgram = adventOfCodeDay02.getIntCodeProgram();
        assertEquals("intCodeProgram", expectedIntCodeProgram, intCodeProgram);
        int firstValue = adventOfCodeDay02.getFirstValue();
        assertEquals("firstValue", expectedFirstValue, firstValue);
    }


    @Test
    public void myPuzzleInputPart01() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/day02/myPuzzleInput.txt");
        int expectedFirstValue = 7594646;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        int firstValue = adventOfCodeDay02.restoreGravityAssistProgram();
        assertEquals("firstValue", expectedFirstValue, firstValue);
    }

}
