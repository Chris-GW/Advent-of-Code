package de.adventofcode.chrisgw.day08.cpuInstruction;

import de.adventofcode.chrisgw.day08.CpuRegister;
import de.adventofcode.chrisgw.day08.CpuRegisterInstructionCondition;


public class ConditionalIncrementCpuRegisterInstruction extends IncrementCpuRegisterInstruction
        implements ConditionalCpuRegisterInstruction {

    private CpuRegisterInstructionCondition instructionCondition;


    public ConditionalIncrementCpuRegisterInstruction(CpuRegister cpuRegister, int value,
            CpuRegisterInstructionCondition instructionCondition) {
        super(cpuRegister, value);
        this.instructionCondition = instructionCondition;
    }


    @Override
    public boolean testInsturctionCondition() {
        return instructionCondition.testInsturctionCondition();
    }

    @Override
    public void executeCpuRegisterInstruction() {
        if (testInsturctionCondition()) {
            super.executeCpuRegisterInstruction();
        }
    }

}
