package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class SubterraneanSustainabilityTest {


    @Test
    public void countPlantPotWithPlants_example01() {
        List<String> descriptionLines = Arrays.asList( //
                "initial state: #..#.#..##......###...###", //
                "", //
                "...## => #", //
                "..#.. => #", //
                ".#... => #", //
                ".#.#. => #", //
                ".#.## => #", //
                ".##.. => #", //
                ".#### => #", //
                "#.#.# => #", //
                "#.### => #", //
                "##.#. => #", //
                "##.## => #", //
                "###.. => #", //
                "###.# => #", //
                "####. => #");

        List<String> expectedPlantPotField = Arrays.asList( //
                "  0: ...#..#.#..##......###...###...........", //
                "  1: ...#...#....#.....#..#..#..#...........", //
                "  2: ...##..##...##....#..#..#..##..........", //
                "  3: ..#.#...#..#.#....#..#..#...#..........", //
                "  4: ...#.#..#...#.#...#..#..##..##.........", //
                "  5: ....#...##...#.#..#..#...#...#.........", //
                "  6: ....##.#.#....#...#..##..##..##........", //
                "  7: ...#..###.#...##..#...#...#...#........", //
                "  8: ...#....##.#.#.#..##..##..##..##.......", //
                "  9: ...##..#..#####....#...#...#...#.......", //
                " 10: ..#.#..#...#.##....##..##..##..##......", //
                " 11: ...#...##...#.#...#.#...#...#...#......", //
                " 12: ...##.#.#....#.#...#.#..##..##..##.....", //
                " 13: ..#..###.#....#.#...#....#...#...#.....", //
                " 14: ..#....##.#....#.#..##...##..##..##....", //
                " 15: ..##..#..#.#....#....#..#.#...#...#....", //
                " 16: .#.#..#...#.#...##...#...#.#..##..##...", //
                " 17: ..#...##...#.#.#.#...##...#....#...#...", //
                " 18: ..##.#.#....#####.#.#.#...##...##..##..", //
                " 19: .#..###.#..#.#.#######.#.#.#..#.#...#..", //
                " 20: .#....##....#####...#######....#.#..##.");

        int generation = 20;
        long expectedTotalPotWithPlantes = 325;

        SubterraneanSustainability plantToField = SubterraneanSustainability.parseSubterraneanSustainability(
                descriptionLines);
        for (int gen = 0; gen < generation; gen++) {
            System.out.println(plantToField);
            assertEquals("plantPotField " + gen, expectedPlantPotField.get(gen), plantToField.toString());
            plantToField = plantToField.nextGeneration();
        }
        System.out.println(plantToField);
        long totalPotWithPlantes = plantToField.getTotalPotWithPlantesSum();
        assertEquals("totalPotWithPlantes", expectedTotalPotWithPlantes, totalPotWithPlantes);
    }


    @Test
    public void countPlantPotWithPlants_myPuzzleInput() throws Exception {
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day12/myPuzzleInput.txt");
        List<String> descriptionLines = Files.readAllLines(myPuzzleInputFile);

        int generation = 20;
        long expectedTotalPotWithPlantes = 1511;

        SubterraneanSustainability plantToField = SubterraneanSustainability.parseSubterraneanSustainability(
                descriptionLines);
        for (int gen = 0; gen < generation; gen++) {
            System.out.println(plantToField);
            plantToField = plantToField.nextGeneration();
        }
        System.out.println(plantToField);
        long totalPotWithPlantes = plantToField.getTotalPotWithPlantesSum();
        assertEquals("totalPotWithPlantes", expectedTotalPotWithPlantes, totalPotWithPlantes);
    }

}