package de.adventofcode.chrisgw.day04;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;


@EqualsAndHashCode
public class Guard  {

    @Getter
    private final int guardId;

    @EqualsAndHashCode.Exclude
    private Map<LocalDate, GuardShift> guardShiftPerDay = new HashMap<>();


    public Guard(int guardId) {
        this.guardId = guardId;
    }


    public Duration totalSleepDuration() {
        return guardShifts().map(GuardShift::totalSleepDuration).reduce(Duration.ZERO, Duration::plus);
    }


    public LocalTime mostFrequentlySleepTime() {
        long guardShiftDurationAsMinutes = GuardShift.guardShiftDuration().toMinutes();
        return LongStream.rangeClosed(0, guardShiftDurationAsMinutes)
                .mapToObj(GuardShift.GUARD_SHIFT_START_TIME::plusMinutes)
                .max(Comparator.comparingLong(this::countSleepTimeAt))
                .orElse(null);
    }

    public long mostFrequentlySleepTimeCount() {
        return countSleepTimeAt(mostFrequentlySleepTime());
    }

    private long countSleepTimeAt(LocalTime time) {
        return guardShifts().filter(guardShift -> guardShift.isSleepingAt(time)).count();
    }


    public Stream<GuardShift> guardShifts() {
        return guardShiftPerDay.values().stream();
    }


    public Optional<GuardShift> getGuardShiftsOnDate(LocalDate date) {
        return Optional.ofNullable(guardShiftPerDay.get(date));
    }


    public GuardShift addGuardShift(GuardShift guardShift) {
        return guardShiftPerDay.put(guardShift.guardShiftDay(), guardShift);
    }


    @Override
    public String toString() {
        return String.format("#%04d", guardId);
    }


}
