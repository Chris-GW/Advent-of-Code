package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class InventoryManagmentSystemTest {


    @Test
    public void example01_abcdef() {
        String boxId = "abcdef";
        boolean expectTwoLetterBoxId = false;
        boolean expectThreeLetterBoxId = false;

        boolean twoLetterBoxId = InventoryManagmentSystem.isTwoLetterBoxId(boxId);
        boolean threeLetterBoxId = InventoryManagmentSystem.isThreeLetterBoxId(boxId);

        assertEquals("twoLetterBoxId", expectTwoLetterBoxId, twoLetterBoxId);
        assertEquals("threeLetterBoxId", expectThreeLetterBoxId, threeLetterBoxId);
    }

    @Test
    public void example02_bababc() {
        String boxId = "bababc";
        boolean expectTwoLetterBoxId = true;
        boolean expectThreeLetterBoxId = true;

        boolean twoLetterBoxId = InventoryManagmentSystem.isTwoLetterBoxId(boxId);
        boolean threeLetterBoxId = InventoryManagmentSystem.isThreeLetterBoxId(boxId);

        assertEquals("twoLetterBoxId", expectTwoLetterBoxId, twoLetterBoxId);
        assertEquals("threeLetterBoxId", expectThreeLetterBoxId, threeLetterBoxId);
    }

    @Test
    public void example03_abbcde() {
        String boxId = "abbcde";
        boolean expectTwoLetterBoxId = true;
        boolean expectThreeLetterBoxId = false;

        boolean twoLetterBoxId = InventoryManagmentSystem.isTwoLetterBoxId(boxId);
        boolean threeLetterBoxId = InventoryManagmentSystem.isThreeLetterBoxId(boxId);

        assertEquals("twoLetterBoxId", expectTwoLetterBoxId, twoLetterBoxId);
        assertEquals("threeLetterBoxId", expectThreeLetterBoxId, threeLetterBoxId);
    }

    @Test
    public void example04_abcccd() {
        String boxId = "abcccd";
        boolean expectTwoLetterBoxId = false;
        boolean expectThreeLetterBoxId = true;

        boolean twoLetterBoxId = InventoryManagmentSystem.isTwoLetterBoxId(boxId);
        boolean threeLetterBoxId = InventoryManagmentSystem.isThreeLetterBoxId(boxId);

        assertEquals("twoLetterBoxId", expectTwoLetterBoxId, twoLetterBoxId);
        assertEquals("threeLetterBoxId", expectThreeLetterBoxId, threeLetterBoxId);
    }

    @Test
    public void example05_aabcdd() {
        String boxId = "aabcdd";
        boolean expectTwoLetterBoxId = true;
        boolean expectThreeLetterBoxId = false;

        boolean twoLetterBoxId = InventoryManagmentSystem.isTwoLetterBoxId(boxId);
        boolean threeLetterBoxId = InventoryManagmentSystem.isThreeLetterBoxId(boxId);

        assertEquals("twoLetterBoxId", expectTwoLetterBoxId, twoLetterBoxId);
        assertEquals("threeLetterBoxId", expectThreeLetterBoxId, threeLetterBoxId);
    }

    @Test
    public void example06_abcdee() {
        String boxId = "abcdee";
        boolean expectTwoLetterBoxId = true;
        boolean expectThreeLetterBoxId = false;

        boolean twoLetterBoxId = InventoryManagmentSystem.isTwoLetterBoxId(boxId);
        boolean threeLetterBoxId = InventoryManagmentSystem.isThreeLetterBoxId(boxId);

        assertEquals("twoLetterBoxId", expectTwoLetterBoxId, twoLetterBoxId);
        assertEquals("threeLetterBoxId", expectThreeLetterBoxId, threeLetterBoxId);
    }

    @Test
    public void example07_ababab() {
        String boxId = "ababab";
        boolean expectTwoLetterBoxId = false;
        boolean expectThreeLetterBoxId = true;

        boolean twoLetterBoxId = InventoryManagmentSystem.isTwoLetterBoxId(boxId);
        boolean threeLetterBoxId = InventoryManagmentSystem.isThreeLetterBoxId(boxId);

        assertEquals("twoLetterBoxId", expectTwoLetterBoxId, twoLetterBoxId);
        assertEquals("threeLetterBoxId", expectThreeLetterBoxId, threeLetterBoxId);
    }


    @Test
    public void calculateBoxChecksum_example01() {
        long expectedBoxIdChecksum = 4 * 3;
        List<String> boxIds = Arrays.asList("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab");

        long boxIdChecksum = InventoryManagmentSystem.calculateBoxChecksum(boxIds);
        assertEquals("boxIdChecksum", expectedBoxIdChecksum, boxIdChecksum);
    }

    @Test
    public void calculateBoxChecksum_myTask() throws Exception {
        long expectedBoxIdChecksum = 5952;
        Path boxIdsFile = TestUtils.getResourcePath("/day02/boxIds.txt");

        long boxIdChecksum = InventoryManagmentSystem.calculateBoxChecksum(boxIdsFile);
        assertEquals("boxIdChecksum", expectedBoxIdChecksum, boxIdChecksum);
    }

}
