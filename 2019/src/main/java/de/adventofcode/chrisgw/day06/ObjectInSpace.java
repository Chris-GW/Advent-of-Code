package de.adventofcode.chrisgw.day06;

import lombok.Data;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;


@Data
public class ObjectInSpace {

    public static final String CENTER_OF_MASS_NAME = "COM";

    private final String name;
    private ObjectInSpace centerObject;


    public ObjectInSpace(String name) {
        this.name = requireNonNull(name);
    }


    public Stream<ObjectInSpace> toCenterOfMass() {
        Predicate<ObjectInSpace> isNotCenterOfMass = Predicate.not(ObjectInSpace::isCenterOfMass);
        return Stream.iterate(this, isNotCenterOfMass, ObjectInSpace::getCenterObject);
    }


    public boolean isCenterOfMass() {
        return centerObject == null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectInSpace)) {
            return false;
        }

        ObjectInSpace that = (ObjectInSpace) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

}
