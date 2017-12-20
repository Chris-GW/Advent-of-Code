package de.adventofcode.chrisgw.day08;

import java.util.Iterator;
import java.util.regex.Pattern;


public class RotateRowPixelScreenOperation implements PixelScreenOperation {

    public static final Pattern OPERATION_PATTERN = Pattern.compile("rotate row y=(-?\\d+) by (-?\\d+)");

    private final int y;
    private final int shiftedPixel;


    public RotateRowPixelScreenOperation(int y, int shiftedPixel) {
        this.y = y;
        this.shiftedPixel = shiftedPixel;
    }


    @Override
    public void executeOperation(TwoFactorAuthentication twoFactorAuthentication) {
        boolean[] newRow = new boolean[twoFactorAuthentication.columns()];
        Iterator<Boolean> columnPixelIterator = twoFactorAuthentication.newColumnIterator(y, shiftedPixel);
        for (int i = 0; columnPixelIterator.hasNext(); i++) {
            boolean pixel = columnPixelIterator.next();
            newRow[i] = pixel;
        }

        for (int x = 0; x < newRow.length; x++) {
            boolean pixel = newRow[(x + shiftedPixel) % newRow.length];
            twoFactorAuthentication.setPixel(x, y, pixel);
        }
    }

}
