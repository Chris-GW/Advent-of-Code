package de.adventofcode.chrisgw.day10;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AddxCpuInstruction extends CpuInstruction {

    private final int value;

    public AddxCpuInstruction(int value) {
        super("addx", 2);
        this.value = value;
    }

    @Override
    public void runInstructionOnCpu(CommunicationSystemCpu cpu) {
        int newRegisterValue = cpu.getRegisterValue() + this.value;
        cpu.setRegisterValue(newRegisterValue);
    }

}
