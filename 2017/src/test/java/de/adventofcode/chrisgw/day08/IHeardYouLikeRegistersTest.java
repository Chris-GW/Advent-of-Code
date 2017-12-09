package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day08.cpuInstruction.CpuRegisterInstruction;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class IHeardYouLikeRegistersTest {

    @Test
    public void findLargestCpuRegister_example() {
        // @formatter:off
        List<String> cpuRegisterInstructionLines = Arrays.asList(
                "b inc 5 if a > 1",
                "a inc 1 if b < 5",
                "c dec -10 if a >= 1",
                "c inc -20 if c == 10");
        // @formatter:on
        int expectedLargestCpuRegisterValue = 1;
        Map<String, Integer> expectedCpuRegisterValues = new HashMap<>();
        expectedCpuRegisterValues.put("a", 1);
        expectedCpuRegisterValues.put("b", 0);
        expectedCpuRegisterValues.put("c", -10);

        IHeardYouLikeRegisters iHeardYouLikeRegisters = new IHeardYouLikeRegisters();
        List<CpuRegisterInstruction> cpuRegisterInstructions = iHeardYouLikeRegisters.parseCpuRegisterInstructions(
                cpuRegisterInstructionLines);
        cpuRegisterInstructions.forEach(CpuRegisterInstruction::executeCpuRegisterInstruction);

        int largestCpuRegisterValue = iHeardYouLikeRegisters.findLargestCpuRegisterValue();
        Assert.assertEquals("Expect largestCpuRegisterValue", expectedLargestCpuRegisterValue, largestCpuRegisterValue);
        for (Entry<String, Integer> cpuRegisterNameExpectedValueEntry : expectedCpuRegisterValues.entrySet()) {
            String cpuRegisterName = cpuRegisterNameExpectedValueEntry.getKey();
            int expectedCpuRegisterValue = cpuRegisterNameExpectedValueEntry.getValue();
            CpuRegister cpuRegister = iHeardYouLikeRegisters.findCpuRegister(cpuRegisterName);
            Assert.assertTrue("cpuRegister not null", cpuRegister != null);
            Assert.assertEquals("Expect cpuRegister value", expectedCpuRegisterValue, cpuRegister.getValue());
        }
    }


    @Test
    public void findLargestCpuRegister_myTask() {
        String classpathResource = "/day08/IHeardYouLikeRegisters_chrisgw.txt";
        List<String> cpuRegisterInstructionLines = TestUtils.readAllLinesOfClassPathResource(classpathResource);
        int expectedLargestCpuRegisterValue = 3089;

        IHeardYouLikeRegisters iHeardYouLikeRegisters = new IHeardYouLikeRegisters();
        List<CpuRegisterInstruction> cpuRegisterInstructions = iHeardYouLikeRegisters.parseCpuRegisterInstructions(
                cpuRegisterInstructionLines);
        cpuRegisterInstructions.forEach(CpuRegisterInstruction::executeCpuRegisterInstruction);

        int largestCpuRegisterValue = iHeardYouLikeRegisters.findLargestCpuRegisterValue();
        Assert.assertEquals("Expect largestCpuRegisterValue", expectedLargestCpuRegisterValue, largestCpuRegisterValue);
    }

}