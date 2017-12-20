package de.adventofcode.chrisgw.day08;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TwoFactorAuthenticationTest {

    @Test
    public void TwoFactorAuthenticationTest_part1_example() {
        // @formatter:off
        List<String> pixelOperationLines = Arrays.asList(
                "rect 3x2",
                "rotate column x=1 by 1",
                "rotate row y=0 by 4",
                "rotate column x=1 by 1");
        boolean[][][] expectedPixelScreen = new boolean[][][] { {
                parsePixelRowLine("###...."),
                parsePixelRowLine("###...."),
                parsePixelRowLine(".......")
        },{
                parsePixelRowLine("#.#...."),
                parsePixelRowLine("###...."),
                parsePixelRowLine(".#.....")
        },{
                parsePixelRowLine("....#.#"),
                parsePixelRowLine("###...."),
                parsePixelRowLine(".#.....")
        },{
                parsePixelRowLine(".#..#.#"),
                parsePixelRowLine("#.#...."),
                parsePixelRowLine(".#.....")
        } };
        // @formatter:on

        TwoFactorAuthentication twoFactorAuthentication = new TwoFactorAuthentication(7,3);
        List<PixelScreenOperation> pixelScreenOperations = pixelOperationLines.stream()
                .map(TwoFactorAuthentication::parsePixelScreenOperations)
                .collect(Collectors.toList());

        for (int i = 0; i < pixelScreenOperations.size(); i++) {
            PixelScreenOperation pixelScreenOperation = pixelScreenOperations.get(i);
            twoFactorAuthentication.executePixelOperation(pixelScreenOperation);
            System.out.println(twoFactorAuthentication);

            boolean[][] expectedPixelSceen = expectedPixelScreen[i];
            for (int y = 0; y < expectedPixelSceen.length; y++) {
                boolean[] expectedPixelRow = expectedPixelSceen[y];
                for (int x = 0; x < expectedPixelRow.length; x++) {
                    boolean pixel = twoFactorAuthentication.getPixel(x, y);
                    boolean expectedPixel = expectedPixelRow[x];
                    Assert.assertEquals("expect pixel", expectedPixel, pixel);
                }
            }
        }
    }


    private static boolean[] parsePixelRowLine(String pixelRowLine) {
        boolean[] pixelRow = new boolean[pixelRowLine.length()];
        for (int i = 0; i < pixelRow.length; i++) {
            pixelRow[i] = pixelRowLine.charAt(i) == '#';
        }
        return pixelRow;
    }

}