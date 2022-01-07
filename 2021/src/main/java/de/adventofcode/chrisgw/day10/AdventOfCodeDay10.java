package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


/**
 * https://adventofcode.com/2021/day/10
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay10(List<String> inputLines) {
        super(Year.of(2021), 10, inputLines);
    }

    public Integer solveFirstPart() {
        int syntaxErrorScore = 0;
        for (String navigationLine : getInputLines()) {
            syntaxErrorScore += syntaxErrorScoreFor(navigationLine);
        }

        //TODO solveFirstPart
        return syntaxErrorScore;
    }

    private int syntaxErrorScoreFor(String navigationLine) {
        int syntaxErrorScore = 0;
        Deque<ChunkType> openChunks = new ArrayDeque<>();
        for (int i = 0; i < navigationLine.length(); i++) {
            char bracket = navigationLine.charAt(i);
            ChunkType chunkType = ChunkType.valueOf(bracket);
            if (chunkType.isOpenBracket(bracket)) {
                openChunks.push(chunkType);
            } else if (!openChunks.pop().equals(chunkType)) {
                syntaxErrorScore += chunkType.getPoints();
                break;
            }
        }
        return syntaxErrorScore;
    }


    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }


}
