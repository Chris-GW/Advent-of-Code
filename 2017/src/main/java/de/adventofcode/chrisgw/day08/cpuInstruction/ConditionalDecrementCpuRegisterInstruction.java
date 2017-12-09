package de.adventofcode.chrisgw.day08.cpuInstruction;

import de.adventofcode.chrisgw.day08.CpuRegister;
import de.adventofcode.chrisgw.day08.CpuRegisterInstructionCondition;


public class ConditionalDecrementCpuRegisterInstruction extends DecrementCpuRegisterInstruction
        implements ConditionalCpuRegisterInstruction {

    private CpuRegisterInstructionCondition instructionCondition;


    public ConditionalDecrementCpuRegisterInstruction(CpuRegister cpuRegister, int value,
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
