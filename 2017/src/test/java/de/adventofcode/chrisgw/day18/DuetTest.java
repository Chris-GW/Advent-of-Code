package de.adventofcode.chrisgw.day18;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DuetTest {


    @Test
    public void duetInstructions_part1_example() {
        // @formatter:off
        List<DuetCommand> duetCommands = Stream.of(
                "set a 1",
                "add a 2",
                "mul a a",
                "mod a 5",
                "snd a",
                "set a 0",
                "rcv a",
                "jgz a -1",
                "set a 1",
                "jgz a -2")
                .map(Duet::parseDuetCommand)
                .collect(Collectors.toList());
        // @formatter:on
        long expectedRecoverFrequency = 4;

        Duet duet = new Duet(duetCommands);
        for (int i = 0; duet.hasNextDuetCommand(); i++) {
            DuetCommand duetCommand = duet.nextDuetCommand();
            System.out.println(duetCommand);
            long registerValueA = duet.getRegisterValue('a');
            if (i == 0) {
                Assert.assertEquals("expect register value in step " + i, (1), registerValueA);
            } else if (i == 1) {
                Assert.assertEquals("expect register value in step " + i, (1 + 2), registerValueA);
            } else if (i == 2) {
                Assert.assertEquals("expect register value in step " + i, (3 * 3), registerValueA);
            } else if (i == 3) {
                Assert.assertEquals("expect register value in step " + i, (9 % 5), registerValueA);
            } else if (i == 5) {
                Assert.assertEquals("expect register value in step " + i, (0), registerValueA);
            }
        }
        Assert.assertFalse("no more commands", duet.hasNextDuetCommand());

        long recoverdFrequency = duet.getRecoveredFrequency();
        Assert.assertEquals("last recovered frequency", expectedRecoverFrequency, recoverdFrequency);
    }

    @Test
    public void duetInstructions_part1_myTask() {
        String[] splittedCommands = ("set i 31\n" + "set a 1\n" + "mul p 17\n" + "jgz p p\n" + "mul a 2\n"
                + "add i -1\n" + "jgz i -2\n" + "add a -1\n" + "set i 127\n" + "set p 735\n" + "mul p 8505\n"
                + "mod p a\n" + "mul p 129749\n" + "add p 12345\n" + "mod p a\n" + "set b p\n" + "mod b 10000\n"
                + "snd b\n" + "add i -1\n" + "jgz i -9\n" + "jgz a 3\n" + "rcv b\n" + "jgz b -1\n" + "set f 0\n"
                + "set i 126\n" + "rcv a\n" + "rcv b\n" + "set p a\n" + "mul p -1\n" + "add p b\n" + "jgz p 4\n"
                + "snd a\n" + "set a b\n" + "jgz 1 3\n" + "snd b\n" + "set f 1\n" + "add i -1\n" + "jgz i -11\n"
                + "snd a\n" + "jgz f -16\n" + "jgz a -19").split("\n");
        List<DuetCommand> duetCommands = Arrays.stream(splittedCommands)
                .map(Duet::parseDuetCommand)
                .collect(Collectors.toList());
        long expectedRecoverFrequency = 862;

        Duet duet = new Duet(duetCommands);
        for (int i = 0; duet.hasNextDuetCommand(); i++) {
            DuetCommand duetCommand = duet.nextDuetCommand();
            System.out.println(duet);
            System.out.println();
        }
        Assert.assertFalse("no more commands", duet.hasNextDuetCommand());

        long recoverdFrequency = duet.getRecoveredFrequency();
        Assert.assertEquals("last recovered frequency", expectedRecoverFrequency, recoverdFrequency);
    }

}