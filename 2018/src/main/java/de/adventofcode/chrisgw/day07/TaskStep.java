package de.adventofcode.chrisgw.day07;

import lombok.*;

import java.time.Duration;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskStep implements Comparable<TaskStep> {

    @Getter
    @EqualsAndHashCode.Include
    private final char letter;

    @Getter
    @EqualsAndHashCode.Include
    private final Duration taskDuration;

    public TaskStep(char letter, Duration taskDuration) {
        this.letter = letter;
        this.taskDuration = taskDuration.plusSeconds(letter - 'A' + 1);
    }

    private SortedSet<TaskStep> requierdTaskSteps = new TreeSet<>();
    private SortedSet<TaskStep> nextTaskSteps = new TreeSet<>();


    public boolean addRequierdStep(TaskStep requierdTaskStep) {
        requierdTaskStep.nextTaskSteps.add(requierdTaskStep);
        return requierdTaskSteps.add(requierdTaskStep);
    }

    public boolean isFirstStep() {
        return requierdTaskSteps.isEmpty();
    }


    public boolean canBeFinished(Set<TaskStep> finishedTaskSteps) {
        return finishedTaskSteps.containsAll(requierdTaskSteps);
    }


    @Override
    public int compareTo(TaskStep otherTaskStep) {
        return Character.compare(this.letter, otherTaskStep.letter);
    }

    @Override
    public String toString() {
        return String.valueOf(letter);
    }

}
