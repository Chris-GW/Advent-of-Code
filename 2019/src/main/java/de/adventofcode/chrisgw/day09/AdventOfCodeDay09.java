package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.day07.ThrusterAmplifier;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;

import java.util.Arrays;
import java.util.stream.IntStream;


/**
 * 2019 Day 9: Sensor Boost
 * https://adventofcode.com/2019/day/9
 */
public class AdventOfCodeDay09 {


    public static long runBoostProgramInTestMode(IntCodeProgram boostProgram) {
        boostProgram.addInput(1);
        boostProgram.run();
        long[] outputs = boostProgram.getAllOutput().toArray();
        System.out.println(Arrays.toString(outputs));
        return outputs[outputs.length - 1];
    }

}
