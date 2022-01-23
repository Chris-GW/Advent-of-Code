package de.adventofcode.chrisgw.day12;

public class CavePathWithTwiceSmallCave extends CavePath {

    private Cave smallCaveTwice = null;


    public CavePathWithTwiceSmallCave(Cave startCave) {
        super(startCave);
    }

    public CavePathWithTwiceSmallCave(CavePath otherCavePath) {
        super(otherCavePath);
    }


    @Override
    public boolean canAppend(Cave cave) {
        return super.canAppend(cave) || isSmallCaveTwice(cave);
    }

    private boolean isSmallCaveTwice(Cave cave) {
        return smallCaveTwice == null && cave.isSmallCave();
    }


    @Override
    public CavePathWithTwiceSmallCave append(Cave cave) {
        var newCavePath = new CavePathWithTwiceSmallCave(super.append(cave));
        newCavePath.smallCaveTwice = this.smallCaveTwice;
        if (cave.isSmallCave() && this.containsCave(cave)) {
            newCavePath.smallCaveTwice = cave;
        }
        return newCavePath;
    }

}
