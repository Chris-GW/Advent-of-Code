package de.adventofcode.chrisgw.day21;

import java.util.Arrays;


public class ArtPixelPattern {


    private boolean[][] pixelGrid;


    private ArtPixelPattern(boolean[][] pixelGrid) {
        this.pixelGrid = pixelGrid;
    }


    public static ArtPixelPattern parseArtPixelPattern(String artPixelPatternLine) {
        String splitRegex = "\n";
        if (artPixelPatternLine.contains("/")) {
            splitRegex = "/";
        }
        String[] splitArtPixelPattern = artPixelPatternLine.split(splitRegex);
        boolean[][] pixelPattern = new boolean[splitArtPixelPattern.length][splitArtPixelPattern.length];

        for (int y = 0; y < splitArtPixelPattern.length; y++) {
            String artPixelPatternRow = splitArtPixelPattern[y];
            if (artPixelPatternRow.length() != splitArtPixelPattern.length) {
                throw new IllegalArgumentException(
                        "expect each pixel art row of same size: " + splitArtPixelPattern.length);
            }
            for (int x = 0; x < artPixelPatternRow.length(); x++) {
                pixelPattern[y][x] = artPixelPatternRow.charAt(x) == '#';
            }
        }
        return new ArtPixelPattern(pixelPattern);
    }


    public ArtPixelPattern rotatePixelPattern() {
        boolean[][] rotatedPixelPattern = new boolean[pixelGrid.length][pixelGrid.length];

        for (int y = 0; y < size(); y++) {
            for (int x = 0; x < size(); x++) {
                int newX = Math.abs(y - (size() - 1));
                rotatedPixelPattern[x][newX] = pixelGrid[y][x];
            }
        }

        return new ArtPixelPattern(rotatedPixelPattern);
    }

    public ArtPixelPattern flipPixelPattern() {
        boolean[][] flipedPixelGrid = new boolean[pixelGrid.length][pixelGrid.length];

        for (int y = 0; y < size(); y++) {
            for (int x = 0; x < size(); x++) {
                int flipX = Math.abs(x - (size() - 1));
                flipedPixelGrid[y][flipX] = pixelGrid[y][x];
            }
        }
        return new ArtPixelPattern(flipedPixelGrid);
    }


    public boolean getPixel(int x, int y) {
        return pixelGrid[y][x];
    }

    public void setPixel(int x, int y, boolean pixel) {
        pixelGrid[y][x] = pixel;
    }


    public int size() {
        return pixelGrid.length;
    }


    public String getArtPixelPattern() {
        return toString().replace("\n", "/");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ArtPixelPattern))
            return false;

        ArtPixelPattern that = (ArtPixelPattern) o;
        return Arrays.deepEquals(this.pixelGrid, that.pixelGrid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(pixelGrid);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < pixelGrid.length; y++) {
            boolean[] pixelRow = pixelGrid[y];
            for (int x = 0; x < pixelRow.length; x++) {
                if (getPixel(x, y)) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
