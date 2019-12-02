package de.adventofcode.chrisgw.day01;

/**
 * https://adventofcode.com/2019/day/1
 */
public class AdventOfCodeDay01 {

    public static long calculateModuleFuelRequirement(long moduleMass) {
        long fuel = moduleMass / 3;
        return fuel - 2;
    }

}
