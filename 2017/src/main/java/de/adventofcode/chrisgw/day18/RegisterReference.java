package de.adventofcode.chrisgw.day18;

public class RegisterReference {

    public static final String REGISTER_REFERENCE_PATTERN = "((\\-?\\d+)|([a-z]))";

    private String registerReference;


    public RegisterReference(String registerReference) {
        this.registerReference = registerReference;
    }


    public long getValue(Duet duet) {
        if (isStaticValue()) {
            return Long.parseLong(registerReference);
        } else {
            return duet.getRegisterValue(getRegisterName());
        }
    }


    public char getRegisterName() {
        return registerReference.charAt(0);
    }

    public boolean isStaticValue() {
        return registerReference.charAt(0) < 'A';
    }


    @Override
    public String toString() {
        return registerReference;
    }

}
