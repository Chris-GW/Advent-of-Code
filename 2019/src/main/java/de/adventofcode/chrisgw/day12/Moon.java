package de.adventofcode.chrisgw.day12;

import lombok.Data;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
public class Moon {

    public static final Pattern MOON_POSITION_PATTERN = Pattern.compile("<x=(-?\\d+), y=(-?\\d+), z=(-?\\d+)>");

    private int[] position;
    private int[] velocity;
    private int[] startPosition;


    public Moon(int x, int y, int z) {
        this(new int[] { x, y, z });
    }

    public Moon(int[] startPosition) {
        this.position = Arrays.copyOf(startPosition, startPosition.length);
        this.velocity = new int[this.position.length];
        this.startPosition = Arrays.copyOf(startPosition, startPosition.length);
    }


    public static Moon parseMoonPosition(String moonPositionStr) {
        Matcher matcher = MOON_POSITION_PATTERN.matcher(moonPositionStr);
        if (matcher.matches()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int z = Integer.parseInt(matcher.group(3));
            return new Moon(x, y, z);
        } else {
            throw new IllegalArgumentException("Expect moon position matching: " + MOON_POSITION_PATTERN);
        }
    }


    public void applyGravity(Moon otherMoon) {
        for (int i = 0; i < dimension(); i++) {
            int coordinate = this.position[i];
            int otherCoordinate = otherMoon.position[i];
            if (coordinate < otherCoordinate) {
                this.velocity[i]++;
                otherMoon.velocity[i]--;
            } else if (coordinate > otherCoordinate) {
                this.velocity[i]--;
                otherMoon.velocity[i]++;
            }
        }
    }


    public void applyVelocity() {
        for (int i = 0; i < dimension(); i++) {
            position[i] += velocity[i];
        }
    }


    public int totalEnergy() {
        return potentialEnergy() * kineticEnergy();
    }

    public int potentialEnergy() {
        return Arrays.stream(position).map(Math::abs).sum();
    }

    public int kineticEnergy() {
        return Arrays.stream(velocity).map(Math::abs).sum();
    }


    public boolean isAtStartPoisitionForCoordinate(int i) {
        return velocity[i] == 0 && startPosition[i] == position[i];
    }

    public int dimension() {
        return position.length;
    }


    @Override
    public String toString() {
        String pos = String.format("pos=<x=%2d, y=%2d, z=%2d>", position[0], position[1], position[2]);
        String vel = String.format("pos=<x=%2d, y=%2d, z=%2d>", velocity[0], velocity[1], velocity[2]);
        return pos + ", " + vel;
    }

}
