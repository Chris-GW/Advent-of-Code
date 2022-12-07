package de.adventofcode.chrisgw.day04;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CleaningSectionAssignmentPair extends MutablePair<CleaningSectionAssignment, CleaningSectionAssignment> {

    public static final Pattern SECTION_ASSIGNMENT_PATTERN = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");


    public CleaningSectionAssignmentPair(CleaningSectionAssignment left, CleaningSectionAssignment right) {
        super(left, right);
    }

    public static CleaningSectionAssignmentPair of(final CleaningSectionAssignment left,
            final CleaningSectionAssignment right) {
        return new CleaningSectionAssignmentPair(left, right);
    }

    public static CleaningSectionAssignmentPair parseCleaningSectionAssignmentPair(String sectionAssignmentString) {
        Matcher matcher = SECTION_ASSIGNMENT_PATTERN.matcher(sectionAssignmentString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Expect sectionAssignment matching pattern '%s', but was: %s".formatted(SECTION_ASSIGNMENT_PATTERN,
                            sectionAssignmentString));
        }
        int firstFromSectionId = Integer.parseInt(matcher.group(1));
        int firstToSectionId = Integer.parseInt(matcher.group(2));
        var firstSectionAssignment = new CleaningSectionAssignment(firstFromSectionId, firstToSectionId);

        int secondFromSectionId = Integer.parseInt(matcher.group(3));
        int secondToSectionId = Integer.parseInt(matcher.group(4));
        var secondSectionAssignment = new CleaningSectionAssignment(secondFromSectionId, secondToSectionId);
        return CleaningSectionAssignmentPair.of(firstSectionAssignment, secondSectionAssignment);
    }


    public boolean hasFullyContainedSectionAssignment() {
        return left.containsFully(right) || right.containsFully(left);
    }


    @Override
    public String toString() {
        return getLeft() + "," + getRight();
    }

}
