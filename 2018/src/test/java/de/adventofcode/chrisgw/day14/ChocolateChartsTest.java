package de.adventofcode.chrisgw.day14;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;


public class ChocolateChartsTest {


    @Test
    public void chocolateRecipes_till_9() {
        int neededRecipes = 9;
        List<ChocolateRecipe> expectedLastTenRecipesQuality = IntStream.of(5, 1, 5, 8, 9, 1, 6, 7, 7, 9)
                .mapToObj(ChocolateRecipe::of)
                .collect(Collectors.toList());

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        List<ChocolateRecipe> lastTenRecipes = chocolateCharts.makeRecipes(neededRecipes);
        assertEquals("lastRecipes", expectedLastTenRecipesQuality, lastTenRecipes);
    }


    @Test
    public void chocolateRecipes_till_5() {
        int neededRecipes = 5;
        List<ChocolateRecipe> expectedLastTenRecipesQuality = IntStream.of(0, 1, 2, 4, 5, 1, 5, 8, 9, 1)
                .mapToObj(ChocolateRecipe::of)
                .collect(Collectors.toList());

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        List<ChocolateRecipe> lastTenRecipes = chocolateCharts.makeRecipes(neededRecipes);
        assertEquals("lastRecipes", expectedLastTenRecipesQuality, lastTenRecipes);
    }


    @Test
    public void chocolateRecipes_till_18() {
        int neededRecipes = 18;
        List<ChocolateRecipe> expectedLastTenRecipesQuality = IntStream.of(9, 2, 5, 1, 0, 7, 1, 0, 8, 5)
                .mapToObj(ChocolateRecipe::of)
                .collect(Collectors.toList());

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        List<ChocolateRecipe> lastTenRecipes = chocolateCharts.makeRecipes(neededRecipes);
        assertEquals("lastRecipes", expectedLastTenRecipesQuality, lastTenRecipes);
    }


    @Test
    public void chocolateRecipes_till_2018() {
        int neededRecipes = 2018;
        List<ChocolateRecipe> expectedLastTenRecipesQuality = IntStream.of(5, 9, 4, 1, 4, 2, 9, 8, 8, 2)
                .mapToObj(ChocolateRecipe::of)
                .collect(Collectors.toList());

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        List<ChocolateRecipe> lastTenRecipes = chocolateCharts.makeRecipes(neededRecipes);
        assertEquals("lastRecipes", expectedLastTenRecipesQuality, lastTenRecipes);
    }


    @Test
    public void chocolateRecipes_till_myPuzzleInput_147061() {
        int neededRecipes = 147061;
        List<ChocolateRecipe> expectedLastTenRecipesQuality = IntStream.of(2, 1, 4, 5, 5, 8, 1, 1, 3, 1)
                .mapToObj(ChocolateRecipe::of)
                .collect(Collectors.toList());

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        List<ChocolateRecipe> lastTenRecipes = chocolateCharts.makeRecipes(neededRecipes);
        String joinedLastTenRecipeQuality = expectedLastTenRecipesQuality.stream()
                .map(ChocolateRecipe::toString)
                .collect(Collectors.joining(""));
        System.out.println("lastRecipes: " + joinedLastTenRecipeQuality);
        assertEquals("lastRecipes", expectedLastTenRecipesQuality, lastTenRecipes);
    }


    @Test
    public void makeRecipeTillReachingQualitySequence_till_51589() {
        List<Integer> neededQualitySequence = Arrays.asList(5, 1, 5, 8, 9);
        int expectedRecipesToLeftCount = 9;

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        int recipesToLeftCount = chocolateCharts.makeRecipeTillReachingQualitySequence(neededQualitySequence);
        assertEquals("recipesToLeftCount", expectedRecipesToLeftCount, recipesToLeftCount);
    }

    @Test
    public void makeRecipeTillReachingQualitySequence_till_01245() {
        List<Integer> neededQualitySequence = Arrays.asList(0, 1, 2, 4, 5);
        int expectedRecipesToLeftCount = 5;

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        int recipesToLeftCount = chocolateCharts.makeRecipeTillReachingQualitySequence(neededQualitySequence);
        assertEquals("recipesToLeftCount", expectedRecipesToLeftCount, recipesToLeftCount);
    }

    @Test
    public void makeRecipeTillReachingQualitySequence_till_92510() {
        List<Integer> neededQualitySequence = Arrays.asList(9, 2, 5, 1, 0);
        int expectedRecipesToLeftCount = 18;

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        int recipesToLeftCount = chocolateCharts.makeRecipeTillReachingQualitySequence(neededQualitySequence);
        assertEquals("recipesToLeftCount", expectedRecipesToLeftCount, recipesToLeftCount);
    }

    @Test
    public void makeRecipeTillReachingQualitySequence_till_59414() {
        List<Integer> neededQualitySequence = Arrays.asList(5, 9, 4, 1, 4);
        int expectedRecipesToLeftCount = 2018;

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        int recipesToLeftCount = chocolateCharts.makeRecipeTillReachingQualitySequence(neededQualitySequence);
        assertEquals("recipesToLeftCount", expectedRecipesToLeftCount, recipesToLeftCount);
    }


    @Test
    public void makeRecipeTillReachingQualitySequence_till_myPuzzleInput_147061() {
        List<Integer> neededQualitySequence = Arrays.asList(1, 4, 7, 0, 6, 1);
        int expectedRecipesToLeftCount = 20283721;

        ChocolateCharts chocolateCharts = new ChocolateCharts();
        int recipesToLeftCount = chocolateCharts.makeRecipeTillReachingQualitySequence(neededQualitySequence);
        assertEquals("recipesToLeftCount", expectedRecipesToLeftCount, recipesToLeftCount);
    }

}
