package de.adventofcode.chrisgw.day10;

public class NoopCpuInstruction extends CpuInstruction {

    public NoopCpuInstruction() {
        super("noop", 1);
    }

    @Override
    public void runInstructionOnCpu(CommunicationSystemCpu cpu) {
        // noop
    }

}
