package de.adventofcode.chrisgw.day23;


public interface CoprocessorCommand {

    void executeCoprocessorCommand(CoprocessorConflagration coprocessor);

    String toString(CoprocessorConflagration coprocessor);

}
