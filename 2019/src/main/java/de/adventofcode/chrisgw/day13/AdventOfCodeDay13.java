package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static de.adventofcode.chrisgw.day13.JoystickInput.*;


/**
 * 2019 Day 13: Care Package
 * https://adventofcode.com/2019/day/13
 */
public class AdventOfCodeDay13 {

    private AdventOfCodeDay13() {

    }

    public static long countBlockTilesOnScreen(ArcadeCabinet arcadeCabinet) {
        arcadeCabinet.runGame();
        return arcadeCabinet.countTiles(ScreenTileType.BLOCK);
    }


    public static long countWinningScore(ArcadeCabinet arcadeCabinet) {
        arcadeCabinet.setQuarters(2);
        do {
            arcadeCabinet.runGame();
            arcadeCabinet.inputJoystick(nextJoystickInput(arcadeCabinet));
        } while (!arcadeCabinet.isFinishedGame());
        return arcadeCabinet.getPlayerScore();
    }

    private static JoystickInput nextJoystickInput(ArcadeCabinet arcadeCabinet) {
        int ballPostion = arcadeCabinet.ballPostion();
        int paddlePosition = arcadeCabinet.horizontalPaddlePosition();
        if (ballPostion > paddlePosition) {
            return TILTED_RIGHT;
        } else if (ballPostion < paddlePosition) {
            return TILTED_LEFT;
        } else {
            return NEUTRAL_POSITION;
        }
    }


    public static void main(String[] args) throws IOException {
        Path arcadeCabinetProgramFile = readArcadeCabinetProgramFile(args).toAbsolutePath();
        System.out.println("use arcadeCabinetProgramFile: " + arcadeCabinetProgramFile);
        String intCodeProgrammStr = Files.lines(arcadeCabinetProgramFile)
                .filter(s -> !(s.startsWith("#") || s.startsWith("//")))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Cant read first line in: " + arcadeCabinetProgramFile));
        IntCodeProgram arcadeCabinetProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgrammStr);
        ArcadeCabinet arcadeCabinet = new ArcadeCabinet(arcadeCabinetProgram);
        arcadeCabinet.setQuarters(2);
        runGame(arcadeCabinet);
    }

    private static Path readArcadeCabinetProgramFile(String[] args) {
        if (args.length > 0) {
            return Paths.get(args[0]);
        } else {
            return Paths.get("2019/src/test/resources/puzzleInputDay13.txt");
        }
    }

    private static void runGame(ArcadeCabinet arcadeCabinet) {
        Scanner inputScanner = new Scanner(System.in);
        do {
            arcadeCabinet.runGame();
            System.out.println(arcadeCabinet);

            System.out.print("Joystick Input (-1 = LEFT, 0 = NUTRAL, 1 = RIGHT): ");
            int nextJoystickInput = inputScanner.nextInt();
            JoystickInput joystickInput = JoystickInput.fromCode(nextJoystickInput);
            arcadeCabinet.inputJoystick(joystickInput);
        } while (!arcadeCabinet.isFinishedGame());
        System.out.println("final player score: " + arcadeCabinet.getPlayerScore());
    }

}
