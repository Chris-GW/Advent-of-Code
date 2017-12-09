package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class RecursiveCircusTest {


    @Test
    public void shouldParseDiscTower_example() {
        // @formatter:off
        List<String> discTowers = Arrays.asList(
                "pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)");
        // @formatter:on

        String expectedDiscTowerName = "tknk";
        int expectedDiscTowerWeight = 41;

        DiscTower rootDiscTower = RecursiveCircus.parseDiscTower(discTowers);
        System.out.println(rootDiscTower);

        Assert.assertTrue("rootDisctTower should be not null", rootDiscTower != null);
        Assert.assertEquals(expectedDiscTowerName, rootDiscTower.getName());
        Assert.assertEquals(expectedDiscTowerWeight, rootDiscTower.getWeight());
    }


    @Test
    public void shouldParseDiscTower_myTask() {
        List<String> discTowerStr = TestUtils.readAllLinesOfClassPathResource("/day07/RecursiveCircus_chrisgw.txt");

        String expectedDiscTowerName = "airlri";
        int expectedDiscTowerWeight = 97;

        DiscTower rootDiscTower = RecursiveCircus.parseDiscTower(discTowerStr);
        System.out.println(rootDiscTower);

        Assert.assertTrue("rootDisctTower should be not null", rootDiscTower != null);
        Assert.assertEquals(expectedDiscTowerName, rootDiscTower.getName());
        Assert.assertEquals(expectedDiscTowerWeight, rootDiscTower.getWeight());
    }


    // --- part 2

    @Test
    public void findUnbalencedDiscTower_example() {
        // @formatter:off
        List<String> discTowers = Arrays.asList(
                "pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)");
        // @formatter:on

        String expectedDiscTowerName = "ugml";
        int expectedDiscTowerWeight = 60;

        DiscTower rootDiscTower = RecursiveCircus.parseDiscTower(discTowers);
        Assert.assertTrue("rootDisctTower should be not null", rootDiscTower != null);

        DiscTower unbalencedDiscTower = RecursiveCircus.findUnbalencedDiscTower(rootDiscTower);
        Assert.assertTrue("rootDisctTower should be not null", unbalencedDiscTower != null);
        Assert.assertEquals(expectedDiscTowerName, unbalencedDiscTower.getName());
        Assert.assertEquals(expectedDiscTowerWeight, unbalencedDiscTower.getTotalWeight());
    }


    @Test
    public void findUnbalencedDiscTower_myTask() {
        List<String> discTowerStr = TestUtils.readAllLinesOfClassPathResource("/day07/RecursiveCircus_chrisgw.txt");

        String expectedDiscTowerName = "dqwocyn";
        int expectedDiscTowerWeight = 1206;

        DiscTower rootDiscTower = RecursiveCircus.parseDiscTower(discTowerStr);
        Assert.assertTrue("rootDisctTower should be not null", rootDiscTower != null);
        DiscTower unbalencedDiscTower = RecursiveCircus.findUnbalencedDiscTower(rootDiscTower);
        System.out.println(unbalencedDiscTower);

        Assert.assertEquals(expectedDiscTowerName, unbalencedDiscTower.getName());
        Assert.assertEquals(expectedDiscTowerWeight, unbalencedDiscTower.getWeight());
    }

}