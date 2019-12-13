package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ArcadeCabinet {

    private IntCodeProgram arcadeCabinetProgram;
    private Map<Long, Map<Long, ScreenTileType>> screenTileGrid = new HashMap<>();


    public ArcadeCabinet(IntCodeProgram arcadeCabinetProgram) {
        this.arcadeCabinetProgram = arcadeCabinetProgram;
    }


    public void startGame() {
        arcadeCabinetProgram.reset();
        arcadeCabinetProgram.run();
        drawScreen();
    }


    private void drawScreen() {
        while (arcadeCabinetProgram.hasNextOutput()) {
            long x = arcadeCabinetProgram.nextOutput();
            long y = arcadeCabinetProgram.nextOutput();
            long tileId = arcadeCabinetProgram.nextOutput();
            ScreenTileType screenTileType = ScreenTileType.fromTileId(tileId);
            drawScreenTile(x, y, screenTileType);
        }
    }


    public ScreenTileType screenTileAt(long x, long y) {
        Map<Long, ScreenTileType> row = screenTileGrid.getOrDefault(y, Collections.emptyMap());
        return row.getOrDefault(x, ScreenTileType.EMPTY);
    }

    private void drawScreenTile(long x, long y, ScreenTileType screenTileType) {
        Map<Long, ScreenTileType> row = screenTileGrid.computeIfAbsent(y, keyY -> new HashMap<>());
        row.put(x, screenTileType);
    }


    public long countTiles(ScreenTileType screenTileType) {
        return screenTileGrid.values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(screenTileType::equals)
                .count();
    }

}
