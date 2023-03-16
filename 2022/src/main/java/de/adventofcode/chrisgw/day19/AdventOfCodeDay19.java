package de.adventofcode.chrisgw.day19;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2022/day/19">Advent of Code - day 19</a>
 */
public class AdventOfCodeDay19 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay19(List<String> inputLines) {
        super(Year.of(2022), 19, inputLines);
    }


    public Integer solveFirstPart() {
        List<RobotFactoryBlueprint> blueprints = inputLines().map(RobotFactoryBlueprint::parseBlueprint).toList();
        return blueprints.stream().mapToInt(this::blueprintQualityLevel).max().orElse(0);
    }

    private int blueprintQualityLevel(RobotFactoryBlueprint factoryBlueprint) {
        Deque<RobotFactoryMinute> factoryMinutes = new ArrayDeque<>();
        factoryMinutes.add(new RobotFactoryMinute(factoryBlueprint));

        int maxQualityLevel = 0;
        while (!factoryMinutes.isEmpty()) {
            RobotFactoryMinute currentMinute = factoryMinutes.pop();
            if (currentMinute.getMinute() == 24 && currentMinute.qualityLevel() > maxQualityLevel) {
                maxQualityLevel = currentMinute.qualityLevel();
            }
            if (currentMinute.getMinute() < 24) {
                List<RobotFactoryMinute> nextMinutes = currentMinute.nextMinute().toList();
                factoryMinutes.addAll(nextMinutes);
            }
        }

        return maxQualityLevel;
    }


    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


}
