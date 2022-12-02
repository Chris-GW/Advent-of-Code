package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.ArrayList;
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
        return findMostTotalCalories(elfInventories);
    }

    private List<ElfInventory> parseElfInventoriesFromInput() {
        List<ElfInventory> elfInventories = new ArrayList<>();
        List<String> inventoryList = getInputLines();
        int inventoryStartIndex = 0;
        for (int i = 0; i < inventoryList.size(); i++) {
            String inventoryLine = inventoryList.get(i);
            if (StringUtils.isBlank(inventoryLine)) {
                List<String> subInventoryList = inventoryList.subList(inventoryStartIndex, i);
                ElfInventory elfInventory = ElfInventory.parseElfInventoryList(subInventoryList);
                elfInventories.add(elfInventory);
                inventoryStartIndex = i + 1;
            }
        }
        return elfInventories;
    }

    private static int findMostTotalCalories(List<ElfInventory> elfInventories) {
        return elfInventories.stream().mapToInt(ElfInventory::totalFoodCalories).max().orElse(0);
    }

    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }

}
