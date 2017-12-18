package de.adventofcode.chrisgw.day18.command;

import de.adventofcode.chrisgw.day18.Duet;
import de.adventofcode.chrisgw.day18.DuetCommand;
import de.adventofcode.chrisgw.day18.RegisterReference;


public class AddRegisterDuetCommand implements DuetCommand {

    private final RegisterReference targetRegister;
    private final RegisterReference addendRegister;


    public AddRegisterDuetCommand(RegisterReference targetRegister, RegisterReference addendRegister) {
        this.targetRegister = targetRegister;
        this.addendRegister = addendRegister;
    }


    @Override
    public void executeDuetCommand(Duet duet) {
        int oldRegisterValue = targetRegister.getValue(duet);
        int newRegisterValue = oldRegisterValue + addendRegister.getValue(duet);
        duet.setRegisterValue(targetRegister.getRegisterName(), newRegisterValue);
    }

    @Override
    public String toString() {
        return "add " + targetRegister + " " + addendRegister;
    }

}
