package de.adventofcode.chrisgw.day05;

import lombok.Value;

import static java.lang.Character.*;


@Value
public class PolymerUnit {

    private final char value;


    public PolymerUnit(char value) {
        this.value = value;
    }

    public PolymerUnit(int value) {
        this((char) value);
    }


    public boolean isReactingTo(PolymerUnit otherPolymerUnit) {
        return isSameType(otherPolymerUnit) && isOppositeTo(otherPolymerUnit);
    }

    public boolean isSameType(PolymerUnit otherPolymerUnit) {
        return toLowerCase(this.value) == toLowerCase(otherPolymerUnit.value);
    }

    public boolean isOppositeTo(PolymerUnit otherPolymerUnit) {
        if (isLowerCase(this.value)) {
            return isUpperCase(otherPolymerUnit.value);
        } else {
            return isLowerCase(otherPolymerUnit.value);
        }
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
