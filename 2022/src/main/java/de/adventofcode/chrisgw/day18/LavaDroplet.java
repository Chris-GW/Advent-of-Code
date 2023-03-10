package de.adventofcode.chrisgw.day18;

import java.util.Arrays;
import java.util.stream.Stream;


public record LavaDroplet(int x, int y, int z) {

    public static LavaDroplet parseLavaDroplet(String positionStr) {
        String[] split = positionStr.split(",");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        int z = Integer.parseInt(split[2]);
        return new LavaDroplet(x, y, z);
    }


    public LavaDroplet neighborOn(CubeSide cubeSide) {
        int x = this.x() - cubeSide.dx;
        int y = this.y() - cubeSide.dy;
        int z = this.z() - cubeSide.dz;
        return new LavaDroplet(x, y, z);
    }

    public Stream<LavaDroplet> neighbors() {
        return Arrays.stream(CubeSide.values()).map(this::neighborOn);
    }

}
