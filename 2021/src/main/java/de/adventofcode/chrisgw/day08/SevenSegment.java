package de.adventofcode.chrisgw.day08;

public enum SevenSegment {

    a('a'),
    b('b'),
    c('c'),
    d('d'),
    e('e'),
    f('f'),
    g('g'),
    ;

    private final char segmentLetter;

    SevenSegment(char segmentLetter) {
        this.segmentLetter = segmentLetter;
    }


    public static SevenSegment valueOf(char segmentLetter) {
        for (SevenSegment sevenSegment : SevenSegment.values()) {
            if (sevenSegment.segmentLetter() == segmentLetter) {
                return sevenSegment;
            }
        }
        throw new IllegalArgumentException("unknown segmentLetter: " + segmentLetter);
    }

    public char segmentLetter() {
        return segmentLetter;
    }

}
