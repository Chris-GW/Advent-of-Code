package de.adventofcode.chrisgw.day04;

import java.util.stream.IntStream;


/**
 * https://adventofcode.com/2019/day/4
 */
public class AdventOfCodeDay04 {


    public static long countPossiblePasswordsPart01(int rangeFrom, int rangeTo) {
        return IntStream.rangeClosed(rangeFrom, rangeTo).filter(AdventOfCodeDay04::isValidPassword).count();
    }

    public static long countPossiblePasswordsPart02(int rangeFrom, int rangeTo) {
        return IntStream.rangeClosed(rangeFrom, rangeTo).filter(AdventOfCodeDay04::isValidPasswordPart02).count();
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


    public static boolean isValidPasswordPart02(int password) {
        String passwordStr = String.valueOf(password);
        boolean hasTwoSameAdjacentDigits = false;
        int sameDigitCount = 0;
        int lastDigit = -1;
        for (int i = 0; i < passwordStr.length(); i++) {
            int currentDigit = passwordStr.charAt(i) - '0';
            if (lastDigit > currentDigit) {
                return false;
            } else if (lastDigit == currentDigit) {
                sameDigitCount++;
            } else { // lastDigit < currentDigit
                if (sameDigitCount == 1) {
                    hasTwoSameAdjacentDigits = true;
                }
                sameDigitCount = 0;
            }
            lastDigit = currentDigit;
        }
        return hasTwoSameAdjacentDigits || sameDigitCount == 1;
    }

}
