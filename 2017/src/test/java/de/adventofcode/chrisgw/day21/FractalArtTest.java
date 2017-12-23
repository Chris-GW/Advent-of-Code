package de.adventofcode.chrisgw.day21;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.adventofcode.chrisgw.day21.ArtPixelEnhancmentRule.parsePixelEnhancmentRule;
import static de.adventofcode.chrisgw.day21.ArtPixelPattern.parseArtPixelPattern;


public class FractalArtTest {


    @Test
    public void artPixelPattern_flip() {
        ArtPixelPattern artPixelPattern = parseArtPixelPattern("" //
                + ".#./" //
                + "..#/" //
                + "###");
        ArtPixelPattern expectedFlipPixelPattern = parseArtPixelPattern("" //
                + ".#./" //
                + "#../" //
                + "###");
        System.out.println(artPixelPattern);
        System.out.println("--flip--");

        ArtPixelPattern flipPixelPattern = artPixelPattern.flipPixelPattern();
        System.out.println(flipPixelPattern);
        Assert.assertEquals("expect fliped art pixel pattern", expectedFlipPixelPattern, flipPixelPattern);
    }

    @Test
    public void artPixelPattern_rotate() {
        ArtPixelPattern artPixelPattern = parseArtPixelPattern("" //
                + ".#./" //
                + "..#/" //
                + "###");
        ArtPixelPattern expectedRotatedPixelPattern = parseArtPixelPattern("" //
                + "#../" //
                + "#.#/" //
                + "##.");
        System.out.println(artPixelPattern);
        System.out.println("--rotate--");

        ArtPixelPattern rotatedPixelPattern = artPixelPattern.rotatePixelPattern();
        System.out.println(rotatedPixelPattern);
        Assert.assertEquals("expect rotated art pixel pattern", expectedRotatedPixelPattern, rotatedPixelPattern);
    }


    @Test
    public void fractalArt_part1_example() {
        List<ArtPixelEnhancmentRule> pixelEnhancmentRules = Arrays.asList(
                parsePixelEnhancmentRule("../.# => ##./#../..."),
                parsePixelEnhancmentRule(".#./..#/### => #..#/..../..../#..#"));
        // @formatter:off
        List<ArtPixelPattern> expectedPixelPatterns = new ArrayList<>();
        expectedPixelPatterns.add(parseArtPixelPattern(""
                        + "#..#\n"
                        + "....\n"
                        + "....\n"
                        + "#..#"));
        expectedPixelPatterns.add(parseArtPixelPattern(""
                        + "##.##.\n"
                        + "#..#..\n"
                        + "......\n"
                        + "##.##.\n"
                        + "#..#..\n"
                        + "......"));
        // @formatter:on
        int enhancementRounds = 2;
        int expectedLitPixelCount = 12;

        FractalArt fractalArt = new FractalArt(pixelEnhancmentRules);
        System.out.println("-----0-----");
        System.out.println(fractalArt);

        for (int round = 1; round <= enhancementRounds; round++) {
            fractalArt.doEnhancePixelGrid();
            System.out.println("-----" + round + "-----");
            System.out.println(fractalArt);

            ArtPixelPattern pixelGrid = fractalArt.getPixelGrid();
            ArtPixelPattern expectedPixelGrid = expectedPixelPatterns.get(round - 1);
            Assert.assertEquals("expect pixel grid after enhancment round: " + round, expectedPixelGrid, pixelGrid);
        }

        int litPixelCount = fractalArt.countLitPixels();
        Assert.assertEquals("expect lit pixels in end", expectedLitPixelCount, litPixelCount);
    }


    @Test
    public void fractalArt_part1_myTask() {
        String classpathResource = "/day21/FractalArt_chrisgw.txt";
        List<ArtPixelEnhancmentRule> pixelEnhancmentRules = TestUtils.readAllLinesOfClassPathResource(classpathResource)
                .stream()
                .map(ArtPixelEnhancmentRule::parsePixelEnhancmentRule)
                .collect(Collectors.toList());
        int enhancementRounds = 5;
        int expectedLitPixelCount = 167;

        FractalArt fractalArt = new FractalArt(pixelEnhancmentRules);
        System.out.println("-----" + 0 + "-----");
        System.out.println(fractalArt);

        for (int round = 1; round <= enhancementRounds; round++) {
            fractalArt.doEnhancePixelGrid();
            System.out.println("-----" + round + "-----");
            System.out.println(fractalArt);
        }

        int litPixelCount = fractalArt.countLitPixels();
        Assert.assertEquals("expect lit pixels in end", expectedLitPixelCount, litPixelCount);
    }

}
