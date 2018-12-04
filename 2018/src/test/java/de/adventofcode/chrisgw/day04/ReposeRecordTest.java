package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ReposeRecordTest {


    @Test
    public void strategy_01_example_01() {
        List<String> guardShiftRecords = Arrays.asList( //
                "[1518-11-01 00:00] Guard #10 begins shift", //
                "[1518-11-01 00:05] falls asleep", //
                "[1518-11-01 00:25] wakes up", //
                "[1518-11-01 00:30] falls asleep", //
                "[1518-11-01 00:55] wakes up", //
                "[1518-11-01 23:58] Guard #99 begins shift", //
                "[1518-11-02 00:40] falls asleep", //
                "[1518-11-02 00:50] wakes up", //
                "[1518-11-03 00:05] Guard #10 begins shift", //
                "[1518-11-03 00:24] falls asleep", //
                "[1518-11-03 00:29] wakes up", //
                "[1518-11-04 00:02] Guard #99 begins shift", //
                "[1518-11-04 00:36] falls asleep", //
                "[1518-11-04 00:46] wakes up", //
                "[1518-11-05 00:03] Guard #99 begins shift", //
                "[1518-11-05 00:45] falls asleep", //
                "[1518-11-05 00:55] wakes up");
        long expectedSolution = 10 * 24;

        ReposeRecord reposeRecord = ReposeRecord.parseGuardShiftRecords(guardShiftRecords.stream());
        System.out.println(reposeRecord);
        long solution = reposeRecord.solveUsingStrategie01();
        assertEquals(expectedSolution, solution);
    }


    @Test
    public void strategy_01_myTask() throws Exception {
        long expectedSolution = 39698;
        Path guardShiftRecordFile = TestUtils.getResourcePath("/day04/guardShiftRecords.txt");

        ReposeRecord reposeRecord = ReposeRecord.parseGuardShiftRecords(guardShiftRecordFile);
        long solution = reposeRecord.solveUsingStrategie01();
        assertEquals(expectedSolution, solution);
    }

}