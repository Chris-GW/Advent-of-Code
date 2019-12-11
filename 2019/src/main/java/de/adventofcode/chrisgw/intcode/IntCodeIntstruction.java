package de.adventofcode.chrisgw.intcode;

import java.util.function.Consumer;


public enum IntCodeIntstruction {


    // day 02
    ADD(1, 4, IntCodeIntstruction::add), //
    MULITPLY(2, 4, IntCodeIntstruction::multiply), //
    // day 05 part 01
    INPUT(3, 2, IntCodeIntstruction::input), //
    OUTPUT(4, 2, IntCodeIntstruction::output), //
    JUMP_IF_TRUE(5, 3, IntCodeIntstruction::jumpIfTrue), //
    JUMP_IF_FALSE(6, 3, IntCodeIntstruction::jumpIfFalse), //
    // day 05 part 02
    LESS_THAN(7, 4, IntCodeIntstruction::lessThan), //
    EQUALS(8, 4, IntCodeIntstruction::equalsTo), //
    // day 09 part 01
    ADJUST_RELATIVE_BASE(9, 2, IntCodeIntstruction::adjustRelativeBase), //
    EXIT(99, 0, IntCodeProgram::finishProgram);

    private final int opCode;
    private final int instructionSize;
    private final Consumer<IntCodeProgram> execute;


    IntCodeIntstruction(int opCode, int instructionSize, Consumer<IntCodeProgram> execute) {
        this.opCode = opCode;
        this.instructionSize = instructionSize;
        this.execute = execute;
    }


    public int opCode() {
        return opCode;
    }

    public int instructionSize() {
        return instructionSize;
    }

    public void execute(IntCodeProgram intCodeProgram) {
        execute.accept(intCodeProgram);
    }


    private static void add(IntCodeProgram intCodeProgram) {
        long firstSummand = intCodeProgram.parameterAt(0);
        long secondSummand = intCodeProgram.parameterAt(1);
        long sum = firstSummand + secondSummand;
        int destinationAddress = intCodeProgram.parameterAddressAt(2);
        intCodeProgram.setValueAt(destinationAddress, sum);
    }

    private static void multiply(IntCodeProgram intCodeProgram) {
        long firstFactor = intCodeProgram.parameterAt(0);
        long secondFactor = intCodeProgram.parameterAt(1);
        long product = firstFactor * secondFactor;
        int destinationAddress = intCodeProgram.parameterAddressAt(2);
        intCodeProgram.setValueAt(destinationAddress, product);
    }

    private static void input(IntCodeProgram intCodeProgram) {
        long input = intCodeProgram.nextInput();
        int destinationAddress = intCodeProgram.parameterAddressAt(0);
        intCodeProgram.setValueAt(destinationAddress, input);
    }

    private static void output(IntCodeProgram intCodeProgram) {
        long output = intCodeProgram.parameterAt(0);
        intCodeProgram.addOutput(output);
    }

    private static void jumpIfTrue(IntCodeProgram intCodeProgram) {
        long testValue = intCodeProgram.parameterAt(0);
        if (testValue != 0) {
            int jumpDestination = (int) intCodeProgram.parameterAt(1);
            intCodeProgram.moveInstructionPointerTo(jumpDestination);
        }
    }

    private static void jumpIfFalse(IntCodeProgram intCodeProgram) {
        long testValue = intCodeProgram.parameterAt(0);
        if (testValue == 0) {
            int jumpDestination = (int) intCodeProgram.parameterAt(1);
            intCodeProgram.moveInstructionPointerTo(jumpDestination);
        }
    }

    private static void lessThan(IntCodeProgram intCodeProgram) {
        long firstValue = intCodeProgram.parameterAt(0);
        long secondValue = intCodeProgram.parameterAt(1);
        int destinationAddress = intCodeProgram.parameterAddressAt(2);
        if (firstValue < secondValue) {
            intCodeProgram.setValueAt(destinationAddress, 1);
        } else {
            intCodeProgram.setValueAt(destinationAddress, 0);
        }
    }

    private static void equalsTo(IntCodeProgram intCodeProgram) {
        long firstValue = intCodeProgram.parameterAt(0);
        long secondValue = intCodeProgram.parameterAt(1);
        int destinationAddress = intCodeProgram.parameterAddressAt(2);
        if (firstValue == secondValue) {
            intCodeProgram.setValueAt(destinationAddress, 1);
        } else {
            intCodeProgram.setValueAt(destinationAddress, 0);
        }
    }

    private static void adjustRelativeBase(IntCodeProgram intCodeProgram) {
        long parameterValue = intCodeProgram.parameterAt(0);
        intCodeProgram.adjustRelativeBase(parameterValue);
    }

}
