package de.adventofcode.chrisgw.day01;

/**
 * <h1><a href="https://adventofcode.com/2017/day/1">https://adventofcode.com/2017/day/1</a></h1>
 * <p>
 * The captcha requires you to review a sequence of digits (your puzzle input)
 * and find the <em>sum</em> of all digits that match the <em>next</em> digit in the list. The
 * list is circular, so the digit after the last digit is the <em>first</em> digit in the list.
 * <p>
 * For example:
 * <ul>
 * <li>1122 produces a sum of 3 (1 + 2) because the first digit (1) matches
 * the second digit and the third digit (2) matches the fourth digit.</li>
 * <li>1111 produces 4 because each digit (all 1) matches the next.</li>
 * <li>1234 produces 0 because no digit matches the next.</li>
 * <li>91212129 produces 9 because the only digit that matches the next one is the last digit, 9.</li>
 * </ul>
 * <p>
 * --- Part Two ---
 * <p>
 * You notice a progress bar that jumps to 50% completion.
 * Apparently, the door isn't yet satisfied, but it did emit a star as encouragement. The instructions change:
 * <p>
 * Now, instead of considering the <em>next</em> digit, it wants you to consider the
 * digit <em>halfway around</em> the circular list. That is, if your list contains <code>10</code>
 * items, only include a digit in your sum if the digit <code>10/2 = 5</code> steps forward
 * matches it. Fortunately, your list has an even number of elements.
 * <p>
 * For example:
 * <li>1212 produces 6: the list contains 4 items, and all four digits match the digit 2 items ahead.</li>
 * <li>1221 produces 0, because every comparison is between a 1 and a 2.</li>
 * <li>123425 produces 4, because both 2s match each other, but no other digit has a match.</li>
 * <li>123123 produces 12.</li>
 * <li>12131415 produces 4.</li>
 * </ul>
 */
public class InverseCaptcha {

    /**
     * The captcha requires you to review a sequence of digits (your puzzle input)
     * and find the <em>sum</em> of all digits that match the <em>next</em> digit in the list. The
     * list is circular, so the digit after the last digit is the <em>first</em> digit in the list.
     *
     * @param captchaInput positive number
     * @return captcha solution sum
     * @see #solveInverseCaptcha(String, int)
     */
    public static long solveInverseCaptchaPart1(String captchaInput) {
        int nextDigitOffset = 1;
        return solveInverseCaptcha(captchaInput, nextDigitOffset);
    }

    /**
     * Now, instead of considering the <em>next</em> digit, it wants you to consider the
     * digit <em>halfway around</em> the circular list. That is, if your list contains <code>10</code>
     * items, only include a digit in your sum if the digit <code>10/2 = 5</code> steps forward
     * matches it. Fortunately, your list has an even number of elements.
     *
     * @param captchaInput positive number even number of elements
     * @return captcha solution sum
     * @see #solveInverseCaptcha(String, int)
     */
    public static long solveInverseCaptchaPart2(String captchaInput) {
        int halfWayDigitOffset = captchaInput.length() / 2;
        return solveInverseCaptcha(captchaInput, halfWayDigitOffset);
    }

    /**
     * @param captchaInput    positive number
     * @param nextDigitOffset offset of next digit to compare
     * @return captcha solution sum
     */
    private static long solveInverseCaptcha(String captchaInput, int nextDigitOffset) {
        long sum = 0;

        for (int i = 0; i < captchaInput.length(); i++) {
            int currentDigit = getDigitOfNumber(captchaInput, i);
            int nextDigit = getDigitOfNumber(captchaInput, (i + nextDigitOffset));

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
        int numberLength = numberStr.length();
        if (n >= numberLength) {
            n = n % numberLength; // start from number beginning again (circular number list)
        }
        int numericValue = numberStr.charAt(n) - '0';
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
                System.out.printf("captcha input: '%s'\n", captchaInput);

                long startTime = System.nanoTime();
                long captchaSolution1 = solveInverseCaptchaPart1(captchaInput);
                long duration = System.nanoTime() - startTime;
                System.out.printf("next digit captcha:    %9d\t(after %9d ns)\n", captchaSolution1, duration);

                startTime = System.nanoTime();
                long captchaSolution2 = solveInverseCaptchaPart2(captchaInput);
                duration = System.nanoTime() - startTime;
                System.out.printf("halfway digit captcha: %9d\t(after %9d ns)\n", captchaSolution2, duration);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
