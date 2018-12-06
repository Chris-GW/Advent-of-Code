package de.adventofcode.chrisgw.day05;

import lombok.Value;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Value
public class AlchemicalReduction {

    private final List<PolymerUnit> polymerUnits;


    public AlchemicalReduction(List<PolymerUnit> polymerUnits) {
        this.polymerUnits = Collections.unmodifiableList(polymerUnits);
    }


    public static AlchemicalReduction parsePolymerString(String polymerString) {
        List<PolymerUnit> polymerUnits = new ArrayList<>(polymerString.length());
        for (int i = 0; i < polymerString.length(); i++) {
            PolymerUnit polymerUnit = new PolymerUnit(polymerString.charAt(i));
            polymerUnits.add(polymerUnit);
        }
        return new AlchemicalReduction(polymerUnits);
    }


    public AlchemicalReduction triggerAllReactions() {
        if (polymerUnits.isEmpty()) {
            return this;
        }

        List<PolymerUnit> polymerUnits = new ArrayList<>(this.polymerUnits);
        for (int i = 1; i < polymerUnits.size(); i++) {
            PolymerUnit previousPolymerUnit = polymerUnits.get(i - 1);
            PolymerUnit currentPolymerUnit = polymerUnits.get(i);
            if (previousPolymerUnit.isReactingTo(currentPolymerUnit)) {
                polymerUnits.remove(i--);
                polymerUnits.remove(i--);
                if (i < 0) {
                    i = 0;
                }
            }
        }
        return new AlchemicalReduction(polymerUnits);
    }


    public AlchemicalReduction triggerAllReactionsWithoutBlockingUnit() {
        return IntStream.rangeClosed('a', 'z')
                .mapToObj(PolymerUnit::new)
                .filter(polymerUnit -> polymerUnits.stream().anyMatch(polymerUnit::isSameType))
                .map(this::withoutUnit)
                .map(AlchemicalReduction::triggerAllReactions)
                .min(Comparator.comparingInt(AlchemicalReduction::polymerLength))
                .orElse(this);
    }

    private AlchemicalReduction withoutUnit(PolymerUnit blockingUnit) {
        List<PolymerUnit> polymerUnitsWithoutBlockingUnit = this.polymerUnits.stream()
                .filter(polymerUnit -> !polymerUnit.isSameType(blockingUnit))
                .collect(Collectors.toList());
        return new AlchemicalReduction(polymerUnitsWithoutBlockingUnit);
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
        String firstPolymerLine = Files.lines(puzzleInputFile).findFirst().orElse("");
        AlchemicalReduction alchemicalReduction = AlchemicalReduction.parsePolymerString(firstPolymerLine);
        System.out.println("read polymer string: " + alchemicalReduction);
        System.out.println("polymerLength: " + alchemicalReduction.polymerLength());

        AlchemicalReduction afterAllReactions = alchemicalReduction.triggerAllReactions();
        System.out.println("afterAllReactions:   " + afterAllReactions);
        System.out.println("polymerLength: " + afterAllReactions.polymerLength());

        AlchemicalReduction withoutBlockingUnit = alchemicalReduction.triggerAllReactionsWithoutBlockingUnit();
        System.out.println("withoutBlockingUnit: " + withoutBlockingUnit);
        System.out.println("polymerLength: " + withoutBlockingUnit.polymerLength());
    }

}
