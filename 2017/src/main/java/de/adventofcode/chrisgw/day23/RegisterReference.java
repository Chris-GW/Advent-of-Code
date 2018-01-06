package de.adventofcode.chrisgw.day23;


public class RegisterReference {

    private String registerReference;


    public RegisterReference(String registerReference) {
        this.registerReference = registerReference;
    }


    public long getValue(CoprocessorConflagration coprocessor) {
        if (isStaticValue()) {
            return Long.parseLong(registerReference);
        } else {
            return coprocessor.getRegisterValue(getRegisterName());
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
