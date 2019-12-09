package de.adventofcode.chrisgw.intcode;


public class MulIntCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 2;
    }

    @Override
    public String instructionName() {
        return "MUL";
    }

    @Override
    public int instructionSize() {
        return 4;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        long firstFactor = intCodeProgram.parameterAt(0);
        long secondFactor = intCodeProgram.parameterAt(1);
        long product = firstFactor * secondFactor;
        int destinationAddress = intCodeProgram.parameterAddressAt(2);
        intCodeProgram.setValueAt(destinationAddress, product);
    }

}
