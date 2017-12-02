package de.adventofcode.chrisgw.day02;

import java.util.ArrayList;
import java.util.List;


/**
 * --- <a href="http://adventofcode.com/2017/day/2">Day 2: Corruption Checksum</a> ---
 * <pre>
 * The spreadsheet consists of rows of apparently-random numbers. To make sure
 * the recovery process is on the right track, they need you to calculate the
 * spreadsheet's checksum. For each row, determine the difference between the
 * largest value and the smallest value; the checksum is the sum of all of
 * these differences.
 *
 * For example, given the following spreadsheet:
 *
 * 5 1 9 5
 * 7 5 3
 * 2 4 6 8
 *
 * - The first row's largest and smallest values are 9 and 1, and their difference is 8.
 * - The second row's largest and smallest values are 7 and 3, and their difference is 4.
 * - The third row's difference is 6.
 * In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
 * </pre>
 */
public class CorruptionChecksum {

    private List<List<Integer>> spreadsheet;


    private CorruptionChecksum(List<List<Integer>> spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public static CorruptionChecksum parseSpreadsheet(String spreadsheetStr) {
        String[] splittedRows = spreadsheetStr.split("\n");
        List<List<Integer>> spreadsheet = new ArrayList<>(splittedRows.length);

        for (int rowIndex = 0; rowIndex < splittedRows.length; rowIndex++) {
            String row = splittedRows[rowIndex];
            String[] splittedColumns = row.split("\\s+");
            List<Integer> columns = new ArrayList<>(splittedColumns.length);
            spreadsheet.add(rowIndex, columns);

            for (int columnIndex = 0; columnIndex < splittedColumns.length; columnIndex++) {
                String fieldValue = splittedColumns[columnIndex];
                columns.add(columnIndex, Integer.valueOf(fieldValue));
            }
        }
        return new CorruptionChecksum(spreadsheet);
    }


    public int calculateChecksum() {
        int checksum = 0;
        for (int rowIndex = 0; rowIndex < spreadsheet.size(); rowIndex++) {
            List<Integer> row = spreadsheet.get(rowIndex);
            int rowChecksum = calculateRowChecksum(row);
            checksum += rowChecksum;
        }
        return checksum;
    }

    private int calculateRowChecksum(List<Integer> row) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
            int fieldValue = row.get(columnIndex);
            if (fieldValue < minValue) {
                minValue = fieldValue;
            }
            if (fieldValue > maxValue) {
                maxValue = fieldValue;
            }
        }
        int rowChecksum = maxValue - minValue;
        return rowChecksum;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int rowIndex = 0; rowIndex < spreadsheet.size(); rowIndex++) {
            List<Integer> rows = spreadsheet.get(rowIndex);
            for (int columnIndex = 0; columnIndex < rows.size(); columnIndex++) {
                Integer fieldValue = rows.get(columnIndex);
                sb.append(String.format("%6d ", fieldValue));
            }
            sb.setCharAt(sb.length() - 1, '\n');
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Empty args[] without any spreadsheet's to check");
            return;
        }

        for (String spreadsheet : args) {
            try {
                CorruptionChecksum corruptionChecksum = CorruptionChecksum.parseSpreadsheet(spreadsheet);
                int calculateChecksum = corruptionChecksum.calculateChecksum();
                System.out.println(spreadsheet);
                System.out.println("checksum: " + calculateChecksum);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
