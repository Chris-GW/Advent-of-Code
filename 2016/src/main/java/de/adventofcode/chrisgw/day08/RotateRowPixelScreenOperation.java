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
        Iterator<Boolean> rowPixelIterator = twoFactorAuthentication.newRowIterator(y);
        for (int x = shiftedPixel; rowPixelIterator.hasNext(); x++) {
            if (x >= newRow.length) {
                x = 0;
            }
            newRow[x] = rowPixelIterator.next();
        }

        for (int x = 0; x < newRow.length; x++) {
            twoFactorAuthentication.setPixel(x, y, newRow[x]);
        }
    }

}
