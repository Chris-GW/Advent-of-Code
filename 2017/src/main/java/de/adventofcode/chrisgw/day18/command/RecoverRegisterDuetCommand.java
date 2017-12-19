package de.adventofcode.chrisgw.day18.command;

import de.adventofcode.chrisgw.day18.Duet;
import de.adventofcode.chrisgw.day18.DuetCommand;
import de.adventofcode.chrisgw.day18.RegisterReference;


public class RecoverRegisterDuetCommand implements DuetCommand {

    private final RegisterReference targetRegister;


    public RecoverRegisterDuetCommand(RegisterReference targetRegister) {
        this.targetRegister = targetRegister;
    }


    @Override
    public void executeDuetCommand(Duet duet) {
        long registerValue = targetRegister.getValue(duet);
        if (registerValue != 0) {
            duet.addRecoveredFrequency();
        }
    }

    @Override
    public String toString(Duet duet) {
        return targetRegister.getValue(duet) + " != 0";
    }

    @Override
    public String toString() {
        return "rcv " + targetRegister;
    }

}
