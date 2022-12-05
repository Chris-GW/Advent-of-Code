package de.adventofcode.chrisgw.day05;

import java.util.List;

import static java.util.Objects.requireNonNull;


public abstract class CrateMover {

    private final List<CrateStack> crateStacks;


    protected CrateMover(List<CrateStack> crateStacks) {
        this.crateStacks = requireNonNull(crateStacks);
    }

    protected CrateStack getStack(int stackIndex) {
        return crateStacks.get(stackIndex);
    }


    public String topCrateCodes() {
        return crateStacks.stream()
                .skip(1)
                .map(CrateStack::peekCrate)
                .map(StackableCargoCrate::crateCode)
                .reduce("", (codeString, crateCode) -> codeString + crateCode, String::concat);
    }

    public abstract void runRearrangementProcedure(RearrangementProcedure rearrangementProcedure);


}
