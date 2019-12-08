package de.adventofcode.chrisgw.day08;

import java.util.Comparator;


/**
 * 2019 Day 8: Space Image Format
 * https://adventofcode.com/2019/day/78
 */
public class AdventOfCodeDay08 {

    private LayeredPixelImage layeredPixelImage;

    public AdventOfCodeDay08(LayeredPixelImage layeredPixelImage) {
        this.layeredPixelImage = layeredPixelImage;
    }




    public LayeredPixelImage findFewest0DigitsImageLayer() {
        return layeredPixelImage.imageLayers()
                .min(Comparator.comparingInt(pixelImage -> pixelImage.pixelColorCount(0)))
                .orElse(layeredPixelImage);
    }

    public int calculatePart01() {
        LayeredPixelImage fewest0DigitsImageLayer = findFewest0DigitsImageLayer();
        int digit1Count = fewest0DigitsImageLayer.pixelColorCount(1);
        int digit2Count = fewest0DigitsImageLayer.pixelColorCount(2);
        return digit1Count * digit2Count;
    }

}
