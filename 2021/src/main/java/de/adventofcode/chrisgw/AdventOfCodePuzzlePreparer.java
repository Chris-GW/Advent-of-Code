package de.adventofcode.chrisgw;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.AdventOfCodePuzzle.ADVENT_OF_CODE_PUZZLE_COUNT;


@RequiredArgsConstructor
public class AdventOfCodePuzzlePreparer {

    private final Path projectDirectory;
    private final Year year;
    private final String sessionCookie;
    private final HttpClient httpClient;


    public void prepareAvailableAocPuzzle(Year year) {
        for (int day = 1; day < ADVENT_OF_CODE_PUZZLE_COUNT; day++) {
            prepareAocPuzzle(year, day);
        }
    }

    public void prepareAocPuzzle(Year year, int day) {
        prepareAocPuzzle(new AdventOfCodePuzzle(year, day));
    }

    public void prepareAocPuzzle(AdventOfCodePuzzle aocPuzzle) {
        if (ZonedDateTime.now().isBefore(aocPuzzle.puzzleOpeningDateTime())) {
            System.out.println("skip closed puzzle: " + aocPuzzle);
            return;
        }
        requestMyPuzzleInput(aocPuzzle);
    }

    private List<String> requestMyPuzzleInput(AdventOfCodePuzzle aocPuzzle) {
        try {
            HttpRequest getAocInputRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(aocPuzzle.puzzleInputURI())
                    .header("cookie", "session=" + sessionCookie)
                    .header("accept", "text/html")
                    .build();
            HttpResponse<Stream<String>> response = httpClient.send(getAocInputRequest, BodyHandlers.ofLines());
            if (response.statusCode() != 200) {
                throw new RuntimeException();
            }
            return response.body().toList();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("missing first argument for session cookie value for advent of code page");
            System.exit(1);
        }
        String aocSessionCookie = args[0];
        Year year = Year.now();
        Path projectDirectory = Paths.get(".").toAbsolutePath().normalize();
        System.out.println("prepare Advent of Code " + year + " in project directory: " + projectDirectory);
        HttpClient httpClient = HttpClient.newHttpClient();
        var aocPuzzlePreparer = new AdventOfCodePuzzlePreparer(projectDirectory, year, aocSessionCookie, httpClient);
        aocPuzzlePreparer.prepareAvailableAocPuzzle(year);
        System.exit(0);
    }


}
