package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.day09.CharacterGarbageLexer.Token;
import de.adventofcode.chrisgw.day09.CharacterGarbageLexer.TokenType;

import java.util.List;


/**
 * <h1><a href="https://adventofcode.com/2017/day/9>Day 9: Stream Processing</a></h1>
 * <pre>
 *
 * </pre>
 */
public class StreamProcessing {


    public static List<Token> tokenizeCharacterGarbageStream(String charaterGarbageStream) {
        return CharacterGarbageLexer.tokenizeCharacterGarbageStream(charaterGarbageStream);
    }

    public static int countGroupScoreForTokens(List<Token> tokens) {
        int groupScoreSum = 0;

        int groupDeth = 0;
        for (Token token : tokens) {
            if (TokenType.GROUP_START.equals(token.type)) {
                groupDeth++;
                groupScoreSum += groupDeth;
            } else if (TokenType.GROUP_END.equals(token.type)) {
                groupDeth--;
            }
        }

        return groupScoreSum;
    }

}
