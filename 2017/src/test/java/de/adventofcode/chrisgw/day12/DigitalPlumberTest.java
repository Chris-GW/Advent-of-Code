package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DigitalPlumberTest {


    @Test
    public void shouldParseDigitalPrograms_example_expect_6_connectedProgramms() {
        // @formatter:off
        List<String> digitalProgramLines = Arrays.asList(
                "0 <-> 2",
                "1 <-> 1",
                "2 <-> 0, 3, 4",
                "3 <-> 2, 4",
                "4 <-> 2, 3, 6",
                "5 <-> 6",
                "6 <-> 4, 5");
        // @formatter:on
        int expectedConnectedProgramCount = 6;
        int expectedProgramGroups = 2;

        Map<Integer, DigitalProgram> idToDigitalProgram = DigitalPlumber.parseDigitalProgramConnections(
                digitalProgramLines);
        DigitalProgram digitalProgram = idToDigitalProgram.get(0);
        Set<DigitalProgram> allReachableDigitalPrograms = digitalProgram.getAllReachableDigitalPrograms();

        Assert.assertEquals("Expect connected program count", expectedConnectedProgramCount,
                allReachableDigitalPrograms.size());
    }

    @Test
    public void shouldParseDigitalPrograms_myTask_expect_right_connectedProgramms() {
        String classpathResource = "/day12/DigitalPlumber_chrisgw.txt";
        List<String> digitalProgramLines = TestUtils.readAllLinesOfClassPathResource(classpathResource);
        int expectedConnectedProgramCount = 288;

        Map<Integer, DigitalProgram> idToDigitalProgram = DigitalPlumber.parseDigitalProgramConnections(
                digitalProgramLines);
        DigitalProgram digitalProgram = idToDigitalProgram.get(0);
        Set<DigitalProgram> allReachableDigitalPrograms = digitalProgram.getAllReachableDigitalPrograms();

        Assert.assertEquals("Expect connected program count", expectedConnectedProgramCount,
                allReachableDigitalPrograms.size());
    }


    // --- part 2

    @Test
    public void shouldParseDigitalPrograms_example_expect_2_programGroups() {
        // @formatter:off
        List<String> digitalProgramLines = Arrays.asList(
                "0 <-> 2",
                "1 <-> 1",
                "2 <-> 0, 3, 4",
                "3 <-> 2, 4",
                "4 <-> 2, 3, 6",
                "5 <-> 6",
                "6 <-> 4, 5");
        // @formatter:on
        int expectedProgramGroups = 2;

        Map<Integer, DigitalProgram> idToDigitalProgram = DigitalPlumber.parseDigitalProgramConnections(
                digitalProgramLines);
        int programGroupCount = DigitalPlumber.countConnectedDigitalProgramGroups(idToDigitalProgram);

        Assert.assertEquals("Expect program group count", expectedProgramGroups, programGroupCount);
    }

    @Test
    public void shouldParseDigitalPrograms_myTask_expect_right_groupCount() {
        String classpathResource = "/day12/DigitalPlumber_chrisgw.txt";
        List<String> digitalProgramLines = TestUtils.readAllLinesOfClassPathResource(classpathResource);
        int expectedProgramGroups = 211;

        Map<Integer, DigitalProgram> idToDigitalProgram = DigitalPlumber.parseDigitalProgramConnections(
                digitalProgramLines);
        int programGroupCount = DigitalPlumber.countConnectedDigitalProgramGroups(idToDigitalProgram);

        Assert.assertEquals("Expect program group count", expectedProgramGroups, programGroupCount);
    }

}
