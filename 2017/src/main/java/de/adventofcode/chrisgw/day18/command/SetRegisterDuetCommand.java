package de.adventofcode.chrisgw.day18.command;

import de.adventofcode.chrisgw.day18.Duet;
import de.adventofcode.chrisgw.day18.DuetCommand;
import de.adventofcode.chrisgw.day18.RegisterReference;


public class SetRegisterDuetCommand implements DuetCommand {

    private final RegisterReference targetRegister;
    private final RegisterReference registerValueToSet;


    public SetRegisterDuetCommand(RegisterReference targetRegister, RegisterReference registerValueToSet) {
        this.targetRegister = targetRegister;
        this.registerValueToSet = registerValueToSet;
    }


    @Override
    public void executeDuetCommand(Duet duet) {
        long valueToSet = registerValueToSet.getValue(duet);
        duet.setRegisterValue(targetRegister.getRegisterName(), valueToSet);
    }

    @Override
    public String toString(Duet duet) {
        return targetRegister.getValue(duet) + " = " + registerValueToSet.getValue(duet);
    }

    @Override
    public String toString() {
        return "set " + targetRegister + " " + registerValueToSet;
    }

}
