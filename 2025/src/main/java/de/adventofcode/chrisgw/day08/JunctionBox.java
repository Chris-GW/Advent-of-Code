package de.adventofcode.chrisgw.day08;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public final class JunctionBox {

    private final int x;
    private final int y;
    private final int z;
    private Set<JunctionBox> connections = new HashSet<>();


    public JunctionBox(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        connections.add(this);
    }


    public void connectTo(JunctionBox otherJunctionBox) {
        this.connections.addAll(otherJunctionBox.connections);
        this.connections.forEach(junctionBox -> junctionBox.connections = this.connections);
    }


    public static JunctionBox parse(String line) {
        String[] split = line.split(",");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        int z = Integer.parseInt(split[2]);
        return new JunctionBox(x, y, z);
    }


    public double distanceTo(JunctionBox other) {
        double dx = (double) other.x() - this.x();
        double dy = (double) other.y() - this.y();
        double dz = (double) other.z() - this.z();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }


    public Set<JunctionBox> getConnections() {
        return connections;
    }


    public int x() {return x;}

    public int y() {return y;}

    public int z() {return z;}


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (JunctionBox) obj;
        return this.x == that.x &&
                this.y == that.y &&
                this.z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "(%d;%d;%d)".formatted(x, y, z);
    }
}
