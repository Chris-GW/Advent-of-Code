package de.adventofcode.chrisgw.day13;

public class FirewallScanner {

    private int layer;
    private int range;
    private ScanDirection scanDirection = ScanDirection.DOWN;
    private int position = 0;


    public FirewallScanner(int layer, int range) {
        this.layer = layer;
        this.range = range;
    }


    public void nextScanStep() {
        if (range == 1) {
            // no op
        } else if (ScanDirection.UP.equals(scanDirection)) {
            moveUp();
        } else if (ScanDirection.DOWN.equals(scanDirection)) {
            moveDown();
        } else {
            throw new IllegalStateException("Unknown ScanDirection: " + scanDirection);
        }
    }

    private void moveUp() {
        if (--position < 0) {
            position = 1;
            scanDirection = ScanDirection.DOWN;
        }
    }

    private void moveDown() {
        if (++position >= range) {
            position = range - 2;
            scanDirection = ScanDirection.UP;
        }
    }


    public void reset() {
        position = 0;
        scanDirection = ScanDirection.DOWN;
    }


    public int getSeverity() {
        return layer * range;
    }


    public int getPosition() {
        return position;
    }

    public int getLayer() {
        return layer;
    }

    public int getRange() {
        return range;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FirewallScanner))
            return false;

        FirewallScanner that = (FirewallScanner) o;

        if (getLayer() != that.getLayer())
            return false;
        return getRange() == that.getRange();
    }

    @Override
    public int hashCode() {
        int result = getLayer();
        result = 31 * result + getRange();
        return result;
    }


    private enum ScanDirection {
        UP, DOWN;
    }

}
