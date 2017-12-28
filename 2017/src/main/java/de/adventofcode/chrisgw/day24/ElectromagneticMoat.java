package de.adventofcode.chrisgw.day24;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 * <h1><a href="https://adventofcode.com/2017/day/24>Day 24: Electromagnetic Moat</a></h1>
 * <pre>
 * </pre>
 */
public class ElectromagneticMoat {

    private Deque<BridgeComponent> bridgeComponents = new LinkedList<>();


    public ElectromagneticMoat() {
        bridgeComponents.addFirst(new BridgeComponent(0, 0));
    }

    public ElectromagneticMoat(ElectromagneticMoat bridge) {
        for (BridgeComponent bridgeComponent : bridge.bridgeComponents) {
            BridgeComponent newBridgeComponent = new BridgeComponent(bridgeComponent.getPortIn(),
                    bridgeComponent.getPortOut());
            this.bridgeComponents.addLast(newBridgeComponent);
        }
    }


    public static ElectromagneticMoat buildLongestAndSrongestBridge(List<BridgeComponent> aviableBridgeComponents) {
        return buildBestBridge(new ElectromagneticMoat(), aviableBridgeComponents,
                Comparator.comparingInt(ElectromagneticMoat::getLength)
                        .thenComparingInt(ElectromagneticMoat::getStrength));
    }

    public static ElectromagneticMoat buildStrongestBridge(List<BridgeComponent> aviableBridgeComponents) {
        return buildBestBridge(new ElectromagneticMoat(), aviableBridgeComponents,
                Comparator.comparingInt(ElectromagneticMoat::getStrength));
    }


    public static ElectromagneticMoat buildBestBridge(ElectromagneticMoat currentBridge,
            List<BridgeComponent> aviableBridgeComponents, Comparator<ElectromagneticMoat> bestBrigeComparator) {
        ElectromagneticMoat bestBridge = currentBridge;
        for (int i = 0; i < aviableBridgeComponents.size(); i++) {
            BridgeComponent bridgeComponent = aviableBridgeComponents.get(i);
            if (!currentBridge.canAddComponent(bridgeComponent)) {
                continue;
            }

            ElectromagneticMoat newBridge = new ElectromagneticMoat(currentBridge);
            newBridge.addComponent(bridgeComponent);
            List<BridgeComponent> newAviableBridgeComponents = new LinkedList<>(aviableBridgeComponents);
            newAviableBridgeComponents.remove(i);
            newBridge = buildBestBridge(newBridge, newAviableBridgeComponents, bestBrigeComparator);

            if (bestBrigeComparator.compare(newBridge, bestBridge) > 0) {
                bestBridge = newBridge;
            }
        }
        return bestBridge;
    }


    public boolean canAddComponent(BridgeComponent bridgeComponent) {
        BridgeComponent lastBridgeComponent = bridgeComponents.peekLast();
        if (lastBridgeComponent.canConnect(bridgeComponent)) {
            return true;
        }
        bridgeComponent.reverse();
        boolean connectReversed = lastBridgeComponent.canConnect(bridgeComponent);
        bridgeComponent.reverse();
        return connectReversed;
    }

    public void addComponent(BridgeComponent bridgeComponent) {
        if (!canAddComponent(bridgeComponent)) {
            throw new IllegalArgumentException("can't add bridge component: " + bridgeComponent);
        }
        BridgeComponent lastBridgeComponent = bridgeComponents.peekLast();
        if(!lastBridgeComponent.canConnect(bridgeComponent)) {
            bridgeComponent.reverse();
        }
        bridgeComponents.addLast(bridgeComponent);
    }

    public BridgeComponent removeLastComponent() {
        return bridgeComponents.pollLast();
    }


    public int getStrength() {
        int strength = 0;
        for (BridgeComponent bridgeComponent : bridgeComponents) {
            strength += bridgeComponent.getPortIn();
            strength += bridgeComponent.getPortOut();
        }
        return strength;
    }


    public int getLength() {
        return bridgeComponents.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ElectromagneticMoat))
            return false;

        ElectromagneticMoat that = (ElectromagneticMoat) o;

        return bridgeComponents != null ?
                bridgeComponents.equals(that.bridgeComponents) :
                that.bridgeComponents == null;
    }

    @Override
    public int hashCode() {
        return bridgeComponents != null ? bridgeComponents.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (BridgeComponent bridgeComponent : bridgeComponents) {
            sb.append(bridgeComponent).append("--");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

}
