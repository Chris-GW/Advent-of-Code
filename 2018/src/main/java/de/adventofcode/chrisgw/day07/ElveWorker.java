package de.adventofcode.chrisgw.day07;

import lombok.Data;

import java.time.Duration;


@Data
public class ElveWorker {

    private final int id;
    private Duration remainingDuration = Duration.ZERO;
    private TaskStep assignedTaskStep;


    public boolean canWorkOnNextStep() {
        return remainingDuration == null || remainingDuration.isZero() || remainingDuration.isNegative();
    }


    public TaskStep assignTask(TaskStep taskStep) {
        TaskStep finishedTaskStep = assignedTaskStep;
        remainingDuration = taskStep.getTaskDuration();
        assignedTaskStep = taskStep;
        return finishedTaskStep;
    }


    public void doWork() {
        remainingDuration = remainingDuration.minusSeconds(1);
    }

}
