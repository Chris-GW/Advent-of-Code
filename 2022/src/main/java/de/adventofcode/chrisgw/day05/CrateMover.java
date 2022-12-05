package de.adventofcode.chrisgw.day05;

import java.util.ArrayList;
import java.util.List;


public abstract class CrateMover {

    private final List<CrateStack> crateStacks = new ArrayList<>();


    public void parseInitStacks(List<String> inputLines) {
        int columnWidth = "[C] ".length();

        for (String line : inputLines) {
            for (int stackIndex = 1, crateLetterIndex = 1; //
                 crateLetterIndex < line.length(); //
                 stackIndex++, crateLetterIndex += columnWidth) {
                char crateLetter = line.charAt(crateLetterIndex);
                if (Character.isSpaceChar(crateLetter)) {
                    continue;
                } else if (Character.isDigit(crateLetter)) {
                    return;
                }
                var crate = new StackableCargoCrate(crateLetter);
                getStack(stackIndex).addLastCrate(crate);
            }
        }
    }

    protected CrateStack getStack(int stackIndex) {
        for (int i = crateStacks.size(); i <= stackIndex; i++) {
            crateStacks.add(new CrateStack(i));
        }
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
