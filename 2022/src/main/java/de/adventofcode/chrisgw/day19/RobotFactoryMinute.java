package de.adventofcode.chrisgw.day19;

import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day19.Resource.GEODE;
import static de.adventofcode.chrisgw.day19.Resource.ORE;
import static java.util.Objects.requireNonNull;


public class RobotFactoryMinute {

    private final RobotFactoryBlueprint blueprint;
    private final ResourceInventory resourceInventory;
    private final ResourceInventory robotInventory;
    private final RobotRecipe targetRobotRecipe;
    private final int minute;


    public RobotFactoryMinute(RobotFactoryBlueprint blueprint) {
        this.blueprint = requireNonNull(blueprint);
        this.resourceInventory = new ResourceInventory();
        this.robotInventory = new ResourceInventory();
        this.robotInventory.add(ORE, 1);
        this.targetRobotRecipe = null;
        this.minute = 0;
    }

    private RobotFactoryMinute(RobotFactoryMinute robotFactoryMinute, RobotRecipe targetRobotRecipe) {
        this.blueprint = robotFactoryMinute.blueprint;
        this.resourceInventory = new ResourceInventory(robotFactoryMinute.resourceInventory);
        this.robotInventory = new ResourceInventory(robotFactoryMinute.robotInventory);
        this.targetRobotRecipe = targetRobotRecipe;
        this.minute = robotFactoryMinute.minute + 1;
    }


    public Stream<RobotFactoryMinute> nextMinute() {
        if (targetRobotRecipe == null || canProduce(targetRobotRecipe)) {
            return blueprint.robotRecipes().stream().filter(this::isProducible).map(this::nextRoboFactoryMinute);
        } else {
            return Stream.of(nextRoboFactoryMinute(targetRobotRecipe));
        }
    }

    private boolean isProducible(RobotRecipe robotRecipe) {
        return robotRecipe.resourceInputs()
                .stream()
                .map(ResourceQuantity::resource)
                .mapToInt(robotInventory::get)
                .allMatch(robotCountForResource -> robotCountForResource > 0);
    }

    private RobotFactoryMinute nextRoboFactoryMinute(RobotRecipe robotRecipe) {
        var nextFactoryMinute = new RobotFactoryMinute(this, robotRecipe);
        if (targetRobotRecipe != null && nextFactoryMinute.canProduce(targetRobotRecipe)) {
            nextFactoryMinute.spend(targetRobotRecipe);
            nextFactoryMinute.collectResources();
            nextFactoryMinute.produce(targetRobotRecipe);
        } else {
            nextFactoryMinute.collectResources();
        }
        return nextFactoryMinute;
    }


    private boolean canProduce(RobotRecipe robotRecipe) {
        return robotRecipe.resourceInputs().stream().allMatch(resourceInventory::contains);
    }

    private void spend(RobotRecipe robotRecipe) {
        //        System.out.printf("Spend %s to start building a %s-collecting robot.%n", //
        //                robotRecipe.resourceInputs(), robotRecipe.output());
        robotRecipe.resourceInputs().forEach(resourceInventory::subtract);
    }

    private void produce(RobotRecipe recipe) {
        int robotCount = robotInventory.add(recipe.output(), 1);
        //        System.out.printf("The new %s-collecting robot is ready: you now have %d of them.%n", //
        //                recipe.output(), robotCount);
    }


    private void collectResources() {
        for (Resource resource : Resource.values()) {
            int robotCount = robotInventory.get(resource);
            if (robotCount == 0) {
                continue;
            }
            int storedQuantity = resourceInventory.add(resource, robotCount);
            //            System.out.printf("%d %s-collecting robot; you now have %d %s.%n", robotCount, resource, storedQuantity,
            //                    resource);
        }
    }


    public int producedGeodes() {
        return resourceInventory.get(GEODE);
    }

    public int qualityLevel() {
        return getBlueprint().blueprintId() * producedGeodes();
    }

    public int getMinute() {
        return minute;
    }

    public RobotFactoryBlueprint getBlueprint() {
        return blueprint;
    }


    @Override
    public String toString() {
        return "%2dm: resources=%s | robots=%s | %s".formatted(minute, resourceInventory, robotInventory,
                targetRobotRecipe);
    }

}
