package de.adventofcode.chrisgw.day13;

import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record PaperFoldInstruction(boolean foldHorizontal, int position)
        implements UnaryOperator<PaperDot> {

    public static final Pattern PAPER_FOLD_INSTRUCTION_PATTERN = Pattern.compile("fold along ([yx])=(\\d+)");

    public static PaperFoldInstruction parsePaperFoldInstruction(String line) {
        Matcher matcher = PAPER_FOLD_INSTRUCTION_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect line matches '%s', but was: %s"
                    .formatted(PAPER_FOLD_INSTRUCTION_PATTERN, line));
        }
        boolean foldHorizontal = "y".equals(matcher.group(1));
        int position = Integer.parseInt(matcher.group(2));
        return new PaperFoldInstruction(foldHorizontal, position);
    }


    @Override
    public PaperDot apply(PaperDot dot) {
        if (foldHorizontal && dot.y() > position) {
            int distance = dot.y() - position;
            int y = position - distance;
            return new PaperDot(dot.x(), y);
        } else if (!foldHorizontal && dot.x() > position) {
            int distance = dot.x() - position;
            int x = position - distance;
            return new PaperDot(x, dot.y());
        } else {
            return dot;
        }
    }


    @Override
    public String toString() {
        if (foldHorizontal) {
            return "fold along y=" + position;
        } else {
            return "fold along x=" + position;
        }
    }

}
