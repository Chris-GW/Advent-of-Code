package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.day08.cpuInstruction.ConditionalDecrementCpuRegisterInstruction;
import de.adventofcode.chrisgw.day08.cpuInstruction.ConditionalIncrementCpuRegisterInstruction;
import de.adventofcode.chrisgw.day08.cpuInstruction.CpuRegisterInstruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Cpu {

    public static final Pattern CPU_REGISTER_INSTURCTION_PATTERN = Pattern.compile(
            "(\\w+)\\s+(inc|dec)\\s(-?\\d+)\\s+if\\s+(\\w+)\\s+([!=<>]+)\\s+(-?\\d+)");


    private Map<String, CpuRegister> cpuRegisters = new HashMap<>();

    public List<CpuRegisterInstruction> parseCpuRegisterInstructions(List<String> cpuRegisterInsturctionLines) {
        return cpuRegisterInsturctionLines.stream().map(this::parseCpuRegisterInstruction).collect(Collectors.toList());
    }

    public CpuRegisterInstruction parseCpuRegisterInstruction(String cpuRegisterInsturctionStr) {
        Matcher instructionMatcher = CPU_REGISTER_INSTURCTION_PATTERN.matcher(cpuRegisterInsturctionStr);
        if (!instructionMatcher.matches()) {
            throw new IllegalArgumentException();
        }
        return createCpuRegisterInstructionOfMatchResult(instructionMatcher.toMatchResult());
    }


    public CpuRegisterInstruction createCpuRegisterInstructionOfMatchResult(MatchResult instructionMatchResult) {
        CpuRegister cpuRegister = findCpuRegister(instructionMatchResult.group(1));
        String insturctionType = instructionMatchResult.group(2);
        int value = Integer.parseInt(instructionMatchResult.group(3));

        CpuRegisterInstructionCondition instructionCondition = createCpuRegisterInstructionConditionOfMatchResult(
                instructionMatchResult);

        switch (insturctionType) {
        case "inc":
            return new ConditionalIncrementCpuRegisterInstruction(cpuRegister, value, instructionCondition);
        case "dec":
            return new ConditionalDecrementCpuRegisterInstruction(cpuRegister, value, instructionCondition);
        default:
            throw new IllegalArgumentException("Unknown cpu register insturction: " + insturctionType);
        }
    }

    private CpuRegisterInstructionCondition createCpuRegisterInstructionConditionOfMatchResult(
            MatchResult instructionMatchResult) {
        CpuRegister referenceCpuRegister = findCpuRegister(instructionMatchResult.group(4));
        String conditionSign = instructionMatchResult.group(5);
        int compareValue = Integer.parseInt(instructionMatchResult.group(6));

        switch (conditionSign) {
        case "==":
            return CpuRegisterInstructionCondition.equals(referenceCpuRegister, compareValue);
        case "!=":
            return CpuRegisterInstructionCondition.notEquals(referenceCpuRegister, compareValue);
        case "<":
            return CpuRegisterInstructionCondition.smaller(referenceCpuRegister, compareValue);
        case "<=":
            return CpuRegisterInstructionCondition.smallerEquals(referenceCpuRegister, compareValue);
        case ">":
            return CpuRegisterInstructionCondition.greater(referenceCpuRegister, compareValue);
        case ">=":
            return CpuRegisterInstructionCondition.greaterEquals(referenceCpuRegister, compareValue);
        default:
            throw new IllegalArgumentException("Unknown cpu register condition: " + conditionSign);
        }
    }


    public CpuRegister findCpuRegister(String registerName) {
        return cpuRegisters.computeIfAbsent(registerName, CpuRegister::new);
    }

    public int findLargestCpuRegisterValue() {
        return cpuRegisters.values().stream().mapToInt(CpuRegister::getValue).max().orElse(0);
    }

    public int findLargestCpuRegisterValueHeld() {
        return cpuRegisters.values().stream().mapToInt(CpuRegister::getHighestValue).max().orElse(Integer.MIN_VALUE);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CPU Register:");
        for (CpuRegister cpuRegister : cpuRegisters.values()) {
            sb.append("\n").append(cpuRegister);
        }
        return sb.toString();
    }

}
