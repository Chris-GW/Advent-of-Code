package de.adventofcode.chrisgw.day24;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ElectromagneticMoatTest {


    @Test
    public void electromagneticMoat_part1_example() {
        List<BridgeComponent> aviableComponents = Stream.of( //
                "0/2", //
                "2/2", //
                "2/3", //
                "3/4", //
                "3/5", //
                "0/1", //
                "10/1", //
                "9/10").map(BridgeComponent::parseComponent).collect(Collectors.toList());
        ElectromagneticMoat expectedBridge = new ElectromagneticMoat();
        expectedBridge.addComponent(new BridgeComponent(0, 1));
        expectedBridge.addComponent(new BridgeComponent(1, 10));
        expectedBridge.addComponent(new BridgeComponent(10, 9));

        ElectromagneticMoat bestBridge = ElectromagneticMoat.buildStrongestBridge(aviableComponents);

        Assert.assertEquals("expect best bridge", expectedBridge, bestBridge);
    }

    @Test
    public void electromagneticMoat_part1_myTask() {
        String classpathResource = "/day24/ElectromagneticMoat_chrisgw.txt";
        List<BridgeComponent> aviableComponents = TestUtils.readAllLinesOfClassPathResource(classpathResource)
                .stream()
                .map(BridgeComponent::parseComponent)
                .collect(Collectors.toList());
        int expectedBridgeStrehngth = 2006;

        ElectromagneticMoat bestBridge = ElectromagneticMoat.buildStrongestBridge(aviableComponents);
        int bridgeStrength = bestBridge.getStrength();

        Assert.assertEquals("expect best bridge strength", expectedBridgeStrehngth, bridgeStrength);
    }


    @Test
    public void electromagneticMoat_part2_example() {
        List<BridgeComponent> aviableComponents = Stream.of( //
                "0/2", //
                "2/2", //
                "2/3", //
                "3/4", //
                "3/5", //
                "0/1", //
                "10/1", //
                "9/10").map(BridgeComponent::parseComponent).collect(Collectors.toList());
        ElectromagneticMoat expectedBridge = new ElectromagneticMoat();
        expectedBridge.addComponent(new BridgeComponent(0, 2));
        expectedBridge.addComponent(new BridgeComponent(2, 2));
        expectedBridge.addComponent(new BridgeComponent(2, 3));
        expectedBridge.addComponent(new BridgeComponent(3, 5));

        ElectromagneticMoat bestBridge = ElectromagneticMoat.buildLongestAndSrongestBridge(aviableComponents);

        Assert.assertEquals("expect best bridge", expectedBridge, bestBridge);
    }

    @Test
    public void electromagneticMoat_part2_myTask() {
        String classpathResource = "/day24/ElectromagneticMoat_chrisgw.txt";
        List<BridgeComponent> aviableComponents = TestUtils.readAllLinesOfClassPathResource(classpathResource)
                .stream()
                .map(BridgeComponent::parseComponent)
                .collect(Collectors.toList());
        int expectedBridgeLength = 35;
        int expectedBridgeStrength = 1994;

        ElectromagneticMoat bestBridge = ElectromagneticMoat.buildLongestAndSrongestBridge(aviableComponents);
        int bridgeLength = bestBridge.getLength();
        int bridgeStrength = bestBridge.getStrength();

        Assert.assertEquals("expect best bridge length", expectedBridgeLength, bridgeLength);
        Assert.assertEquals("expect best bridge strength", expectedBridgeStrength, bridgeStrength);
    }

}