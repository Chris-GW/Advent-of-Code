package de.adventofcode.chrisgw.day09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CharacterGarbageLexer {


    public static List<Token> tokenizeCharacterGarbageStream(String charaterGarbageStream) {
        List<Token> tokens = new LinkedList<>();
        for (int i = 0; i < charaterGarbageStream.length(); ) {
            for (TokenType tokenType : TokenType.values()) {
                Matcher tokenMatcher = tokenType.pattern.matcher(charaterGarbageStream);
                if (tokenMatcher.find(i)) {
                    String value = tokenMatcher.group();
                    tokens.add(new Token(tokenType, value));
                    i = tokenMatcher.end();
                    break;
                }
            }
        }
        return tokens;
    }




    public enum TokenType {

        GARBAGE_START("\\G(?<!!)<"), GARBAGE_END("\\G(?<!!)>"), //
        GARBAGE("\\G(?<=(?<!!)<).+?(?=(?<!!)>)"), //
        GROUP_START("\\G(?<!!)\\{"), GROUP_END("\\G(?<!!)\\}"), //
        SEPERATOR("\\G(?<!!),"), //
        ;
        public final Pattern pattern;

        TokenType(String patternStr) {
            this.pattern = Pattern.compile(patternStr);
        }

    }


    public static class Token {

        public final TokenType type;
        public final String value;


        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Token token = (Token) o;

            if (type != token.type)
                return false;
            return value != null ? value.equals(token.value) : token.value == null;
        }

        @Override
        public int hashCode() {
            int result = type != null ? type.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return String.format("(%s '%s')", type, value);
        }

    }


}
