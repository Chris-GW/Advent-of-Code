package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;


/**
 * https://adventofcode.com/2019/day/5
 */
public class AdventOfCodeDay05 {

    private IntCodeProgram intCodeProgram;

    public AdventOfCodeDay05(String intCodeProgramStr) {
        this.intCodeProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgramStr);
    }


    public long runTestAndReturnDignosticCode() {
        return runTestAndReturnDignosticCode(1);
    }

    public long runTestAndReturnDignosticCode(int systemId) {
        intCodeProgram.reset();
        intCodeProgram.addInput(systemId);
        intCodeProgram.run();
        return intCodeProgram.lastOutput();
    }

}
