package de.adventofcode.chrisgw.day18.command;

import de.adventofcode.chrisgw.day18.Duet;
import de.adventofcode.chrisgw.day18.DuetCommand;
import de.adventofcode.chrisgw.day18.RegisterReference;


public class MultiplyRegisterDuetCommand implements DuetCommand {

    private final RegisterReference targetRegister;
    private final RegisterReference factorRegister;


    public MultiplyRegisterDuetCommand(RegisterReference targetRegister, RegisterReference factorRegister) {
        this.targetRegister = targetRegister;
        this.factorRegister = factorRegister;
    }


    @Override
    public void executeDuetCommand(Duet duet) {
        long oldRegisterValue = targetRegister.getValue(duet);
        long factor = factorRegister.getValue(duet);
        long newRegisterValue = oldRegisterValue * factor;
        duet.setRegisterValue(targetRegister.getRegisterName(), newRegisterValue);
    }

    @Override
    public String toString(Duet duet) {
        return targetRegister.getValue(duet) + " * " + factorRegister.getValue(duet);
    }

    @Override
    public String toString() {
        return "mul " + targetRegister + " " + factorRegister;
    }

}
