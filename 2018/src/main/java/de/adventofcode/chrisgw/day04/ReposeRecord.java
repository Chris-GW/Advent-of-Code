package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.day04.GuardShift.GuardShiftBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;


@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReposeRecord {

    public static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter GUARD_SHIFT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");

    private Map<Integer, Guard> guardsById;


    public static ReposeRecord parseGuardShiftRecords(Path guardShiftRecordsFile) throws IOException {
        return parseGuardShiftRecords(Files.lines(guardShiftRecordsFile));
    }

    public static ReposeRecord parseGuardShiftRecords(Stream<String> guardShiftRecordLines) {
        Map<Integer, Guard> guardsById = new HashMap<>();
        Guard currentGuard = null;
        GuardShiftBuilder guardShiftBuilder = null;
        LocalTime sleepStartTime = null;

        Iterator<String> lineIterator = guardShiftRecordLines.sorted(Comparator.comparing(ReposeRecord::parseTimestamp))
                .iterator();
        for (int lineNumber = 1; lineIterator.hasNext(); lineNumber++) {
            String recoardLine = lineIterator.next();
            LocalDateTime timestamp = parseTimestamp(recoardLine);

            if (recoardLine.endsWith(" begins shift")) {
                if (currentGuard != null && guardShiftBuilder != null) {
                    currentGuard.addGuardShift(guardShiftBuilder.build());
                }
                int guardId = parseGuardId(recoardLine);
                currentGuard = guardsById.computeIfAbsent(guardId, Guard::new);
                guardShiftBuilder = GuardShift.builder().guard(currentGuard).startTime(timestamp);

            } else if (recoardLine.endsWith(" falls asleep")) {
                sleepStartTime = timestamp.toLocalTime();

            } else if (recoardLine.endsWith(" wakes up")) {
                if (guardShiftBuilder != null) {
                    LocalTime sleepEndTime = timestamp.toLocalTime().minusMinutes(1);
                    GuardShiftSleepTime sleepTime = new GuardShiftSleepTime(sleepStartTime, sleepEndTime);
                    guardShiftBuilder = guardShiftBuilder.sleepTime(sleepTime);
                }

            } else {
                throw new IllegalArgumentException("unexpected record line: " + recoardLine);
            }
        }

        if (currentGuard != null && guardShiftBuilder != null) {
            currentGuard.addGuardShift(guardShiftBuilder.build());
        }
        return new ReposeRecord(guardsById);
    }

    private static int parseGuardId(String readLine) {
        int guardIdStartIndex = readLine.indexOf('#') + 1;
        int guardIdEndIndex = readLine.indexOf(" begins shift");
        String guardIdStr = readLine.substring(guardIdStartIndex, guardIdEndIndex);
        return Integer.parseInt(guardIdStr);
    }

    private static LocalDateTime parseTimestamp(String readLine) {
        String timestampStr = readLine.substring(1, "yyyy-MM-dd HH:mm".length() + 1);
        return LocalDateTime.parse(timestampStr, TIMESTAMP_FORMATTER);
    }


    // part 01
    public long solveUsingStrategie01() {
        return guards().max(Comparator.comparing(Guard::totalSleepDuration))
                .map(this::calculateSolutionValue)
                .orElse(0);
    }

    private int calculateSolutionValue(Guard guard) {
        int guardId = guard.getGuardId();
        int mostFrequentlySleepMinute = guard.mostFrequentlySleepTime().getMinute();
        return guardId * mostFrequentlySleepMinute;
    }


    // part 02

    public long solveUsingStrategie02() {
        return guards().max(Comparator.comparingLong(Guard::mostFrequentlySleepTimeCount))
                .map(this::calculateSolutionValue)
                .orElse(0);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (guardsById.isEmpty()) {
            sb.append("NO GUARD SHIFTS");
            return sb.toString();
        }
        sb.append("Date   ID     Minute\n");
        sb.append("              000000000011111111112222222222333333333344444444445555555555\n");
        sb.append("              012345678901234567890123456789012345678901234567890123456789\n");


        guards().flatMap(Guard::guardShifts)
                .sorted(Comparator.comparing(GuardShift::guardShiftDay).thenComparingInt(GuardShift::getGuardId))
                .map(this::formatGuardShift)
                .forEachOrdered(sb::append);

        return sb.toString();
    }


    private StringBuilder formatGuardShift(GuardShift guardShift) {
        StringBuilder sb = new StringBuilder();
        Guard guard = guardShift.getGuard();
        sb.append(GUARD_SHIFT_DATE_FORMATTER.format(guardShift.guardShiftDay())).append("  ");
        sb.append(guard).append("  ");

        LocalTime time = GuardShift.GUARD_SHIFT_START_TIME;
        while (!time.isAfter(GuardShift.GUARD_SHIFT_END_TIME)) {
            if (guardShift.isSleepingAt(time)) {
                sb.append('#');
            } else if (guardShift.isWithinGuardShift(time)) {
                sb.append('.');
            } else {
                sb.append('x');
            }
            time = time.plusMinutes(1);
        }
        return sb.append("\n");
    }


    public Guard getGuard(int guardId) {
        return guardsById.get(guardId);
    }

    public Stream<Guard> guards() {
        return guardsById.values().stream();
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }

        Path guardShiftRecordFile = Paths.get(args[0]);

        ReposeRecord reposeRecord = ReposeRecord.parseGuardShiftRecords(guardShiftRecordFile);
        long solution01 = reposeRecord.solveUsingStrategie01();
        System.out.println("solveUsingStrategie01: " + solution01);

        long solution02 = reposeRecord.solveUsingStrategie02();
        System.out.println("solveUsingStrategie02: " + solution02);
    }

}
