package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2022/day/10">Advent of Code - day 10</a>
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay10(List<String> inputLines) {
        super(Year.of(2022), 10, inputLines);
    }


    public Integer solveFirstPart() {
        List<CpuInstruction> cpuInstructions = inputLines().map(CpuInstruction::parseCpuInstruction).toList();
        var cpu = new CommunicationSystemCpu(cpuInstructions);
        int firstCycleSignalCheck = 20;
        int cycleSignalCheckStep = 40;
        int signalCheckLimit = 6;
        return Stream.iterate(cpu, CommunicationSystemCpu::nextCycle)
                .filter(cpu1 -> cpu1.getCycle() % cycleSignalCheckStep == firstCycleSignalCheck)
                .limit(signalCheckLimit)
                .mapToInt(CommunicationSystemCpu::getSignalStrength)
                .sum();
    }

    public String solveSecondPart() {
        List<CpuInstruction> cpuInstructions = inputLines().map(CpuInstruction::parseCpuInstruction).toList();
        var cpu = new CommunicationSystemCpu(cpuInstructions);
        String crtDisplay = Stream.iterate(cpu, CommunicationSystemCpu::nextCycle)
                .filter(CommunicationSystemCpu::isCrtDisplayCompleted)
                .map(CommunicationSystemCpu::printCrtDisplay)
                .findFirst()
                .orElse("");
        System.out.println(crtDisplay);
        return crtDisplay;
    }

}
