package de.adventofcode.chrisgw;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import static java.time.Month.DECEMBER;


public record AdventOfCodePuzzle(Year year, int day) {

    public static final Year FIRST_ADVENT_OF_CODE_YEAR = Year.of(2015);
    public static final int ADVENT_OF_CODE_PUZZLE_COUNT = 25;
    public static final URI ADVENT_OF_CODE_BASE_URI = URI.create("https://adventofcode.com/");


    public LocalDate getDate() {
        return year().atMonth(DECEMBER).atDay(day());
    }

    public ZonedDateTime puzzleOpeningDateTime() {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        return ZonedDateTime.of(getDate(), LocalTime.of(5, 0), utc.toZoneId());
    }


    public URI puzzleURI() {
        return ADVENT_OF_CODE_BASE_URI.resolve(year() + "/day/" + day());
    }

    public URI puzzleInputURI() {
        return ADVENT_OF_CODE_BASE_URI.resolve(year() + "/day/" + day() + "/input");
    }


    @Override
    public String toString() {
        return "Advent of Code " + year() + " day " + day();
    }

}
