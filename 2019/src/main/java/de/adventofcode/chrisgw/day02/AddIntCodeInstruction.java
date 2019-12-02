package de.adventofcode.chrisgw.day02;

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
        int firstAddress = intCodeProgram.parameterAt(0);
        int secondAddress = intCodeProgram.parameterAt(1);
        int firstSummand = intCodeProgram.valueAt(firstAddress);
        int secondSummand = intCodeProgram.valueAt(secondAddress);
        int sum = firstSummand + secondSummand;
        int destinationAddress = intCodeProgram.parameterAt(2);
        intCodeProgram.setValueAt(destinationAddress, sum);
    }

}
