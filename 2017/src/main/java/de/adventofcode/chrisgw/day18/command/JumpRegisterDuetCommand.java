package de.adventofcode.chrisgw.day18.command;

import de.adventofcode.chrisgw.day18.Duet;
import de.adventofcode.chrisgw.day18.DuetCommand;
import de.adventofcode.chrisgw.day18.RegisterReference;


public class JumpRegisterDuetCommand implements DuetCommand {

    private final RegisterReference targetRegister;
    private final RegisterReference jumpRegister;


    public JumpRegisterDuetCommand(RegisterReference targetRegister, RegisterReference jumpRegister) {
        this.targetRegister = targetRegister;
        this.jumpRegister = jumpRegister;
    }


    @Override
    public void executeDuetCommand(Duet duet) {
        int registerValue = targetRegister.getValue(duet);
        if (registerValue > 0) {
            int jump = jumpRegister.getValue(duet);
            duet.jumpInstruction(jump);
        }
    }

    @Override
    public String toString() {
        return "jgz " + targetRegister + " " + jumpRegister;
    }

}
