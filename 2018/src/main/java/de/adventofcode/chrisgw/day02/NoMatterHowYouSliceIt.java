package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.day02.CutRectangle.SquarePositon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class NoMatterHowYouSliceIt {

    private Collection<CutRectangle> cutRectangles;


    public NoMatterHowYouSliceIt(Collection<CutRectangle> cutRectangles) {
        this.cutRectangles = new ArrayList<>(cutRectangles);
    }

    public static NoMatterHowYouSliceIt fromRectangeSpecificationFile(Path rectangeSpecificationFile)
            throws IOException {
        List<CutRectangle> cutRectangles = Files.lines(rectangeSpecificationFile)
                .map(CutRectangle::parse)
                .collect(Collectors.toList());
        return new NoMatterHowYouSliceIt(cutRectangles);
    }


    public long countOverlappingSquares() {
        Map<SquarePositon, Long> overlappingRectangeCountPerSquarePosition = cutRectangles.stream()
                .flatMap(CutRectangle::squarePositons)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return overlappingRectangeCountPerSquarePosition.values()
                .stream()
                .filter(overlappingSquareCount -> overlappingSquareCount > 1)
                .count();
    }


    public String toString() {
        int largestX = cutRectangles.stream()
                .map(CutRectangle::bottomRight)
                .mapToInt(SquarePositon::getX)
                .max()
                .orElse(0);

        int largestY = cutRectangles.stream()
                .map(CutRectangle::bottomRight)
                .mapToInt(SquarePositon::getY)
                .max()
                .orElse(0);


        char[][] fabricPlate = new char[largestY + 1][largestX + 1];
        for (char[] row : fabricPlate) {
            Arrays.fill(row, '.');
        }
        for (CutRectangle cutRectangle : cutRectangles) {
            cutRectangle.squarePositons().forEach(squarePositon -> {
                char currentLetter = fabricPlate[squarePositon.getY()][squarePositon.getX()];
                if (currentLetter == '.') {
                    char cutRectangleLetter = cutRectangle.rectangleLetter();
                    fabricPlate[squarePositon.getY()][squarePositon.getX()] = cutRectangleLetter;
                } else {
                    fabricPlate[squarePositon.getY()][squarePositon.getX()] = 'X';
                }
            });
        }
        return Arrays.stream(fabricPlate).map(String::new).collect(Collectors.joining("\n"));
    }


    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path rectangeSpecificationFile = Paths.get(args[0]);
        NoMatterHowYouSliceIt noMatterHowYouSliceIt = NoMatterHowYouSliceIt.fromRectangeSpecificationFile(
                rectangeSpecificationFile);
        long countOverlappingSquares = noMatterHowYouSliceIt.countOverlappingSquares();
        System.out.println("countOverlappingSquares: " + countOverlappingSquares);

    }

}
