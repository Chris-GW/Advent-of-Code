package de.adventofcode.chrisgw.day01;

import java.math.BigInteger;


/**
 * <h1><a href="https://adventofcode.com/2017/day/1">https://adventofcode.com/2017/day/1</a></h1>
 * The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list. The list is circular, so the digit after the last digit is the first digit in the list.
 * <p>
 * For example:
 * <ul>
 * <li>1122 produces a sum of 3 (1 + 2) because the first digit (1) matches
 * the second digit and the third digit (2) matches the fourth digit.</li>
 * <li>1111 produces 4 because each digit (all 1) matches the next.</li>
 * <li>1234 produces 0 because no digit matches the next.</li>
 * <li>91212129 produces 9 because the only digit that matches the next one is the last digit, 9.</li>
 * </ul>
 */
public class InverseCaptcha {


    /**
     * The captcha requires you to review a sequence of digits (your puzzle input)
     * and find the sum of all digits that match the next digit in the list.
     *
     * @param captchaInput positive number
     * @return captcha solution sum
     * @see #solveInverseCaptcha(String)
     */
    public static long solveInverseCaptcha(BigInteger captchaInput) {
        if (captchaInput.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Expect positive number, but was " + captchaInput);
        }
        return solveInverseCaptcha(captchaInput.toString());
    }

    /**
     * The captcha requires you to review a sequence of digits (your puzzle input)
     * and find the sum of all digits that match the next digit in the list.
     *
     * @param captchaInput positive number
     * @return captcha solution sum
     * @see #solveInverseCaptcha(BigInteger)
     */
    public static long solveInverseCaptcha(String captchaInput) {
        long sum = 0;

        for (int i = 0; i < captchaInput.length(); i++) {
            int currentDigit = getDigitOfNumber(captchaInput, i);
            int nextDigit = getDigitOfNumber(captchaInput, (i + 1));

            if (currentDigit == nextDigit) {
                sum += currentDigit;
            }
        }
        return sum;
    }


    /**
     * @param numberStr positive circular number as String
     * @param n         digit index from left (0-based)
     * @return digit at given index n
     */
    private static int getDigitOfNumber(String numberStr, int n) {
        if (n >= numberStr.length()) {
            n = 0; // start from number beginning again (circular number)
        }
        int numericValue = Character.getNumericValue(numberStr.charAt(n));
        if (numericValue < 0 || numericValue > 9) {
            throw new NumberFormatException(String.format("'%s' has no digit at index %d", numberStr, n));
        }
        return numericValue;
    }


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Empty args[] without any captcha's");
            return;
        }

        for (String captchaInput : args) {
            try {
                long captchaSolution = solveInverseCaptcha(captchaInput);
                System.out.printf("%6d = '%s'\n", captchaSolution, captchaInput);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
