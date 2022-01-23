package de.adventofcode.chrisgw.day12;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@Data
public class CavePath implements Iterable<Cave> {

    @Getter(AccessLevel.NONE)
    private final Deque<Cave> cavePath = new ArrayDeque<>();


    public CavePath(Cave startCave) {
        cavePath.add(startCave);
    }

    public CavePath(CavePath otherCavePath) {
        this.cavePath.addAll(otherCavePath.cavePath);
    }


    public boolean canAppend(Cave cave) {
        return cave != null && cave.isBigCave() || !containsCave(cave);
    }

    public boolean containsCave(Cave cave) {
        return cavePath.contains(cave);
    }


    public Cave getLast() {
        return cavePath.getLast();
    }

    public Stream<Cave> appendableCaves() {
        return getLast().connectedCaves().filter(this::canAppend);
    }

    public boolean isComplete() {
        return getLast().isEnd();
    }


    public CavePath append(Cave cave) {
        if (!canAppend(cave)) {
            throw new IllegalArgumentException("can't append cave: " + cave);
        }
        CavePath cavePath = new CavePath(this);
        cavePath.cavePath.add(cave);
        return cavePath;
    }


    @Override
    public Iterator<Cave> iterator() {
        return cavePath.iterator();
    }

    @Override
    public String toString() {
        return cavePath.stream().map(Cave::getName).collect(joining(","));
    }

}
