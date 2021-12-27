package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;

/**
 * https://adventofcode.com/2021/day/8
 */
public class AdventOfCodeDay08 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay08(List<String> inputLines) {
        super(Year.of(2021), 8, inputLines);
    }

    public Integer solveFirstPart() {
        List<SignalEntry> signalEntries = inputAsSignalEntries();
        List<SignalPattern> easyOutputDigits = signalEntries.stream().flatMap(SignalEntry::easyOutputDigits).toList();
        return easyOutputDigits.size();
    }

    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }


    private List<SignalEntry> inputAsSignalEntries() {
        return inputLines().map(SignalEntry::parseSignalEntry).toList();
    }

}
