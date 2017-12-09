package de.adventofcode.chrisgw.day07;


import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <pre>
 * Wandering further through the circuits of the computer, you come upon a
 * tower of programs that have gotten themselves into a bit of trouble. A
 * recursive algorithm has gotten out of hand, and now they're balanced
 * precariously in a large tower.
 *
 * One program at the bottom supports the entire tower. It's holding a large
 * disc, and on the disc are balanced several more sub-towers. At the bottom
 * of these sub-towers, standing on the bottom disc, are other programs, each
 * holding their own disc, and so on. At the very tops of these sub-sub-sub-
 * ...-towers, many programs stand simply keeping the disc below them
 * balanced but with no disc of their own.
 *
 * You offer to help, but first you need to understand the structure of these
 * towers. You ask each program to yell out their name, their weight, and (if
 * they're holding a disc) the names of the programs immediately above them
 * balancing on that disc. You write this information down (your puzzle
 * input). Unfortunately, in their panic, they don't do this in an orderly
 * fashion; by the time you're done, you're not sure which program gave which
 * information.
 *
 * For example, if your list is the following:
 *
 * pbga (66)
 * xhth (57)
 * ebii (61)
 * havc (66)
 * ktlj (57)
 * fwft (72) -> ktlj, cntj, xhth
 * qoyq (66)
 * padx (45) -> pbga, havc, qoyq
 * tknk (41) -> ugml, padx, fwft
 * jptl (61)
 * ugml (68) -> gyxo, ebii, jptl
 * gyxo (61)
 * cntj (57)
 *
 * ...then you would be able to recreate the structure of the towers that
 * looks like this:
 *
 *                 gyxo
 *               /
 *          ugml - ebii
 *        /      \
 *       |         jptl
 *       |
 *       |         pbga
 *      /        /
 * tknk --- padx - havc
 *      \        \
 *       |         qoyq
 *       |
 *       |         ktlj
 *        \      /
 *          fwft - cntj
 *               \
 *                 xhth
 *
 * In this example, tknk is at the bottom of the tower (the bottom program),
 * and is holding up ugml, padx, and fwft. Those programs are, in turn,
 * holding up other programs; in this example, none of those programs are
 * holding up any other programs, and are all the tops of their own towers.
 * (The actual tower balancing in front of you is much larger.)
 *
 * Before you're ready to help them, you need to make sure your information
 * is correct. What is the name of the bottom program?
 * </pre>
 */
public class RecursiveCircus {

    private static final Pattern DISC_TOWER_PATTERN = Pattern.compile("(\\w+)\\s+\\((\\d+)\\)(\\s+->\\s+((.+).))?");


    public static DiscTower parseDiscTower(List<String> discTowersStr) {
        Map<String, DiscTower> nameToDiscTowerMap = new HashMap<>();
        Map<String, List<String>> discTowerNameToRelations = new HashMap<>();

        for (String discTowerStr : discTowersStr) {
            Matcher discTowerMatcher = DISC_TOWER_PATTERN.matcher(discTowerStr);
            if (!discTowerMatcher.matches()) {
                throw new IllegalArgumentException();
            }
            MatchResult discTowerMatchResult = discTowerMatcher.toMatchResult();
            DiscTower newDiscTower = createDiscTowerOfMatchResult(discTowerMatchResult);
            nameToDiscTowerMap.put(newDiscTower.getName(), newDiscTower);

            List<String> discTowerRelations = createDiscTowerRelationsOfMatchResult(discTowerMatchResult);
            discTowerNameToRelations.put(newDiscTower.getName(), discTowerRelations);
        }

        for (DiscTower discTower : nameToDiscTowerMap.values()) {
            List<String> relations = discTowerNameToRelations.getOrDefault(discTower.getName(),
                    Collections.emptyList());
            for (String relation : relations) {
                DiscTower childDiscTower = nameToDiscTowerMap.get(relation);
                discTower.addChildDiscTower(childDiscTower);
            }
        }
        return findRootDiscTower(nameToDiscTowerMap);
    }


    private static DiscTower createDiscTowerOfMatchResult(MatchResult discTowerMatchResult) {
        String discTowerName = discTowerMatchResult.group(1);
        int discTowerWeight = Integer.parseInt(discTowerMatchResult.group(2));
        return new DiscTower(discTowerName, discTowerWeight);
    }

    private static List<String> createDiscTowerRelationsOfMatchResult(MatchResult discTowerMatchResult) {
        String discTowerRelationGroup = discTowerMatchResult.group(4);
        if (discTowerRelationGroup == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(discTowerRelationGroup.split(", "));
    }


    private static DiscTower findRootDiscTower(Map<String, DiscTower> nameToDiscTowerMap) {
        return nameToDiscTowerMap.values()
                .stream()
                .filter(discTower -> discTower.getParent() == null)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }


}
