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
        Assert.assertEquals(rootDiscTower.getName(), expectedDiscTowerName);
        Assert.assertEquals(rootDiscTower.getWeight(), expectedDiscTowerWeight);
    }


    @Test
    public void shouldParseDiscTower_myTask() {
        List<String> discTowerStr = TestUtils.readAllLinesOfClassPathResource("/day07/RecursiveCircus_chrisgw.txt");

        String expectedDiscTowerName = "airlri";
        int expectedDiscTowerWeight = 97;

        DiscTower rootDiscTower = RecursiveCircus.parseDiscTower(discTowerStr);
        System.out.println(rootDiscTower);

        Assert.assertTrue("rootDisctTower should be not null", rootDiscTower != null);
        Assert.assertEquals(rootDiscTower.getName(), expectedDiscTowerName);
        Assert.assertEquals(rootDiscTower.getWeight(), expectedDiscTowerWeight);
    }


}