package de.adventofcode.chrisgw.day08;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;


public record TreeMapPoint(TreeHeightMap treeMap, int x, int y, int treeHeight) {

    public Stream<TreeMapPoint> treeStreamForDirection(Direction direction) {
        TreeMapPoint seedTree = neighborTree(direction);
        return Stream.iterate(seedTree, Objects::nonNull, treeMapPoint -> treeMapPoint.neighborTree(direction));
    }

    public TreeMapPoint neighborTree(Direction direction) {
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


    public boolean isVisibleFromOutside() {
        return isEdgeTree() || Arrays.stream(Direction.values())
                .map(this::treeStreamForDirection)
                .anyMatch(trees -> trees.noneMatch(this::isBlockingView));
    }


    public int scenicScore() {
        return Arrays.stream(Direction.values())
                .mapToInt(this::viewableTreeCount)
                .reduce(1, (left, right) -> left * right);
    }

    private int viewableTreeCount(Direction direction) {
        AtomicBoolean isViewBlocked = new AtomicBoolean(false);
        return (int) treeStreamForDirection(direction) //
                .takeWhile(treeMapPoint -> !isViewBlocked.getAndSet(this.isBlockingView(treeMapPoint))) //
                .count();
    }


    public String toString() {
        return "(%2d;%2d) = %d".formatted(x(), y(), treeHeight());
    }

}
