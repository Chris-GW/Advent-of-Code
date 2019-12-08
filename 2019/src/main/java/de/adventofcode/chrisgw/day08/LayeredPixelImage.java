package de.adventofcode.chrisgw.day08;

import lombok.Data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Data
public class LayeredPixelImage {

    public static final int PIXEL_DATA_BLACK = 0;
    public static final int PIXEL_DATA_WHITE = 1;
    public static final int PIXEL_DATA_TRANSPARENT = 2;

    private final int width;
    private final int height;
    private final int[] imageData;


    public static LayeredPixelImage parseLayeredPixelImageStr(int width, int height, String imageDataStr) {
        int[] imageData = imageDataStr.chars().map(numberChar -> numberChar - '0').toArray();
        if (!Arrays.stream(imageData).allMatch(value -> 0 <= value && value <= 2)) {
            throw new IllegalArgumentException("unknown pixelData");
        }
        return new LayeredPixelImage(width, height, imageData);
    }


    public Stream<LayeredPixelImage> imageLayers() {
        return IntStream.range(0, imageLayerCount()).mapToObj(this::getImageLayer);
    }

    public LayeredPixelImage getImageLayer(int layerIndex) {
        int[] imageLayerData = imageLayerData(layerIndex);
        return new LayeredPixelImage(width, height, imageLayerData);
    }

    private int[] imageLayerData(int layerIndex) {
        int imageLayerSize = imageLayerSize();
        int fromIndex = imageLayerSize * layerIndex;
        int toIndex = fromIndex + imageLayerSize;
        return Arrays.copyOfRange(imageData, fromIndex, toIndex);
    }


    public int pixelDataAt(int row, int column) {
        int index = (row * width) + column;
        return imageData[index];
    }

    private void setPixelDataAt(int row, int column, int pixelData) {
        int index = (row * width) + column;
        imageData[index] = pixelData;
    }

    public int visiblePixelDataAt(int row, int column) {
        return imageLayers().mapToInt(layeredPixelImage -> layeredPixelImage.pixelDataAt(row, column))
                .filter(pixelData -> pixelData != PIXEL_DATA_TRANSPARENT)
                .findFirst()
                .orElse(PIXEL_DATA_TRANSPARENT);
    }

    public int pixelColorCount(int pixelColor) {
        return (int) Arrays.stream(imageData).filter(value -> value == pixelColor).count();
    }


    public LayeredPixelImage visiblePixelImage() {
        int[] visibleImageData = new int[imageLayerSize()];
        LayeredPixelImage layeredPixelImage = new LayeredPixelImage(width, height, visibleImageData);
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                int visiblePixelData = visiblePixelDataAt(row, column);
                layeredPixelImage.setPixelDataAt(row, column, visiblePixelData);
            }
        }
        return layeredPixelImage;
    }


    public int imageLayerCount() {
        return imageData.length / imageLayerSize();
    }

    public int imageLayerSize() {
        return height * width;
    }

    public boolean isMultyLayeredImage() {
        return imageLayerCount() > 1;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String leftPadding = "           ";
        Iterator<LayeredPixelImage> pixelImageIterator = imageLayers().iterator();
        for (int layerIndey = 1; pixelImageIterator.hasNext(); layerIndey++) {
            sb.append("Layer ").append(String.format("%3d", layerIndey)).append(": ");
            LayeredPixelImage layeredPixelImage = pixelImageIterator.next();
            for (int row = 0; row < layeredPixelImage.getHeight(); row++) {
                for (int column = 0; column < layeredPixelImage.getWidth(); column++) {
                    int pixelData = layeredPixelImage.pixelDataAt(row, column);
                    sb.append(pixelData);
                }
                sb.append("\n").append(leftPadding);
            }
            sb.setLength(sb.length() - leftPadding.length());
            sb.append("\n");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }


}
