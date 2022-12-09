package de.adventofcode.chrisgw.day08;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;


public record TreeMapPoint(TreeHeightMap treeMap, int x, int y, int treeHeight) {

    public Stream<TreeMapPoint> treesInDirection(Direction direction) {
        TreeMapPoint seedTree = neighborTrees(direction);
        return Stream.iterate(seedTree, Objects::nonNull, treeMapPoint -> treeMapPoint.neighborTrees(direction));
    }

    public TreeMapPoint neighborTrees(Direction direction) {
        int x = this.x() + direction.getDx();
        int y = this.y() + direction.getDy();
        return treeMap.treeAt(x, y);
    }


    public boolean isEdgeTree() {
        return x == 0 || y == 0 || x == treeMap.width() - 1 || y == treeMap.height() - 1;
    }

    public boolean isBlockingView(TreeMapPoint otherTree) {
        return this.treeHeight() <= otherTree.treeHeight();
    }


    public boolean isVisible() {
        return isEdgeTree() || Arrays.stream(Direction.values())
                .map(this::treesInDirection)
                .anyMatch(trees -> trees.noneMatch(this::isBlockingView));
    }


    public int scenicScore() {
        return Arrays.stream(Direction.values())
                .mapToInt(this::visibleTreesCount)
                .reduce(1, (left, right) -> left * right);
    }

    private int visibleTreesCount(Direction direction) {
        int visibleTreeCount = 0;
        Iterator<TreeMapPoint> treeIterator = treesInDirection(direction).iterator();
        while (treeIterator.hasNext()) {
            visibleTreeCount++;
            if (isBlockingView(treeIterator.next())) {
                break;
            }
        }
        return visibleTreeCount;
    }


    public String toString() {
        return "(%2d;%2d) = %d".formatted(x(), y(), treeHeight());
    }

}
