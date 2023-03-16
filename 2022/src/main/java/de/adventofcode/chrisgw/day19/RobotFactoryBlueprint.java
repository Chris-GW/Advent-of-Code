package de.adventofcode.chrisgw.day19;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.adventofcode.chrisgw.day19.RobotRecipe.RECIPE_PATTERN;


public record RobotFactoryBlueprint(int blueprintId, List<RobotRecipe> robotRecipes) {

    public static final Pattern BLUEPRINT_PATTERN = Pattern.compile("^Blueprint (\\d):(.+)$");

    public static RobotFactoryBlueprint parseBlueprint(String blueprintLine) {
        Matcher matcher = BLUEPRINT_PATTERN.matcher(blueprintLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "expect blueprint matching pattern: %s, but was: %s".formatted(BLUEPRINT_PATTERN, blueprintLine));
        }

        List<RobotRecipe> robotRecipes = RECIPE_PATTERN.matcher(blueprintLine)
                .results()
                .map(MatchResult::group)
                .map(RobotRecipe::parseRecipe)
                .toList();

        int blueprintId = Integer.parseInt(matcher.group(1));
        return new RobotFactoryBlueprint(blueprintId, robotRecipes);
    }

}
