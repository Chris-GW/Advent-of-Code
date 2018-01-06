package de.adventofcode.chrisgw.day23;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CoprocessorConflagrationTest {


    private String[] myTaskCommands = { //
            "set b 65", //
            "set c b", //
            "jnz a 2", //
            "jnz 1 5", //
            "mul b 100", //
            "sub b -100000", //
            "set c b", //
            "sub c -17000", //
            "set f 1", //
            "set d 2", //
            "set e 2", //
            "set g d", //
            "mul g e", //
            "sub g b", //
            "jnz g 2", //
            "set f 0", //
            "sub e -1", //
            "set g e", //
            "sub g b", //
            "jnz g -8", //
            "sub d -1", //
            "set g d", //
            "sub g b", //
            "jnz g -13", //
            "jnz f 2", //
            "sub h -1", //
            "set g b", //
            "sub g c", //
            "jnz g 2", //
            "jnz 1 3", //
            "sub b -17", //
            "jnz 1 -23" };


    @Test
    public void coprocessorConflagration_part1_myTask() {
        List<CoprocessorCommand> coprocessorCommands = Stream.of(myTaskCommands)
                .map(CoprocessorConflagration::parseCoprocessorCommand)
                .collect(Collectors.toList());
        long expectedExecutedMultiplyCommands = 3969;

        CoprocessorConflagration coprocessorConflagration = new CoprocessorConflagration(coprocessorCommands);
        for (int i = 0; coprocessorConflagration.hasNextCoprocessorCommand(); i++) {
            coprocessorConflagration.nextCoprocessorCommand();
        }

        long multiplyCommandCounter = coprocessorConflagration.getMultiplyCommandCounter();
        Assert.assertEquals("Expected executed multiply commands", expectedExecutedMultiplyCommands,
                multiplyCommandCounter);
    }

}