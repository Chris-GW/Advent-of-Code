package de.adventofcode.chrisgw.intcode;


public class AddIntCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 1;
    }

    @Override
    public String instructionName() {
        return "ADD";
    }

    @Override
    public int instructionSize() {
        return 4;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        long firstSummand = intCodeProgram.parameterAt(0);
        long secondSummand = intCodeProgram.parameterAt(1);
        long sum = firstSummand + secondSummand;
        int destinationAddress = intCodeProgram.parameterAddressAt(2);
        intCodeProgram.setValueAt(destinationAddress, sum);
    }

}
