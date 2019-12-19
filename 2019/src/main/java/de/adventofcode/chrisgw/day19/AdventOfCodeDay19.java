package de.adventofcode.chrisgw.day19;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.HashMap;
import java.util.Map;


/**
 * 2019 Day 19: Tractor Beam
 * https://adventofcode.com/2019/day/19
 */
public class AdventOfCodeDay19 {

    private final IntCodeProgram droneProgram;
    private Map<Vector2D, Boolean> affectedPoints;


    public AdventOfCodeDay19(IntCodeProgram droneProgram) {
        this.droneProgram = droneProgram;
    }


    public long countAffectedCointsForGrid(int size) {
        this.affectedPoints = new HashMap<>(size * size);
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Vector2D point = new Vector2D(x, y);
                boolean affacted = testAffactionFor(point);
                affectedPoints.put(point, affacted);
            }
        }
        return affectedPoints.values().stream().filter(Boolean.TRUE::equals).count();
    }


    private boolean testAffactionFor(Vector2D point) {
        long x = Math.round(point.getX());
        long y = Math.round(point.getY());
        droneProgram.reset();
        droneProgram.addInput(x);
        droneProgram.addInput(y);
        droneProgram.run();
        return droneProgram.nextOutput() == 1;
    }


    private boolean isAffected(Vector2D point) {
        return affectedPoints.getOrDefault(point, false);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                Vector2D point = new Vector2D(x, y);
                if (isAffected(point)) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
