package de.adventofcode.chrisgw.day10;


import lombok.Getter;

public class CrtDisplay {

    @Getter
    private final int pixelWidth;
    @Getter
    private final int pixelHeight;
    @Getter
    private final int spriteWidth;

    private final boolean[][] pixelScreen;

    @Getter
    private int cycle;


    public CrtDisplay(int pixelWidth, int pixelHeight, int spriteWidth) {
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        this.spriteWidth = spriteWidth;
        this.pixelScreen = new boolean[pixelHeight][pixelWidth];
        this.cycle = 0;
    }


    public void drawPixel(int spritePosition) {
        int pixelPosition = currentPixelPosition();
        int pixelRow = cycle / pixelWidth;
        if (isInsideSprite(spritePosition)) {
            pixelScreen[pixelRow][pixelPosition] = true;
        }
        cycle++;
    }

    private boolean isInsideSprite(int spritePosition) {
        int positionDifference = Math.abs(currentPixelPosition() - spritePosition);
        return positionDifference <= spriteWidth / 2;
    }

    public String printCurrentCrtRow() {
        StringBuilder sb = new StringBuilder();
        int pixelRow = cycle / pixelWidth;
        for (int column = 0; column < currentPixelPosition(); column++) {
            if (hasLitPixelAt(pixelRow, column)) {
                sb.append('#');
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }


    public boolean hasLitPixelAt(int pixelRow, int column) {
        return pixelScreen[pixelRow][column];
    }


    public boolean hasFinishedDrawing() {
        return cycle >= pixelWidth * pixelHeight;
    }

    public int currentPixelPosition() {
        return cycle % pixelWidth;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < pixelHeight; row++) {
            for (int column = 0; column < pixelWidth; column++) {
                if (hasLitPixelAt(row, column)) {
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
