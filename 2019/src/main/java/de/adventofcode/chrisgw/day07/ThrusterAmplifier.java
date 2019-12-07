package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.day02.IntCodeProgram;
import lombok.Data;


@Data
public class ThrusterAmplifier {

    private final IntCodeProgram controllerSoftware;

    private int phaseSetting = 0;
    private int inputSignal = 0;


    public ThrusterAmplifier(IntCodeProgram controllerSoftware) {
        this.controllerSoftware = new IntCodeProgram(controllerSoftware);
    }


    public int runControllerSoftware() {
        controllerSoftware.reset();
        controllerSoftware.addInput(getPhaseSetting());
        controllerSoftware.addInput(getInputSignal());
        controllerSoftware.run();
        return controllerSoftware.lastOutput();
    }


}
