package de.adventofcode.chrisgw.day20;

import java.util.Arrays;
import java.util.List;


public class MixingEncryption {


    private int currentIndex;
    private NumberNode nullNode;
    private final List<NumberNode> nodes;


    public MixingEncryption(int[] originalNumbers) {
        this.currentIndex = 0;
        this.nodes = Arrays.stream(originalNumbers).mapToObj(NumberNode::new).toList();
        for (int i = 0; i < nodes.size() - 1; i++) {
            NumberNode currentNode = nodes.get(i);
            currentNode.setNextNode(nodes.get(i + 1));
            if (currentNode.getValue() == 0) {
                nullNode = currentNode;
            }
        }
        NumberNode firstNode = nodes.get(0);
        NumberNode lastNode = nodes.get(nodes.size() - 1);
        firstNode.setPreviousNode(lastNode);
    }


    public boolean hasNextMixing() {
        return currentIndex < nodes.size();
    }


    public void doMixing() {
        NumberNode node = nodes.get(currentIndex++);
        int value = node.getValue();
        if (value > 0) {
            node.remove();
            NumberNode previousNode = node.forwardNodes().skip(value).findFirst().orElseThrow();
            NumberNode nextNode = previousNode.getNextNode();
            node.setPreviousNode(previousNode);
            node.setNextNode(nextNode);
        } else if (value < 0) {
            node.remove();
            NumberNode nextNode = node.backwardsNodes().skip(Math.abs(value)).findFirst().orElseThrow();
            NumberNode previousNode = nextNode.getPreviousNode();
            node.setPreviousNode(previousNode);
            node.setNextNode(nextNode);
        }
    }


    public int nthNode(int index) {
        return nullNode.forwardNodes().skip(index % nodes.size()).findFirst().map(NumberNode::getValue).orElseThrow();
    }

    @Override
    public String toString() {
        return Arrays.toString(nullNode.forwardNodes().limit(nodes.size()).mapToInt(NumberNode::getValue).toArray());
    }

}
