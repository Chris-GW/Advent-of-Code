package de.adventofcode.chrisgw.day18.command;

import de.adventofcode.chrisgw.day18.Duet;
import de.adventofcode.chrisgw.day18.DuetCommand;
import de.adventofcode.chrisgw.day18.RegisterReference;


public class PlaySoundDuetCommand implements DuetCommand {

    private final RegisterReference targetRegister;


    public PlaySoundDuetCommand(RegisterReference targetRegister) {
        this.targetRegister = targetRegister;
    }


    @Override
    public void executeDuetCommand(Duet duet) {
        int registerValue = targetRegister.getValue(duet);
        duet.playSound(registerValue);
    }

    @Override
    public String toString() {
        return "snd " + targetRegister;
    }

}
