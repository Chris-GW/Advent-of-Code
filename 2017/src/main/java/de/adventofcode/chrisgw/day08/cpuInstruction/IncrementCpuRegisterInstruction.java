package de.adventofcode.chrisgw.day08.cpuInstruction;

import de.adventofcode.chrisgw.day08.CpuRegister;


public class IncrementCpuRegisterInstruction implements CpuRegisterInstruction {

    private int value;
    private CpuRegister cpuRegister;


    public IncrementCpuRegisterInstruction(CpuRegister cpuRegister, int value) {
        this.cpuRegister = cpuRegister;
        this.value = value;
    }


    @Override
    public void executeCpuRegisterInstruction() {
        cpuRegister.incrementValue(value);
    }

}
