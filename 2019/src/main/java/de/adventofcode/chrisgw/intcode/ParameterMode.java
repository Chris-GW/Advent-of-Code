package de.adventofcode.chrisgw.intcode;

public enum ParameterMode {

    POSITION_MODE(0), //
    IMMEDIATE_MODE(1), //
    RELATIVE_MODE(2);


    private int code;


    ParameterMode(int parameterModeCode) {
        this.code = parameterModeCode;
    }

    public static ParameterMode forCode(int parameterModeCode) {
        for (ParameterMode parameterMode : ParameterMode.values()) {
            if (parameterMode.getCode() == parameterModeCode) {
                return parameterMode;
            }
        }
        throw new IllegalArgumentException("Unknown parameter mode code: " + parameterModeCode);
    }


    public int getCode() {
        return code;
    }

}
