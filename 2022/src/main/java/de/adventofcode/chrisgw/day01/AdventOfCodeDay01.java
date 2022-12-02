package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * https://adventofcode.com/2022/day/1
 */
public class AdventOfCodeDay01 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay01(List<String> inputLines) {
        super(Year.of(2022), 1, inputLines);
    }

    public Integer solveFirstPart() {
        List<ElfInventory> elfInventories = parseElfInventoriesFromInput();
        return sumCaloriesTop3(elfInventories, 1);
    }


    public Integer solveSecondPart() {
        List<ElfInventory> elfInventories = parseElfInventoriesFromInput();
        return sumCaloriesTop3(elfInventories, 3);
    }


    private List<ElfInventory> parseElfInventoriesFromInput() {
        List<ElfInventory> elfInventories = new ArrayList<>();
        List<String> inventoryList = getInputLines();

        int inventoryStartIndex = 0;
        for (int i = 0; i < inventoryList.size(); i++) {
            if (i + 1 >= inventoryList.size() || StringUtils.isBlank(inventoryList.get(i + 1))) {
                List<String> subInventoryList = inventoryList.subList(inventoryStartIndex, i + 1);
                ElfInventory elfInventory = ElfInventory.parseElfInventoryList(subInventoryList);
                elfInventories.add(elfInventory);
                inventoryStartIndex = i + 2;
            }
        }
        return elfInventories;
    }

    private static int sumCaloriesTop3(List<ElfInventory> elfInventories, int top) {
        return elfInventories.stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(ElfInventory::totalFoodCalories)
                .limit(top)
                .sum();
    }

}
