package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class AdventOfCodeDay10Test {

    @Test
    public void example01_part01() {
        List<String> asteroidMap = List.of( //
                ".#..#", //
                ".....", //
                "#####", //
                "....#", //
                "...##");
        int expectedStationLocationX = 3;
        int expectedStationLocationY = 4;
        long expectedVisibleAsteroidCount = 8;

        AdventOfCodeDay10 aocDay10 = new AdventOfCodeDay10(asteroidMap);
        AsteroidMapLocation monitoringStation = aocDay10.findBestMonitoringStationLocation();
        assertThat("monitoringStation", monitoringStation,
                allOf(hasProperty("x", is(expectedStationLocationX)), hasProperty("y", is(expectedStationLocationY))));
        assertEquals("visibleAsteroidCount", expectedVisibleAsteroidCount, monitoringStation.visibleAsteroidCount());
    }


    @Test
    public void example02_part01() {
        List<String> asteroidMap = List.of( //
                "......#.#.", //
                "#..#.#....", //
                "..#######.", //
                ".#.#.###..", //
                ".#..#.....", //
                "..#....#.#", //
                "#..#....#.", //
                ".##.#..###", //
                "##...#..#.", //
                ".#....####");
        int expectedStationLocationX = 5;
        int expectedStationLocationY = 8;
        long expectedVisibleAsteroidCount = 33;

        AdventOfCodeDay10 aocDay10 = new AdventOfCodeDay10(asteroidMap);
        AsteroidMapLocation monitoringStation = aocDay10.findBestMonitoringStationLocation();
        assertThat("monitoringStation", monitoringStation,
                allOf(hasProperty("x", is(expectedStationLocationX)), hasProperty("y", is(expectedStationLocationY))));
        assertEquals("visibleAsteroidCount", expectedVisibleAsteroidCount, monitoringStation.visibleAsteroidCount());
    }

    @Test
    public void example03_part01() {
        List<String> asteroidMap = List.of( //
                "#.#...#.#.", //
                ".###....#.", //
                ".#....#...", //
                "##.#.#.#.#", //
                "....#.#.#.", //
                ".##..###.#", //
                "..#...##..", //
                "..##....##", //
                "......#...", //
                ".####.###.");
        int expectedStationLocationX = 1;
        int expectedStationLocationY = 2;
        long expectedVisibleAsteroidCount = 35;

        AdventOfCodeDay10 aocDay10 = new AdventOfCodeDay10(asteroidMap);
        AsteroidMapLocation monitoringStation = aocDay10.findBestMonitoringStationLocation();
        assertThat("monitoringStation", monitoringStation,
                allOf(hasProperty("x", is(expectedStationLocationX)), hasProperty("y", is(expectedStationLocationY))));
        assertEquals("visibleAsteroidCount", expectedVisibleAsteroidCount, monitoringStation.visibleAsteroidCount());
    }


    @Test
    public void example04_part01() {
        List<String> asteroidMap = List.of( //
                ".#..#..###", //
                "####.###.#", //
                "....###.#.", //
                "..###.##.#", //
                "##.##.#.#.", //
                "....###..#", //
                "..#.#..#.#", //
                "#..#.#.###", //
                ".##...##.#", //
                ".....#.#..");
        int expectedStationLocationX = 6;
        int expectedStationLocationY = 3;
        long expectedVisibleAsteroidCount = 41;

        AdventOfCodeDay10 aocDay10 = new AdventOfCodeDay10(asteroidMap);
        AsteroidMapLocation monitoringStation = aocDay10.findBestMonitoringStationLocation();
        assertThat("monitoringStation", monitoringStation,
                allOf(hasProperty("x", is(expectedStationLocationX)), hasProperty("y", is(expectedStationLocationY))));
        assertEquals("visibleAsteroidCount", expectedVisibleAsteroidCount, monitoringStation.visibleAsteroidCount());
    }


    @Test
    public void example05_part01() {
        List<String> asteroidMap = List.of( //
                ".#..##.###...#######", //
                "##.############..##.", //
                ".#.######.########.#", //
                ".###.#######.####.#.", //
                "#####.##.#.##.###.##", //
                "..#####..#.#########", //
                "####################", //
                "#.####....###.#.#.##", //
                "##.#################", //
                "#####.##.###..####..", //
                "..######..##.#######", //
                "####.##.####...##..#", //
                ".#####..#.######.###", //
                "##...#.##########...", //
                "#.##########.#######", //
                ".####.#.###.###.#.##", //
                "....##.##.###..#####", //
                ".#.#.###########.###", //
                "#.#.#.#####.####.###", //
                "###.##.####.##.#..##");
        int expectedStationLocationX = 11;
        int expectedStationLocationY = 13;
        long expectedVisibleAsteroidCount = 210;

        AdventOfCodeDay10 aocDay10 = new AdventOfCodeDay10(asteroidMap);
        AsteroidMapLocation monitoringStation = aocDay10.findBestMonitoringStationLocation();
        assertThat("monitoringStation", monitoringStation,
                allOf(hasProperty("x", is(expectedStationLocationX)), hasProperty("y", is(expectedStationLocationY))));
        assertEquals("visibleAsteroidCount", expectedVisibleAsteroidCount, monitoringStation.visibleAsteroidCount());
    }


    @Test
    public void myPuzzleInput_part01() {
        List<String> asteroidMap = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        long expectedVisibleAsteroidCount = 1;

        AdventOfCodeDay10 aocDay10 = new AdventOfCodeDay10(asteroidMap);
        System.out.println(aocDay10);
        AsteroidMapLocation monitoringStation = aocDay10.findBestMonitoringStationLocation();
        assertEquals("visibleAsteroidCount", expectedVisibleAsteroidCount, monitoringStation.visibleAsteroidCount());
    }


}
