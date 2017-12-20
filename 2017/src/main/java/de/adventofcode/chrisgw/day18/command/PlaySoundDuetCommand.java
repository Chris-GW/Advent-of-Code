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
        long registerValue = targetRegister.getValue(duet);
        if (duet.getPartnerDuet() != null) {
//            System.out.println("send: " + duet);
            duet.getPartnerDuet().playSound(registerValue); // send
        } else {
            duet.playSound(registerValue);
        }
    }

    @Override
    public String toString(Duet duet) {
        return targetRegister.getValue(duet) + "";
    }

    @Override
    public String toString() {
        return "snd " + targetRegister;
    }

}
