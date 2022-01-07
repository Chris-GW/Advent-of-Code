package de.adventofcode.chrisgw.day10;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ChunkBracket {

    ROUND_CHUNK('(', ')', 3, 1), //
    SQUARE_CHUNK('[', ']', 57, 2), //
    CURLY_CHUNK('{', '}', 1197, 3), //
    ANGLE_CHUNK('<', '>', 25137, 4); //


    private final char openBracket;
    private final char closeBracket;
    private final int syntaxErrorPoints;
    private final int completionPoints;


    public static ChunkBracket valueOf(char bracket) {
        for (ChunkBracket chunkBracket : ChunkBracket.values()) {
            if (chunkBracket.isOpenBracket(bracket) || chunkBracket.isCloseBracket(bracket)) {
                return chunkBracket;
            }
        }
        throw new IllegalArgumentException("unexpected bracket: " + bracket);
    }


    public boolean isOpenBracket(char bracket) {
        return getOpenBracket() == bracket;
    }

    public boolean isCloseBracket(char bracket) {
        return getCloseBracket() == bracket;
    }


}
