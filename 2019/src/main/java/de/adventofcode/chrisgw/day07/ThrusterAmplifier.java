package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.day02.IntCodeProgram;


public class ThrusterAmplifier {

    private final IntCodeProgram controllerSoftware;
    private ThrusterAmplifier outputAmplifier;


    public ThrusterAmplifier(IntCodeProgram controllerSoftware) {
        this.controllerSoftware = new IntCodeProgram(controllerSoftware);
    }

    public void withPhaseSetting(int phaseSetting) {
        controllerSoftware.reset();
        controllerSoftware.addInput(phaseSetting);
    }


    public int runControllerSoftware() {
        controllerSoftware.run();
        int outputSignal = outputSignal();
        if (outputAmplifier != null) {
            outputAmplifier.addInputSignal(outputSignal);
        }
        return outputSignal;
    }


    public void addInputSignal(int inputSignal) {
        controllerSoftware.addInput(inputSignal);
    }

    public int outputSignal() {
        return controllerSoftware.lastOutput();
    }


    public void connectOutputTo(ThrusterAmplifier thrusterAmplifier) {
        this.outputAmplifier = thrusterAmplifier;
    }


}
