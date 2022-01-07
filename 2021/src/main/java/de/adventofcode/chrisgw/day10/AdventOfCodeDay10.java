package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;


/**
 * https://adventofcode.com/2021/day/10
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzleSolver<Long> {

    public AdventOfCodeDay10(List<String> inputLines) {
        super(Year.of(2021), 10, inputLines);
    }

    public Long solveFirstPart() {
        long syntaxErrorScore = 0;
        for (String navigationLine : getInputLines()) {
            syntaxErrorScore += syntaxErrorScoreFor(navigationLine);
        }

        //TODO solveFirstPart
        return syntaxErrorScore;
    }

    private long syntaxErrorScoreFor(String navigationLine) {
        long syntaxErrorScore = 0;
        Deque<ChunkBracket> openChunks = new ArrayDeque<>();
        for (int i = 0; i < navigationLine.length(); i++) {
            char bracket = navigationLine.charAt(i);
            ChunkBracket chunkBracket = ChunkBracket.valueOf(bracket);
            if (chunkBracket.isOpenBracket(bracket)) {
                openChunks.push(chunkBracket);
            } else if (!openChunks.pop().equals(chunkBracket)) {
                syntaxErrorScore += chunkBracket.getSyntaxErrorPoints();
                break;
            }
        }
        return syntaxErrorScore;
    }


    public Long solveSecondPart() {
        List<Long> completionScores = new ArrayList<>();
        for (String navigationLine : getInputLines()) {
            if (syntaxErrorScoreFor(navigationLine) > 0) {
                continue;
            }
            completionScores.add(completionScoreFor(navigationLine));
        }
        completionScores.sort(Comparator.naturalOrder());
        int middleScoreIndex = completionScores.size() / 2;
        return completionScores.get(middleScoreIndex);
    }

    private long completionScoreFor(String navigationLine) {
        Deque<ChunkBracket> openChunks = new ArrayDeque<>();
        for (int i = 0; i < navigationLine.length(); i++) {
            char bracket = navigationLine.charAt(i);
            ChunkBracket chunkBracket = ChunkBracket.valueOf(bracket);
            if (chunkBracket.isOpenBracket(bracket)) {
                openChunks.push(chunkBracket);
            } else {
                openChunks.pop();
            }
        }

        long completionScore = 0;
        while (!openChunks.isEmpty()) {
            ChunkBracket popChunkBracket = openChunks.pop();
            completionScore *= 5;
            completionScore += popChunkBracket.getCompletionPoints();
        }
        return completionScore;
    }


}
