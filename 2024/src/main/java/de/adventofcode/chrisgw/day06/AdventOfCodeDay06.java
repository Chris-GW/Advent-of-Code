package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

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


    private void traceGuardPath() {
        Direction guardDirection = Direction.UP;
        MapTile guard = guardStartTile;
        while (!guard.isOutside()) {
            setTile(guard.x, guard.y, GUARD_PATH);
            MapTile nextTile = guard.move(guardDirection);
            if (nextTile.isObstacle()) {
                guardDirection = guardDirection.turnRight();
            } else {
                guard = nextTile;
            }
        }
    }


    private long countGuardPathTiles() {
        return Arrays.stream(map)
                .flatMap(Arrays::stream)
                .map(MapTile::type)
                .filter(GUARD_PATH::equals)
                .count();
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
        // TODO solveSecondPart
        return 0;
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


        public MapTileType type() {
            return type;
        }

    }

}
