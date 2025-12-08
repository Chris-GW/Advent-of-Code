package de.adventofcode.chrisgw.day08;

public record JunctionBox(int x, int y, int z) {


    public static JunctionBox parse(String line) {
        String[] split = line.split(",");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        int z = Integer.parseInt(split[2]);
        return new JunctionBox(x, y, z);
    }


    public double distanceTo(JunctionBox other) {
        double distance = Math.pow(this.x() + other.x(), 2);
        distance += Math.pow(this.y() + other.y(), 2);
        distance += Math.pow(this.z() + other.z(), 2);
        return Math.sqrt(distance);
    }

}
