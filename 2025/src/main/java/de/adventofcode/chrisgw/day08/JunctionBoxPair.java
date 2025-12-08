package de.adventofcode.chrisgw.day08;

record JunctionBoxPair(JunctionBox left, JunctionBox right, double distance) {

    JunctionBoxPair(JunctionBox left, JunctionBox right) {
        this(left, right, left.distanceTo(right));
    }


    public void doConnection() {
        left.connectTo(right);
    }


    public int distanceToWall() {
        return left().x() * right().x();
    }

}
