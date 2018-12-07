package de.adventofcode.chrisgw.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TheSumOfOtsPart {

    private final Map<Character, Step> letterToStep;
    private final Step firstStep;

    private TheSumOfOtsPart(Map<Character, Step> letterToStep) {
        this.letterToStep = new HashMap<>(letterToStep);
        this.firstStep = letterToStep.values().stream().filter(Step::isFirstStep).findAny().orElse(null);
    }

    public static TheSumOfOtsPart fromStepInstructions(Path stepInstructionFile) throws IOException {
        return fromStepInstructions(Files.lines(stepInstructionFile));
    }

    public static TheSumOfOtsPart fromStepInstructions(Stream<String> stepInstructions) {
        Map<Character, Step> letterToStep = new HashMap<>('Z' - 'A');

        Iterator<String> stepInstructionIterator = stepInstructions.iterator();
        while (stepInstructionIterator.hasNext()) {
            String stepInstruction = stepInstructionIterator.next();
            Step step = parseStep(letterToStep, stepInstruction);
            Step requierdStep = parseRequierdStep(letterToStep, stepInstruction);
            step.addRequierdStep(requierdStep);
        }

        return new TheSumOfOtsPart(letterToStep);
    }

    private static Step parseStep(Map<Character, Step> letterToStep, String stepInstruction) {
        char stepLetter = stepInstruction.charAt("Step P must be finished before step ".length());
        return letterToStep.computeIfAbsent(stepLetter, Step::new);
    }

    private static Step parseRequierdStep(Map<Character, Step> letterToStep, String stepInstruction) {
        char stepLetter = stepInstruction.charAt("Step ".length());
        return letterToStep.computeIfAbsent(stepLetter, Step::new);
    }


    public Stream<Step> getStepCompletionOrder() {
        Set<Step> stepCompletionOrder = new LinkedHashSet<>(letterToStep.size());
        stepCompletionOrder.add(firstStep);
        while (stepCompletionOrder.size() < letterToStep.size()) {
            Optional<Step> nextStep = findNextStep(stepCompletionOrder);
            nextStep.ifPresent(stepCompletionOrder::add);
        }
        return stepCompletionOrder.stream();
    }

    private Optional<Step> findNextStep(Set<Step> stepCompletionOrder) {
        return letterToStep.values()
                .stream()
                .filter(step -> !stepCompletionOrder.contains(step))
                .filter(step -> step.canBeFinished(stepCompletionOrder))
                .findFirst();
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);
        TheSumOfOtsPart theSumOfOtsPart = TheSumOfOtsPart.fromStepInstructions(puzzleInputFile);
        String stepCompletionOrder = toStepLetterString(theSumOfOtsPart.getStepCompletionOrder());
        System.out.println("stepCompletionOrder: " + stepCompletionOrder);
    }

    public static String toStepLetterString(Stream<Step> stepComplitionOrder) {
        return stepComplitionOrder.map(Step::getLetter).map(String::valueOf).collect(Collectors.joining(""));
    }

}
