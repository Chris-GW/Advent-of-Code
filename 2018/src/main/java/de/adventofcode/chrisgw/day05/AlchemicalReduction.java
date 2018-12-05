package de.adventofcode.chrisgw.day05;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Collectors;


public class AlchemicalReduction {

    private LinkedList<PolymerUnit> polymerUnits = new LinkedList<>();

    public static AlchemicalReduction parsePolymerString(String polymerString) {
        AlchemicalReduction alchemicalReduction = new AlchemicalReduction();
        for (int i = 0; i < polymerString.length(); i++) {
            PolymerUnit polymerUnit = new PolymerUnit(polymerString.charAt(i));
            alchemicalReduction.polymerUnits.add(polymerUnit);
        }
        return alchemicalReduction;
    }


    public boolean triggerAllReactions() {
        if (polymerUnits.isEmpty()) {
            return false;
        }
        boolean hasTriggerdAnyReactions = false;

        for (int i = 1; i < polymerUnits.size(); i++) {
            PolymerUnit previousPolymerUnit = polymerUnits.get(i - 1);
            PolymerUnit currentPolymerUnit = polymerUnits.get(i);
            if (previousPolymerUnit.isReactingTo(currentPolymerUnit)) {
                polymerUnits.remove(i--);
                polymerUnits.remove(i--);
                hasTriggerdAnyReactions = true;
                if (i < 0) {
                    i = 0;
                }
            }
        }
        return hasTriggerdAnyReactions;
    }

    public int polymerLength() {
        return polymerUnits.size();
    }


    @Override
    public String toString() {
        return polymerUnits.stream().map(PolymerUnit::toString).collect(Collectors.joining(""));
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);

    }

}
