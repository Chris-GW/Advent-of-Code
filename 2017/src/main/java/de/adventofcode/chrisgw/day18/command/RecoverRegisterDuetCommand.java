package de.adventofcode.chrisgw.day18.command;

import de.adventofcode.chrisgw.day18.Duet;
import de.adventofcode.chrisgw.day18.DuetCommand;
import de.adventofcode.chrisgw.day18.RegisterReference;


public class RecoverRegisterDuetCommand implements DuetCommand {

    private final RegisterReference targetRegister;


    public RecoverRegisterDuetCommand(RegisterReference targetRegister) {
        this.targetRegister = targetRegister;
    }


    @Override
    public void executeDuetCommand(Duet duet) {
        if (duet.getPartnerDuet() != null && duet.canRecoverFrequency()) {
            long registerValue = duet.recoverFrequency();
            duet.setRegisterValue(targetRegister.getRegisterName(), registerValue);
            duet.setLocked(false);
//            System.out.println("unlock: " + duet);
        } else if (duet.getPartnerDuet() != null) {
            duet.jumpInstruction(0); // try to repeat recover command next time
//            if (!duet.isLocked()) {
//                System.out.println("locked: " + duet);
//            }
            duet.setLocked(true);
        } else if (targetRegister.getValue(duet) != 0) {
            duet.recoverFrequency();
        }
    }

    @Override
    public String toString(Duet duet) {
        return targetRegister.getValue(duet) + " != 0";
    }

    @Override
    public String toString() {
        return "rcv " + targetRegister;
    }

}
