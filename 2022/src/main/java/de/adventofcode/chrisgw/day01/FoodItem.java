package de.adventofcode.chrisgw.day01;

public record FoodItem(int calories) implements Comparable<FoodItem> {


    @Override
    public int compareTo(FoodItem otherFoodItem) {
        return Integer.compare(this.calories(), otherFoodItem.calories());
    }

    @Override
    public String toString() {
        return String.valueOf(calories);
    }

}
