package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2025/day/8">Advent of Code - day 8</a>
 */
public class AdventOfCodeDay08 extends AdventOfCodePuzzleSolver {

    private final List<JunctionBox> junctionBoxes;

    public AdventOfCodeDay08(List<String> inputLines) {
        super(Year.of(2025), 8, inputLines);
        this.junctionBoxes = inputLines()
                .map(JunctionBox::parse)
                .toList();
    }


    @Override
    public Integer solveFirstPart() {
        int connectionCount = getInputLines().size() > 20 ? 1000 : 10;
        IntStream.range(0, junctionBoxes.size())
                .mapToObj(this::junctionBoxPairs)
                .flatMap(Function.identity())
                .sorted(Comparator.comparingDouble(JunctionBoxPair::distance))
                .limit(connectionCount)
                .forEach(JunctionBoxPair::doConnection);

        Comparator<Set<JunctionBox>> setSizeComparator = Comparator.comparingInt(Set::size);
        return junctionBoxes.stream()
                .map(JunctionBox::getConnections)
                .distinct()
                .sorted(setSizeComparator.reversed())
                .mapToInt(Set::size)
                .limit(3)
                .reduce(1, (left, right) -> left * right);
    }

    private Stream<JunctionBoxPair> junctionBoxPairs(int fromIndex) {
        return IntStream.range(fromIndex + 1, junctionBoxes.size())
                .mapToObj(junctionBoxes::get)
                .map(k -> new JunctionBoxPair(junctionBoxes.get(fromIndex), k));
    }


    @Override
    public Integer solveSecondPart() {
        JunctionBoxPair lastPair = IntStream.range(0, junctionBoxes.size())
                .mapToObj(this::junctionBoxPairs)
                .flatMap(Function.identity())
                .sorted(Comparator.comparingDouble(JunctionBoxPair::distance))
                .peek(JunctionBoxPair::doConnection)
                .dropWhile(Predicate.not(this::isFullCircuit))
                .findFirst()
                .orElseThrow();
        return lastPair.distanceToWall();
    }

    private boolean isFullCircuit(JunctionBoxPair junctionBoxPair) {
        Set<JunctionBox> connections = junctionBoxPair.left().getConnections();
        return connections.size() >= junctionBoxes.size();
    }

}
