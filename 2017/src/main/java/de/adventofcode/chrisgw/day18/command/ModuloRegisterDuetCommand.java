package de.adventofcode.chrisgw.day18.command;

import de.adventofcode.chrisgw.day18.Duet;
import de.adventofcode.chrisgw.day18.DuetCommand;
import de.adventofcode.chrisgw.day18.RegisterReference;


public class ModuloRegisterDuetCommand implements DuetCommand {

    private final RegisterReference targetRegister;
    private final RegisterReference quotientRegister;


    public ModuloRegisterDuetCommand(RegisterReference targetRegister, RegisterReference quotientRegister) {
        this.targetRegister = targetRegister;
        this.quotientRegister = quotientRegister;
    }



    @Override
    public void executeDuetCommand(Duet duet) {
        int oldRegisterValue = targetRegister.getValue(duet);
        int newRegisterValue = oldRegisterValue % quotientRegister.getValue(duet);
        duet.setRegisterValue(targetRegister.getRegisterName(), newRegisterValue);
    }

    @Override
    public String toString() {
        return "mod " + targetRegister + " " + quotientRegister;
    }

}
