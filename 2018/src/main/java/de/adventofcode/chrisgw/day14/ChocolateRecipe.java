package de.adventofcode.chrisgw.day14;

import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Value
public class ChocolateRecipe {

    private final int qualityScore;


    public List<ChocolateRecipe> combineRecipes(ChocolateRecipe otherRecipe) {
        int sum = this.qualityScore + otherRecipe.qualityScore;
        if (sum < 10) {
            ChocolateRecipe newRecipe = new ChocolateRecipe(sum);
            return Collections.singletonList(newRecipe);
        } else {
            int firstRecipeQuality = sum / 10;
            ChocolateRecipe firstNewRecipe = new ChocolateRecipe(firstRecipeQuality);

            int secondRecipeQuality = sum % 10;
            ChocolateRecipe secondNewRecipe = new ChocolateRecipe(secondRecipeQuality);
            return Arrays.asList(firstNewRecipe, secondNewRecipe);
        }
    }


    @Override
    public String toString() {
        return String.valueOf(qualityScore);
    }

}
