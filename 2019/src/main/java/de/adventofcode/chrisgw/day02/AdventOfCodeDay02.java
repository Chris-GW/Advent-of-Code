package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;


/**
 * https://adventofcode.com/2019/day/2
 */
public class AdventOfCodeDay02 {

    private IntCodeProgram intCodeProgram;

    public AdventOfCodeDay02(String intCodeProgramStr) {
        this.intCodeProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgramStr);
    }


    public long restoreGravityAssistProgram() {
        return runIntCodeProgram(12, 2);
    }


    public int completeGravityAssistProgram(int whisedOutput) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                long output = runIntCodeProgram(noun, verb);
                if (output == whisedOutput) {
                    return 100 * noun + verb;
                }
            }
        }
        return 0;
    }


    private long runIntCodeProgram(int noun, int verb) {
        intCodeProgram.reset();
        intCodeProgram.setNoun(noun);
        intCodeProgram.setVerb(verb);
        intCodeProgram.run();
        return intCodeProgram.valueAt(0);
    }

}
