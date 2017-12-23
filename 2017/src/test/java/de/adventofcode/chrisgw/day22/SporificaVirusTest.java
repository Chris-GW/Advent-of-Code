package de.adventofcode.chrisgw.day22;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class SporificaVirusTest {


    @Test
    public void sporificaVirus_part1_example_7_bursts() {
        List<String> nodeMapLines = Arrays.asList( //
                "..#", //
                "#..", //
                "...");
        int bursts = 7;
        long expectedInfectionCount = 5;

        GridComputingCluster gridComputingCluster = GridComputingCluster.parseComputingNodeMap(nodeMapLines);
        SporificaVirus sporificaVirus = new SporificaVirus(gridComputingCluster);
        System.out.println("---" + 0 + "---");
        System.out.println(sporificaVirus);

        for (int burst = 1; burst <= bursts; burst++) {
            sporificaVirus.nextVirusBurst();
            System.out.println("---" + burst + "---");
            System.out.println(sporificaVirus);
        }

        long infectionCount = sporificaVirus.getInfectionCount();
        Assert.assertEquals("expect infection count after bursts: " + bursts, expectedInfectionCount, infectionCount);
    }


    @Test
    public void sporificaVirus_part1_example_70_bursts() {
        List<String> nodeMapLines = Arrays.asList( //
                "..#", //
                "#..", //
                "...");
        int bursts = 70;
        long expectedInfectionCount = 41;

        GridComputingCluster gridComputingCluster = GridComputingCluster.parseComputingNodeMap(nodeMapLines);
        SporificaVirus sporificaVirus = new SporificaVirus(gridComputingCluster);
        System.out.println("---" + 0 + "---");
        System.out.println(sporificaVirus);

        for (int burst = 1; burst <= bursts; burst++) {
            sporificaVirus.nextVirusBurst();
            System.out.println("---" + burst + "---");
            System.out.println(sporificaVirus);
        }

        long infectionCount = sporificaVirus.getInfectionCount();
        Assert.assertEquals("expect infection count after bursts: " + bursts, expectedInfectionCount, infectionCount);
    }

    @Test
    public void sporificaVirus_part1_example_10000_bursts() {
        List<String> nodeMapLines = Arrays.asList( //
                "..#", //
                "#..", //
                "...");
        int bursts = 10_000;
        long expectedInfectionCount = 5587;

        GridComputingCluster gridComputingCluster = GridComputingCluster.parseComputingNodeMap(nodeMapLines);
        SporificaVirus sporificaVirus = new SporificaVirus(gridComputingCluster);

        for (int burst = 1; burst <= bursts; burst++) {
            sporificaVirus.nextVirusBurst();
        }

        long infectionCount = sporificaVirus.getInfectionCount();
        Assert.assertEquals("expect infection count after bursts: " + bursts, expectedInfectionCount, infectionCount);
    }


    @Test
    public void sporificaVirus_part1_myTask_10000_bursts() {
        List<String> nodeMapLines = TestUtils.readAllLinesOfClassPathResource("/day22/SporificaVirus_chrisgw.txt");
        int bursts = 10_000;
        long expectedInfectionCount = 5538;

        GridComputingCluster gridComputingCluster = GridComputingCluster.parseComputingNodeMap(nodeMapLines);
        SporificaVirus sporificaVirus = new SporificaVirus(gridComputingCluster);

        for (int burst = 1; burst <= bursts; burst++) {
            sporificaVirus.nextVirusBurst();
        }

        long infectionCount = sporificaVirus.getInfectionCount();
        Assert.assertEquals("expect infection count after bursts: " + bursts, expectedInfectionCount, infectionCount);
    }


    // --- part 2

    @Test
    public void sporificaVirus_part2_example_100_bursts() {
        List<String> nodeMapLines = Arrays.asList( //
                "..#", //
                "#..", //
                "...");
        int bursts = 100;
        long expectedInfectionCount = 26;

        GridComputingCluster gridComputingCluster = GridComputingCluster.parseComputingNodeMap(nodeMapLines);
        SporificaVirus sporificaVirus = new EvolvedSporificaVirus(gridComputingCluster);

        for (int burst = 1; burst <= bursts; burst++) {
            sporificaVirus.nextVirusBurst();
            if (burst < 8) {
                System.out.println("---" + burst + "---");
                System.out.println(sporificaVirus);
            }
        }

        long infectionCount = sporificaVirus.getInfectionCount();
        Assert.assertEquals("expect infection count after bursts: " + bursts, expectedInfectionCount, infectionCount);
    }

    @Test
    public void sporificaVirus_part2_example_10000000_bursts() {
        List<String> nodeMapLines = Arrays.asList( //
                "..#", //
                "#..", //
                "...");
        int bursts = 10_000_000;
        long expectedInfectionCount = 2_511_944;

        GridComputingCluster gridComputingCluster = GridComputingCluster.parseComputingNodeMap(nodeMapLines);
        SporificaVirus sporificaVirus = new EvolvedSporificaVirus(gridComputingCluster);

        for (int burst = 1; burst <= bursts; burst++) {
            sporificaVirus.nextVirusBurst();
        }

        long infectionCount = sporificaVirus.getInfectionCount();
        Assert.assertEquals("expect infection count after bursts: " + bursts, expectedInfectionCount, infectionCount);
    }


    @Test
    public void sporificaVirus_part2_myTask_10000000_bursts() {
        List<String> nodeMapLines = TestUtils.readAllLinesOfClassPathResource("/day22/SporificaVirus_chrisgw.txt");
        int bursts = 10_000_000;
        long expectedInfectionCount = 2_511_090;

        GridComputingCluster gridComputingCluster = GridComputingCluster.parseComputingNodeMap(nodeMapLines);
        SporificaVirus sporificaVirus = new EvolvedSporificaVirus(gridComputingCluster);

        for (int burst = 1; burst <= bursts; burst++) {
            sporificaVirus.nextVirusBurst();
        }

        long infectionCount = sporificaVirus.getInfectionCount();
        Assert.assertEquals("expect infection count after bursts: " + bursts, expectedInfectionCount, infectionCount);
    }

}