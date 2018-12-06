package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.Assert.*;


public class ChronalCoordinatesTest {

    @Test
    public void largestAreaSize_example_01() {
        int expectedLargestAreaSize = 17;
        Stream<String> placeCoordinateLines = Stream.of( //
                "1, 1",//
                "1, 6",//
                "8, 3",//
                "3, 4",//
                "5, 5",//
                "8, 9");

        ChronalCoordinates chronalCoordinates = ChronalCoordinates.parsePlaceCoordinates(placeCoordinateLines);
        System.out.println(chronalCoordinates.toString());

        int largestAreaSize = chronalCoordinates.largestAreaSize();
        assertEquals("largestAreaSize", expectedLargestAreaSize, largestAreaSize);
    }

    @Test
    public void largestAreaSize_myPuzzleInput() throws Exception {
        int expectedLargestAreaSize = 3660;
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day06/myPuzzleInput.txt");

        ChronalCoordinates chronalCoordinates = ChronalCoordinates.parsePlaceCoordinates(myPuzzleInputFile);
        int largestAreaSize = chronalCoordinates.largestAreaSize();
        assertEquals("largestAreaSize", expectedLargestAreaSize, largestAreaSize);
    }


    // part 02

    @Test
    public void closestAreaSize_example_01() {
        int expectedClosestAreaSize = 16;
        Stream<String> placeCoordinateLines = Stream.of( //
                "1, 1",//
                "1, 6",//
                "8, 3",//
                "3, 4",//
                "5, 5",//
                "8, 9");

        ChronalCoordinates chronalCoordinates = ChronalCoordinates.parsePlaceCoordinates(placeCoordinateLines);
        System.out.println(chronalCoordinates.toString());

        int closestAreaSize = chronalCoordinates.closestAreaSize(32);
        assertEquals("largestAreaSize", expectedClosestAreaSize, closestAreaSize);
    }

    @Test
    public void closestAreaSize_myPuzzleInput() throws Exception {
        int expectedClosestAreaSize = 3660;
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day06/myPuzzleInput.txt");

        ChronalCoordinates chronalCoordinates = ChronalCoordinates.parsePlaceCoordinates(myPuzzleInputFile);
        int closestAreaSize = chronalCoordinates.closestAreaSize(10_000);
        assertEquals("largestAreaSize", expectedClosestAreaSize, closestAreaSize);
    }

}
