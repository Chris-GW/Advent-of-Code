package de.adventofcode.chrisgw.day23.command;

import de.adventofcode.chrisgw.day23.CoprocessorCommand;
import de.adventofcode.chrisgw.day23.CoprocessorConflagration;
import de.adventofcode.chrisgw.day23.RegisterReference;


public class MultiplyRegisterCoprocessorCommand implements CoprocessorCommand {

    private final RegisterReference targetRegister;
    private final RegisterReference factorRegister;


    public MultiplyRegisterCoprocessorCommand(RegisterReference targetRegister, RegisterReference factorRegister) {
        this.targetRegister = targetRegister;
        this.factorRegister = factorRegister;
    }


    @Override
    public void executeCoprocessorCommand(CoprocessorConflagration coprocessor) {
        long oldRegisterValue = targetRegister.getValue(coprocessor);
        long factor = factorRegister.getValue(coprocessor);
        long newRegisterValue = oldRegisterValue * factor;
        coprocessor.setRegisterValue(targetRegister.getRegisterName(), newRegisterValue);
    }

    @Override
    public String toString(CoprocessorConflagration coprocessor) {
        return targetRegister.getValue(coprocessor) + " * " + factorRegister.getValue(coprocessor);
    }

    @Override
    public String toString() {
        return "mul " + targetRegister + " " + factorRegister;
    }

}
