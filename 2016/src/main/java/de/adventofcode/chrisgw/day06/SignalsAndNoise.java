package de.adventofcode.chrisgw.day06;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 *
 * --- Part Two ---
 *
 * Of course, that would be the message - if you hadn't agreed to use a
 * modified repetition code instead.
 *
 * In this modified code, the sender instead transmits what looks like random
 * data, but for each character, the character they actually want to send is
 * slightly less likely than the others. Even after signal-jamming noise, you
 * can look at the letter distributions in each column and choose the least
 * common letter to reconstruct the original message.
 *
 * In the above example, the least common character in the first column is a;
 * in the second, d, and so on. Repeating this process for the remaining
 * characters produces the original message, advent.
 *
 * Given the recording in your puzzle input and this new decoding methodology,
 * what is the original message that Santa is trying to send?
 * </pre>
 */
public class SignalsAndNoise {


    public static String getErrorCorrectedMessageByMostOccurenceColumnLetter(List<String> messages) {
        List<Map<Character, Integer>> letterAppearancePerColumn = getMessageLetterOccurencePerColumn(messages);
        return correctMessageWithMostAppearanceColumnLetter(letterAppearancePerColumn);
    }

    public static String getErrorCorrectedMessageByLeastOccurenceColumnLetter(List<String> messages) {
        List<Map<Character, Integer>> letterAppearancePerColumn = getMessageLetterOccurencePerColumn(messages);
        return correctMessagesWithLeastAppearanceColumnLetter(letterAppearancePerColumn);
    }


    private static List<Map<Character, Integer>> getMessageLetterOccurencePerColumn(List<String> messages) {
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
        return letterAppearancesPerColumn;
    }


    private static String correctMessageWithMostAppearanceColumnLetter(
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

    private static String correctMessagesWithLeastAppearanceColumnLetter(
            List<Map<Character, Integer>> letterAppearancePerColumn) {
        StringBuilder errorCorrectedMessage = new StringBuilder(letterAppearancePerColumn.size());
        for (Map<Character, Integer> letterAppearance : letterAppearancePerColumn) {
            letterAppearance.entrySet()
                    .stream()
                    .min(Entry.comparingByValue())
                    .map(Entry::getKey)
                    .ifPresent(errorCorrectedMessage::append);
        }
        return errorCorrectedMessage.toString();
    }

}
