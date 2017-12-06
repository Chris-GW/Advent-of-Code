package de.adventofcode.chrisgw.day06;


import java.util.*;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <pre>
 * --- Day 6: Signals and Noise ---
 *
 * Something is jamming your communications with Santa. Fortunately, your
 * signal is only partially jammed, and protocol in situations like this is to
 * switch to a simple repetition code to get the message through.
 *
 * In this model, the same message is sent repeatedly. You've recorded the
 * repeating message signal (your puzzle input), but the data seems quite
 * corrupted - almost too badly to recover. Almost.
 *
 * All you need to do is figure out which character is most frequent for each
 * position. For example, suppose you had recorded the following messages:
 *
 * eedadn
 * drvtee
 * eandsr
 * raavrd
 * atevrs
 * tsrnev
 * sdttsa
 * rasrtv
 * nssdts
 * ntnada
 * svetve
 * tesnvt
 * vntsnd
 * vrdear
 * dvrsen
 * enarar
 *
 * The most common character in the first column is e; in the second, a; in
 * the third, s, and so on. Combining these characters returns the
 * error-corrected message, easter.
 *
 * Given the recording in your puzzle input, what is the error-corrected
 * version of the message being sent?
 * </pre>
 */
public class SignalsAndNoise {


    public static String getErrorCorrectedMessage(List<String> messages) {
        List<Map<Character, Integer>> letterAppearancesPerColumn = Stream.generate(
                (Supplier<Map<Character, Integer>>) HashMap::new)
                .limit(messages.size())
                .collect(Collectors.toCollection(ArrayList::new));

        for (String message : messages) {
            for (int letterIndex = 0; letterIndex < message.length(); letterIndex++) {
                Map<Character, Integer> letterAppearences = letterAppearancesPerColumn.get(letterIndex);

                char letter = message.charAt(letterIndex);
                letterAppearences.compute(letter, (l, occurence) -> occurence == null ? 0 : occurence + 1);
            }
        }
        return correctMessagesWithMostAppearanceLetter(letterAppearancesPerColumn);
    }

    private static String correctMessagesWithMostAppearanceLetter(
            List<Map<Character, Integer>> letterAppearancesPerColumn) {
        StringBuilder errorCorrectedMessage = new StringBuilder(letterAppearancesPerColumn.size());
        for (Map<Character, Integer> letterAppearance : letterAppearancesPerColumn) {
            letterAppearance.entrySet()
                    .stream()
                    .max(Entry.comparingByValue())
                    .map(Entry::getKey)
                    .ifPresent(errorCorrectedMessage::append);
        }
        return errorCorrectedMessage.toString();
    }

}
