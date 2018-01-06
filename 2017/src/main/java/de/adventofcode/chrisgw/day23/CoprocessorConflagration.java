package de.adventofcode.chrisgw.day23;


import de.adventofcode.chrisgw.day23.command.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <h1><a href="https://adventofcode.com/2017/day/23>Day 23: Coprocessor Conflagration</a></h1>
 */
public class CoprocessorConflagration {

    private Map<Character, Long> registerMap;

    private List<CoprocessorCommand> coprocessorCommands;
    private int commandPointer;

    private long multiplyCommandCounter;


    private List<Long> valuesOfRegisterH = new ArrayList<>();


    public CoprocessorConflagration(List<CoprocessorCommand> coprocessorCommands) {
        this.coprocessorCommands = new ArrayList<>(coprocessorCommands);
        this.registerMap = new HashMap<>();
        this.commandPointer = 0;
        this.multiplyCommandCounter = 0;
    }


    public void withDebug(boolean withDebug) {
        if (!withDebug) {
            setRegisterValue('a', 1);
        }
    }


    public static CoprocessorCommand parseCoprocessorCommand(String coprocessorCommandStr) {
        Matcher commandMatcher = getNewCommandPattern("set").matcher(coprocessorCommandStr);
        if (commandMatcher.matches()) {
            RegisterReference targetRegister = new RegisterReference(commandMatcher.group(1));
            RegisterReference registerValueToSet = new RegisterReference(commandMatcher.group(2));
            return new SetRegisterCoprocessorCommand(targetRegister, registerValueToSet);
        }

        commandMatcher = getNewCommandPattern("mul").matcher(coprocessorCommandStr);
        if (commandMatcher.matches()) {
            RegisterReference targetRegister = new RegisterReference(commandMatcher.group(1));
            RegisterReference factorRegister = new RegisterReference(commandMatcher.group(2));
            return new MultiplyRegisterCoprocessorCommand(targetRegister, factorRegister);
        }

        commandMatcher = getNewCommandPattern("sub").matcher(coprocessorCommandStr);
        if (commandMatcher.matches()) {
            RegisterReference targetRegister = new RegisterReference(commandMatcher.group(1));
            RegisterReference subtrahendRegister = new RegisterReference(commandMatcher.group(2));
            return new SubtractRegisterCoprocessorCommand(targetRegister, subtrahendRegister);
        }

        commandMatcher = getNewCommandPattern("jnz").matcher(coprocessorCommandStr);
        if (commandMatcher.matches()) {
            RegisterReference targetRegister = new RegisterReference(commandMatcher.group(1));
            RegisterReference jump = new RegisterReference(commandMatcher.group(2));
            return new JumpRegisterCoprocessorCommand(targetRegister, jump);
        }
        throw new IllegalArgumentException("Unknown CoprocessorCommand: " + coprocessorCommandStr);
    }

    private static Pattern getNewSimpleCommandPattern(String command) {
        return Pattern.compile(command + "\\s+(-?\\d+|[a-z])", Pattern.CASE_INSENSITIVE);
    }

    private static Pattern getNewCommandPattern(String command) {
        return Pattern.compile(command + "\\s+(-?\\d+|[a-z])\\s+(-?\\d+|[a-z])", Pattern.CASE_INSENSITIVE);
    }


    public long getRegisterValue(char register) {
        return registerMap.computeIfAbsent(register, (r) -> 0L);
    }

    public void setRegisterValue(char targetRegister, long registerValue) {
        registerMap.put(targetRegister, registerValue);
        if(targetRegister == 'h') {
            valuesOfRegisterH.add(registerValue);
        }
    }


    public void jumpInstruction(long jump) {
        commandPointer += jump - 1;
    }


    public CoprocessorCommand nextCoprocessorCommand() {
        CoprocessorCommand coprocessorCommand = coprocessorCommands.get(commandPointer++);
        coprocessorCommand.executeCoprocessorCommand(this);
        if (coprocessorCommand instanceof MultiplyRegisterCoprocessorCommand) {
            multiplyCommandCounter++;
        }
        return coprocessorCommand;
    }

    public boolean hasNextCoprocessorCommand() {
        return commandPointer < coprocessorCommands.size();
    }


    public long getMultiplyCommandCounter() {
        return multiplyCommandCounter;
    }


    public List<Long> getValuesOfRegisterH() {
        return valuesOfRegisterH;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        CoprocessorCommand currentCoprocessorCommand = coprocessorCommands.get(commandPointer);
        if (currentCoprocessorCommand instanceof JumpRegisterCoprocessorCommand) {
            int from = Math.max(0, commandPointer - 5);
            int to = commandPointer + 5;

            for (int i = from; i < coprocessorCommands.size() && i < to; i++) {
                CoprocessorCommand coprocessorCommand = coprocessorCommands.get(i);
                sb.append(i).append(": ");
                if (i == commandPointer) {
                    sb.append("[")
                            .append(coprocessorCommand)
                            .append(" = ")
                            .append(coprocessorCommand.toString(this))
                            .append("]");
                } else {
                    sb.append(coprocessorCommand);
                }
                sb.append("\n");
            }
        } else {
            sb.append(commandPointer).append(": ");
            sb.append(currentCoprocessorCommand).append(" = ").append(currentCoprocessorCommand.toString(this));
        }

        return sb.toString();
    }

}
