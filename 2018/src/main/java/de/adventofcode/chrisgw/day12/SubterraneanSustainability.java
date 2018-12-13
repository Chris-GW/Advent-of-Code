package de.adventofcode.chrisgw.day12;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day12.PlantGrowNote.parsePlantGrowNote;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubterraneanSustainability {

    private int generation = 0;

    private int plantPotZeroIndex = 0;
    private List<PlantPot> plantPots = new ArrayList<>();

    private List<PlantGrowNote> plantGrowNotes = new ArrayList<>();


    public static SubterraneanSustainability parseSubterraneanSustainability(List<String> descriptionLines) {
        SubterraneanSustainability plantPotField = new SubterraneanSustainability();
        String initalPlantPotLine = descriptionLines.get(0);
        initalPlantPotLine = initalPlantPotLine.substring("initial state: ".length());
        plantPotField.plantPots = parseInitialPlantPots(plantPotField, initalPlantPotLine);

        List<String> plantGrowNotes = descriptionLines.subList(2, descriptionLines.size());
        plantPotField.plantGrowNotes = parsePlantGrowNotes(plantGrowNotes);
        return plantPotField;
    }

    private static List<PlantPot> parseInitialPlantPots(SubterraneanSustainability plantPotField,
            String initalPlantPotLine) {
        List<PlantPot> initalPlantPots = new ArrayList<>(initalPlantPotLine.length());
        for (int i = 0; i < initalPlantPotLine.length(); i++) {
            boolean hasPlant = initalPlantPotLine.charAt(i) == '#';
            PlantPot plantPot = plantPotField.new PlantPot(i, hasPlant);
            initalPlantPots.add(plantPot);
        }
        return initalPlantPots;
    }

    private static List<PlantGrowNote> parsePlantGrowNotes(List<String> plantGrowNotesStr) {
        List<PlantGrowNote> plantGrowNotes = new ArrayList<>(plantGrowNotesStr.size());
        for (String plantGrowTransitionNoteStr : plantGrowNotesStr) {
            PlantGrowNote plantGrowNote = parsePlantGrowNote(plantGrowTransitionNoteStr);
            plantGrowNotes.add(plantGrowNote);
        }
        return plantGrowNotes;
    }


    public SubterraneanSustainability nextGeneration() {
        SubterraneanSustainability newPlantPotField = new SubterraneanSustainability();
        newPlantPotField.generation = this.generation + 1;
        newPlantPotField.plantGrowNotes = this.plantGrowNotes;
        newPlantPotField.plantPots = collectNewGenerationPlantPots(newPlantPotField);
        return newPlantPotField;
    }

    private List<PlantPot> collectNewGenerationPlantPots(SubterraneanSustainability plantPotField) {
        List<PlantPot> newGenerationPlantPots = new ArrayList<>(plantPotFieldSize() + 10);
        int firstPlantPotNumber = plantPots.get(0).getNumber();
        List<PlantPot> newNegativePlantPots = IntStream.range(firstPlantPotNumber - 5, firstPlantPotNumber)
                .mapToObj(number -> plantPotField.new PlantPot(number))
                .collect(Collectors.toList());
        newGenerationPlantPots.addAll(newNegativePlantPots);
        plantPotField.plantPotZeroIndex = plantPotZeroIndex + newNegativePlantPots.size();

        List<PlantPot> newPlantPots = plantPots.stream().map(plantPot -> {
            int number = plantPot.number;
            boolean hasPlant = plantPot.willHavePlantInNextGeneration();
            return plantPotField.new PlantPot(number, hasPlant);
        }).collect(Collectors.toList());
        newGenerationPlantPots.addAll(newPlantPots);

        int lastPlantPotNumber = plantPots.get(plantPots.size() - 1).getNumber();
        List<PlantPot> newPositivePlantPot = IntStream.range(lastPlantPotNumber + 1, lastPlantPotNumber + 6)
                .mapToObj(number -> plantPotField.new PlantPot(number))
                .collect(Collectors.toList());
        newGenerationPlantPots.addAll(newPositivePlantPot);
        return newGenerationPlantPots;
    }


    public long getTotalPotWithPlantesSum() {
        return allPlantPots().filter(PlantPot::hasPlant).mapToInt(PlantPot::getNumber).sum();
    }


    private PlantPot plantPotAt(int potNumber) {
        int potIndex = potNumber + plantPotZeroIndex;
        if (0 <= potIndex && potIndex < plantPots.size()) {
            PlantPot plantPot = plantPots.get(potIndex);
            if (plantPot.getNumber() != potNumber) {
                throw new IllegalArgumentException();
            }
            return plantPot;
        } else {
            return new PlantPot(potNumber);
        }
    }


    private Stream<PlantPot> allPlantPots() {
        return plantPots.stream();
    }

    public int plantPotFieldSize() {
        return plantPots.size();
    }


    public int getGeneration() {
        return generation;
    }


    public class PlantPot {

        private int number;
        private boolean hasPlant;


        public PlantPot(int number) {
            this(number, false);
        }

        public PlantPot(int number, boolean hasPlant) {
            this.number = number;
            this.hasPlant = hasPlant;
        }


        public int getNumber() {
            return number;
        }

        public boolean hasPlant() {
            return hasPlant;
        }


        public boolean willHavePlantInNextGeneration() {
            Optional<PlantGrowNote> matchingPlantGrowNote = plantGrowNotes.stream()
                    .filter(plantGrowNote -> plantGrowNote.matchesPlantPot(this))
                    .findFirst();

            if (number == -1) {
                StringBuilder debug = new StringBuilder();
                debug.append(StringUtils.leftPad("", Math.abs(number) + "  1:  ".length()));
                for (int i = -2; i <= 2; i++) {
                    PlantPot plantPotTo = getPlantPotTo(i);
                    debug.append(plantPotTo.hasPlant() ? "#" : ".");
                }
                System.out.println(debug + " <=> " + matchingPlantGrowNote.map(PlantGrowNote::toString).orElse(""));
            }
            return matchingPlantGrowNote.map(PlantGrowNote::isResultIntoPotWithPlant).orElse(false);
        }


        PlantPot getPlantPotTo(int relativePlantPotIndex) {
            return plantPotAt(number + relativePlantPotIndex);
        }

        @Override
        public String toString() {
            String plantStr = hasPlant ? "#" : ".";
            return String.format("(%3d) %s", number, plantStr);
        }

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(6 + plantPotFieldSize());
        sb.append(String.format("%3d: ", generation));
        for (int number = -3; number <= 35; number++) {
            PlantPot plantPot = plantPotAt(number);
            sb.append(plantPot.hasPlant ? "#" : ".");
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage <gridSerialNumber> <fuelCellGridSize>");
            return;
        }
        Path inputFile = Paths.get(args[0]);
        SubterraneanSustainability plantPotField = SubterraneanSustainability.parseSubterraneanSustainability(
                Files.readAllLines(inputFile));

        for (int gen = 0; gen < 20; gen++) {
            System.out.println(plantPotField);
            plantPotField = plantPotField.nextGeneration();
        }

        long totalPotWithPlantesSum = plantPotField.getTotalPotWithPlantesSum();
        System.out.println("totalPotWithPlantesSum: " + totalPotWithPlantesSum);
    }

}
