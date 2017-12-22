package de.adventofcode.chrisgw.day21;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ArtPixelEnhancmentRule {

    public static final Pattern PIXEL_ENHANCMENT_RULE_PATTERN = Pattern.compile("(([.#]+/?)+)\\s+=>\\s+(([.#]+/?)+)");

    private Set<ArtPixelPattern> inputPixelPatterns = new LinkedHashSet<>();
    private ArtPixelPattern outputPixelPatterns;


    private ArtPixelEnhancmentRule(ArtPixelPattern inputPixelPattern, ArtPixelPattern outputPixelPatterns) {
        calculateAllPixelInputPatterns(inputPixelPattern);
        this.outputPixelPatterns = outputPixelPatterns;
    }

    private void calculateAllPixelInputPatterns(ArtPixelPattern inputPixelPattern) {
        ArtPixelPattern flipPixelPattern = inputPixelPattern.flipPixelPattern();
        inputPixelPatterns.add(inputPixelPattern);
        inputPixelPatterns.add(flipPixelPattern);

        for (int i = 0; i < 3; i++) {
            inputPixelPattern = inputPixelPattern.rotatePixelPattern();
            flipPixelPattern = flipPixelPattern.rotatePixelPattern();
            inputPixelPatterns.add(inputPixelPattern);
            inputPixelPatterns.add(flipPixelPattern);
        }
    }


    public static ArtPixelEnhancmentRule parsePixelEnhancmentRule(String pixelEnhancmentRuleLine) {
        Matcher pixelEnhancmentRuleMatcher = PIXEL_ENHANCMENT_RULE_PATTERN.matcher(pixelEnhancmentRuleLine);
        if (!pixelEnhancmentRuleMatcher.matches()) {
            throw new IllegalArgumentException(
                    "expect art pixel enhancment rule following pattern: " + PIXEL_ENHANCMENT_RULE_PATTERN);
        }
        ArtPixelPattern inputPixelPattern = ArtPixelPattern.parseArtPixelPattern(pixelEnhancmentRuleMatcher.group(1));
        ArtPixelPattern outputPixelPattern = ArtPixelPattern.parseArtPixelPattern(pixelEnhancmentRuleMatcher.group(3));
        if (inputPixelPattern.size() != outputPixelPattern.size() - 1) {
            throw new IllegalArgumentException("expect art pixel output pattern one size greater than input");
        }
        return new ArtPixelEnhancmentRule(inputPixelPattern, outputPixelPattern);
    }


    public boolean matches(ArtPixelPattern pixelPattern) {
        return inputPixelPatterns.stream().anyMatch(otherPixelPattern -> otherPixelPattern.equals(pixelPattern));
    }

    public int size() {
        return inputPixelPatterns.stream().findAny().orElseThrow(IllegalStateException::new).size();
    }


    public ArtPixelPattern getOutputPixelPatterns() {
        return outputPixelPatterns;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ArtPixelEnhancmentRule))
            return false;

        ArtPixelEnhancmentRule that = (ArtPixelEnhancmentRule) o;

        return inputPixelPatterns != null ?
                inputPixelPatterns.equals(that.inputPixelPatterns) :
                that.inputPixelPatterns == null;
    }

    @Override
    public int hashCode() {
        return inputPixelPatterns != null ? inputPixelPatterns.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArtPixelPattern firstPixelPattern = inputPixelPatterns.stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        sb.append(firstPixelPattern.getArtPixelPattern());
        sb.append(" => ");
        sb.append(outputPixelPatterns.getArtPixelPattern());
        return sb.toString();
    }

}
