package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import static org.junit.Assert.*;


public class ChronalCalibrationTest {

    // part 01

    @Test
    public void calculateFrequency_1_1_1_should_be_3() throws Exception {
        long expectedFrequency = 3;
        IntStream frequencies = IntStream.of(1, 1, 1);
        long actualFrequency = ChronalCalibration.calculateFrequency(frequencies);
        assertEquals("resulting frequency", expectedFrequency, actualFrequency);
    }

    @Test
    public void calculateFrequency_1_1_m2_should_be_0() throws Exception {
        long expectedFrequency = 0;
        IntStream frequencies = IntStream.of(1, 1, -2);
        long actualFrequency = ChronalCalibration.calculateFrequency(frequencies);
        assertEquals("resulting frequency", expectedFrequency, actualFrequency);
    }

    @Test
    public void calculateFrequency_m1_m2_m3_should_be_m6() throws Exception {
        long expectedFrequency = -6;
        IntStream frequencies = IntStream.of(-1, -2, -3);
        long actualFrequency = ChronalCalibration.calculateFrequency(frequencies);
        assertEquals("resulting frequency", expectedFrequency, actualFrequency);
    }


    @Test
    public void calculateFrequency_myTask() throws Exception {
        long expectedFrequency = 516;
        Path frequenciesDataPath = TestUtils.getResourcePath("/day01/frequenciesData.txt");
        System.out.println(frequenciesDataPath.toAbsolutePath());

        long actualFrequency = ChronalCalibration.calculateFrequency(frequenciesDataPath);
        assertEquals("resulting frequency", expectedFrequency, actualFrequency);
    }


    // part 02


    @Test
    public void calculateFirstRepeatedFrequency_example_01() {
        long expectedFrequency = 2;
        IntStream frequencies = IntStream.of(1, -2, 3, 1, 1, -2);
        long repeatedFrequency = ChronalCalibration.calculateFirstRepeatedFrequency(frequencies);
        assertEquals("first repeated frequency", expectedFrequency, repeatedFrequency);
    }

    @Test
    public void calculateFirstRepeatedFrequency_example_02() {
        long expectedFrequency = 0;
        IntStream frequencies = IntStream.of(1, -1);
        long repeatedFrequency = ChronalCalibration.calculateFirstRepeatedFrequency(frequencies);
        assertEquals("first repeated frequency", expectedFrequency, repeatedFrequency);
    }

    @Test
    public void calculateFirstRepeatedFrequency_example_03() {
        long expectedFrequency = 10;
        IntStream frequencies = IntStream.of(+3, +3, +4, -2, -4);
        long repeatedFrequency = ChronalCalibration.calculateFirstRepeatedFrequency(frequencies);
        assertEquals("first repeated frequency", expectedFrequency, repeatedFrequency);
    }

    @Test
    public void calculateFirstRepeatedFrequency_example_04() {
        long expectedFrequency = 5;
        IntStream frequencies = IntStream.of(-6, +3, +8, +5, -6);
        long repeatedFrequency = ChronalCalibration.calculateFirstRepeatedFrequency(frequencies);
        assertEquals("first repeated frequency", expectedFrequency, repeatedFrequency);
    }

    @Test
    public void calculateFirstRepeatedFrequency_example_05() {
        long expectedFrequency = 14;
        IntStream frequencies = IntStream.of(+7, +7, -2, -7, -4);
        long repeatedFrequency = ChronalCalibration.calculateFirstRepeatedFrequency(frequencies);
        assertEquals("first repeated frequency", expectedFrequency, repeatedFrequency);
    }


    @Test
    public void calculateFirstRepeatedFrequency_myTask() throws Exception {
        long expectedFrequency = 71892;
        Path frequenciesDataPath = TestUtils.getResourcePath("/day01/frequenciesData.txt");
        System.out.println(frequenciesDataPath.toAbsolutePath());

        long actualFrequency = ChronalCalibration.calculateFirstRepeatedFrequency(frequenciesDataPath);
        assertEquals("first repeated frequency", expectedFrequency, actualFrequency);
    }

}
