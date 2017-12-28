package de.adventofcode.chrisgw.day24;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BridgeComponent {

    public static final Pattern COMPONENT_PATTERN = Pattern.compile("(\\d+)/(\\d+)");

    private int portA;
    private int portB;
    private boolean reversed = false;


    public BridgeComponent(int portA, int portB) {
        this.portA = portA;
        this.portB = portB;
    }

    public static BridgeComponent parseComponent(String componentLine) {
        Matcher componentMatcher = COMPONENT_PATTERN.matcher(componentLine);
        if (!componentMatcher.matches()) {
            throw new IllegalArgumentException("Expect component line with pattern: " + COMPONENT_PATTERN);
        }
        int portA = Integer.parseInt(componentMatcher.group(1));
        int portB = Integer.parseInt(componentMatcher.group(2));
        return new BridgeComponent(portA, portB);
    }


    public boolean canConnect(BridgeComponent bridgeComponent) {
        return getPortOut() == bridgeComponent.getPortIn();
    }


    public void reverse() {
        reversed = !reversed;
    }


    public int getPortIn() {
        if (reversed) {
            return portB;
        }
        return portA;
    }

    public int getPortOut() {
        if (reversed) {
            return portA;
        }
        return portB;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BridgeComponent))
            return false;

        BridgeComponent that = (BridgeComponent) o;
        return this.getPortIn() == that.getPortIn() && this.getPortOut() == that.getPortOut();
    }

    @Override
    public int hashCode() {
        int result = portA;
        result = 31 * result + portB;
        result = 31 * result + (reversed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return getPortIn() + "/" + getPortOut();
    }

}
