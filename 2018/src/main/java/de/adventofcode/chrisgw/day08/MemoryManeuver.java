package de.adventofcode.chrisgw.day08;

import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;


public class MemoryManeuver {

    @Getter
    private LicenseNode rootNode;


    public MemoryManeuver(LicenseNode rootNode) {
        this.rootNode = rootNode;
    }

    public static MemoryManeuver fromLicenseNodeFormat(String licenseNodeFormatStr) {
        Scanner dataScanner = new Scanner(licenseNodeFormatStr);
        LicenseNode rootNode = parseLicenseNode(dataScanner, 0);
        return new MemoryManeuver(rootNode);
    }

    private static LicenseNode parseLicenseNode(Scanner dataScanner, int nodeId) {
        int childNodeQuantity = dataScanner.nextInt();
        int metaDataQuantity = dataScanner.nextInt();
        LicenseNode licenseNode = new LicenseNode(nodeId, childNodeQuantity, metaDataQuantity);
        for (int childNodeIndex = 0; childNodeIndex < childNodeQuantity; childNodeIndex++) {
            LicenseNode childLicenseNode = parseLicenseNode(dataScanner, ++nodeId);
            licenseNode.addChild(childLicenseNode);
        }
        for (int metaDataIndex = 0; metaDataIndex < metaDataQuantity; metaDataIndex++) {
            int metaData = dataScanner.nextInt();
            licenseNode.addMetaData(metaData);
        }
        return licenseNode;
    }


    public int calculateMetaDataSum() {
        return calculateMetaDataSum(rootNode);
    }

    private int calculateMetaDataSum(LicenseNode currentLicenseNode) {
        int metaDataSum = currentLicenseNode.metaData().sum();
        Iterator<LicenseNode> childNodeIterator = currentLicenseNode.childNodes().iterator();
        while (childNodeIterator.hasNext()) {
            LicenseNode childNode = childNodeIterator.next();
            metaDataSum += calculateMetaDataSum(childNode);
        }
        return metaDataSum;
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);
        String licenseNodeFormatStr = Files.lines(puzzleInputFile).findFirst().orElse(null);
        MemoryManeuver memoryManeuver = MemoryManeuver.fromLicenseNodeFormat(licenseNodeFormatStr);
        int metaDataSum = memoryManeuver.calculateMetaDataSum();
        System.out.println("metaDataSum: " + memoryManeuver);

    }

}
