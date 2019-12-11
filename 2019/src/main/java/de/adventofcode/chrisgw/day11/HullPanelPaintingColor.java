package de.adventofcode.chrisgw.day11;

public enum HullPanelPaintingColor {

    BLACK(0), WHITE(1);

    private final int colorCode;

    HullPanelPaintingColor(int colorCode) {this.colorCode = colorCode;}


    public int getColorCode() {
        return colorCode;
    }

}
