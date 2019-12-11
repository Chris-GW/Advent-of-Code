package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.day03.Direction;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static de.adventofcode.chrisgw.day11.HullPanelPaintingColor.BLACK;
import static de.adventofcode.chrisgw.day11.HullPanelPaintingColor.WHITE;


@Data
public class HullPaintingRobot {

    private final IntCodeProgram robotProgram;
    private final Map<Integer, Map<Integer, HullPanelPaintingColor>> panelPainting = new HashMap<>();

    private Direction direction = Direction.UP;
    private int x = 0;
    private int y = 0;


    public static HullPaintingRobot paintingRobotOnWhiteStartingPanel(IntCodeProgram robotProgram) {
        HullPaintingRobot newPlacedHullPaintingRobot = new HullPaintingRobot(robotProgram);
        newPlacedHullPaintingRobot.paintPanel(WHITE);
        return newPlacedHullPaintingRobot;
    }


    public void run() {
        while (!robotProgram.isFinished()) {
            HullPanelPaintingColor panelColor = panelColorAt(x, y);
            robotProgram.addInput(panelColor.getColorCode());
            robotProgram.run();

            HullPanelPaintingColor paintingColor = nextPaintedPanelHullColor();
            paintPanel(paintingColor);
            direction = nextDirectionTurn();
            moveForward();
        }
    }


    private HullPanelPaintingColor nextPaintedPanelHullColor() {
        long paintedColorCode = robotProgram.nextOutput();
        if (paintedColorCode == 0) {
            return BLACK;
        } else if (paintedColorCode == 1) {
            return WHITE;
        } else {
            throw new IllegalArgumentException("Unknown panel hull color code: " + paintedColorCode);
        }
    }

    private void paintPanel(HullPanelPaintingColor paintingColor) {
        Map<Integer, HullPanelPaintingColor> row = panelPainting.computeIfAbsent(y, integer -> new HashMap<>());
        row.put(x, paintingColor);
    }


    private Direction nextDirectionTurn() {
        long directionTurnCode = robotProgram.nextOutput();
        if (directionTurnCode == 0) {
            return direction.rotateLeft();
        } else if (directionTurnCode == 1) {
            return direction.rotateRight();
        } else {
            throw new IllegalArgumentException("Unknown direction turn code: " + directionTurnCode);
        }
    }

    private void moveForward() {
        x += direction.getDx();
        y += direction.getDy();
    }


    public HullPanelPaintingColor panelColorAt(int x, int y) {
        Map<Integer, HullPanelPaintingColor> row = panelPainting.getOrDefault(y, Collections.emptyMap());
        return row.getOrDefault(x, BLACK);
    }


    public int paintedPanelsCount() {
        return panelPainting.values().stream().map(Map::values).mapToInt(Collection::size).sum();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y >= -5; y--) {
            for (int x = 0; x <= 42; x++) {
                HullPanelPaintingColor panelColor = panelColorAt(x, y);
                if (this.x == x && this.y == y) {
                    sb.append(directionSign());
                } else if (BLACK.equals(panelColor)) {
                    sb.append('.');
                } else {
                    sb.append('#');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String directionSign() {
        switch (direction) {
        case UP:
            return "↑";
        case RIGHT:
            return "→";
        case DOWN:
            return "↓";
        case LEFT:
            return "←";
        default:
            throw new IllegalArgumentException("unknown direction: " + direction);
        }
    }

}
