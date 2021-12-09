package de.adventofcode.chrisgw.day04;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;


public class BingoBoard {

    public static final int BOARD_SIZE = 5;

    private final int[][] numbers = new int[BOARD_SIZE][BOARD_SIZE];
    private final Set<Integer> markedNumbers = new LinkedHashSet<>();


    public static BingoBoard parseBingoBoard(List<String> bingoBoardLines) {
        BingoBoard bingoBoard = new BingoBoard();
        for (int row = 0; row < BOARD_SIZE; row++) {
            String bingoBoardLine = bingoBoardLines.get(row);
            try (Scanner sc = new Scanner(bingoBoardLine)) {
                for (int column = 0; column < BOARD_SIZE; column++) {
                    bingoBoard.numbers[row][column] = sc.nextInt();
                }
            }
        }
        return bingoBoard;
    }


    public boolean isWinner() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            boolean isWinningRow = numbersInRow(i).allMatch(this::isMarkedNumber);
            if (isWinningRow || numbersInColumn(i).allMatch(this::isMarkedNumber)) {
                return true;
            }
        }
        return false;
    }


    public int unmarkedNumberSum() {
        return Arrays.stream(numbers)
                .flatMapToInt(Arrays::stream)
                .filter(((IntPredicate) this::isMarkedNumber).negate())
                .sum();
    }


    public int numberAt(int row, int column) {
        return numbers[row][column];
    }

    public IntStream numbersInRow(int row) {
        return IntStream.range(0, BOARD_SIZE).map(column -> numberAt(row, column));
    }

    public IntStream numbersInColumn(int column) {
        return IntStream.range(0, BOARD_SIZE).map(row -> numberAt(row, column));
    }


    public boolean isMarkedNumber(int number) {
        return markedNumbers.contains(number);
    }

    public boolean addDrawnNumber(int number) {
        return markedNumbers.add(number);
    }


    public Set<Integer> getMarkedNumbers() {
        return Collections.unmodifiableSet(markedNumbers);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.join(markedNumbers, ",")).append("\n\n");
        for (int row = 0; row < numbers.length; row++) {
            int[] numberRow = numbers[row];
            for (int column = 0; column < numberRow.length; column++) {
                int number = numberAt(row, column);
                if (isMarkedNumber(number)) {
                    sb.append(String.format("(%2d) ", number));
                } else {
                    sb.append(String.format(" %2d  ", number));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}