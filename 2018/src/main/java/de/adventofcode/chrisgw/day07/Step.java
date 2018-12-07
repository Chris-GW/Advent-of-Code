package de.adventofcode.chrisgw.day07;

import lombok.*;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Step implements Comparable<Step> {

    @Getter
    @EqualsAndHashCode.Include
    private final char letter;
    private SortedSet<Step> requierdSteps = new TreeSet<>();
    private SortedSet<Step> nextSteps = new TreeSet<>();


    public boolean addRequierdStep(Step requierdStep) {
        requierdStep.nextSteps.add(requierdStep);
        return requierdSteps.add(requierdStep);
    }

    public boolean isFirstStep() {
        return requierdSteps.isEmpty();
    }


    public boolean canBeFinished(Set<Step> finishedSteps) {
        return finishedSteps.containsAll(requierdSteps);
    }


    public Stream<Step> requierdSteps() {
        return requierdSteps.stream();
    }

    public Stream<Step> nextSteps() {
        return nextSteps.stream();
    }


    @Override
    public int compareTo(Step otherStep) {
        return Character.compare(this.letter, otherStep.letter);
    }

    @Override
    public String toString() {
        return String.valueOf(letter);
    }

}
