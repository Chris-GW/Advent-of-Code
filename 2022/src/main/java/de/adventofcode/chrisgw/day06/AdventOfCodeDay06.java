package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2022/day/6">Advent of Code - day 6</a>
 */
public class AdventOfCodeDay06 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay06(List<String> inputLines) {
        super(Year.of(2022), 6, inputLines);
    }


    public Integer solveFirstPart() {
        DataStreamBuffer dataStreamBuffer = new DataStreamBuffer(getInputLines().get(0));
        return dataStreamBuffer.findStartOfPacketMarker();
    }

    public Integer solveSecondPart() {
        DataStreamBuffer dataStreamBuffer = new DataStreamBuffer(getInputLines().get(0));
        return dataStreamBuffer.findStartOfMessageMarker();
    }

}
