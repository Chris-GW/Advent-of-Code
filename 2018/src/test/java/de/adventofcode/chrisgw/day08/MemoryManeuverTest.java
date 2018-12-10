package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class MemoryManeuverTest {

    @Test
    public void fromLicenseNodeFormat_example01() {
        String licenseNodeFormatStr = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        List<Integer> expectedRootChildNodeIds = Arrays.asList(1, 2);
        List<Integer> expectedRootMetaData = Arrays.asList(1, 1, 2);

        MemoryManeuver memoryManeuver = MemoryManeuver.fromLicenseNodeFormat(licenseNodeFormatStr);
        LicenseNode rootNode = memoryManeuver.getRootNode();

        List<Integer> rootChildNodeIds = rootNode.childNodes().map(LicenseNode::getId).collect(Collectors.toList());
        assertEquals("rootNode rootChildNodeIds", expectedRootChildNodeIds, rootChildNodeIds);

        List<Integer> rootMetaData = rootNode.metaData().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        assertEquals("rootNode rootMetaData", expectedRootMetaData, rootMetaData);
    }


    @Test
    public void calculateMetaDataSum_example01() {
        String licenseNodeFormatStr = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        int expectedmetaDataSum = 1 + 1 + 2 + 10 + 11 + 12 + 2 + 99; // 138;

        MemoryManeuver memoryManeuver = MemoryManeuver.fromLicenseNodeFormat(licenseNodeFormatStr);
        int metaDataSum = memoryManeuver.calculateMetaDataSum();
        assertEquals("metaDataSum", expectedmetaDataSum, metaDataSum);
    }


    @Test
    public void calculateMetaDataSum_myPuzzleInput() throws Exception {
        Path licenseFile = TestUtils.getResourcePath("/day08/myPuzzleInput.txt");
        String licenseNodeFormatStr = Files.lines(licenseFile).findFirst().orElse(null);
        int expectedMetaDataSum = 43825;

        MemoryManeuver memoryManeuver = MemoryManeuver.fromLicenseNodeFormat(licenseNodeFormatStr);
        int metaDataSum = memoryManeuver.calculateMetaDataSum();
        assertEquals("metaDataSum", expectedMetaDataSum, metaDataSum);
    }


    // part 02

    @Test
    public void calculateLicenseNodeValue_example01() {
        String licenseNodeFormatStr = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        int expectedLicenseNodeValue = 33 + 33 + 0; // 66

        MemoryManeuver memoryManeuver = MemoryManeuver.fromLicenseNodeFormat(licenseNodeFormatStr);
        int licenseNodeValue = memoryManeuver.calculateLicenseNodeValue();
        assertEquals("licenseNodeValue", expectedLicenseNodeValue, licenseNodeValue);
    }


    @Test
    public void calculateLicenseNodeValue_myPuzzleInput() throws Exception {
        Path licenseFile = TestUtils.getResourcePath("/day08/myPuzzleInput.txt");
        String licenseNodeFormatStr = Files.lines(licenseFile).findFirst().orElse(null);
        int expectedLicenseNodeValue = 19276;

        MemoryManeuver memoryManeuver = MemoryManeuver.fromLicenseNodeFormat(licenseNodeFormatStr);
        int licenseNodeValue = memoryManeuver.calculateLicenseNodeValue();
        assertEquals("licenseNodeValue", expectedLicenseNodeValue, licenseNodeValue);
    }

}