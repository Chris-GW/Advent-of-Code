package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay03Test {

    @Test
    public void example01_part01() {
        List<String> wirePaths = List.of( //
                "R8,U5,L5,D3", //
                "U7,R6,D4,L4");
        GridPoint expectedClosesPoint = new GridPoint(3, 3);
        int expectedDistance = 3 + 3;

        AdventOfCodeDay03 adventOfCodeDay03 = new AdventOfCodeDay03(wirePaths);
        System.out.println(adventOfCodeDay03);

        GridPoint closesPoint = adventOfCodeDay03.closesIntersectionPoint();
        int distance = adventOfCodeDay03.getCentralPort().distanceTo(closesPoint);
        assertEquals("closesPoint", expectedClosesPoint, closesPoint);
        assertEquals("distance", expectedDistance, distance);
    }

    @Test
    public void example02_part01() {
        List<String> wirePaths = List.of( //
                "R75,D30,R83,U83,L12,D49,R71,U7,L72", //
                "U62,R66,U55,R34,D71,R55,D58,R83");
        int expectedDistance = 159;

        AdventOfCodeDay03 adventOfCodeDay03 = new AdventOfCodeDay03(wirePaths);
        System.out.println(adventOfCodeDay03);

        GridPoint closesPoint = adventOfCodeDay03.closesIntersectionPoint();
        int distance = adventOfCodeDay03.getCentralPort().distanceTo(closesPoint);
        assertEquals("distance", expectedDistance, distance);
    }

    @Test
    public void example03_part01() {
        List<String> wirePaths = List.of( //
                "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", //
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
        int expectedDistance = 135;

        AdventOfCodeDay03 adventOfCodeDay03 = new AdventOfCodeDay03(wirePaths);
        System.out.println(adventOfCodeDay03);

        GridPoint closesPoint = adventOfCodeDay03.closesIntersectionPoint();
        int distance = adventOfCodeDay03.getCentralPort().distanceTo(closesPoint);
        assertEquals("distance", expectedDistance, distance);
    }


    @Test
    public void myPuzzleInput_part01() {
        List<String> wirePaths = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int expectedDistance = 1983;

        AdventOfCodeDay03 adventOfCodeDay03 = new AdventOfCodeDay03(wirePaths);

        GridPoint closesPoint = adventOfCodeDay03.closesIntersectionPoint();
        int distance = adventOfCodeDay03.getCentralPort().distanceTo(closesPoint);
        assertEquals("distance", expectedDistance, distance);
    }


    // part 02

    @Test
    public void example01_part02() {
        List<String> wirePaths = List.of( //
                "R8,U5,L5,D3", //
                "U7,R6,D4,L4");
        int expectedDistance = (8 + 5 + 2) + (7 + 6 + 2);

        AdventOfCodeDay03 adventOfCodeDay03 = new AdventOfCodeDay03(wirePaths);
        int distance = adventOfCodeDay03.closesSignalIntersectionPoint();
        assertEquals("distance", expectedDistance, distance);
    }

    @Test
    public void example02_part02() {
        List<String> wirePaths = List.of( //
                "R75,D30,R83,U83,L12,D49,R71,U7,L72", //
                "U62,R66,U55,R34,D71,R55,D58,R83");
        int expectedDistance = 610;

        AdventOfCodeDay03 adventOfCodeDay03 = new AdventOfCodeDay03(wirePaths);
        int distance = adventOfCodeDay03.closesSignalIntersectionPoint();
        assertEquals("distance", expectedDistance, distance);
    }

    @Test
    public void example03_part02() {
        List<String> wirePaths = List.of( //
                "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", //
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
        int expectedDistance = 410;

        AdventOfCodeDay03 adventOfCodeDay03 = new AdventOfCodeDay03(wirePaths);
        int distance = adventOfCodeDay03.closesSignalIntersectionPoint();
        assertEquals("distance", expectedDistance, distance);
    }


    @Test
    public void myPuzzleInput_part02() {
        List<String> wirePaths = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int expectedDistance = 107754;

        AdventOfCodeDay03 adventOfCodeDay03 = new AdventOfCodeDay03(wirePaths);
        int distance = adventOfCodeDay03.closesSignalIntersectionPoint();
        assertEquals("distance", expectedDistance, distance);
    }

}