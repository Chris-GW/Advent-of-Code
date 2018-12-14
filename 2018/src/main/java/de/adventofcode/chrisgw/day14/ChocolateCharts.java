package de.adventofcode.chrisgw.day14;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ChocolateCharts {


    @Getter
    private int rounds = 0;

    @Getter
    private ChocolateElve firstElve;

    @Getter
    private ChocolateElve secondElve;

    private List<ChocolateRecipe> chocolateRecipes = new ArrayList<>();


    public ChocolateCharts() {
        ChocolateRecipe firstRecipe = new ChocolateRecipe(3);
        chocolateRecipes.add(firstRecipe);
        firstElve = new ChocolateElve(1, 0, firstRecipe);

        ChocolateRecipe secondRecipe = new ChocolateRecipe(7);
        chocolateRecipes.add(secondRecipe);
        secondElve = new ChocolateElve(2, 1, secondRecipe);
    }


    public List<ChocolateRecipe> makeRecipes(int neededRecipes) {
        while (needsMoreRecpies(neededRecipes)) {
            ChocolateRecipe firstRecipe = firstElve.getCurrentRecipe();
            ChocolateRecipe secondRecipe = secondElve.getCurrentRecipe();
            List<ChocolateRecipe> newCombinedRecipes = firstRecipe.combineRecipes(secondRecipe);
            for (int i = 0; i < newCombinedRecipes.size() && needsMoreRecpies(neededRecipes); i++) {
                chocolateRecipes.add(newCombinedRecipes.get(i));
            }

            firstElve.takeNextRecipe();
            secondElve.takeNextRecipe();
            rounds++;
        }
        return lastTenRecipes();
    }

    private boolean needsMoreRecpies(int neededRecipes) {
        return size() < neededRecipes + 10;
    }


    public List<ChocolateRecipe> lastTenRecipes() {
        if (size() == 0) {
            return Collections.emptyList();
        }
        int fromIndex = Math.max(0, size() - 10);
        int toIndex = size();
        return Collections.unmodifiableList(chocolateRecipes.subList(fromIndex, toIndex));
    }


    public int size() {
        return chocolateRecipes.size();
    }

    public ChocolateRecipe getRecipe(int index) {
        return chocolateRecipes.get(index);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size() * 3);
        for (int i = 0; i < chocolateRecipes.size(); i++) {
            if (firstElve.getCurrentRecipeIndex() == i) {
                ChocolateRecipe recipe = firstElve.getCurrentRecipe();
                sb.append("(").append(recipe.getQualityScore()).append(")");
            } else if (secondElve.getCurrentRecipeIndex() == i) {
                ChocolateRecipe recipe = secondElve.getCurrentRecipe();
                sb.append("[").append(recipe.getQualityScore()).append("]");
            } else {
                ChocolateRecipe recipe = getRecipe(i);
                sb.append(" ").append(recipe.getQualityScore()).append(" ");
            }
        }
        return sb.toString();
    }


    @Data
    @AllArgsConstructor
    @Setter(AccessLevel.PRIVATE)
    public class ChocolateElve {

        private final int id;
        private int currentRecipeIndex;

        private ChocolateRecipe currentRecipe;


        private void takeNextRecipe() {
            currentRecipeIndex += currentRecipe.getQualityScore() + 1;
            currentRecipeIndex %= size();
            currentRecipe = getRecipe(currentRecipeIndex);
        }

    }


    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage <neededRecipes>");
            return;
        }
        int neededRecipes = Integer.parseInt(args[0]);
        ChocolateCharts chocolateCharts = new ChocolateCharts();
        List<ChocolateRecipe> lastTenRecipes = chocolateCharts.makeRecipes(neededRecipes);

        String joinedLastTenRecipeStr = lastTenRecipes.stream()
                .map(ChocolateRecipe::toString)
                .collect(Collectors.joining(""));
        System.out.println("lastTenRecipes: " + joinedLastTenRecipeStr);
    }

}
