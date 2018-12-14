package de.adventofcode.chrisgw.day14;

import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Value
public class ChocolateRecipe {

    private final int qualityScore;
    private static List<ChocolateRecipe> chocolateRecipeCache = Collections.unmodifiableList(
            IntStream.range(0, 10).mapToObj(ChocolateRecipe::new).collect(Collectors.toList()));


    private ChocolateRecipe(int qualityScore) {
        this.qualityScore = qualityScore;
    }

    public static ChocolateRecipe of(int qualityScore) {
        return chocolateRecipeCache.get(qualityScore);
    }


    public List<ChocolateRecipe> combineRecipes(ChocolateRecipe otherRecipe) {
        int sum = this.qualityScore + otherRecipe.qualityScore;
        if (sum < 10) {
            ChocolateRecipe newRecipe = new ChocolateRecipe(sum);
            return Collections.singletonList(newRecipe);
        } else {
            int firstRecipeQuality = sum / 10;
            ChocolateRecipe firstNewRecipe = ChocolateRecipe.of(firstRecipeQuality);

            int secondRecipeQuality = sum % 10;
            ChocolateRecipe secondNewRecipe = ChocolateRecipe.of(secondRecipeQuality);
            return Arrays.asList(firstNewRecipe, secondNewRecipe);
        }
    }


    @Override
    public String toString() {
        return String.valueOf(qualityScore);
    }

}
