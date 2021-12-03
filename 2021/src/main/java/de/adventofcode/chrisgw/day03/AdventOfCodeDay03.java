package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


/**
 * https://adventofcode.com/2021/day/3
 */
public class AdventOfCodeDay03 extends AdventOfCodePuzzleSolver<Integer> {

    public boolean[][] diagnosticReport;


    public AdventOfCodeDay03(List<String> inputLines) {
        super(Year.of(2021), 3, inputLines);
        diagnosticReport = parseDiagnosticReport(inputLines);
    }

    private boolean[][] parseDiagnosticReport(List<String> inputLines) {
        boolean[][] diagnosticReport = new boolean[inputLines.size()][];
        for (int row = 0; row < inputLines.size(); row++) {
            String inputLine = inputLines.get(row);
            diagnosticReport[row] = new boolean[inputLine.length()];
            for (int column = 0; column < inputLine.length(); column++) {
                char binaryValue = inputLine.charAt(column);
                if (binaryValue == '1') {
                    diagnosticReport[row][column] = true;
                } else if (binaryValue == '0') {
                    diagnosticReport[row][column] = false;
                } else {
                    throw new IllegalArgumentException("expect binary 0 | 1, but was: " + binaryValue);
                }
            }
        }
        return diagnosticReport;
    }


    public Integer solveFirstPart() {
        boolean[] gammaRateBits = new boolean[diagnosticReport[0].length];
        boolean[] epsilonRateBits = new boolean[diagnosticReport[0].length];
        List<boolean[]> allNumbers = Arrays.stream(diagnosticReport).toList();
        for (int column = 0; column < diagnosticReport[0].length; column++) {
            boolean mostCommonBit = mostCommonBitInColumn(column, allNumbers);
            gammaRateBits[column] = mostCommonBit;
            epsilonRateBits[column] = !mostCommonBit;
        }

        int gammaRate = bitsAsNumber(gammaRateBits);
        int epsilonRate = bitsAsNumber(epsilonRateBits);
        return gammaRate * epsilonRate;
    }

    private boolean mostCommonBitInColumn(int column, List<boolean[]> numbers) {
        int trueCount = 0;
        int falseCount = 0;
        for (int row = 0; row < numbers.size(); row++) {
            boolean[] binaryNumber = numbers.get(row);
            if (binaryNumber[column]) {
                trueCount++;
            } else {
                falseCount++;
            }
        }
        return falseCount <= trueCount;
    }


    public Integer solveSecondPart() {
        List<boolean[]> oxygenGeneratorRatings = new ArrayList<>(Arrays.stream(diagnosticReport).toList());
        List<boolean[]> c02ScrubberRatings = new ArrayList<>(Arrays.stream(diagnosticReport).toList());

        for (int column = 0; column < diagnosticReport[0].length; column++) {
            if (oxygenGeneratorRatings.size() > 1) {
                boolean mostCommonBit = mostCommonBitInColumn(column, oxygenGeneratorRatings);
                oxygenGeneratorRatings.removeIf(hasBitInColumn(column, !mostCommonBit));
            }
            if (c02ScrubberRatings.size() > 1) {
                boolean mostCommonBit = mostCommonBitInColumn(column, c02ScrubberRatings);
                c02ScrubberRatings.removeIf(hasBitInColumn(column, mostCommonBit));
            }
        }

        int oxygenGeneratorRating = bitsAsNumber(oxygenGeneratorRatings.get(0));
        int c02ScrubberRating = bitsAsNumber(c02ScrubberRatings.get(0));
        return oxygenGeneratorRating * c02ScrubberRating;
    }

    public Predicate<boolean[]> hasBitInColumn(int column, boolean bit) {
        return binaryNumber -> binaryNumber[column] == bit;
    }


    private int bitsAsNumber(boolean[] binaryNumber) {
        StringBuilder binaryNumberString = new StringBuilder();
        for (int column = 0; column < binaryNumber.length; column++) {
            if (binaryNumber[column]) {
                binaryNumberString.append('1');
            } else {
                binaryNumberString.append('0');
            }
        }
        return Integer.parseInt(binaryNumberString.toString(), 2);
    }


    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        for (int row = 0; row < diagnosticReport.length; row++) {
            boolean[] binaryNumber = diagnosticReport[row];
            for (int column = 0; column < binaryNumber.length; column++) {
                boolean bit = binaryNumber[column];
                if (bit) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
