package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


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
        StringBuilder gammaRateBits = new StringBuilder();
        StringBuilder epsilonRateBits = new StringBuilder();
        for (int column = 0; column < diagnosticReport[0].length; column++) {
            if (mostCommonBitInColumn(column)) {
                gammaRateBits.append('1');
                epsilonRateBits.append('0');
            } else {
                gammaRateBits.append('0');
                epsilonRateBits.append('1');
            }
        }
        int gammaRate = Integer.parseInt(gammaRateBits.toString(), 2);
        int epsilonRate = Integer.parseInt(epsilonRateBits.toString(), 2);
        return gammaRate * epsilonRate;
    }


    public Integer solveSecondPart() {
        return null;
    }


    private boolean mostCommonBitInColumn(int column) {
        int trueCount = 0;
        int falseCount = 0;
        for (int row = 0; row < diagnosticReport.length; row++) {
            boolean bit = diagnosticReport[row][column];
            if (bit) {
                trueCount++;
            } else {
                falseCount++;
            }
        }
        return falseCount < trueCount;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        for (int row = 0; row < diagnosticReport.length; row++) {
            boolean[] binaryNumber = diagnosticReport[row];
            for (int column = 0; column < binaryNumber.length; column++) {
                boolean bit = binaryNumber[column];
                sb.append(bit);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
