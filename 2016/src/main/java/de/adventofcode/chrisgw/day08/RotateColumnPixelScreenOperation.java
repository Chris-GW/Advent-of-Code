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
        Iterator<Boolean> rowPixelIterator = twoFactorAuthentication.newRowIterator(x, shiftedPixel);
        for (int i = 0; rowPixelIterator.hasNext(); i++) {
            boolean pixel = rowPixelIterator.next();
            newColumn[i] = pixel;
        }

        for (int y = 0; y < newColumn.length; y++) {
            boolean pixel = newColumn[(y + shiftedPixel) % newColumn.length];
            twoFactorAuthentication.setPixel(x, y, pixel);
        }
    }


}
