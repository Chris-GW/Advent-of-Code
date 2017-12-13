package de.adventofcode.chrisgw.day13;

public class OptimazePacketScanner {

    private int layer;
    private int range;
    private int cycleSize;


    public OptimazePacketScanner(int layer, int range) {
        this.layer = layer;
        this.range = range;
        this.cycleSize = (this.range - 1) * 2;
    }


    public boolean isOnTop(int picosecond) {
        return getPosition(picosecond) == 0;
    }

    public int getPosition(int picosecond) {
        int modVal = picosecond % cycleSize;
        if (modVal < range) {
            return modVal;
        } else {
            return cycleSize - modVal;
        }
    }


    public int getSeverity() {
        return layer * range;
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


}
