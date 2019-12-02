package de.adventofcode.chrisgw.day02;

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
        int firstAddress = intCodeProgram.parameterAt(0);
        int secondAddress = intCodeProgram.parameterAt(1);
        int firstFactor = intCodeProgram.valueAt(firstAddress);
        int secondFactor = intCodeProgram.valueAt(secondAddress);
        int product = firstFactor * secondFactor;
        int destinationAddress = intCodeProgram.parameterAt(2);
        intCodeProgram.setValueAt(destinationAddress, product);
    }

}
