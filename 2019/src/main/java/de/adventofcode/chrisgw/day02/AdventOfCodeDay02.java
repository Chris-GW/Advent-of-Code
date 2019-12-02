package de.adventofcode.chrisgw.day02;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * https://adventofcode.com/2019/day/2
 */
public class AdventOfCodeDay02 {

    private List<Integer> intCodeProgram;
    private int instructionPointer = 0;

    public AdventOfCodeDay02(String intCodeProgramStr) {
        Pattern splitPattern = Pattern.compile(",");
        intCodeProgram = splitPattern.splitAsStream(intCodeProgramStr)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


    public int restoreGravityAssistProgram() {
        intCodeProgram.set(1, 12);
        intCodeProgram.set(2, 2);
        while (!execute()) {
            // no-op
        }
        return getFirstValue();
    }


    public boolean execute() {
        IntCodeCommand intCodeCommand = new IntCodeCommand(instructionPointer);
        boolean finished = intCodeCommand.execute();
        instructionPointer += 4;
        return finished;
    }

    public int getFirstValue() {
        return intCodeProgram.get(0);
    }

    public List<Integer> getIntCodeProgram() {
        return new ArrayList<>(intCodeProgram);
    }

    @Override
    public String toString() {
        return intCodeProgram.toString();
    }


    @Data
    private class IntCodeCommand {

        private final int instructionPointer;


        int opCode() {
            return intCodeProgram.get(instructionPointer);
        }

        int firstOperand() {
            int index = intCodeProgram.get(instructionPointer + 1);
            return intCodeProgram.get(index);
        }

        int secondOperand() {
            int index = intCodeProgram.get(instructionPointer + 2);
            return intCodeProgram.get(index);
        }

        int destinationOperand() {
            return intCodeProgram.get(instructionPointer + 3);
        }


        public boolean execute() {
            int opCode = opCode();
            if (opCode == 99) {
                return true;
            }

            int first = firstOperand();
            int second = secondOperand();
            int destinaction = destinationOperand();
            if (opCode == 1) {
                int sum = first + second;
                intCodeProgram.set(destinaction, sum);
            } else if (opCode == 2) {
                int product = first * second;
                intCodeProgram.set(destinaction, product);
            } else {
                throw new IllegalArgumentException("Unknown opCode: " + opCode);
            }
            return false;
        }


    }

}
