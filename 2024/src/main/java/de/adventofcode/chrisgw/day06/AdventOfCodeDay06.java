package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day06.MapTileType.GUARD;
import static de.adventofcode.chrisgw.day06.MapTileType.GUARD_PATH;
import static de.adventofcode.chrisgw.day06.MapTileType.OBSTACLE;
import static de.adventofcode.chrisgw.day06.MapTileType.OUTSIDE;


/**
 * <a href="https://adventofcode.com/2024/day/6">Advent of Code - day 6</a>
 */
public class AdventOfCodeDay06 extends AdventOfCodePuzzleSolver {

    private MapTile[][] map;
    private MapTile guardStartTile;


    public AdventOfCodeDay06(List<String> inputLines) {
        super(Year.of(2024), 6, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        parseGuardMap();
        traceGuardPath();
        return Math.toIntExact(countGuardPathTiles());
    }

    private void parseGuardMap() {
        map = new MapTile[getInputLines().size()][];
        for (int y = 0; y < getInputLines().size(); y++) {
            String mapLine = getInputLines().get(y);
            map[y] = new MapTile[mapLine.length()];

            for (int x = 0; x < mapLine.length(); x++) {
                char sign = mapLine.charAt(x);
                MapTileType mapTileType = MapTileType.fromSign(sign);
                setTile(x, y, mapTileType);
                if (GUARD.equals(mapTileType)) {
                    guardStartTile = getTileAt(x, y);
                    setTile(x, y, GUARD_PATH);
                }
            }
        }
    }

    record GuardDirection(MapTile tile, Direction direction) {

    }

    private boolean traceGuardPath() {
        Set<GuardDirection> visitedObstacles = new HashSet<>();
        Direction guardDirection = Direction.UP;
        MapTile guard = guardStartTile;

        while (!guard.isOutside()) {
            setTile(guard.x, guard.y, GUARD_PATH);
            MapTile nextTile = guard.move(guardDirection);

            if (nextTile.isObstacle() && !visitedObstacles.add(new GuardDirection(guard, guardDirection))) {
                return true;
            } else if (nextTile.isObstacle()) {
                guardDirection = guardDirection.turnRight();
            } else {
                guard = nextTile;
            }
        }
        return false;
    }


    private long countGuardPathTiles() {
        return mapTiles()
                .filter(MapTile::isGuardPath)
                .count();
    }

    private Stream<MapTile> mapTiles() {
        return Arrays.stream(map).flatMap(Arrays::stream);
    }


    private void setTile(int x, int y, MapTileType mapTileType) {
        map[y][x] = new MapTile(x, y, mapTileType);
    }

    private MapTile getTileAt(int x, int y) {
        if (y < 0 || map.length <= y || x < 0 || map[y].length <= x) {
            return new MapTile(x, y, OUTSIDE);
        }
        return map[y][x];
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                MapTile tile = getTileAt(x, y);
                sb.append(tile.type.getSign());
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    @Override
    public Integer solveSecondPart() {
        parseGuardMap();
        traceGuardPath();
        return Math.toIntExact(mapTiles()
                .filter(MapTile::isGuardPath)
                .filter(mapTile -> {
                    setTile(mapTile.x, mapTile.y, OBSTACLE);
                    boolean isLoop = traceGuardPath();
                    setTile(mapTile.x, mapTile.y, GUARD_PATH);
                    return isLoop;
                }).count());
    }


    class MapTile {
        final int x;
        final int y;
        final MapTileType type;


        public MapTile(int x, int y, MapTileType type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }


        public MapTile move(Direction direction) {
            int x = this.x + direction.dx;
            int y = this.y + direction.dy;
            return getTileAt(x, y);
        }


        public boolean isObstacle() {
            return OBSTACLE.equals(type);
        }

        public boolean isOutside() {
            return OUTSIDE.equals(type);
        }

        public boolean isGuardPath() {
            return GUARD_PATH.equals(type);
        }


        public MapTileType type() {
            return type;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MapTile mapTile)) return false;
            return new EqualsBuilder().append(x, mapTile.x).append(y, mapTile.y).append(type, mapTile.type).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(x).append(y).append(type).toHashCode();
        }

    }

}
