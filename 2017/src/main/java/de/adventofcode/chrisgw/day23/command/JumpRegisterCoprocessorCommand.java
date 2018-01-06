package de.adventofcode.chrisgw.day23.command;

import de.adventofcode.chrisgw.day23.CoprocessorCommand;
import de.adventofcode.chrisgw.day23.CoprocessorConflagration;
import de.adventofcode.chrisgw.day23.RegisterReference;


public class JumpRegisterCoprocessorCommand implements CoprocessorCommand {

    private final RegisterReference targetRegister;
    private final RegisterReference jumpRegister;


    public JumpRegisterCoprocessorCommand(RegisterReference targetRegister, RegisterReference jumpRegister) {
        this.targetRegister = targetRegister;
        this.jumpRegister = jumpRegister;
    }


    @Override
    public void executeCoprocessorCommand(CoprocessorConflagration coprocessor) {
        long registerValue = targetRegister.getValue(coprocessor);
        if (registerValue != 0) {
            long jump = jumpRegister.getValue(coprocessor);
            coprocessor.jumpInstruction(jump);
        }
    }

    @Override
    public String toString(CoprocessorConflagration coprocessor) {
        return targetRegister.getValue(coprocessor) + " -> " + jumpRegister.getValue(coprocessor);
    }

    @Override
    public String toString() {
        return "jgz " + targetRegister + " " + jumpRegister;
    }

}
