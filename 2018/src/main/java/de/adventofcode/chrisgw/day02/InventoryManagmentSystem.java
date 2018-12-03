package de.adventofcode.chrisgw.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class InventoryManagmentSystem {

    // part 01

    public static long calculateBoxChecksum(Path boxIdsFile) throws IOException {
        return calculateBoxChecksum(Files.readAllLines(boxIdsFile));
    }

    public static long calculateBoxChecksum(Collection<String> boxIds) {
        long twoLetterBoxIds = boxIds.stream().filter(InventoryManagmentSystem::isTwoLetterBoxId).count();
        long threeLetterBoxIds = boxIds.stream().filter(InventoryManagmentSystem::isThreeLetterBoxId).count();
        return twoLetterBoxIds * threeLetterBoxIds;
    }


    public static boolean isTwoLetterBoxId(String boxId) {
        return hasAnyLetterWithOccurrence(boxId, 2);
    }

    public static boolean isThreeLetterBoxId(String boxId) {
        return hasAnyLetterWithOccurrence(boxId, 3);
    }

    private static boolean hasAnyLetterWithOccurrence(String boxId, int desiredOccurrence) {
        Map<Integer, Long> letterOccurrence = boxId.chars()
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return letterOccurrence.values().stream().anyMatch(occurrence -> occurrence == desiredOccurrence);
    }


    // part 02


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path boxIdsFile = Paths.get(args[0]);
        long boxChecksum = calculateBoxChecksum(boxIdsFile);
        System.out.println("boxChecksum: " + boxChecksum);

    }


}
