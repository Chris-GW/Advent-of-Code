package de.adventofcode.chrisgw.day12;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Data
public class Cave {

    public static final String START_CAVE_NAME = "start";
    public static final String END_CAVE_NAME = "end";

    private final String name;

    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private final Set<Cave> connectedCaves = new HashSet<>();


    public void connect(Cave otherCave) {
        otherCave.connectedCaves.add(this);
        connectedCaves.add(otherCave);
    }

    public boolean isBigCave() {
        return StringUtils.isAllUpperCase(name);
    }

    public boolean isSmallCave() {
        return StringUtils.isAllLowerCase(name) && !isStart() && !isEnd();
    }


    public boolean isStart() {
        return START_CAVE_NAME.equals(name);
    }

    public boolean isEnd() {
        return END_CAVE_NAME.equals(name);
    }


    public Stream<Cave> connectedCaves() {
        return connectedCaves.stream();
    }


    @Override
    public String toString() {
        return name;
    }

}
