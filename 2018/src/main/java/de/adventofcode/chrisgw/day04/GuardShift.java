package de.adventofcode.chrisgw.day04;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;


@Value
@Builder
public class GuardShift {

    public static final LocalTime GUARD_SHIFT_START_TIME = LocalTime.of(0, 0);
    public static final LocalTime GUARD_SHIFT_END_TIME = LocalTime.of(0, 59);

    private final Guard guard;
    private final LocalDateTime startTime;

    @Singular
    private final List<GuardShiftSleepTime> sleepTimes;


    public GuardShift(Guard guard, LocalDateTime startTime, List<GuardShiftSleepTime> sleepTimes) {
        this.guard = requireNonNull(guard);
        this.startTime = requireNonNull(startTime);
        sleepTimes = new ArrayList<>(sleepTimes);
        Collections.sort(sleepTimes);
        this.sleepTimes = Collections.unmodifiableList(sleepTimes);
    }


    public boolean isSleepingAt(LocalTime time) {
        return sleepTimes.stream().anyMatch(guardShiftSleepTime -> guardShiftSleepTime.containsTime(time));
    }

    public Duration totalSleepDuration() {
        return sleepTimes.stream().map(GuardShiftSleepTime::sleepDuration).reduce(Duration.ZERO, Duration::plus);
    }


    public LocalDate guardShiftDay() {
        if (startTime.toLocalTime().isAfter(GUARD_SHIFT_END_TIME)) {
            return startTime.toLocalDate().plusDays(1);
        } else {
            return startTime.toLocalDate();
        }
    }

    public boolean isOnDay(LocalDate date) {
        return guardShiftDay().equals(date);
    }


    public boolean isWithinGuardShift(LocalTime time) {
        return !time.isBefore(guardShiftStartTime()) && !time.isAfter(GUARD_SHIFT_END_TIME);
    }

    private LocalTime guardShiftStartTime() {
        LocalTime startTime = getStartTime().toLocalTime();
        if (startTime.isAfter(GUARD_SHIFT_END_TIME)) {
            startTime = GUARD_SHIFT_START_TIME;
        }
        return startTime;
    }


    public int getGuardId() {
        return guard.getGuardId();
    }


    public static Duration guardShiftDuration() {
        return Duration.between(GuardShift.GUARD_SHIFT_START_TIME, GuardShift.GUARD_SHIFT_END_TIME);
    }

}
