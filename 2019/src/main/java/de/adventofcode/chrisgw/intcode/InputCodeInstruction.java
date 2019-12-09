package de.adventofcode.chrisgw.intcode;


public class InputCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 3;
    }

    @Override
    public String instructionName() {
        return "INP";
    }

    @Override
    public int instructionSize() {
        return 2;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        long input = intCodeProgram.nextInput();
        int destinationAddress =  intCodeProgram.parameterAddressAt(0);
        intCodeProgram.setValueAt(destinationAddress, input);
    }

}
