package de.adventofcode.chrisgw.day21;

import org.junit.Assert;
import org.junit.Test;

import static de.adventofcode.chrisgw.day21.ArtPixelEnhancmentRule.parsePixelEnhancmentRule;


public class FractalArtTest {


    @Test
    public void artPixelPattern_flip() {
        ArtPixelPattern artPixelPattern = ArtPixelPattern.parseArtPixelPattern("" //
                + ".#./" //
                + "..#/" //
                + "###");
        ArtPixelPattern expectedFlipPixelPattern = ArtPixelPattern.parseArtPixelPattern("" //
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
        ArtPixelPattern artPixelPattern = ArtPixelPattern.parseArtPixelPattern("" //
                + ".#./" //
                + "..#/" //
                + "###");
        ArtPixelPattern expectedRotatedPixelPattern = ArtPixelPattern.parseArtPixelPattern("" //
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
        ArtPixelEnhancmentRule pixelEnhancmentRule = parsePixelEnhancmentRule("../.# => ##./#../...");
        System.out.println(pixelEnhancmentRule);
    }

}
