package de.adventofcode.chrisgw.day07;

import lombok.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class TheSumOfOtsPart {

    private final Map<Character, TaskStep> letterToStep;

    private TheSumOfOtsPart(Map<Character, TaskStep> letterToStep) {
        this.letterToStep = new HashMap<>(letterToStep);
    }

    public static TheSumOfOtsPart fromStepInstructions(Path stepInstructionFile, Duration workDuration)
            throws IOException {
        return fromStepInstructions(Files.lines(stepInstructionFile), workDuration);
    }

    public static TheSumOfOtsPart fromStepInstructions(Stream<String> stepInstructions, Duration workDuration) {
        Map<Character, TaskStep> letterToStep = new HashMap<>('Z' - 'A');

        Iterator<String> stepInstructionIterator = stepInstructions.iterator();
        while (stepInstructionIterator.hasNext()) {
            String stepInstruction = stepInstructionIterator.next();

            char stepLetter = parseStepLetter(stepInstruction);
            TaskStep taskStep = letterToStep.computeIfAbsent(stepLetter,
                    (letter) -> new TaskStep(letter, workDuration));

            char requierdStepLetter = parseRequierdStepLetter(stepInstruction);
            TaskStep requierdTaskStep = letterToStep.computeIfAbsent(requierdStepLetter,
                    (letter) -> new TaskStep(letter, workDuration));

            taskStep.addRequierdStep(requierdTaskStep);
        }

        return new TheSumOfOtsPart(letterToStep);
    }

    private static char parseStepLetter(String stepInstruction) {
        return stepInstruction.charAt("Step P must be finished before step ".length());
    }

    private static char parseRequierdStepLetter(String stepInstruction) {
        return stepInstruction.charAt("Step ".length());
    }


    public StepComplitionResult calculateStepCompletionOrder() {
        return calculateStepCompletionOrder(1);
    }

    public StepComplitionResult calculateStepCompletionOrder(int aviableWorkers) {
        Set<TaskStep> taskStepCompletionOrder = new LinkedHashSet<>(letterToStep.size());
        List<ElveWorker> elveWorkers = createElveWorkers(aviableWorkers);

        System.out.println(formatCompletionOrderHeader(elveWorkers));
        Duration elapsedDuration;
        for (elapsedDuration = Duration.ZERO; //
             taskStepCompletionOrder.size() < letterToStep.size(); //
             elapsedDuration = elapsedDuration.plusSeconds(1)) {

            assignElveWorkerWithTasks(elveWorkers, taskStepCompletionOrder);
            elveWorkers.forEach(ElveWorker::doWork);
            System.out.println(formatCompletionOrder(elapsedDuration, elveWorkers, taskStepCompletionOrder));
        }
        elapsedDuration = elapsedDuration.minusSeconds(1);

        List<TaskStep> completionOrder = new ArrayList<>(taskStepCompletionOrder);
        return new StepComplitionResult(aviableWorkers, elapsedDuration, completionOrder);
    }

    private CharSequence formatCompletionOrder(Duration elapsedDuration, List<ElveWorker> elveWorkers,
            Set<TaskStep> taskStepCompletionOrder) {
        StringBuilder sb = new StringBuilder();

        long elapsedSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedDuration.toMillis());
        sb.append(String.format("%6d\t", elapsedSeconds));

        for (ElveWorker elveWorker : elveWorkers) {
            TaskStep assignedTaskStep = elveWorker.getAssignedTaskStep();
            char assignedTaskLetter = assignedTaskStep != null ? assignedTaskStep.getLetter() : '-';
            sb.append(assignedTaskLetter).append("\t");
        }

        for (TaskStep taskStep : taskStepCompletionOrder) {
            sb.append(taskStep.getLetter());
        }
        return sb;
    }

    private static CharSequence formatCompletionOrderHeader(List<ElveWorker> elveWorkers) {
        StringBuilder sb = new StringBuilder();
        sb.append("Second").append("\t");
        for (ElveWorker elveWorker : elveWorkers) {
            sb.append(elveWorker.getId()).append("\t");
        }
        sb.append("Done");
        return sb;
    }


    private List<ElveWorker> createElveWorkers(int aviableWorkers) {
        return IntStream.rangeClosed(1, aviableWorkers).mapToObj(ElveWorker::new).collect(Collectors.toList());
    }

    private void assignElveWorkerWithTasks(List<ElveWorker> elveWorkers, Set<TaskStep> taskStepCompletionOrder) {
        currentlyCompletedStepTask(elveWorkers).forEachOrdered(taskStepCompletionOrder::add);

        List<ElveWorker> aviableElveWorkers = aviableElveWorkers(elveWorkers);
        List<TaskStep> aviableNextTaskSteps = aviableNextSteps(elveWorkers, taskStepCompletionOrder);
        int newAssignedTaskCount = Math.min(aviableElveWorkers.size(), aviableNextTaskSteps.size());
        for (int i = 0; i < newAssignedTaskCount; i++) {
            ElveWorker nextElveWorker = aviableElveWorkers.get(i);
            TaskStep nextTaskStep = aviableNextTaskSteps.get(i);
            nextElveWorker.assignTask(nextTaskStep);
        }
    }

    private Stream<TaskStep> currentlyCompletedStepTask(List<ElveWorker> elveWorkers) {
        return elveWorkers.stream()
                .filter(ElveWorker::canWorkOnNextStep)
                .map(ElveWorker::getAssignedTaskStep)
                .filter(Objects::nonNull);
    }

    private List<ElveWorker> aviableElveWorkers(List<ElveWorker> elveWorkers) {
        return elveWorkers.stream().filter(ElveWorker::canWorkOnNextStep).collect(Collectors.toList());
    }

    private List<TaskStep> aviableNextSteps(List<ElveWorker> elveWorkers, Set<TaskStep> taskStepCompletionOrder) {
        return letterToStep.values()
                .stream()
                .filter(taskStep -> !taskStepCompletionOrder.contains(taskStep))
                .filter(taskStep -> taskStep.canBeFinished(taskStepCompletionOrder))
                .filter(taskStep -> elveWorkers.stream()
                        .map(ElveWorker::getAssignedTaskStep)
                        .noneMatch(taskStep::equals))
                .collect(Collectors.toList());
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);
        Duration taskDuration = Duration.ofSeconds(60);

        TheSumOfOtsPart theSumOfOtsPart = TheSumOfOtsPart.fromStepInstructions(puzzleInputFile, taskDuration);
        StepComplitionResult stepComplitionResult1 = theSumOfOtsPart.calculateStepCompletionOrder();
        System.out.println(stepComplitionResult1);

        int aviableWorkers = 5;
        StepComplitionResult stepComplitionResult2 = theSumOfOtsPart.calculateStepCompletionOrder(aviableWorkers);
        System.out.println(stepComplitionResult2);
    }


    @Value
    public static class StepComplitionResult {

        private final int workerCound;
        private final Duration neededDuration;
        private final List<TaskStep> completionOrder;


        public String completionOrderString() {
            return completionOrder.stream().map(TaskStep::toString).collect(Collectors.joining(""));
        }

    }

}
