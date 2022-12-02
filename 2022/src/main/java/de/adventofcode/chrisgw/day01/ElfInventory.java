package de.adventofcode.chrisgw.day01;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ElfInventory {

    private final List<FoodItem> foodItems;


    private ElfInventory(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public static ElfInventory parseElfInventoryList(List<String> elfInventoryLines) {
        List<FoodItem> foodItems = elfInventoryLines.stream()
                .mapToInt(Integer::parseInt)
                .mapToObj(FoodItem::new)
                .collect(Collectors.toList());
        return new ElfInventory(foodItems);
    }


    public Stream<FoodItem> foodItems() {
        return foodItems.stream();
    }

    public int totalFoodCalories() {
        return foodItems().mapToInt(FoodItem::calories).sum();
    }


    @Override
    public String toString() {
        return foodItems.toString();
    }

}
