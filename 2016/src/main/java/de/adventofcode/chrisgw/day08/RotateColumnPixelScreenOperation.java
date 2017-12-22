package de.adventofcode.chrisgw.day08;

import java.util.Iterator;
import java.util.regex.Pattern;


public class RotateColumnPixelScreenOperation implements PixelScreenOperation {

    public static final Pattern OPERATION_PATTERN = Pattern.compile("rotate column x=(-?\\d+) by (-?\\d+)");

    private final int x;
    private final int shiftedPixel;


    public RotateColumnPixelScreenOperation(int x, int shiftedPixel) {
        this.x = x;
        this.shiftedPixel = shiftedPixel;
    }


    @Override
    public void executeOperation(TwoFactorAuthentication twoFactorAuthentication) {
        boolean[] newColumn = new boolean[twoFactorAuthentication.rows()];
        Iterator<Boolean> columnPixelIterator = twoFactorAuthentication.newColumnIterator(x);
        for (int y = shiftedPixel; columnPixelIterator.hasNext(); y++) {
            if (y >= newColumn.length) {
                y = 0;
            }
            newColumn[y] = columnPixelIterator.next();
        }

        for (int y = 0; y < newColumn.length; y++) {
            twoFactorAuthentication.setPixel(x, y, newColumn[y]);
        }
    }


}
