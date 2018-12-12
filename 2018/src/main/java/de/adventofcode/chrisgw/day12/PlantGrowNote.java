package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.day12.SubterraneanSustainability.PlantPot;
import lombok.Value;


@Value
public class PlantGrowNote {

    private boolean[] plantPotPattern;
    private boolean resultIntoPotWithPlant;


    public static PlantGrowNote parsePlantGrowNote(String plantGrowNote) {
        String[] split = plantGrowNote.split("\\s*=>\\s*");
        String plantPotPatternStr = split[0];
        String resultIntoPotWithPlantStr = split[1];
        boolean resultIntoPotWithPlant = resultIntoPotWithPlantStr.equals("#");

        boolean[] plantPotPattern = new boolean[plantPotPatternStr.length()];
        for (int i = 0; i < plantPotPattern.length; i++) {
            plantPotPattern[i] = plantPotPatternStr.charAt(i) == '#';
        }
        return new PlantGrowNote(plantPotPattern, resultIntoPotWithPlant);
    }


    public boolean matchesPlantPot(PlantPot plantPot) {
        int halfPlantPotPatternLength = plantPotPattern.length / 2;
        for (int i = -halfPlantPotPatternLength; i <= halfPlantPotPatternLength; i++) {
            PlantPot relativePlantPot = plantPot.getPlantPotTo(i);
            if (!matchesPlantPots(relativePlantPot, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesPlantPots(PlantPot plantPot, int relativePlantPotIndex) {
        int halfPlantPotPatternLength = plantPotPattern.length / 2;
        boolean shouldHavePlantInPot = plantPotPattern[relativePlantPotIndex + halfPlantPotPatternLength];
        return shouldHavePlantInPot == plantPot.hasPlant();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean shouldHavePlant : plantPotPattern) {
            if (shouldHavePlant) {
                sb.append('#');
            } else {
                sb.append('.');
            }
        }
        sb.append(" => ");
        if (resultIntoPotWithPlant) {
            sb.append('#');
        } else {
            sb.append('.');
        }
        return sb.toString();
    }

}
