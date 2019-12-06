package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay06Test {


    @Test
    public void example01_part01_countTotalOrbits() {
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
    public void myPuzzleInput_part01_countTotalOrbits() {
        List<String> orbitMapLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        long expectedTotalOrbits = 358244;

        AdventOfCodeDay06 aocDay06 = AdventOfCodeDay06.parseUniversalOrbitMap(orbitMapLines);
        long totalOrbits = aocDay06.countTotalOrbits();
        assertEquals("totalOrbits", expectedTotalOrbits, totalOrbits);
    }


    // part 02


    @Test
    public void example01_part02_countNeededOrbitalTransfers() {
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
                "K)L", //
                "K)YOU", //
                "I)SAN");
        int expectedNeededOrbitalTransfers = 4;

        AdventOfCodeDay06 aocDay06 = AdventOfCodeDay06.parseUniversalOrbitMap(orbitMapLines);
        int neededOrbitalTransfers = aocDay06.countNeededOrbitalTransfers();
        assertEquals("neededOrbitalTransfers", expectedNeededOrbitalTransfers, neededOrbitalTransfers);
    }


    @Test
    public void myPuzzleInput_part02() {
        List<String> orbitMapLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        int expectedNeededOrbitalTransfers = 517;

        AdventOfCodeDay06 aocDay06 = AdventOfCodeDay06.parseUniversalOrbitMap(orbitMapLines);
        int neededOrbitalTransfers = aocDay06.countNeededOrbitalTransfers();
        assertEquals("neededOrbitalTransfers", expectedNeededOrbitalTransfers, neededOrbitalTransfers);
    }

}
