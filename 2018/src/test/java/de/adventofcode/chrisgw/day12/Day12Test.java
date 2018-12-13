package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Day12Test {


    @Test
    public void part01() throws Exception {
        Path myPuzzleInputFile = TestUtils.getResourcePath("/day12/myPuzzleInput.txt");
        List<String> descriptionLines = Files.readAllLines(myPuzzleInputFile);

        Day12 day12 = new Day12(descriptionLines);
        day12.parse();

        Integer integer = day12.part1();
        System.out.println(integer);
    }

}