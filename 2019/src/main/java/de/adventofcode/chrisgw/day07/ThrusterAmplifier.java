package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;


public class ThrusterAmplifier {

    private final IntCodeProgram controllerSoftware;
    private ThrusterAmplifier outputAmplifier;


    public ThrusterAmplifier(IntCodeProgram controllerSoftware) {
        this.controllerSoftware = new IntCodeProgram(controllerSoftware);
    }

    public void withPhaseSetting(long phaseSetting) {
        controllerSoftware.reset();
        controllerSoftware.addInput(phaseSetting);
    }


    public long runControllerSoftware() {
        controllerSoftware.run();
        long outputSignal = outputSignal();
        if (outputAmplifier != null) {
            outputAmplifier.addInputSignal(outputSignal);
        }
        return outputSignal;
    }


    public void addInputSignal(long inputSignal) {
        controllerSoftware.addInput(inputSignal);
    }

    public long outputSignal() {
        return controllerSoftware.lastOutput();
    }


    public void connectOutputTo(ThrusterAmplifier thrusterAmplifier) {
        this.outputAmplifier = thrusterAmplifier;
    }


}
