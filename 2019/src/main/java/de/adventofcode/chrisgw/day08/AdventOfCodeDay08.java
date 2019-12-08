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


    public String printVisiblePixelImagePart02() {
        LayeredPixelImage visiblePixelImage = layeredPixelImage.visiblePixelImage();
        StringBuilder sb = new StringBuilder(visiblePixelImage.imageLayerSize());
        for (int row = 0; row < visiblePixelImage.getHeight(); row++) {
            for (int column = 0; column < visiblePixelImage.getWidth(); column++) {
                int pixelDataAt = visiblePixelImage.pixelDataAt(row, column);
                if (pixelDataAt == LayeredPixelImage.PIXEL_DATA_BLACK) {
                    sb.append("\u2588");
                } else if (pixelDataAt == LayeredPixelImage.PIXEL_DATA_WHITE) {
                    sb.append("\u2591");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        String visiblePixelImageStr = sb.toString();
        System.out.println(visiblePixelImageStr);
        return visiblePixelImageStr;
    }

}
