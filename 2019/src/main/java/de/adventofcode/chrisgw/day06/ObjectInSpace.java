package de.adventofcode.chrisgw.day06;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;


@Data
public class ObjectInSpace {

    public static final String CENTER_OF_MASS_NAME = "COM";

    private final String name;

    @EqualsAndHashCode.Exclude
    private ObjectInSpace centerObject;


    public ObjectInSpace(String name) {
        this.name = requireNonNull(name);
    }


    public Stream<ObjectInSpace> toCenterOfMass() {
        return Stream.iterate(this.getCenterObject(), Objects::nonNull, ObjectInSpace::getCenterObject);
    }


    public boolean isCenterOfMass() {
        return centerObject == null;
    }


    @Override
    public String toString() {
        return getName();
    }

}
