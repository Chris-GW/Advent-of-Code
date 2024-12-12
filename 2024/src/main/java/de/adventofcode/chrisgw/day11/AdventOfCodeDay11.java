package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


/**
 * <a href="https://adventofcode.com/2024/day/11">Advent of Code - day 11</a>
 */
public class AdventOfCodeDay11 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay11(List<String> inputLines) {
        super(Year.of(2024), 11, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<Long> stones = parseStoneLine();
        for (int blink = 0; blink < 25; blink++) {
            nextBlink(stones);
        }
        return stones.size();
    }

    private List<Long> parseStoneLine() {
        String stoneLine = getInputLines().get(0);
        List<Long> stones = new LinkedList<>();
        for (String stoneNumberString : stoneLine.split(" ")) {
            long stoneNumber = Long.parseLong(stoneNumberString);
            stones.add(stoneNumber);
        }
        return stones;
    }

    private static void nextBlink(List<Long> stones) {
        ListIterator<Long> stoneIterator = stones.listIterator();
        while (stoneIterator.hasNext()) {
            long stoneNumber = stoneIterator.next();
            if (stoneNumber == 0) {
                stoneIterator.set(1L);
                continue;
            }

            String numberDigits = String.valueOf(stoneNumber);
            boolean evenNumberDigits = (numberDigits.length() % 2) == 0;
            if (evenNumberDigits) {
                int halfIndex = numberDigits.length() / 2;
                long leftHalf = Long.parseLong(numberDigits.substring(0, halfIndex));
                long rightHalf = Long.parseLong(numberDigits.substring(halfIndex));
                stoneIterator.set(leftHalf);
                stoneIterator.add(rightHalf);
            } else {
                stoneIterator.set(stoneNumber * 2024);
            }
        }
    }


    @Override
    public Integer solveSecondPart() {
        List<Long> stones = parseStoneLine();
        for (int blink = 0; blink < 75; blink++) {
            nextBlink(stones);
            System.out.println(blink);
        }
        return stones.size();
    }


}
