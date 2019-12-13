package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay13Test {


    @Test
    public void myPuzzleInput_part01() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay13.txt");
        IntCodeProgram arcadeCabinetProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgrammStr);
        ArcadeCabinet arcadeCabinet = new ArcadeCabinet(arcadeCabinetProgram);
        long expectedBlockTileCount = 296;

        long blockTileCount = AdventOfCodeDay13.countBlockTilesOnScreen(arcadeCabinet);
        assertEquals("blockTileCount", expectedBlockTileCount, blockTileCount);
    }

}
