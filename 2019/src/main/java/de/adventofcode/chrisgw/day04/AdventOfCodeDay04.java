package de.adventofcode.chrisgw.day04;

import java.util.stream.IntStream;


/**
 * https://adventofcode.com/2019/day/4
 */
public class AdventOfCodeDay04 {


    public static long countPossiblePasswords(int rangeFrom, int rangeTo) {
        return IntStream.rangeClosed(rangeFrom, rangeTo).filter(AdventOfCodeDay04::isValidPassword).count();
    }

    private static boolean isValidPassword(int password) {
        String passwordStr = String.valueOf(password);
        boolean hasTwoSameAdjacentDigits = false;
        int lastDigit = -1;
        for (int i = 0; i < passwordStr.length(); i++) {
            int currentDigit = passwordStr.charAt(i) - '0';
            if (lastDigit == currentDigit) {
                hasTwoSameAdjacentDigits = true;
            } else if (lastDigit > currentDigit) {
                return false;
            }
            lastDigit = currentDigit;
        }
        return hasTwoSameAdjacentDigits;
    }

}
