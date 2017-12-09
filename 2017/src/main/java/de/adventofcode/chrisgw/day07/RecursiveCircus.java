package de.adventofcode.chrisgw.day07;


import java.util.*;
import java.util.Map.Entry;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


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
 *
 * --- Part Two ---
 *
 * The programs explain the situation: they can't get down. Rather, they could
 * get down, if they weren't expending all of their energy trying to keep the
 * tower balanced. Apparently, one program has the wrong weight, and until
 * it's fixed, they're stuck here.
 *
 * For any program holding a disc, each program standing on that disc forms a
 * sub-tower. Each of those sub-towers are supposed to be the same weight, or
 * the disc itself isn't balanced. The weight of a tower is the sum of the
 * weights of the programs in that tower.
 *
 * In the example above, this means that for ugml's disc to be balanced, gyxo,
 * ebii, and jptl must all have the same weight, and they do: 61.
 *
 * However, for tknk to be balanced, each of the programs standing on its disc
 * and all programs above it must each match. This means that the following
 * sums must all be the same:
 *
 * ugml + (gyxo + ebii + jptl) = 68 + (61 + 61 + 61) = 251
 * padx + (pbga + havc + qoyq) = 45 + (66 + 66 + 66) = 243
 * fwft + (ktlj + cntj + xhth) = 72 + (57 + 57 + 57) = 243
 *
 * As you can see, tknk's disc is unbalanced: ugml's stack is heavier than the
 * other two. Even though the nodes above ugml are balanced, ugml itself is
 * too heavy: it needs to be 8 units lighter for its stack to weigh 243 and
 * keep the towers balanced. If this change were made, its weight would be 60.
 *
 * Given that exactly one program is the wrong weight, what would its weight
 * need to be to balance the entire tower?
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


    public static DiscTower findUnbalencedDiscTower(DiscTower discTowerRoot) {
        List<DiscTower> wrongDiscTowerOptinal = discTowerRoot.getAllDiscTowerStream()
                .filter(discTower -> !discTower.isBalenced())
                .collect(Collectors.toList());
        DiscTower unbalencedDiscTower = wrongDiscTowerOptinal.get(0);

        Map<Integer, List<DiscTower>> discTowerGroupByCalculatedWeight = unbalencedDiscTower.getChilds()
                .stream()
                .collect(Collectors.groupingBy(DiscTower::getTotalWeight));

        int expectedWeightSum = 0;
        DiscTower wrongDiscTower = null;
        for (Entry<Integer, List<DiscTower>> calculatedWeightDiscTowerList : discTowerGroupByCalculatedWeight.entrySet()) {
            List<DiscTower> discTowerGroup = calculatedWeightDiscTowerList.getValue();
            if (discTowerGroup.size() > 1) {
                expectedWeightSum = calculatedWeightDiscTowerList.getKey();
            } else if (discTowerGroup.size() == 1) {
                wrongDiscTower = discTowerGroup.get(0);
            }
        }
        if (wrongDiscTower == null) {
            throw new IllegalStateException();
        }

        int weightDifference = expectedWeightSum - wrongDiscTower.getTotalWeight();
        int correctedWeight = wrongDiscTower.getWeight() + weightDifference;
        return new DiscTower(wrongDiscTower.getName(), correctedWeight);
    }


}
