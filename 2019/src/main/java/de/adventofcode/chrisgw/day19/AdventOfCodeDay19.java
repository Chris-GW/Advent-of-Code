package de.adventofcode.chrisgw.day19;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.*;
import java.util.stream.Stream;


/**
 * 2019 Day 19: Tractor Beam
 * https://adventofcode.com/2019/day/19
 */
public class AdventOfCodeDay19 {

    private final IntCodeProgram droneProgram;
    private int gridSize;
    private Map<Vector2D, Boolean> affectedPoints;

    private Line leftTractorBeam;
    private Line rightTractorBeam;
    private Map<Long, AffectedBeamRow> affectedRows = new HashMap<>();
    private AffectedBeamRow uperShipRow;
    private AffectedBeamRow lowerShipRow;


    public AdventOfCodeDay19(IntCodeProgram droneProgram) {
        this.droneProgram = droneProgram;
    }


    public long countAffectedPointsForGrid(int size) {
        this.affectedPoints = new HashMap<>(size * size);
        this.gridSize = size;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Vector2D point = new Vector2D(x, y);
                boolean affacted = testAffactionFor(point);
                affectedPoints.put(point, affacted);
            }
        }
        return affectedPoints.values().stream().filter(Boolean.TRUE::equals).count();
    }

    private boolean isAffected(Vector2D point) {
        long row = Math.round(point.getY());
        long x = Math.round(point.getX());
        if (row < gridSize && x < gridSize) {
            return affectedPoints.get(point);
        } else {
            return false;
        }
    }


    public long findShip(int shipSize) {
        int gridSize = 20;
        countAffectedPointsForGrid(gridSize);
        lowerShipRow = findLastAffectedBeamRow(gridSize);
        putAffectedRow(lowerShipRow);

        for (long row = lowerShipRow.getRow() + 1; true; row++) {
            lowerShipRow = scanAffectedBeamRow(row);
            uperShipRow = affectedRows.get(lowerShipRow.getRow() - shipSize + 1);
            if (fitsShip(shipSize, lowerShipRow, uperShipRow)) {
                break;
            }
        }
        long x = lowerShipRow.getLeft();
        long y = uperShipRow.getRow();
        return x * 10000 + y;
    }

    private AffectedBeamRow findLastAffectedBeamRow(int gridSize) {
        Vector2D startTopRight = new Vector2D(gridSize - 2, 0);
        Vector2D stepDown = new Vector2D(0, 1);
        Vector2D lastRight = Stream.iterate(startTopRight, vector2D -> vector2D.add(stepDown))
                .filter(this::isAffected)
                .findFirst()
                .orElseThrow();

        Vector2D stepToLeft = new Vector2D(-1, 0);
        Vector2D lastLeft = Stream.iterate(lastRight, this::isAffected, vector2D -> vector2D.add(stepToLeft))
                .min(Comparator.comparingDouble(Vector2D::getX))
                .orElseThrow();
        return new AffectedBeamRow(lastLeft, lastRight);
    }


    private boolean fitsShip(int shipSize, AffectedBeamRow lowerRow, AffectedBeamRow upperRow) {
        if (upperRow == null) {
            return false;
        }
        long upperRight = upperRow.getRight();
        long lowerLeft = lowerRow.getLeft();
        long width = upperRight - lowerLeft + 1;
        return width == shipSize;
    }


    private AffectedBeamRow scanAffectedBeamRow(long row) {
        Line horizontalLine = new Line(new Vector2D(0, row), new Vector2D(1, row), 1.0e-6);
        Vector2D leftIntersection = findOuterCorner(leftTractorBeam.intersection(horizontalLine));
        Vector2D rightIntersection = findOuterCorner(rightTractorBeam.intersection(horizontalLine));
        AffectedBeamRow affectedBeamRow = new AffectedBeamRow(leftIntersection, rightIntersection);
        putAffectedRow(affectedBeamRow);
        return affectedBeamRow;
    }

    private Vector2D findOuterCorner(Vector2D point) {
        Vector2D leftStep = new Vector2D(-1, 0);
        Vector2D rightStep = new Vector2D(1, 0);

        Iterator<Vector2D> leftStepIterator = Stream.iterate(point, leftStep::add).skip(1).iterator();
        Iterator<Vector2D> rightStepIterator = Stream.iterate(point, rightStep::add).skip(1).iterator();

        Deque<Boolean> affectedResults = new ArrayDeque<>();
        affectedResults.add(testAffactionFor(point));
        while (true) {
            Vector2D left = leftStepIterator.next();
            boolean isLeftAffacted = testAffactionFor(left);
            Vector2D right = rightStepIterator.next();
            boolean isRightAffacted = testAffactionFor(right);

            if (isLeftAffacted && !affectedResults.peekFirst()) {
                return left;
            } else if (!isLeftAffacted && affectedResults.peekFirst()) {
                return left.subtract(leftStep);

            } else if (isRightAffacted && !affectedResults.peekLast()) {
                return right;
            } else if (!isRightAffacted && affectedResults.peekLast()) {
                return right.subtract(rightStep);
            }
            affectedResults.addFirst(isLeftAffacted);
            affectedResults.addLast(isRightAffacted);
        }
    }


    private void putAffectedRow(AffectedBeamRow lastAffectedBeamRow) {
        long row = lastAffectedBeamRow.getRow();
        affectedRows.put(row, lastAffectedBeamRow);
        leftTractorBeam = lastAffectedBeamRow.leftTractorBeam();
        rightTractorBeam = lastAffectedBeamRow.rightTractorBeam();
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < affectedPoints.size(); y++) {
            for (int x = 0; x < affectedPoints.size(); x++) {
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
