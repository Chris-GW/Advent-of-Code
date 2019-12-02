package de.adventofcode.chrisgw.day02;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class IntCodeProgram implements Iterator<IntCodeInstruction> {

    private final int[] initialState;
    private final Map<Integer, IntCodeInstruction> instructionSet;

    private int noun;
    private int verb;

    private int[] memory;
    private int instructionPointer;


    public IntCodeProgram(int[] initialState) {
        this(initialState, Set.of(new AddIntCodeInstruction(), new MulIntCodeInstruction()));
    }

    public IntCodeProgram(int[] initialState, Set<IntCodeInstruction> instructionSet) {
        this.initialState = Arrays.copyOf(initialState, initialState.length);
        this.instructionSet = instructionSet.stream()
                .collect(Collectors.toMap(IntCodeInstruction::opCode, Function.identity()));
        reset();
    }


    public void reset() {
        this.memory = Arrays.copyOf(initialState, initialState.length);
        this.instructionPointer = 0;
    }


    @Override
    public boolean hasNext() {
        return nextOpCode() != 99;
    }

    @Override
    public IntCodeInstruction next() {
        int opCode = nextOpCode();
        IntCodeInstruction intCodeInstruction = instructionSet.get(opCode);
        if (intCodeInstruction == null) {
            throw new IllegalArgumentException("Unknown opCode: " + opCode);
        }
        intCodeInstruction.execute(this);
        instructionPointer += intCodeInstruction.instructionSize();
        return intCodeInstruction;
    }


    private int nextOpCode() {
        return memory[instructionPointer];
    }


    public int parameterAt(int index) {
        int address = instructionPointer + 1 + index;
        return memory[address];
    }

    public int valueAt(int address) {
        return memory[address];
    }

    public void setValueAt(int address, int value) {
        memory[address] = value;
    }


    public int getNoun() {
        return noun;
    }

    public void setNoun(int noun) {
        this.noun = noun;
        this.memory[1] = noun;
    }


    public int getVerb() {
        return verb;
    }

    public void setVerb(int verb) {
        this.verb = verb;
        this.memory[2] = verb;
    }


    public int getOutput() {
        return memory[0];
    }


    public int[] getInitialState() {
        return Arrays.copyOf(initialState, initialState.length);
    }

    public int[] getMemory() {
        return Arrays.copyOf(memory, memory.length);
    }


    @Override
    public String toString() {
        return Arrays.toString(memory);
    }

}
