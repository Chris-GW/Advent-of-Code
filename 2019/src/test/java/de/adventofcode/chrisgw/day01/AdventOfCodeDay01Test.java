package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static de.adventofcode.chrisgw.day01.AdventOfCodeDay01.calculateModuleFuelRequirement;
import static org.junit.Assert.*;


public class AdventOfCodeDay01Test {

    @Test
    public void calculateModuleFuelRequirement_12() {
        long moduleMass = 12;
        long expectedFuel = 2;

        long fuel = calculateModuleFuelRequirement(moduleMass);
        assertEquals("fuel", expectedFuel, fuel);
    }

    @Test
    public void calculateModuleFuelRequirement_14() {
        long moduleMass = 14;
        long expectedFuel = 2;

        long fuel = calculateModuleFuelRequirement(moduleMass);
        assertEquals("fuel", expectedFuel, fuel);
    }

    @Test
    public void calculateModuleFuelRequirement_1969() {
        long moduleMass = 1969;
        long expectedFuel = 654;

        long fuel = calculateModuleFuelRequirement(moduleMass);
        assertEquals("fuel", expectedFuel, fuel);
    }

    @Test
    public void calculateModuleFuelRequirement_100756() {
        long moduleMass = 100756;
        long expectedFuel = 33583;

        long fuel = calculateModuleFuelRequirement(moduleMass);
        assertEquals("fuel", expectedFuel, fuel);
    }


    @Test
    public void calculateTotalModuleFuelRequirement_part01() {
        long expectedTotalFuel = 3442987;
        List<Long> moduleMassList = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay01.txt")
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        long totalFuel = moduleMassList.stream().mapToLong(AdventOfCodeDay01::calculateModuleFuelRequirement).sum();
        assertEquals("totalFuel", expectedTotalFuel, totalFuel);
    }

}
