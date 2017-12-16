package de.adventofcode.chrisgw.day16;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * <h1><a href="https://adventofcode.com/2017/day/16>Day 16: PermutationPromenade</a></h1>
 * <pre>
 * You come upon a very unusual sight; a group of programs here appear to be dancing.
 *
 * There are sixteen programs in total, named a through p. They start by
 * standing in a line: a stands in position 0, b stands in position 1, and so
 * on until p, which stands in position 15.
 *
 * The programs' dance consists of a sequence of dance moves:
 *
 * - Spin, written sX, makes X programs move from the end to the front, but
 *   maintain their order otherwise. (For example, s3 on abcde produces cdeab).
 * - Exchange, written xA/B, makes the programs at positions A and B swap places.
 * - Partner, written pA/B, makes the programs named A and B swap places.
 * For example, with only five programs standing in a line (abcde), they could
 * do the following dance:
 *
 * s1, a spin of size 1: eabcd.
 * x3/4, swapping the last two programs: eabdc.
 * pe/b, swapping programs e and b: baedc.
 *
 * After finishing their dance, the programs end up in order baedc.
 *
 * You watch the dance for a while and record their dance moves (your puzzle input). In what order are the programs standing after their dance?
 * </pre>
 */
public class PermutationPromenade {

    public static final Pattern SPIN_MOVE_PATTERN = Pattern.compile("s(\\d+)");
    public static final Pattern EXCHANGE_MOVE_PATTERN = Pattern.compile("x(\\d+)/(\\d+)");
    public static final Pattern PARTNER_MOVE_PATTERN = Pattern.compile("p([a-z])/([a-z])");

    private Map<String, DancingProgram> nameToDancingProgramMap;
    private DancingProgram[] dancingProgramLine;


    public PermutationPromenade(int memberCound) {
        nameToDancingProgramMap = new HashMap<>(memberCound);
        dancingProgramLine = new DancingProgram[memberCound];

        for (char nameLetter = 'a'; nameLetter < 'a' + memberCound; nameLetter++) {
            String nameStr = String.valueOf(nameLetter);
            DancingProgram dancingProgram = new DancingProgram(nameStr);
            dancingProgram.addToDancingProgramLine(dancingProgramLine, nameLetter - 'a');
            nameToDancingProgramMap.put(nameStr, dancingProgram);
        }
    }


    public void executeDanceMove(String danceMoveDescription) {
        parseDanceMove(danceMoveDescription).executeDanceMove();
    }

    private DanceMove parseDanceMove(String danceMoveDescription) {
        Matcher spinMoveMatcher = SPIN_MOVE_PATTERN.matcher(danceMoveDescription);
        if (spinMoveMatcher.matches()) {
            int targetSpinCount = Integer.parseInt(spinMoveMatcher.group(1));
            return () -> {
                for (int i = 0; i < targetSpinCount; i++) {
                    DancingProgram lastDancingProgram = dancingProgramLine[dancingProgramLine.length - 1];
                    lastDancingProgram.spin();
                }
            };
        }

        Matcher exchangeMoveMatcher = EXCHANGE_MOVE_PATTERN.matcher(danceMoveDescription);
        if (exchangeMoveMatcher.matches()) {
            int dancingProgramPosition = Integer.parseInt(exchangeMoveMatcher.group(1));
            DancingProgram dancingProgram = dancingProgramLine[dancingProgramPosition];
            int withDancingPosition = Integer.parseInt(exchangeMoveMatcher.group(2));
            return () -> dancingProgram.exchange(withDancingPosition);
        }

        Matcher partnerMoveMatcher = PARTNER_MOVE_PATTERN.matcher(danceMoveDescription);
        if (partnerMoveMatcher.matches()) {
            String programName = partnerMoveMatcher.group(1);
            String withOtherProgramName = partnerMoveMatcher.group(2);
            return () -> {
                DancingProgram dancingProgram = nameToDancingProgramMap.get(programName);
                DancingProgram withDancingProgram = nameToDancingProgramMap.get(withOtherProgramName);
                dancingProgram.partner(withDancingProgram);
            };
        }
        throw new IllegalArgumentException("Unknown dance move description: " + danceMoveDescription);
    }


    public String getDancingProgramLineAsString() {
        return Arrays.stream(dancingProgramLine).map(DancingProgram::getName).collect(Collectors.joining(""));
    }

    public DancingProgram[] getDancingProgramLine() {
        return dancingProgramLine;
    }

    public Map<String, DancingProgram> getNameToDancingProgramMap() {
        return nameToDancingProgramMap;
    }


    @Override
    public String toString() {
        return getDancingProgramLineAsString();
    }

}
