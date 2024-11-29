package de.adventofcode.chrisgw.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay10Test {

    @Test
    void solveAocPuzzle_firstPart_simpleExample() {
        List<String> inputLines = List.of(
                ".....",
                ".S-7.",
                ".|.|.",
                ".L-J.",
                ".....");
        int result = new AdventOfCodeDay10(inputLines).solveFirstPart();
        assertEquals(4, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_example() {
        List<String> inputLines = List.of(
                "..F7.",
                ".FJ|.",
                "SJ.L7",
                "|F--J",
                "LJ...");
        int result = new AdventOfCodeDay10(inputLines).solveFirstPart();
        assertEquals(8, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveFirstPart();
        assertEquals(6806, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example1() {
        List<String> inputLines = List.of(
                "...........",
                ".S-------7.",
                ".|F-----7|.",
                ".||.....||.",
                ".||.....||.",
                ".|L-7.F-J|.",
                ".|..|.|..|.",
                ".L--J.L--J.",
                "...........");
        int result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals(4, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example2() {
        List<String> inputLines = List.of(
                "..........",
                ".S------7.",
                ".|F----7|.",
                ".||....||.",
                ".||....||.",
                ".|L-7F-J|.",
                ".|..||..|.",
                ".L--JL--J.",
                "..........");
        int result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals(4, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example3() {
        List<String> inputLines = List.of(
                ".F----7F7F7F7F-7....",
                ".|F--7||||||||FJ....",
                ".||.FJ||||||||L7....",
                "FJL7L7LJLJ||LJ.L-7..",
                "L--J.L7...LJS7F-7L7.",
                "....F-J..F7FJ|L7L7L7",
                "....L7.F7||L7|.L7L7|",
                ".....|FJLJ|FJ|F7|.LJ",
                "....FJL-7.||.||||...",
                "....L---J.LJ.LJLJ...");
        int result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals(8, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals(449, result, "secondPart myPuzzleInput");
    }

}
