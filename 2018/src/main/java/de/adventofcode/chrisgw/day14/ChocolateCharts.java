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
        ChocolateRecipe firstRecipe = ChocolateRecipe.of(3);
        chocolateRecipes.add(firstRecipe);
        firstElve = new ChocolateElve(1, 0, firstRecipe);

        ChocolateRecipe secondRecipe = ChocolateRecipe.of(7);
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
        return lastRecipes(10);
    }

    public int makeRecipeTillReachingQualitySequence(List<Integer> wantedQualitySequence) {
        while (needsMoreRecpies(wantedQualitySequence)) {
            ChocolateRecipe firstRecipe = firstElve.getCurrentRecipe();
            ChocolateRecipe secondRecipe = secondElve.getCurrentRecipe();
            List<ChocolateRecipe> newCombinedRecipes = firstRecipe.combineRecipes(secondRecipe);
            for (int i = 0; i < newCombinedRecipes.size() && needsMoreRecpies(wantedQualitySequence); i++) {
                chocolateRecipes.add(newCombinedRecipes.get(i));
            }

            firstElve.takeNextRecipe();
            secondElve.takeNextRecipe();
            rounds++;
        }
        return size() - wantedQualitySequence.size();
    }


    private boolean needsMoreRecpies(int neededRecipes) {
        return size() < neededRecipes + 10;
    }

    private boolean needsMoreRecpies(List<Integer> wantedQualitySequence) {
        if (size() < wantedQualitySequence.size()) {
            return true;
        }
        List<ChocolateRecipe> qualitySequence = lastRecipes(wantedQualitySequence.size());
        for (int i = 0; i < wantedQualitySequence.size() && i < qualitySequence.size(); i++) {
            int actualQuality = qualitySequence.get(i).getQualityScore();
            int wantedQuality = wantedQualitySequence.get(i);
            if (actualQuality != wantedQuality) {
                return true;
            }
        }
        return false;
    }


    public List<ChocolateRecipe> lastRecipes(int wantedRecipes) {
        if (size() == 0) {
            return Collections.emptyList();
        }
        int fromIndex = Math.max(0, size() - wantedRecipes);
        int toIndex = size();
        return chocolateRecipes.subList(fromIndex, toIndex);
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
        System.out.println("lastRecipes: " + joinedLastTenRecipeStr);
    }

}
