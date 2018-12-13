package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day13.MineCartMadness.MineCartTrack;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                        "| | |  X |  |", //
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
        while (!mineCartMadness.crashedMineCarts().findAny().isPresent()) {
            mineCartMadness.nextTick();
        }
        List<MineCart> crashedMineCarts = mineCartMadness.crashedMineCarts().collect(Collectors.toList());
        System.out.println(mineCartMadness);
        MineCartTrack trackWithCrash = crashedMineCarts.get(0).getCurrentTrack();

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
        while (!mineCartMadness.hasAnyCartCrahsed()) {
            mineCartMadness.nextTick();
        }

        List<MineCart> crashedMineCarts = mineCartMadness.crashedMineCarts().collect(Collectors.toList());
        System.out.println(mineCartMadness);
        MineCartTrack trackWithCrash = crashedMineCarts.get(0).getCurrentTrack();
        System.out.println(trackWithCrash);
        assertEquals("track with crash x", expectedTrackWithCrashX, trackWithCrash.getX());
        assertEquals("track with crash y", expectedTrackWithCrashY, trackWithCrash.getY());
    }

}
