package de.adventofcode.chrisgw.day05;

import java.util.ArrayDeque;
import java.util.Deque;


public class CrateMover9001 extends CrateMover {


    @Override
    public void runRearrangementProcedure(RearrangementProcedure rearrangementProcedure) {
        int quantity = rearrangementProcedure.quantity();
        CrateStack toStack = getStack(rearrangementProcedure.toStackIndex());
        CrateStack fromStack = getStack(rearrangementProcedure.fromStackIndex());
        Deque<StackableCargoCrate> pulledCrates = new ArrayDeque<>(quantity);

        for (int i = 0; i < quantity; i++) {
            var crate = fromStack.pollCrate();
            pulledCrates.addFirst(crate);
        }

        for (int i = 0; i < quantity; i++) {
            var crate = pulledCrates.poll();
            toStack.pushCrate(crate);
        }
    }

}
