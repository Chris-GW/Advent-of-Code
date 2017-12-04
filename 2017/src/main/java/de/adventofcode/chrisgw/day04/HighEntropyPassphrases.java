package de.adventofcode.chrisgw.day04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <pre>
 *     --- Day 4: High-Entropy Passphrases ---
 *
 * A new system policy has been put in place that requires all accounts to use a passphrase
 * instead of simply a password. A passphrase consists of a series of words (lowercase letters)
 * separated by spaces.
 *
 * To ensure security, a valid passphrase must contain no duplicate words.
 *
 * For example:
 *
 * aa bb cc dd ee is valid.
 * aa bb cc dd aa is not valid - the word aa appears more than once.
 * aa bb cc dd aaa is valid - aa and aaa count as different words.
 *
 * The system's full passphrase list is available as your puzzle input.
 * How many passphrases are valid?
 * </pre>
 */
public class HighEntropyPassphrases {


    public static List<Passphrase> parsePassphrases(List<String> passphrasesLines) {
        List<Passphrase> passphraseList = new ArrayList<>(passphrasesLines.size());
        for (String passphraseLine : passphrasesLines) {
            passphraseList.add(parsePassphrase(passphraseLine));
        }
        return passphraseList;
    }

    public static Passphrase parsePassphrase(String passphrase) {
        return new Passphrase(passphrase);
    }


    public static class Passphrase {

        public String passphrase;


        public Passphrase(String passphrase) {
            this.passphrase = passphrase;
        }


        public boolean isValid() {
            String[] splittedWords = getWords();
            Set<String> words = new HashSet<>(splittedWords.length);
            for (String word : splittedWords) {
                boolean isNewWord = words.add(word);
                if (!isNewWord) {
                    return false;
                }
            }
            return true;
        }

        public String[] getWords() {
            return passphrase.split("\\s+");
        }


        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Passphrase))
                return false;

            Passphrase that = (Passphrase) o;

            return passphrase != null ? passphrase.equals(that.passphrase) : that.passphrase == null;
        }

        @Override
        public int hashCode() {
            return passphrase != null ? passphrase.hashCode() : 0;
        }

        @Override
        public String toString() {
            return passphrase;
        }

    }


}
