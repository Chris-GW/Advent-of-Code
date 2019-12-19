package de.adventofcode.chrisgw.day19;

import lombok.Data;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


@Data
public class AffectedBeamRow {

    private final long row;
    private final long left;
    private final long right;


    public AffectedBeamRow(Vector2D left, Vector2D right) {
        this.row = Math.round(left.getY());
        this.left = Math.round(left.getX());
        this.right = Math.round(right.getX());
    }


    public boolean isAffected(Vector2D point) {
        long row = Math.round(point.getY());
        long x = Math.round(point.getX());
        return this.getRow() == row && isAffected(x);
    }

    public boolean isAffected(long x) {
        return left <= x && x <= right;
    }


    public long length() {
        return right - left;
    }


    public Line leftTractorBeam() {
        return new Line(new Vector2D(0, 0), getLeftVector(), 1.0e-6);
    }

    public Line rightTractorBeam() {
        return new Line(new Vector2D(0, 0), getRightVector(), 1.0e-6);
    }


    public Vector2D getLeftVector() {
        return new Vector2D(left, row);
    }

    public Vector2D getRightVector() {
        return new Vector2D(right, row);
    }

}
