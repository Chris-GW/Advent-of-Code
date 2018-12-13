package de.adventofcode.chrisgw.day12;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Day12 {

    List<String> input;
    Set<String> growPatterns;
    int zeroPlantPotIndex;

    String plantPotStr;

    public Day12(List<String> input) {
        this.input = input;
    }

    public Integer part1() {
        System.out.printf("%3d: ", 0);
        StringBuilder debug = new StringBuilder("...");
        for (int number = 0; number <= 35; number++) {
            debug.append(plantPotStr.charAt(number + zeroPlantPotIndex));
        }
        System.out.println(debug);

        for (int geneation = 0; geneation < 20; geneation++) {
            String currentPlantPot = "....." + plantPotStr + "...";
            String next = "";
            zeroPlantPotIndex += 3;
            for (int j = 2; j < currentPlantPot.length() - 2; j++) {
                if (growPatterns.contains(currentPlantPot.substring(j - 2, j + 3))) {
                    next += "#";
                } else {
                    next += ".";
                }
            }
            plantPotStr = next;

            System.out.printf("%3d: ", geneation + 1);
            debug = new StringBuilder();
            for (int number = -3; number <= 35; number++) {
                debug.append(plantPotStr.charAt(number + zeroPlantPotIndex));
            }
            System.out.println(debug);
        }
        int sum = 0;
        for (int i = 0; i < plantPotStr.length(); i++) {
            sum += plantPotStr.charAt(i) == '#' ? i - zeroPlantPotIndex : 0;
        }
        return sum;
    }

    public Object part2() {
        long sum = 0L;

        // 100 iterations
        for (int i = 0; i < 4; i++) {
            sum = part1();
        }

        // get factor
        int last = 0;
        int diff = 0;
        for (int i = 0; i < 3; i++) {
            int next = part1();
            diff = next - last;
            last = next;
        }
        sum += ((50_000_000_000L - 100) / 20) * diff;
        return sum;
    }

    public void parse() {
        growPatterns = new HashSet<>();
        plantPotStr = input.get(0).substring(15);
        for (int i = 2; i < input.size(); i++) {
            String[] split = input.get(i).split(" ");
            if (split[2].startsWith("#")) {
                growPatterns.add(split[0]);
            }
        }
    }

}
