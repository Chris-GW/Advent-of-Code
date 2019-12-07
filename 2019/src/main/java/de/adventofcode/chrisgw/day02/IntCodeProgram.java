package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.day05.*;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class IntCodeProgram implements Iterator<IntCodeInstruction> {

    public static final int PARAMETER_POSITION_MODE = 0;
    public static final int PARAMETER_IMMEDIATE_MODE = 1;
    private final int[] initialState;
    private final Map<Integer, IntCodeInstruction> instructionSet;

    private int noun;
    private int verb;
    private Deque<Integer> inputs = new ArrayDeque<>();
    private Deque<Integer> outputs = new ArrayDeque<>();

    private int[] memory;
    private boolean movedInstructionPointer;
    private int instructionPointer;


    public IntCodeProgram(int[] initialState) {
        this(initialState, Set.of(new AddIntCodeInstruction(), new MulIntCodeInstruction(), // day 02
                new InputCodeInstruction(), new OutputCodeInstruction(), // day 05 part 01
                new JumpIfTrueCodeInstruction(), new JumpIfFalseCodeInstruction(), //
                new LessThanCodeInstruction(), new EqualsCodeInstruction())); // day 05 part 02
    }

    public IntCodeProgram(int[] initialState, Collection<IntCodeInstruction> instructionSet) {
        this.initialState = Arrays.copyOf(initialState, initialState.length);
        this.instructionSet = instructionSet.stream()
                .collect(Collectors.toMap(IntCodeInstruction::opCode, Function.identity()));
        reset();
    }

    public IntCodeProgram(IntCodeProgram intCodeProgram) {
        this(intCodeProgram.initialState, intCodeProgram.instructionSet.values()); // day 07
    }

    public static IntCodeProgram parseIntCodeProgram(String intCodeProgramStr) {
        Pattern splitPattern = Pattern.compile(",");
        int[] initialState = splitPattern.splitAsStream(intCodeProgramStr).mapToInt(Integer::parseInt).toArray();
        return new IntCodeProgram(initialState);
    }


    public void reset() {
        this.memory = Arrays.copyOf(initialState, initialState.length);
        this.instructionPointer = 0;
        this.inputs.clear();
        this.outputs.clear();
    }


    @Override
    public boolean hasNext() {
        return nextOpCode() != 99;
    }

    @Override
    public IntCodeInstruction next() {
        int opCode = nextOpCode();
        int code = opCode % 100;
        IntCodeInstruction intCodeInstruction = instructionSet.get(code);
        if (intCodeInstruction == null) {
            throw new IllegalArgumentException("Unknown opCode: " + code);
        }

        movedInstructionPointer = false;
        intCodeInstruction.execute(this);
        if (!movedInstructionPointer) {
            instructionPointer += intCodeInstruction.instructionSize();
        }
        return intCodeInstruction;
    }

    public void run() {
        while (hasNext()) {
            next();
        }
    }


    private int nextOpCode() {
        return memory[instructionPointer];
    }


    public int parameterAt(int index) {
        int parameterMode = parameterModeAt(index);
        return parameterAt(index, parameterMode);
    }

    public int parameterAt(int index, int parameterMode) {
        int parameterIndex = instructionPointer + 1 + index;
        switch (parameterMode) {
        case PARAMETER_POSITION_MODE:
            int adress = valueAt(parameterIndex);
            return valueAt(adress);
        case PARAMETER_IMMEDIATE_MODE:
            return valueAt(parameterIndex);
        default:
            throw new RuntimeException("unknown parameterMode: " + parameterMode);
        }
    }


    private int parameterModeAt(int index) {
        String opCodeStr = String.valueOf(nextOpCode());
        int parameterModeIndex = opCodeStr.length() - 1 - 2 - index;
        if (parameterModeIndex < 0 || parameterModeIndex >= opCodeStr.length()) {
            return 0;
        }
        return opCodeStr.charAt(parameterModeIndex) - '0';
    }


    public void moveInstructionPointerTo(int instructionPointerDestination) {
        this.instructionPointer = instructionPointerDestination;
        this.movedInstructionPointer = true;
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


    public int getExitOutput() {
        return valueAt(0);
    }


    public int nextInput() {
        return inputs.removeFirst();
    }

    public void addInput(int input) {
        inputs.addLast(input);
    }


    public void addOutput(int output) {
        outputs.addLast(output);
    }

    public int nextOutput() {
        return outputs.removeFirst();
    }

    public int lastOutput() {
        return outputs.getLast();
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
