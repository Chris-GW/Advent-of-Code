package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day13.MineCartMadness.MineCartTrack;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class MineCartMadnessTest {


    @Test
    public void mineCartMadness_example01() {
        List<String> trackLines = Arrays.asList( //
                "|", //
                "v", //
                "|", //
                "|", //
                "|", //
                "^", //
                "|");

        MineCartMadness mineCartMadness = new MineCartMadness(trackLines);
        System.out.println(mineCartMadness);
        for (int tick = 0; tick < 4; tick++) {
            mineCartMadness.nextTick();
            System.out.println(mineCartMadness);
        }
    }

    @Test
    public void mineCartMadness_example02() {
        List<String> trackLines = Arrays.asList( //
                "/->-\\        ", //
                "|   |  /----\\", //
                "| /-+--+-\\  |", //
                "| | |  | v  |", //
                "\\-+-/  \\-+--/", //
                "  \\------/   ");

        List<List<String>> expectedMineCartMadness = Arrays.asList(Arrays.asList( // tick 01
                "/->-\\        ", //
                "|   |  /----\\", //
                "| /-+--+-\\  |", //
                "| | |  | v  |", //
                "\\-+-/  \\-+--/", //
                "  \\------/   "), //

                Arrays.asList( // tick 02
                        "/-->\\        ", //
                        "|   |  /----\\", //
                        "| /-+--+-\\  |", //
                        "| | |  | |  |", //
                        "\\-+-/  \\->--/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 03
                        "/---v        ", //
                        "|   |  /----\\", //
                        "| /-+--+-\\  |", //
                        "| | |  | |  |", //
                        "\\-+-/  \\-+>-/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 04
                        "/---\\        ", //
                        "|   v  /----\\", //
                        "| /-+--+-\\  |", //
                        "| | |  | |  |", //
                        "\\-+-/  \\-+->/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 05
                        "/---\\        ", //
                        "|   |  /----\\", //
                        "| /->--+-\\  |", //
                        "| | |  | |  |", //
                        "\\-+-/  \\-+--^", //
                        "  \\------/   "), //

                Arrays.asList( // tick 06
                        "/---\\        ", //
                        "|   |  /----\\", //
                        "| /-+>-+-\\  |", //
                        "| | |  | |  ^", //
                        "\\-+-/  \\-+--/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 07
                        "/---\\        ", //
                        "|   |  /----\\", //
                        "| /-+->+-\\  ^", //
                        "| | |  | |  |", //
                        "\\-+-/  \\-+--/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 08
                        "/---\\        ", //
                        "|   |  /----<", //
                        "| /-+-->-\\  |", //
                        "| | |  | |  |", //
                        "\\-+-/  \\-+--/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 09
                        "/---\\        ", //
                        "|   |  /---<\\", //
                        "| /-+--+>\\  |", //
                        "| | |  | |  |", //
                        "\\-+-/  \\-+--/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 10
                        "/---\\        ", //
                        "|   |  /--<-\\", //
                        "| /-+--+-v  |", //
                        "| | |  | |  |", //
                        "\\-+-/  \\-+--/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 11
                        "/---\\        ", //
                        "|   |  /-<--\\", //
                        "| /-+--+-\\  |", //
                        "| | |  | v  |", //
                        "\\-+-/  \\-+--/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 12
                        "/---\\        ", //
                        "|   |  /<---\\", //
                        "| /-+--+-\\  |", //
                        "| | |  | |  |", //
                        "\\-+-/  \\-<--/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 13
                        "/---\\        ",//
                        "|   |  v----\\",//
                        "| /-+--+-\\  |",//
                        "| | |  | |  |",//
                        "\\-+-/  \\<+--/",//
                        "  \\------/   "), //

                Arrays.asList( // tick 14
                        "/---\\        ", //
                        "|   |  /----\\", //
                        "| /-+--v-\\  |", //
                        "| | |  | |  |", //
                        "\\-+-/  ^-+--/", //
                        "  \\------/   "), //

                Arrays.asList( // tick 15
                        "/---\\        ", //
                        "|   |  /----\\", //
                        "| /-+--+-\\  |", //
                        "| | |  | |  |", //
                        "\\-+-/  \\-+--/", //
                        "  \\------/   "));


        MineCartMadness mineCartMadness = new MineCartMadness(trackLines);
        for (int tick = 0; tick < expectedMineCartMadness.size(); tick++) {
            String mineCartMadnessStr = mineCartMadness.toString();
            System.out.println("Tick: " + tick);
            System.out.println(mineCartMadness);
            System.out.println();
            String expectedMineCartMadnessStr = String.join("\n", expectedMineCartMadness.get(tick));
            assertEquals("mineCartMadnesss after tick" + tick, expectedMineCartMadnessStr, mineCartMadnessStr);
            mineCartMadness.nextTick();
        }
    }


    @Test
    public void crashedMineCarts_example01() {
        List<String> trackLines = Arrays.asList( //
                "/->-\\        ", //
                "|   |  /----\\", //
                "| /-+--+-\\  |", //
                "| | |  | v  |", //
                "\\-+-/  \\-+--/", //
                "  \\------/   ");
        int expectedTrackWithCrashX = 7;
        int expectedTrackWithCrashY = 3;

        MineCartMadness mineCartMadness = new MineCartMadness(trackLines);
        Collection<MineCart> crashedMineCarts = Collections.emptyList();
        while (crashedMineCarts.isEmpty()) {
            crashedMineCarts = mineCartMadness.nextTick();
        }
        System.out.println(mineCartMadness);

        MineCart crashedMineCart = crashedMineCarts.stream().findAny().orElseThrow(IllegalArgumentException::new);
        MineCartTrack trackWithCrash = crashedMineCart.getCurrentTrack();
        System.out.println(trackWithCrash);
        assertEquals("track with crash x", expectedTrackWithCrashX, trackWithCrash.getX());
        assertEquals("track with crash y", expectedTrackWithCrashY, trackWithCrash.getY());
    }


    @Test
    public void crashedMineCarts_myPuzzleInput() throws Exception {
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day13/myPuzzleInput.txt");
        List<String> trackLines = Files.readAllLines(myPuzzleInputFile);
        int expectedTrackWithCrashX = 41;
        int expectedTrackWithCrashY = 17;

        MineCartMadness mineCartMadness = new MineCartMadness(trackLines);
        Collection<MineCart> crashedMineCarts = Collections.emptyList();
        while (crashedMineCarts.isEmpty()) {
            crashedMineCarts = mineCartMadness.nextTick();
        }
        System.out.println(mineCartMadness);

        MineCart crashedMineCart = crashedMineCarts.stream().findAny().orElseThrow(IllegalArgumentException::new);
        MineCartTrack trackWithCrash = crashedMineCart.getCurrentTrack();
        System.out.println(trackWithCrash);
        assertEquals("track with crash x", expectedTrackWithCrashX, trackWithCrash.getX());
        assertEquals("track with crash y", expectedTrackWithCrashY, trackWithCrash.getY());
    }


    @Test
    public void crashedMineCarts_till_only_one_is_left_example01() {
        List<String> trackLines = Arrays.asList( //
                "/>-<\\  ", //
                "|   |  ", //
                "| /<+-\\", //
                "| | | v", //
                "\\>+</ |", //
                "  |   ^", //
                "  \\<->/");

        List<String> expectedMineCartMadness = Arrays.asList("" // Tick 00
                        + "/>-<\\  \n" //
                        + "|   |  \n" //
                        + "| /<+-\\\n" //
                        + "| | | v\n" //
                        + "\\>+</ |\n" //
                        + "  |   ^\n" //
                        + "  \\<->/", //
                "" // Tick 01
                        + "/---\\  \n" //
                        + "|   |  \n" //
                        + "| v-+-\\\n" //
                        + "| | | |\n" //
                        + "\\-+-/ |\n" //
                        + "  |   |\n" //
                        + "  ^---^", //
                ""  // Tick 02
                        + "/---\\  \n"  //
                        + "|   |  \n"  //
                        + "| /-+-\\\n"  //
                        + "| v | |\n"  //
                        + "\\-+-/ |\n" //
                        + "  ^   ^\n"  //
                        + "  \\---/",  //
                "" // Tick 03
                        + "/---\\  \n" //
                        + "|   |  \n" //
                        + "| /-+-\\\n" //
                        + "| | | |\n" //
                        + "\\-+-/ ^\n" //
                        + "  |   |\n" //
                        + "  \\---/"); //
        int expectedTrackWithCrashX = 6;
        int expectedTrackWithCrashY = 4;

        MineCartMadness mineCartMadness = new MineCartMadness(trackLines);
        for (int tick = 0; mineCartMadness.mineCartCount() > 1; tick++) {
            String mineCartMadnessStr = mineCartMadness.toString();
            System.out.println("Tick: " + tick);
            System.out.println(mineCartMadnessStr);
            System.out.println();
            String expectedMineCartMadnessStr = expectedMineCartMadness.get(tick);
            assertEquals("mineCartMadnesss after tick" + tick, expectedMineCartMadnessStr, mineCartMadnessStr);
            mineCartMadness.nextTick();
        }
        System.out.println(mineCartMadness);

        MineCart survivingMineCart = mineCartMadness.mineCarts().findAny().orElseThrow(IllegalArgumentException::new);
        MineCartTrack track = survivingMineCart.getCurrentTrack();
        System.out.println(track);
        assertEquals("track with crash x", expectedTrackWithCrashX, track.getX());
        assertEquals("track with crash y", expectedTrackWithCrashY, track.getY());
    }

    @Test
    public void crashedMineCarts_till_only_one_is_left_myPuzzleInput() throws Exception {
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day13/myPuzzleInput.txt");
        List<String> trackLines = Files.readAllLines(myPuzzleInputFile);
        int expectedTrackWithCrashX = 134;
        int expectedTrackWithCrashY = 117;

        MineCartMadness mineCartMadness = new MineCartMadness(trackLines);
        for (int tick = 0; mineCartMadness.mineCartCount() > 1; tick++) {
            mineCartMadness.nextTick();
        }
        System.out.println(mineCartMadness);

        MineCart survivingMineCart = mineCartMadness.mineCarts().findAny().orElseThrow(IllegalArgumentException::new);
        MineCartTrack track = survivingMineCart.getCurrentTrack();
        System.out.println(track);
        assertEquals("track with crash x", expectedTrackWithCrashX, track.getX());
        assertEquals("track with crash y", expectedTrackWithCrashY, track.getY());
    }

}
