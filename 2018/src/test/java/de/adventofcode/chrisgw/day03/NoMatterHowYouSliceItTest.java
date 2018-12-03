package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class NoMatterHowYouSliceItTest {

    @Test
    public void parseCutRectangle() {
        CutRectangle expectedCutRectangle = CutRectangle.builder()
                .id(123)
                .distanceLeft(3)
                .distanceTop(2)
                .wide(5)
                .tall(4)
                .build();
        String rectangleSpecification = "#123 @ 3,2: 5x4";

        CutRectangle cutRectangle = CutRectangle.parse(rectangleSpecification);
        assertEquals(rectangleSpecification, expectedCutRectangle, cutRectangle);

        System.out.println(new NoMatterHowYouSliceIt(Collections.singletonList(cutRectangle)));
    }


    // part 01

    @Test
    public void part_01_countOverlappingSquares_example_01() {
        long expectedOverlappingSquareCount = 4;
        String[] cutRectangleSpecifications = new String[] { //
                "#1 @ 1,3: 4x4", //
                "#2 @ 3,1: 4x4", //
                "#3 @ 5,5: 2x2" };
        Collection<CutRectangle> cutRectangles = Arrays.stream(cutRectangleSpecifications)
                .map(CutRectangle::parse)
                .collect(Collectors.toList());

        NoMatterHowYouSliceIt noMatterHowYouSliceIt = new NoMatterHowYouSliceIt(cutRectangles);
        System.out.println(noMatterHowYouSliceIt);
        long overlappingSquareCount = noMatterHowYouSliceIt.countOverlappingSquares();

        assertEquals(expectedOverlappingSquareCount, overlappingSquareCount);
    }


    @Test
    public void part_01_countOverlappingSquares_myTask() throws Exception {
        long expectedOverlappingSquareCount = 110389;
        Path rectangleSpecificationFile = TestUtils.getResourcePath("/day02/cutRectangleSpecifications.txt");

        NoMatterHowYouSliceIt noMatterHowYouSliceIt = NoMatterHowYouSliceIt.fromRectangeSpecificationFile(
                rectangleSpecificationFile);
        long overlappingSquareCount = noMatterHowYouSliceIt.countOverlappingSquares();

        assertEquals(expectedOverlappingSquareCount, overlappingSquareCount);
    }


    // part 02

    @Test
    public void part_02_findNonOverlappingRectangleId_example_01() {
        long expectedNonOverlappingRectangleId = 3;
        String[] cutRectangleSpecifications = new String[] { //
                "#1 @ 1,3: 4x4", //
                "#2 @ 3,1: 4x4", //
                "#3 @ 5,5: 2x2" };
        Collection<CutRectangle> cutRectangles = Arrays.stream(cutRectangleSpecifications)
                .map(CutRectangle::parse)
                .collect(Collectors.toList());

        NoMatterHowYouSliceIt noMatterHowYouSliceIt = new NoMatterHowYouSliceIt(cutRectangles);
        System.out.println(noMatterHowYouSliceIt);
        long nonOverlappingRectangleId = noMatterHowYouSliceIt.findNonOverlappingRectangleId();

        assertEquals(expectedNonOverlappingRectangleId, nonOverlappingRectangleId);
    }

    @Test
    public void part_02_findNonOverlappingRectangleId_myTask() throws Exception {
        long expectedNonOverlappingRectangleId = 552;
        Path rectangleSpecificationFile = TestUtils.getResourcePath("/day02/cutRectangleSpecifications.txt");

        NoMatterHowYouSliceIt noMatterHowYouSliceIt = NoMatterHowYouSliceIt.fromRectangeSpecificationFile(
                rectangleSpecificationFile);
        long nonOverlappingRectangleId = noMatterHowYouSliceIt.findNonOverlappingRectangleId();

        assertEquals(expectedNonOverlappingRectangleId, nonOverlappingRectangleId);
    }

}