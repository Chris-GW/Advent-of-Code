package de.adventofcode.chrisgw.day23.command;

import de.adventofcode.chrisgw.day23.CoprocessorCommand;
import de.adventofcode.chrisgw.day23.CoprocessorConflagration;
import de.adventofcode.chrisgw.day23.RegisterReference;


public class SubtractRegisterCoprocessorCommand implements CoprocessorCommand {

    private final RegisterReference targetRegister;
    private final RegisterReference subtrahendRegister;


    public SubtractRegisterCoprocessorCommand(RegisterReference targetRegister, RegisterReference subtrahendRegister) {
        this.targetRegister = targetRegister;
        this.subtrahendRegister = subtrahendRegister;
    }



    @Override
    public void executeCoprocessorCommand(CoprocessorConflagration coprocessor) {
        long oldRegisterValue = targetRegister.getValue(coprocessor);
        long newRegisterValue = oldRegisterValue - subtrahendRegister.getValue(coprocessor);
        coprocessor.setRegisterValue(targetRegister.getRegisterName(), newRegisterValue);
    }

    @Override
    public String toString(CoprocessorConflagration coprocessor) {
        return targetRegister.getValue(coprocessor) + " - " + subtrahendRegister.getValue(coprocessor);
    }

    @Override
    public String toString() {
        return "mod " + targetRegister + " " + subtrahendRegister;
    }

}
