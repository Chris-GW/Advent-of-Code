package de.adventofcode.chrisgw.day23.command;

import de.adventofcode.chrisgw.day23.CoprocessorCommand;
import de.adventofcode.chrisgw.day23.CoprocessorConflagration;
import de.adventofcode.chrisgw.day23.RegisterReference;


public class SetRegisterCoprocessorCommand implements CoprocessorCommand {

    private final RegisterReference targetRegister;
    private final RegisterReference registerValueToSet;


    public SetRegisterCoprocessorCommand(RegisterReference targetRegister, RegisterReference registerValueToSet) {
        this.targetRegister = targetRegister;
        this.registerValueToSet = registerValueToSet;
    }


    @Override
    public void executeCoprocessorCommand(CoprocessorConflagration coprocessor) {
        long valueToSet = registerValueToSet.getValue(coprocessor);
        coprocessor.setRegisterValue(targetRegister.getRegisterName(), valueToSet);
    }

    @Override
    public String toString(CoprocessorConflagration coprocessor) {
        return targetRegister.getValue(coprocessor) + " = " + registerValueToSet.getValue(coprocessor);
    }

    @Override
    public String toString() {
        return "set " + targetRegister + " " + registerValueToSet;
    }

}
