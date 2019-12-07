package de.adventofcode.chrisgw.day02;

import java.util.regex.Pattern;


/**
 * https://adventofcode.com/2019/day/2
 */
public class AdventOfCodeDay02 {

    private IntCodeProgram intCodeProgram;

    public AdventOfCodeDay02(String intCodeProgramStr) {
        this.intCodeProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgramStr);
    }


    public int restoreGravityAssistProgram() {
        return runIntCodeProgram(12, 2);
    }


    public int completeGravityAssistProgram(int whisedOutput) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                int output = runIntCodeProgram(noun, verb);
                if (output == whisedOutput) {
                    return 100 * noun + verb;
                }
            }
        }
        return 0;
    }


    private int runIntCodeProgram(int noun, int verb) {
        intCodeProgram.reset();
        intCodeProgram.setNoun(noun);
        intCodeProgram.setVerb(verb);
        intCodeProgram.run();
        return intCodeProgram.getExitOutput();
    }

}
