package de.adventofcode.chrisgw.day05;

import java.util.List;


public class CrateMover9000 extends CrateMover {


    public CrateMover9000(List<CrateStack> crateStacks) {
        super(crateStacks);
    }


    @Override
    public void runRearrangementProcedure(RearrangementProcedure rearrangementProcedure) {
        CrateStack fromStack = getStack(rearrangementProcedure.fromStackIndex());
        CrateStack toStack = getStack(rearrangementProcedure.toStackIndex());

        for (int i = 0; i < rearrangementProcedure.quantity(); i++) {
            StackableCargoCrate crate = fromStack.pollCrate();
            toStack.pushCrate(crate);
        }
    }


}
