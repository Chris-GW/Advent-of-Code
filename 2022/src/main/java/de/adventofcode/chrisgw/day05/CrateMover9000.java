package de.adventofcode.chrisgw.day05;

public class CrateMover9000 extends CrateMover {


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
