package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.day07.ThrusterAmplifier;
import de.adventofcode.chrisgw.day08.AdventOfCodeDay08;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;

import java.util.Arrays;
import java.util.stream.IntStream;


/**
 * 2019 Day 9: Sensor Boost
 * https://adventofcode.com/2019/day/9
 */
public class AdventOfCodeDay09 {

    private AdventOfCodeDay09() {

    }


    public static long runBoostProgramInTestMode(IntCodeProgram boostProgram) {
        boostProgram.addInput(1);
        boostProgram.run();
        return boostProgram.lastOutput();
    }

    public static long runBoostProgramInBoostMode(IntCodeProgram boostProgram) {
        boostProgram.addInput(2);
        boostProgram.run();
        return boostProgram.lastOutput();
    }

}
