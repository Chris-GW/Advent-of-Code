package de.adventofcode.chrisgw.day10;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;


public class TheStarsAlign {


    private Set<FloatingPoint> floatingPoints;
    private List<FloatingPointMessageLetter> messageLetters;
    private Map<Integer, Map<Integer, FloatingPoint>> floatingPointsByPosition;

    @Getter
    private Duration elapsedDuration = Duration.ZERO;


    public TheStarsAlign(Path floatingPointNoteFile) throws IOException {
        this(Files.lines(floatingPointNoteFile));
    }

    public TheStarsAlign(Stream<String> floatingPointNotes) {
        this(floatingPointNotes.map(FloatingPoint::parseFloatingPointNote).collect(Collectors.toList()));
    }

    public TheStarsAlign(List<FloatingPoint> floatingPoints) {
        this.floatingPoints = new HashSet<>(floatingPoints);
        this.floatingPointsByPosition = new HashMap<>();
        moveFor(Duration.ZERO);
    }


    public Duration moveFor(Duration duration) {
        this.floatingPoints = floatingPoints() //
                .map(floatingPoint -> floatingPoint.moveFor(duration)) //
                .collect(Collectors.toSet());
        this.floatingPointsByPosition.clear();
        this.floatingPoints.forEach(floatingPoint -> {
            Map<Integer, FloatingPoint> floatingPointRow = this.floatingPointsByPosition.computeIfAbsent(
                    floatingPoint.getY(), y -> new HashMap<>());
            floatingPointRow.put(floatingPoint.getX(), floatingPoint);
        });
        this.messageLetters = collectFloatingPointMessageLetters();
        this.elapsedDuration = elapsedDuration.plus(duration);
        return this.elapsedDuration;

    }

    private List<FloatingPointMessageLetter> collectFloatingPointMessageLetters() {
        List<FloatingPointMessageLetter> floatingPointMessageLetters = new ArrayList<>();
        Set<FloatingPoint> unusedFloatingPoints = new HashSet<>(floatingPoints);
        while (unusedFloatingPoints.size() > 0) {
            FloatingPoint floatingPoint = unusedFloatingPoints.stream()
                    .findAny()
                    .orElseThrow(IllegalStateException::new);
            FloatingPointMessageLetter floatingPointMessageLetter = new FloatingPointMessageLetter(floatingPoint);
            unusedFloatingPoints.remove(floatingPoint);
            collectFloatingPointMessageLetters(floatingPointMessageLetter, floatingPoint, unusedFloatingPoints);
            if (floatingPointMessageLetter.isValidLetter()) {
                floatingPointMessageLetters.add(floatingPointMessageLetter);
            }
        }
        return floatingPointMessageLetters;
    }

    private void collectFloatingPointMessageLetters(FloatingPointMessageLetter messageLetter,
            FloatingPoint currentFloatingPoint, Set<FloatingPoint> unusedFloatingPoints) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int x = currentFloatingPoint.getX() + dx;
                int y = currentFloatingPoint.getY() + dy;
                FloatingPoint nearbyFloatingPoint = getFloatingPointAt(x, y).orElse(null);
                if (nearbyFloatingPoint != null && unusedFloatingPoints.contains(nearbyFloatingPoint)) {
                    unusedFloatingPoints.remove(nearbyFloatingPoint);
                    messageLetter.addConnectedFloatingPoint(nearbyFloatingPoint);
                    collectFloatingPointMessageLetters(messageLetter, nearbyFloatingPoint, unusedFloatingPoints);
                }
            }
        }
    }

    public boolean isFormingFloatingPointMessage() {
        return messageLetters.size() >= 4;
    }


    public String formatFloatingPointMessage(FloatingPoint topLeft, FloatingPoint bottomRight) {
        StringBuilder sb = new StringBuilder();
        for (int y = topLeft.getY(); y <= bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x <= bottomRight.getX(); x++) {
                if (hasFloatingPointAt(y, x)) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString().trim();
    }

    public String formatFloatingPointMessage() {
        if (messageLetters.stream().noneMatch(FloatingPointMessageLetter::isValidLetter)) {
            return "";
        }
        FloatingPoint topLeft = messageLetters.stream().map(FloatingPointMessageLetter::topLeft)
                .min(Comparator.comparingInt(FloatingPoint::getX))
                .orElse(null);

        FloatingPoint bottomRight = messageLetters.stream().map(FloatingPointMessageLetter::bottomRight)
                .max(Comparator.comparingInt(FloatingPoint::getX))
                .orElse(null);

        return formatFloatingPointMessage(topLeft, bottomRight);
    }

    private boolean hasFloatingPointAt(int y, int x) {
        return getFloatingPointAt(x, y).isPresent();
    }

    private Optional<FloatingPoint> getFloatingPointAt(int x, int y) {
        Map<Integer, FloatingPoint> floatingPointRow = floatingPointsByPosition.getOrDefault(y, emptyMap());
        FloatingPoint floatingPoint = floatingPointRow.get(x);
        return Optional.ofNullable(floatingPoint);
    }

    private Stream<FloatingPoint> floatingPoints() {
        return this.floatingPoints.stream();
    }


    public static long toSeconds(Duration duration) {
        return TimeUnit.MILLISECONDS.toSeconds(duration.toMillis());
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);
        TheStarsAlign theStarsAlign = new TheStarsAlign(puzzleInputFile);
        while (!theStarsAlign.isFormingFloatingPointMessage()) {
            theStarsAlign.moveFor(Duration.ofSeconds(1));
        }

        String floatingPointMessage = theStarsAlign.formatFloatingPointMessage();
        long elapsedSeconds = TheStarsAlign.toSeconds(theStarsAlign.getElapsedDuration());
        System.out.println("After " + elapsedSeconds + "s:");
        System.out.println(floatingPointMessage);
    }

}
