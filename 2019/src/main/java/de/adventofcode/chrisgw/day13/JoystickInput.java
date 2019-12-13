package de.adventofcode.chrisgw.day13;

public enum JoystickInput {

    TILTED_LEFT(-1), //
    NEUTRAL_POSITION(0), //
    TILTED_RIGHT(1); //


    private final int code;

    JoystickInput(int code) {this.code = code;}

    public static JoystickInput fromCode(int code) {
        for (JoystickInput joystickInput : JoystickInput.values()) {
            if (joystickInput.getCode() == code) {
                return joystickInput;
            }
        }
        throw new IllegalArgumentException("Unknown joystickInput code: " + code);
    }


    public int getCode() {
        return code;
    }

}
