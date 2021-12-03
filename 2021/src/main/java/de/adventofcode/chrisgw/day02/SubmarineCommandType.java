package de.adventofcode.chrisgw.day02;

import lombok.Getter;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;


public enum SubmarineCommandType {

    FORWARD(0, 1), //
    UP(-1, 0), //
    DOWN(1, 0);


    @Getter
    final int deltaDepth;

    @Getter
    final int deltaHorizontal;


    SubmarineCommandType(int deltaDepth, int deltaHorizontal) {
        this.deltaDepth = deltaDepth;
        this.deltaHorizontal = deltaHorizontal;
    }

    public static SubmarineCommandType parse(String commandTypeString) {
        for (SubmarineCommandType submarineCommandType : SubmarineCommandType.values()) {
            if (equalsIgnoreCase(submarineCommandType.name(), commandTypeString)) {
                return submarineCommandType;
            }
        }
        throw new IllegalArgumentException("unknown SubmarineCommandType: " + commandTypeString);
    }


}
