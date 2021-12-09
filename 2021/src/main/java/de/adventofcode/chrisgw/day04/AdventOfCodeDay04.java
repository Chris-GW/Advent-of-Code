package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PrimitiveIterator.OfInt;
import java.util.stream.IntStream;


/**
 * https://adventofcode.com/2021/day/4
 */
public class AdventOfCodeDay04 extends AdventOfCodePuzzleSolver<Integer> {


    public AdventOfCodeDay04(List<String> inputLines) {
        super(Year.of(2021), 4, inputLines);
    }


    public Integer solveFirstPart() {
        OfInt drawnNumbersFromInput = drawnNumbersFromInput().iterator();
        List<BingoBoard> bingoBoards = parseBingoBoardsFromInput();

        while (drawnNumbersFromInput.hasNext()) {
            int nextNumber = drawnNumbersFromInput.nextInt();
            bingoBoards.forEach(bingoBoard -> bingoBoard.addDrawnNumber(nextNumber));
            var winningBoard = bingoBoards.stream().filter(BingoBoard::isWinner).findAny();
            if (winningBoard.isPresent()) {
                return winningBoard.get().unmarkedNumberSum() * nextNumber;
            }
        }
        return 0;
    }

    private List<BingoBoard> parseBingoBoardsFromInput() {
        List<BingoBoard> bingoBoards = new ArrayList<>();
        for (int i = 2; i < getInputLines().size(); i += BingoBoard.BOARD_SIZE + 1) {
            List<String> bingoBoardLines = getInputLines().subList(i, i + BingoBoard.BOARD_SIZE);
            var bingoBoard = BingoBoard.parseBingoBoard(bingoBoardLines);
            bingoBoards.add(bingoBoard);
        }
        return bingoBoards;
    }

    private IntStream drawnNumbersFromInput() {
        String drawnNumberLine = getInputLines().get(0);
        String[] splitNumbers = drawnNumberLine.split(",");
        return Arrays.stream(splitNumbers).mapToInt(Integer::parseInt);
    }


    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }


}
