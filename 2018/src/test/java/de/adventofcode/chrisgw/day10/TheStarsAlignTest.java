package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class TheStarsAlignTest {

    List<String> floatingPointsNotesExample01 = Arrays.asList(//
            "position=< 9,  1> velocity=< 0,  2>", //
            "position=< 7,  0> velocity=<-1,  0>", //
            "position=< 3, -2> velocity=<-1,  1>", //
            "position=< 6, 10> velocity=<-2, -1>", //
            "position=< 2, -4> velocity=< 2,  2>", //
            "position=<-6, 10> velocity=< 2, -2>", //
            "position=< 1,  8> velocity=< 1, -1>", //
            "position=< 1,  7> velocity=< 1,  0>", //
            "position=<-3, 11> velocity=< 1, -2>", //
            "position=< 7,  6> velocity=<-1, -1>", //
            "position=<-2,  3> velocity=< 1,  0>", //
            "position=<-4,  3> velocity=< 2,  0>", //
            "position=<10, -3> velocity=<-1,  1>", //
            "position=< 5, 11> velocity=< 1, -2>", //
            "position=< 4,  7> velocity=< 0, -1>", //
            "position=< 8, -2> velocity=< 0,  1>", //
            "position=<15,  0> velocity=<-2,  0>", //
            "position=< 1,  6> velocity=< 1,  0>", //
            "position=< 8,  9> velocity=< 0, -1>", //
            "position=< 3,  3> velocity=<-1,  1>", //
            "position=< 0,  5> velocity=< 0, -1>", //
            "position=<-2,  2> velocity=< 2,  0>", //
            "position=< 5, -2> velocity=< 1,  2>", //
            "position=< 1,  4> velocity=< 2,  1>", //
            "position=<-2,  7> velocity=< 2, -2>", //
            "position=< 3,  6> velocity=<-1, -1>", //
            "position=< 5,  0> velocity=< 1,  0>", //
            "position=<-6,  0> velocity=< 2,  0>", //
            "position=< 5,  9> velocity=< 1, -2>", //
            "position=<14,  7> velocity=<-2,  0>", //
            "position=<-3,  6> velocity=< 2, -1>");


    @Test
    public void formatFloatingPointMessage_example01_at_00s() {
        String expectedFloatingPointMessage = "" //
                + "........#.............\n" //
                + "................#.....\n" //
                + ".........#.#..#.......\n" //
                + "......................\n" //
                + "#..........#.#.......#\n" //
                + "...............#......\n" //
                + "....#.................\n" //
                + "..#.#....#............\n" //
                + ".......#..............\n" //
                + "......#...............\n" //
                + "...#...#.#...#........\n" //
                + "....#..#..#.........#.\n" //
                + ".......#..............\n" //
                + "...........#..#.......\n" //
                + "#...........#.........\n" //
                + "...#.......#..........";
        Duration duration = Duration.ofSeconds(0);

        TheStarsAlign theStarsAlign = new TheStarsAlign(floatingPointsNotesExample01.stream());
        theStarsAlign.moveFor(duration);
        String floatingPointMessage = formatExample01FloatingPointMessage(theStarsAlign);

        assertEquals("formatFloatingPointMessage after " + TheStarsAlign.toSeconds(duration) + "s",
                expectedFloatingPointMessage, floatingPointMessage);
    }

    private String formatExample01FloatingPointMessage(TheStarsAlign theStarsAlign) {
        FloatingPoint topLeft = new FloatingPoint(-6, -4, 0, 0);
        FloatingPoint bottomRight = new FloatingPoint(15, 11, 0, 0);
        return theStarsAlign.formatFloatingPointMessage(topLeft, bottomRight);
    }

    @Test
    public void formatFloatingPointMessage_example01_at_01s() {
        String expectedFloatingPointMessage = "" //
                + "......................\n" //
                + "......................\n" //
                + "..........#....#......\n" //
                + "........#.....#.......\n" //
                + "..#.........#......#..\n" //
                + "......................\n" //
                + "......#...............\n" //
                + "....##.........#......\n" //
                + "......#.#.............\n" //
                + ".....##.##..#.........\n" //
                + "........#.#...........\n" //
                + "........#...#.....#...\n" //
                + "..#...........#.......\n" //
                + "....#.....#.#.........\n" //
                + "......................\n" //
                + "......................";
        Duration duration = Duration.ofSeconds(1);

        TheStarsAlign theStarsAlign = new TheStarsAlign(floatingPointsNotesExample01.stream());
        theStarsAlign.moveFor(duration);
        String floatingPointMessage = formatExample01FloatingPointMessage(theStarsAlign);

        assertEquals("formatFloatingPointMessage after " + TheStarsAlign.toSeconds(duration) + "s",
                expectedFloatingPointMessage, floatingPointMessage);
    }

    @Test
    public void formatFloatingPointMessage_example01_at_02s() {
        String expectedFloatingPointMessage = "" //
                + "......................\n" //
                + "......................\n" //
                + "......................\n" //
                + "..............#.......\n" //
                + "....#..#...####..#....\n" //
                + "......................\n" //
                + "........#....#........\n" //
                + "......#.#.............\n" //
                + ".......#...#..........\n" //
                + ".......#..#..#.#......\n" //
                + "....#....#.#..........\n" //
                + ".....#...#...##.#.....\n" //
                + "........#.............\n" //
                + "......................\n" //
                + "......................\n" //
                + "......................";
        Duration duration = Duration.ofSeconds(2);

        TheStarsAlign theStarsAlign = new TheStarsAlign(floatingPointsNotesExample01.stream());
        theStarsAlign.moveFor(duration);
        String floatingPointMessage = formatExample01FloatingPointMessage(theStarsAlign);

        assertEquals("formatFloatingPointMessage after " + TheStarsAlign.toSeconds(duration) + "s",
                expectedFloatingPointMessage, floatingPointMessage);
    }

    @Test
    public void formatFloatingPointMessage_example01_at_03s() {
        String expectedFloatingPointMessage = "" //
                + "......................\n" //
                + "......................\n" //
                + "......................\n" //
                + "......................\n" //
                + "......#...#..###......\n" //
                + "......#...#...#.......\n" //
                + "......#...#...#.......\n" //
                + "......#####...#.......\n" //
                + "......#...#...#.......\n" //
                + "......#...#...#.......\n" //
                + "......#...#...#.......\n" //
                + "......#...#..###......\n" //
                + "......................\n" //
                + "......................\n" //
                + "......................\n" //
                + "......................";
        Duration duration = Duration.ofSeconds(3);

        TheStarsAlign theStarsAlign = new TheStarsAlign(floatingPointsNotesExample01.stream());
        theStarsAlign.moveFor(duration);
        String floatingPointMessage = formatExample01FloatingPointMessage(theStarsAlign);

        assertEquals("formatFloatingPointMessage after " + TheStarsAlign.toSeconds(duration) + "s",
                expectedFloatingPointMessage, floatingPointMessage);
    }

    @Test
    public void formatFloatingPointMessage_example01_at_04s() {
        String expectedFloatingPointMessage = "" //
                + "......................\n" //
                + "......................\n" //
                + "......................\n" //
                + "............#.........\n" //
                + "........##...#.#......\n" //
                + "......#.....#..#......\n" //
                + ".....#..##.##.#.......\n" //
                + ".......##.#....#......\n" //
                + "...........#....#.....\n" //
                + "..............#.......\n" //
                + "....#......#...#......\n" //
                + ".....#.....##.........\n" //
                + "...............#......\n" //
                + "...............#......\n" //
                + "......................\n" //
                + "......................";
        Duration duration = Duration.ofSeconds(4);

        TheStarsAlign theStarsAlign = new TheStarsAlign(floatingPointsNotesExample01.stream());
        theStarsAlign.moveFor(duration);
        String floatingPointMessage = formatExample01FloatingPointMessage(theStarsAlign);

        assertEquals("formatFloatingPointMessage after " + TheStarsAlign.toSeconds(duration) + "s",
                expectedFloatingPointMessage, floatingPointMessage);
    }


    @Test
    public void formatFloatingPointMessage_myPuzzleInput() throws Exception {
        String expectedFloatingPointMessage = "" //
                + "..##....#....#..######...####...#####...#....#..######..######\n" //
                + ".#..#...#....#..#.......#....#..#....#..#...#...#.......#.....\n" //
                + "#....#..#....#..#.......#.......#....#..#..#....#.......#.....\n" //
                + "#....#..#....#..#.......#.......#....#..#.#.....#.......#.....\n" //
                + "#....#..######..#####...#.......#####...##......#####...#####.\n" //
                + "######..#....#..#.......#..###..#..#....##......#.......#.....\n" //
                + "#....#..#....#..#.......#....#..#...#...#.#.....#.......#.....\n" //
                + "#....#..#....#..#.......#....#..#...#...#..#....#.......#.....\n" //
                + "#....#..#....#..#.......#...##..#....#..#...#...#.......#.....\n" //
                + "#....#..#....#..#........###.#..#....#..#....#..######..######";
        long expectedElapsedSeconds = 10243;
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day10/myPuzzleInput.txt");

        TheStarsAlign theStarsAlign = new TheStarsAlign(myPuzzleInputFile);
        while (!theStarsAlign.isFormingFloatingPointMessage()) {
            theStarsAlign.moveFor(Duration.ofSeconds(1));
        }

        String floatingPointMessage = theStarsAlign.formatFloatingPointMessage();
        long elapsedSeconds = TheStarsAlign.toSeconds(theStarsAlign.getElapsedDuration());
        System.out.println("After " + elapsedSeconds + "s:");
        System.out.println(floatingPointMessage);
        assertEquals("elapsedSeconds", expectedElapsedSeconds, elapsedSeconds);
        assertEquals("floatingPointMessage", expectedFloatingPointMessage, floatingPointMessage);
    }

}