package de.adventofcode.chrisgw.day09;

import java.util.LinkedList;
import java.util.List;


public class CharacterGarbageLexer {


    public static List<Token> tokenizeCharacterGarbageStream(String charaterGarbageStream) {
        List<Token> tokens = new LinkedList<>();

        for (int i = 0; i < charaterGarbageStream.length(); i++) {
            char currentChar = charaterGarbageStream.charAt(i);
            TokenType tokenType = TokenType.parseTokenType(currentChar);
            Token token = new Token(tokenType, charaterGarbageStream.substring(i, i + 1));
            tokens.add(token);

            if (TokenType.GARBAGE_START.equals(tokenType)) {
                int garbageStartIndex = i + 1;
                int garbageEndIndex = garbageStartIndex;
                boolean isEscaped = false;

                for (int k = garbageStartIndex; k < charaterGarbageStream.length(); k++) {
                    currentChar = charaterGarbageStream.charAt(k);
                    if (isEscaped) {
                        isEscaped = currentChar == '!';
                        continue;
                    }
                    isEscaped = currentChar == '!';

                    if (currentChar == TokenType.GARBAGE_END.token) {
                        garbageEndIndex = k;
                        i = garbageEndIndex - 1;
                        break;
                    }
                }
                if (garbageStartIndex < garbageEndIndex) {
                    String garbage = charaterGarbageStream.substring(garbageStartIndex, garbageEndIndex);
                    tokens.add(new Token(TokenType.GARBAGE, garbage));
                }
            }
        }

        return tokens;
    }


    public enum TokenType {

        GROUP_START('{'), GROUP_END('}'), //
        GARBAGE_START('<'), GARBAGE_END('>'), //
        SEPERATOR(','), //
        GARBAGE(' ');

        public final char token;

        TokenType(char token) {
            this.token = token;
        }


        public static TokenType parseTokenType(char token) {
            for (TokenType tokenType : TokenType.values()) {
                if (tokenType.token == token) {
                    return tokenType;
                }
            }
            return TokenType.GARBAGE;
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
