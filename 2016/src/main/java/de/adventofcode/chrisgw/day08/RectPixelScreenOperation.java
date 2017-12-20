package de.adventofcode.chrisgw.day08;

import java.util.regex.Pattern;


public class RectPixelScreenOperation implements PixelScreenOperation {

    public static final Pattern OPERATION_PATTERN = Pattern.compile("rect (-?\\d+)x(-?\\d+)");

    private final int a;
    private final int b;


    public RectPixelScreenOperation(int a, int b) {
        this.a = a;
        this.b = b;
    }


    @Override
    public void executeOperation(TwoFactorAuthentication twoFactorAuthentication) {
        for (int y = 0; y < b; y++) {
            for (int x = 0; x < a; x++) {
                twoFactorAuthentication.setPixel(x, y, true);
            }
        }
    }

}
