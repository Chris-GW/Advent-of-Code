package de.adventofcode.chrisgw.day10;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ChunkType {

    ROUND_CHUNK('(', ')', 3), //
    SQUARE_CHUNK('[', ']', 57), //
    CURLY_CHUNK('{', '}', 1197), //
    ANGLE_CHUNK('<', '>', 25137); //


    private final char openBracket;
    private final char closeBracket;
    private final int points;


    public static ChunkType valueOf(char bracket) {
        for (ChunkType chunkType : ChunkType.values()) {
            if (chunkType.isOpenBracket(bracket) || chunkType.isCloseBracket(bracket)) {
                return chunkType;
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
