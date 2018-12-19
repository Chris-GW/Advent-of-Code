package de.adventofcode.chrisgw.day17;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day17.ReservoirResearch.GroundSquare;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ReservoirResearchTest {

    private List<String> groundSliceLines_example01 = Arrays.asList( //
            "x=495, y=2..7", //
            "y=7, x=495..501", // y fixed
            "x=501, y=3..7", //
            "x=498, y=2..4", //
            "x=506, y=1..2", // 5
            "x=498, y=10..13", //
            "x=504, y=10..13", //
            "y=13, x=498..504"); // y fixed


    @Test
    public void parseClayVeinLine_example_01() {
        List<GroundSquare> expectedClaySquares = Arrays.asList( //
                new GroundSquare(495, 2), new GroundSquare(495, 2), //
                new GroundSquare(495, 7), new GroundSquare(501, 7), // y fixed
                new GroundSquare(501, 3), new GroundSquare(501, 7), //
                new GroundSquare(498, 2), new GroundSquare(498, 4), //
                new GroundSquare(506, 1), new GroundSquare(506, 2), // 5
                new GroundSquare(498, 10), new GroundSquare(498, 13), //
                new GroundSquare(504, 10), new GroundSquare(504, 13), //
                new GroundSquare(498, 13), new GroundSquare(504, 13)); // y fixed

        ReservoirResearch reservoirResearch = new ReservoirResearch(groundSliceLines_example01);
        System.out.println(reservoirResearch);

        for (GroundSquare expectedClaySquare : expectedClaySquares) {
            boolean clayGroundSquare = reservoirResearch.isClayGroundSquare(expectedClaySquare);
            assertTrue("expect clayGroundSquare at: " + expectedClaySquare, clayGroundSquare);
        }
    }


    @Test
    public void countAllWaterSquares_example01() {
        int expectedAllWaterSquares = 57;
        ReservoirResearch reservoirResearch = new ReservoirResearch(groundSliceLines_example01);
        for (int i = 0; reservoirResearch.nextWaterSquare(); i++) {
            System.out.println(i);
            System.out.println(reservoirResearch);
        }
        System.out.println(reservoirResearch);
        int allWaterSquares = reservoirResearch.waterSquareCount();
        assertEquals("countAllWaterSquares", expectedAllWaterSquares, allWaterSquares);
    }

    @Test
    public void countAllWaterSquares_myPuzzleInput() throws Exception {
        int expectedAllWaterSquares = 31412;
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day17/myPuzzleInput.txt");
        ReservoirResearch reservoirResearch = new ReservoirResearch(Files.readAllLines(myPuzzleInputFile));

        for (int round = 1; reservoirResearch.nextWaterSquare(); round++) {
//            System.out.println(round);
        }
        System.out.println(reservoirResearch);
        System.out.println(reservoirResearch.getRounds());
        int allWaterSquares = reservoirResearch.waterSquareCount();
        // 31402 to low
        // 31410 to low
        // 31413 wrong
        // 31414 to high

        assertEquals("countAllWaterSquares", expectedAllWaterSquares, allWaterSquares);
    }


}
