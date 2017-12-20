package de.adventofcode.chrisgw.day20;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Particle {

    public static final Pattern PARTICLE_LINE_PATTERN = Pattern.compile(
            "p=<(-?\\d+),(-?\\d+),(-?\\d+)>,\\s+v=<(-?\\d+),(-?\\d+),(-?\\d+)>, a=<(-?\\d+),(-?\\d+),(-?\\d+)>");

    public int x;
    public int y;
    public int z;

    public int accelerationX;
    public int accelerationY;
    public int accelerationZ;

    public int velocityX;
    public int velocityY;
    public int velocityZ;


    public static Particle parseParticleLine(String particleLine) {
        Matcher particleMatcher = PARTICLE_LINE_PATTERN.matcher(particleLine);
        if (!particleMatcher.matches()) {
            throw new IllegalArgumentException(
                    "expect particle line of pattern " + PARTICLE_LINE_PATTERN + ", but was: " + particleLine);
        }
        Particle particle = new Particle();
        particle.x = Integer.parseInt(particleMatcher.group(1));
        particle.y = Integer.parseInt(particleMatcher.group(2));
        particle.z = Integer.parseInt(particleMatcher.group(3));

        particle.velocityX = Integer.parseInt(particleMatcher.group(4));
        particle.velocityY = Integer.parseInt(particleMatcher.group(5));
        particle.velocityZ = Integer.parseInt(particleMatcher.group(6));

        particle.accelerationX = Integer.parseInt(particleMatcher.group(7));
        particle.accelerationY = Integer.parseInt(particleMatcher.group(8));
        particle.accelerationZ = Integer.parseInt(particleMatcher.group(9));

        return particle;
    }


    public void nextTick() {
        velocityX += accelerationX;
        velocityY += accelerationY;
        velocityZ += accelerationZ;

        x += velocityX;
        y += velocityY;
        z += velocityZ;
    }

    public int getDistanceToOrigin() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }


    private static String formatVector(int x, int y, int z) {
        return String.format("<%2d,%2d,%2d>", x, y, z);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Particle))
            return false;

        Particle particle = (Particle) o;

        if (x != particle.x)
            return false;
        if (y != particle.y)
            return false;
        if (z != particle.z)
            return false;
        if (accelerationX != particle.accelerationX)
            return false;
        if (accelerationY != particle.accelerationY)
            return false;
        if (accelerationZ != particle.accelerationZ)
            return false;
        if (velocityX != particle.velocityX)
            return false;
        if (velocityY != particle.velocityY)
            return false;
        return velocityZ == particle.velocityZ;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        result = 31 * result + accelerationX;
        result = 31 * result + accelerationY;
        result = 31 * result + accelerationZ;
        result = 31 * result + velocityX;
        result = 31 * result + velocityY;
        result = 31 * result + velocityZ;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("p=").append(formatVector(x, y, z)).append(", ");
        sb.append("v=").append(formatVector(velocityX, velocityY, velocityZ)).append(", ");
        sb.append("a=").append(formatVector(accelerationX, accelerationY, accelerationZ));
        return sb.toString();
    }

}
