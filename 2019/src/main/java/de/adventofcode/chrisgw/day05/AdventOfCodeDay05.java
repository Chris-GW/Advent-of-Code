package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.day02.IntCodeProgram;

import java.util.regex.Pattern;


/**
 * https://adventofcode.com/2019/day/5
 */
public class AdventOfCodeDay05 {

    private IntCodeProgram intCodeProgram;

    public AdventOfCodeDay05(String intCodeProgramStr) {
        Pattern splitPattern = Pattern.compile(",");
        int[] initialState = splitPattern.splitAsStream(intCodeProgramStr).mapToInt(Integer::parseInt).toArray();
        this.intCodeProgram = new IntCodeProgram(initialState);
    }


    public int runTestAndReturnDignosticCode() {
        return runTestAndReturnDignosticCode(1);
    }

    public int runTestAndReturnDignosticCode(int systemId) {
        intCodeProgram.reset();
        intCodeProgram.addInput(systemId);
        while (intCodeProgram.hasNext()) {
            intCodeProgram.next();
        }
        return intCodeProgram.lastOutput();
    }

}
