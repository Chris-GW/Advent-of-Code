package de.adventofcode.chrisgw.day04;

import lombok.Value;

import java.time.Duration;
import java.time.LocalTime;


@Value
public class GuardShiftSleepTime implements Comparable<GuardShiftSleepTime> {

    private LocalTime startTime;
    private LocalTime endTime;


    public boolean containsTime(LocalTime time) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }

    public Duration sleepDuration() {
        return Duration.between(startTime, endTime);
    }


    @Override
    public int compareTo(GuardShiftSleepTime otherGuardShiftSleepTime) {
        int compare = this.getStartTime().compareTo(otherGuardShiftSleepTime.getStartTime());
        if (compare == 0) {
            compare = this.getEndTime().compareTo(otherGuardShiftSleepTime.getEndTime());
        }
        return compare;
    }

}
