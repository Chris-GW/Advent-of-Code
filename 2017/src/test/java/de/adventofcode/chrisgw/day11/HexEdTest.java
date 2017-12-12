package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;


public class HexEdTest {

    @Test
    public void follow_ne_ne_ne_expect_3_steps_distance() {
        String hexInstructionsStr = "ne,ne,ne";
        HexCell expectedDestinationHexCell = new HexCell(3, 3);
        int expectedStepDistance = 3;


        HexCell destinationHexCell = HexEd.followDirectionInstructions(hexInstructionsStr);
        System.out.println(destinationHexCell);
        int stepDistance = destinationHexCell.getStepDistanceToHexCell(new HexCell());

        Assert.assertEquals("Expect destination hexCell", expectedDestinationHexCell, destinationHexCell);
        Assert.assertEquals("Expect stepDistance", expectedStepDistance, stepDistance);
    }

    @Test
    public void follow_ne_ne_sw_sw_expect_0_steps_distance() {
        String hexInstructionsStr = "ne,ne,sw,sw";
        HexCell expectedDestinationHexCell = new HexCell(0, 0);
        int expectedStepDistance = 0;


        HexCell destinationHexCell = HexEd.followDirectionInstructions(hexInstructionsStr);
        System.out.println(destinationHexCell);
        int stepDistance = destinationHexCell.getStepDistanceToHexCell(new HexCell());

        Assert.assertEquals("Expect destination hexCell", expectedDestinationHexCell, destinationHexCell);
        Assert.assertEquals("Expect stepDistance", expectedStepDistance, stepDistance);
    }

    @Test
    public void follow_ne_ne_s_s_expect_2_steps_distance() {
        String hexInstructionsStr = "ne,ne,s,s";
        HexCell expectedDestinationHexCell = new HexCell(2, -2);
        int expectedStepDistance = 2;


        HexCell destinationHexCell = HexEd.followDirectionInstructions(hexInstructionsStr);
        System.out.println(destinationHexCell);
        int stepDistance = destinationHexCell.getStepDistanceToHexCell(new HexCell());

        Assert.assertEquals("Expect destination hexCell", expectedDestinationHexCell, destinationHexCell);
        Assert.assertEquals("Expect stepDistance", expectedStepDistance, stepDistance);
    }

    @Test
    public void follow_se_sw_se_sw_sw_expect_3_steps_distance() {
        String hexInstructionsStr = "se,sw,se,sw,sw";
        HexCell expectedDestinationHexCell = new HexCell(-1, -5);
        int expectedStepDistance = 3;


        HexCell destinationHexCell = HexEd.followDirectionInstructions(hexInstructionsStr);
        System.out.println(destinationHexCell);
        int stepDistance = destinationHexCell.getStepDistanceToHexCell(new HexCell());

        Assert.assertEquals("Expect destination hexCell", expectedDestinationHexCell, destinationHexCell);
        Assert.assertEquals("Expect stepDistance", expectedStepDistance, stepDistance);
    }

    @Test
    public void follow_myTask_expect_success() {
        String hexInstructionsStr = TestUtils.readAllLinesOfClassPathResource("/day11/HexEd_chrisgw.txt").get(0);
        int expectedStepDistance = 794;

        HexCell destinationHexCell = HexEd.followDirectionInstructions(hexInstructionsStr);
        System.out.println(destinationHexCell);
        int stepDistance = destinationHexCell.getStepDistanceToHexCell(new HexCell());

        Assert.assertEquals("Expect stepDistance", expectedStepDistance, stepDistance);
    }

}