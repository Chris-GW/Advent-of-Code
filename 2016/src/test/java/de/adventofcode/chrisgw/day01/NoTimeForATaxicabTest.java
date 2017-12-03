package test.java.de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.day01.NoTimeForATaxicab.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;


public class NoTimeForATaxicabTest {

    @Test
    public void follow_R2_L3_expect_5_blocks() throws Exception {
        // Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
        String path = "R2, L3";
        int expectedBlocksAway = 5;

        assertTaxicabBlocksAway(path, expectedBlocksAway);
    }

    @Test
    public void follow_R2_R2_R2_expect_2_blocks() throws Exception {
        // R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
        String path = "R2, R2, R2";
        int expectedBlocksAway = 2;

        assertTaxicabBlocksAway(path, expectedBlocksAway);
    }

    @Test
    public void follow_R5_L5_R5_R3_expect_12_blocks() throws Exception {
        // R5, L5, R5, R3 leaves you 12 blocks away.
        String path = "R5, L5, R5, R3";
        int expectedBlocksAway = 12;

        assertTaxicabBlocksAway(path, expectedBlocksAway);
    }


    @Test
    public void follow_myTask_expect_success() throws Exception {
        // R5, L5, R5, R3 leaves you 12 blocks away.
        String path = "R2, L3, R2, R4, L2, L1, R2, R4, R1, L4, L5, R5, R5, R2, R2, R1, L2, L3, L2, L1, R3, L5, R187, R1, R4, L1, R5, L3, L4, R50, L4, R2, R70, L3, L2, R4, R3, R194, L3, L4, L4, L3, L4, R4, R5, L1, L5, L4, R1, L2, R4, L5, L3, R4, L5, L5, R5, R3, R5, L2, L4, R4, L1, R3, R1, L1, L2, R2, R2, L3, R3, R2, R5, R2, R5, L3, R2, L5, R1, R2, R2, L4, L5, L1, L4, R4, R3, R1, R2, L1, L2, R4, R5, L2, R3, L4, L5, L5, L4, R4, L2, R1, R1, L2, L3, L2, R2, L4, R3, R2, L1, L3, L2, L4, L4, R2, L3, L3, R2, L4, L3, R4, R3, L2, L1, L4, R4, R2, L4, L4, L5, L1, R2, L5, L2, L3, R2, L2";
        int expectedBlocksAway = 246;

        assertTaxicabBlocksAway(path, expectedBlocksAway);
    }


    @Test
    public void follow_R8_R4_R4_R8_expect_4_blocks_twice() throws Exception {
        // For example, if your instructions are R8, R4, R4, R8, the first location
        // you visit twice is 4 blocks away, due East.
        String path = "R8, R4, R4, R8";
        int expectedBlocksAway = 4;

        NoTimeForATaxicab taxicab = new NoTimeForATaxicab();
        taxicab.followInsturctions(path, ", ");
        Set<Position> twiceVisitedPositions = taxicab.getTwiceVisitedPositions();
        System.out.println(twiceVisitedPositions);
        int blocksAway = twiceVisitedPositions.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Expect twice visited position"))
                .getBlocksAway();
        Assert.assertEquals("Expect taxicab " + expectedBlocksAway + " away after following path: " + path,
                expectedBlocksAway, blocksAway);
    }


    @Test
    public void follow_myTask_expect_twice_success() throws Exception {
        // For example, if your instructions are R8, R4, R4, R8, the first location
        // you visit twice is 4 blocks away, due East.
        String path = "R2, L3, R2, R4, L2, L1, R2, R4, R1, L4, L5, R5, R5, R2, R2, R1, L2, L3, L2, L1, R3, L5, R187, R1, R4, L1, R5, L3, L4, R50, L4, R2, R70, L3, L2, R4, R3, R194, L3, L4, L4, L3, L4, R4, R5, L1, L5, L4, R1, L2, R4, L5, L3, R4, L5, L5, R5, R3, R5, L2, L4, R4, L1, R3, R1, L1, L2, R2, R2, L3, R3, R2, R5, R2, R5, L3, R2, L5, R1, R2, R2, L4, L5, L1, L4, R4, R3, R1, R2, L1, L2, R4, R5, L2, R3, L4, L5, L5, L4, R4, L2, R1, R1, L2, L3, L2, R2, L4, R3, R2, L1, L3, L2, L4, L4, R2, L3, L3, R2, L4, L3, R4, R3, L2, L1, L4, R4, R2, L4, L4, L5, L1, R2, L5, L2, L3, R2, L2";
        int expectedBlocksAway = 124;

        NoTimeForATaxicab taxicab = new NoTimeForATaxicab();
        taxicab.followInsturctions(path, ", ");
        Set<Position> twiceVisitedPositions = taxicab.getTwiceVisitedPositions();
        System.out.println(twiceVisitedPositions);
        int blocksAway = twiceVisitedPositions.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Expect twice visited position"))
                .getBlocksAway();
        Assert.assertEquals("Expect taxicab " + expectedBlocksAway + " away after following path: " + path,
                expectedBlocksAway, blocksAway);
    }

    private void assertTaxicabBlocksAway(String path, int expectedBlocksAway) {
        NoTimeForATaxicab taxicab = new NoTimeForATaxicab();
        taxicab.followInsturctions(path, ", ");
        int blocksAway = taxicab.getBlocksAway();
        Assert.assertEquals("Expect taxicab " + expectedBlocksAway + " away after following path: " + path,
                expectedBlocksAway, blocksAway);
    }


}