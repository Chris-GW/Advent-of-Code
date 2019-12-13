package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;

import java.util.Arrays;
import java.util.PrimitiveIterator.OfInt;

import static de.adventofcode.chrisgw.day13.ScreenTileType.*;


public class ArcadeCabinet {

    public static final int QUARTERS_NUMBER_ADDRESS = 0;
    public static final int SCREEN_HEIGHT = 24;
    public static final int SCREEN_WIDTH = 45;

    private IntCodeProgram arcadeCabinetProgram;
    private ScreenTileType[][] screenTileGrid;
    private long playerScore = 0;


    public ArcadeCabinet(IntCodeProgram arcadeCabinetProgram) {
        this.arcadeCabinetProgram = arcadeCabinetProgram;
        this.screenTileGrid = new ScreenTileType[SCREEN_HEIGHT][SCREEN_WIDTH];
        resetGame();
    }


    public void runGame() {
        if (!arcadeCabinetProgram.hasNext()) {
            throw new IllegalStateException("Cant run Game which is finished or waits for input");
        }
        arcadeCabinetProgram.run();
        drawAllOutputInstructions();
    }


    public void resetGame() {
        arcadeCabinetProgram.reset();
        Arrays.stream(screenTileGrid).forEach(screenTileTypes -> Arrays.fill(screenTileTypes, EMPTY));
        playerScore = 0;
    }

    public boolean isFinishedGame() {
        return arcadeCabinetProgram.isFinished();
    }

    public boolean isGameWin() {
        return countTiles(BLOCK) == 0;
    }


    public void setQuarters(long quarters) {
        arcadeCabinetProgram.setValueAt(QUARTERS_NUMBER_ADDRESS, quarters);
    }


    public void inputJoystick(JoystickInput joystickInput) {
        if (arcadeCabinetProgram.isWaitingForNextInput()) {
            arcadeCabinetProgram.addInput(joystickInput.getCode());
        } else {
            System.out.println("Ignoring input: " + joystickInput);
        }
    }


    private void drawAllOutputInstructions() {
        OfInt outputIterator = arcadeCabinetProgram.getAllOutput().mapToInt(value -> (int) value).iterator();
        while (outputIterator.hasNext()) {
            int x = outputIterator.nextInt();
            int y = outputIterator.nextInt();
            int value = outputIterator.nextInt();
            drawOutputInstruction(x, y, value);
        }
    }

    private void drawOutputInstruction(int x, int y, int value) {
        if (isPlayerScoreOutput(x, y)) {
            playerScore = value;
        } else {
            ScreenTileType screenTileType = ScreenTileType.fromTileId(value);
            drawScreenTile(x, y, screenTileType);
        }
    }

    private boolean isPlayerScoreOutput(long x, long y) {
        return x == -1 && y == 0;
    }


    public ScreenTileType screenTileAt(int x, int y) {
        ScreenTileType[] screenTileRow = screenTileGrid[y];
        return screenTileRow[x];
    }

    private void drawScreenTile(int x, int y, ScreenTileType screenTileType) {
        ScreenTileType[] screenTileRow = screenTileGrid[y];
        screenTileRow[x] = screenTileType;
    }


    public long countTiles(ScreenTileType screenTileType) {
        return Arrays.stream(screenTileGrid).flatMap(Arrays::stream).filter(screenTileType::equals).count();
    }


    public int ballPostion() {
        return screenTilePostionFor(BALL);
    }

    public int horizontalPaddlePosition() {
        return screenTilePostionFor(HORIZONTAL_PADDLE);
    }


    private int screenTilePostionFor(ScreenTileType screenTileType) {
        for (int y = 0; y < SCREEN_HEIGHT; y++) {
            for (int x = 0; x < SCREEN_WIDTH; x++) {
                ScreenTileType screenTileAt = screenTileAt(x, y);
                if (screenTileType.equals(screenTileAt)) {
                    return x;
                }
            }
        }
        return -1;
    }


    public long getPlayerScore() {
        return playerScore;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player Score: ").append(playerScore).append("\n");
        for (int y = 0; y < SCREEN_HEIGHT; y++) {
            for (int x = 0; x < SCREEN_WIDTH; x++) {
                ScreenTileType screenTileType = screenTileAt(x, y);
                sb.append(screenTileStr(screenTileType));
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String screenTileStr(ScreenTileType screenTileType) {
        switch (screenTileType) {
        case WALL:
            return "\u2551";
        case BLOCK:
            return "\u2585";
        case HORIZONTAL_PADDLE:
            return "\u2501";
        case BALL:
            return "o";
        default:
        case EMPTY:
            return ".";
        }
    }

}
