package de.adventofcode.chrisgw.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public record RobotRecipe(List<ResourceQuantity> resourceInputs, Resource output) {

    public static final Pattern RECIPE_PATTERN = Pattern.compile(
            "Each (\\w+) robot costs (\\d+) (\\w+)(?: and (\\d+) (\\w+))*\\.");

    public static RobotRecipe parseRecipe(String recipeLine) {
        Matcher matcher = RECIPE_PATTERN.matcher(recipeLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "expect recipe matching pattern: %s, but was: %s".formatted(RECIPE_PATTERN, recipeLine));
        }
        Resource output = Resource.valueOf(matcher.group(1).toUpperCase());

        List<ResourceQuantity> resourceInputs = new ArrayList<>(2);
        for (int group = 2; group <= matcher.groupCount(); group += 2) {
            if (matcher.group(group) == null) {
                break;
            }
            int inputQuantity = Integer.parseInt(matcher.group(group));
            Resource inputResource = Resource.valueOf(matcher.group(group + 1).toUpperCase());
            resourceInputs.add(new ResourceQuantity(inputResource, inputQuantity));
        }
        return new RobotRecipe(resourceInputs, output);
    }


    @Override
    public String toString() {
        String resourceInputsStr = resourceInputs().stream()
                .map(ResourceQuantity::toString)
                .collect(Collectors.joining(" and "));
        return "Each %s robot costs %s.".formatted(output(), resourceInputsStr);
    }

}
