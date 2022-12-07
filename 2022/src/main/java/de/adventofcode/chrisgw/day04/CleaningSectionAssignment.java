package de.adventofcode.chrisgw.day04;

import java.util.stream.IntStream;


public record CleaningSectionAssignment(int fromSectionId, int toSectionId) {


    public IntStream sectionIds() {
        return IntStream.rangeClosed(fromSectionId(), toSectionId());
    }

    public boolean containsSectionId(int sectionId) {
        return fromSectionId() <= sectionId && sectionId <= toSectionId();
    }

    public boolean containsFully(CleaningSectionAssignment otherSectionAssignment) {
        return containsSectionId(otherSectionAssignment.fromSectionId()) //
                && containsSectionId(otherSectionAssignment.toSectionId());
    }

    public boolean hasOverlapWith(CleaningSectionAssignment otherSectionAssignment) {
        return this.sectionIds().anyMatch(otherSectionAssignment::containsSectionId);
    }


    @Override
    public String toString() {
        return fromSectionId() + "-" + toSectionId();
    }

}
