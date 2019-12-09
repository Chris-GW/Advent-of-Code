package de.adventofcode.chrisgw.intcode;


public class LessThanCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 7;
    }

    @Override
    public String instructionName() {
        return "LES";
    }

    @Override
    public int instructionSize() {
        return 4;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        long firstValue = intCodeProgram.parameterAt(0);
        long secondValue = intCodeProgram.parameterAt(1);
        int destinationAddress = intCodeProgram.parameterAddressAt(2);
        if (firstValue < secondValue) {
            intCodeProgram.setValueAt(destinationAddress, 1);
        } else {
            intCodeProgram.setValueAt(destinationAddress, 0);
        }
    }

}
