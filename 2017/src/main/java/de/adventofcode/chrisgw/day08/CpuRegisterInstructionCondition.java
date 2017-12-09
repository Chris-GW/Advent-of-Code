package de.adventofcode.chrisgw.day08;

import java.util.function.Predicate;


public class CpuRegisterInstructionCondition {

    private CpuRegister referenceCpuRegister;
    private Predicate<CpuRegister> condition;


    private CpuRegisterInstructionCondition(CpuRegister referenceCpuRegister, Predicate<CpuRegister> condition) {
        this.referenceCpuRegister = referenceCpuRegister;
        this.condition = condition;
    }


    public static CpuRegisterInstructionCondition greater(CpuRegister referenceCpuRegister, int value) {
        return new CpuRegisterInstructionCondition(referenceCpuRegister,
                (cpuRegister) -> cpuRegister.getValue() > value);
    }

    public static CpuRegisterInstructionCondition greaterEquals(CpuRegister referenceCpuRegister, int value) {
        return new CpuRegisterInstructionCondition(referenceCpuRegister,
                (cpuRegister) -> cpuRegister.getValue() >= value);
    }


    public static CpuRegisterInstructionCondition smaller(CpuRegister referenceCpuRegister, int value) {
        return new CpuRegisterInstructionCondition(referenceCpuRegister,
                (cpuRegister) -> cpuRegister.getValue() < value);
    }

    public static CpuRegisterInstructionCondition smallerEquals(CpuRegister referenceCpuRegister, int value) {
        return new CpuRegisterInstructionCondition(referenceCpuRegister,
                (cpuRegister) -> cpuRegister.getValue() <= value);
    }


    public static CpuRegisterInstructionCondition equals(CpuRegister referenceCpuRegister, int value) {
        return new CpuRegisterInstructionCondition(referenceCpuRegister,
                (cpuRegister) -> cpuRegister.getValue() == value);
    }

    public static CpuRegisterInstructionCondition notEquals(CpuRegister referenceCpuRegister, int value) {
        return new CpuRegisterInstructionCondition(referenceCpuRegister,
                (cpuRegister) -> cpuRegister.getValue() != value);
    }


    public boolean testInsturctionCondition() {
        return condition.test(referenceCpuRegister);
    }

    public CpuRegister getReferenceCpuRegister() {
        return referenceCpuRegister;
    }

}
