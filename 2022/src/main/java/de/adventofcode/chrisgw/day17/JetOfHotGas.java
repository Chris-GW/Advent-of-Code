package de.adventofcode.chrisgw.day17;

public record JetOfHotGas(int horizontalPushForce) {


    public static JetOfHotGas parseJetOfHotGasFrom(char pushCode) {
        if (pushCode == '>') {
            return new JetOfHotGas(1);
        } else if (pushCode == '<') {
            return new JetOfHotGas(-1);
        } else {
            throw new IllegalArgumentException("unknown pushCode: " + pushCode);
        }
    }


    @Override
    public String toString() {
        if (horizontalPushForce > 0) {
            return ">";
        } else {
            return "<";
        }
    }

}
