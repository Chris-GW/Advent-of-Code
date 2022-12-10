package de.adventofcode.chrisgw.day10;


public class CrtDisplay {

    private final int pixelWidth = 40;
    private final int pixelHeight = 6;

    private final int spriteWidth = 3;

    private int cycle = 0;
    private final boolean[][] pixelScreen = new boolean[pixelHeight][pixelWidth];


    public void drawPixel(int spritePosition) {
        cycle++;
        int pixelPosition = (cycle - 1) % pixelWidth;
        int pixelRow = (cycle - 1) / pixelWidth;
        System.out.printf("During cycle %2d: CRT draws pixel in position %d%n", cycle, pixelPosition);
        boolean isInsideSprite = Math.abs(pixelPosition - spritePosition) <= (spriteWidth / 2);
        if (isInsideSprite) {
            pixelScreen[pixelRow][pixelPosition] = true;
        }
        System.out.printf("Current CRT row: %s%n", printCurrentRow());
    }

    private String printCurrentRow() {
        StringBuilder sb = new StringBuilder();
        int pixelPosition = (cycle - 1) % pixelWidth;
        int pixelRow = cycle / pixelWidth;
        for (int column = 0; column <= pixelPosition && pixelRow < pixelHeight; column++) {
            if (pixelScreen[pixelRow][column]) {
                sb.append('#');
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < pixelHeight; row++) {
            for (int column = 0; column < pixelWidth; column++) {
                if (pixelScreen[row][column]) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
