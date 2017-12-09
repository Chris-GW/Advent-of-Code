package de.adventofcode.chrisgw.day08;

public class CpuRegister {

    private String name;
    private int value = 0;
    private int highestValue = Integer.MIN_VALUE;


    public CpuRegister(String name) {
        this.name = name;
    }


    public void incrementValue(int incrementValue) {
        this.value += incrementValue;
        checkHighestValue();
    }

    public void decrementValue(int decrementValue) {
        this.value -= decrementValue;
        checkHighestValue();
    }

    private void checkHighestValue() {
        if (value > highestValue) {
            highestValue = value;
        }
    }


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getHighestValue() {
        return highestValue;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CpuRegister that = (CpuRegister) o;

        if (getValue() != that.getValue())
            return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getValue();
        return result;
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }

}
