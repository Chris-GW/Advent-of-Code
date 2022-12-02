package de.adventofcode.chrisgw.day01;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;


public class ElfInventory {

    private final List<FoodItem> foodItems;


    private ElfInventory(List<FoodItem> foodItems) {
        this.foodItems = requireNonNull(foodItems);
    }

    public static ElfInventory parseElfInventoryList(List<String> elfInventoryLines) {
        List<FoodItem> foodItems = elfInventoryLines.stream()
                .mapToInt(Integer::parseInt)
                .mapToObj(FoodItem::new)
                .toList();
        return new ElfInventory(foodItems);
    }


    public Stream<FoodItem> foodItems() {
        return foodItems.stream();
    }

    public int totalFoodCalories() {
        return foodItems().mapToInt(FoodItem::calories).sum();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElfInventory that)) {
            return false;
        }
        return new EqualsBuilder().append(foodItems, that.foodItems).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(foodItems).toHashCode();
    }

    @Override
    public String toString() {
        return foodItems.toString();
    }

}
