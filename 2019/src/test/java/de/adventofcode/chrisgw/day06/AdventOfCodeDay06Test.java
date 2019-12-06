package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay06Test {


    @Test
    public void example01() {
        List<String> orbitMapLines = List.of( //
                "COM)B", //
                "B)C", //
                "C)D", //
                "D)E", //
                "E)F", //
                "B)G", //
                "G)H", //
                "D)I", //
                "E)J", //
                "J)K", //
                "K)L");
        long expectedTotalOrbits = 42;

        AdventOfCodeDay06 aocDay06 = AdventOfCodeDay06.parseUniversalOrbitMap(orbitMapLines);
        long totalOrbits = aocDay06.countTotalOrbits();
        assertEquals("totalOrbits", expectedTotalOrbits, totalOrbits);
    }


    @Test
    public void myPuzzleInput_part01() {
        List<String> orbitMapLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        long expectedTotalOrbits = 358244;

        AdventOfCodeDay06 aocDay06 = AdventOfCodeDay06.parseUniversalOrbitMap(orbitMapLines);
        long totalOrbits = aocDay06.countTotalOrbits();
        assertEquals("totalOrbits", expectedTotalOrbits, totalOrbits);
    }

}
