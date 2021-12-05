package de.adventofcode.chrisgw;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.AdventOfCodePuzzle.ADVENT_OF_CODE_PUZZLE_COUNT;
import static java.nio.file.StandardOpenOption.CREATE_NEW;


@RequiredArgsConstructor
public class AdventOfCodePuzzlePreparer {

    private final Path projectDirectory;
    private final Year year;
    private final String sessionCookie;
    private final HttpClient httpClient;


    public void prepareAvailableAocPuzzle() throws IOException {
        for (int day = 1; day < ADVENT_OF_CODE_PUZZLE_COUNT; day++) {
            prepareAocPuzzle(day);
        }
    }

    public void prepareAocPuzzle(int day) throws IOException {
        AdventOfCodePuzzle aocPuzzle = new AdventOfCodePuzzle(year, day);
        if (ZonedDateTime.now().isBefore(aocPuzzle.puzzleOpening())) {
            System.out.println("skip closed puzzle: " + aocPuzzle);
            return;
        }
        createJavaAocDayClass(day);
        createJavaAocDayTestClass(day);
        requestMyPuzzleInput(aocPuzzle);
    }


    private void createJavaAocDayClass(int day) throws IOException {
        Path javaFile = projectDirectory.resolve("src")
                .resolve("main")
                .resolve(javaPackageDirs())
                .resolve(formattedDay(day))
                .resolve("AdventOfCodeDay" + formattedDay(day) + "Test.java");
        Files.createDirectories(javaFile);
        Files.writeString(javaFile, javaClassTemplate(day), CREATE_NEW);
    }

    private void createJavaAocDayTestClass(int day) throws IOException {
        Path javaTestFile = projectDirectory.resolve("src")
                .resolve("test")
                .resolve(javaPackageDirs())
                .resolve(formattedDay(day))
                .resolve("AdventOfCodeDay" + formattedDay(day) + "Test.java");
        Files.createDirectories(javaTestFile);
        Files.writeString(javaTestFile, javaTestClassTemplate(day), CREATE_NEW);
    }

    private Path javaPackageDirs() {
        return Paths.get("de", "adventofcode", "chrisgw");
    }


    private CharSequence javaClassTemplate(int day) {
        StringBuilder sb = new StringBuilder();
        sb.append("package de.adventofcode.chrisgw.day03;\n\n");
        sb.append("import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;\n\n");
        sb.append("/**\n");
        sb.append(" * https://adventofcode.com/").append(year).append("/day/").append(day).append("\n");
        sb.append(" */\n");
        sb.append("public class AdventOfCodeDay").append(formattedDay(day))
                .append(" extends AdventOfCodePuzzleSolver<Integer> {\n\n");
        sb.append("    public AdventOfCodeDay03(List<String> inputLines) {\n");
        sb.append("        super(Year.of(").append(year).append("), ").append(day).append(", inputLines);\n");
        sb.append("    }\n\n");
        sb.append("    public Integer solveFirstPart() {\n");
        sb.append("        //TODO solveFirstPart\n");
        sb.append("        return 0;\n");
        sb.append("    }\n\n");
        sb.append("    public Integer solveSecondPart() {\n");
        sb.append("        //TODO solveSecondPart\n");
        sb.append("        return 0;\n");
        sb.append("    }\n\n");
        sb.append("}\n");
        return sb;
    }


    private CharSequence javaTestClassTemplate(int day) {
        StringBuilder sb = new StringBuilder();
        sb.append("package de.adventofcode.chrisgw.day03;\n\n");
        sb.append("public class AdventOfCodeDay").append(formattedDay(day)).append("Test {\n\n");
        sb.append("private static final List<String> inputLinesExample = List.of(\"\");\n\n");
        sb.append(" @Test\n");
        sb.append("    public void solveAocPuzzle_firstPart_example() {\n");
        sb.append("        int result = new AdventOfCodeDay").append(formattedDay(day)).append("(inputLinesExample).solveFirstPart();\n");
        sb.append("        assertThat(\"firstPart example\", result, is(7));\n");
        sb.append("    }\n\n");
        sb.append("        //TODO solveFirstPart\n");
        sb.append("        return 0;\n");
        sb.append("    }\n\n");
        sb.append("    public Integer solveSecondPart() {\n");
        sb.append("        //TODO solveSecondPart\n");
        sb.append("        return 0;\n");
        sb.append("    }\n\n");
        sb.append("}\n");
        return sb;
    }

    private String formattedDay(int day) {
        return "day%02d".formatted(day);
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
                throw new RuntimeException("requestMyPuzzleInput response status code was: " + response.statusCode());
            }
            return response.body().toList();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("missing first argument for session cookie value for advent of code page");
            System.exit(1);
        }
        String aocSessionCookie = args[0];
        Year year = Year.now();
        Path projectDirectory = Paths.get(year.toString()).toAbsolutePath();
        System.out.println("prepare Advent of Code " + year + " in project directory: " + projectDirectory);
        HttpClient httpClient = HttpClient.newHttpClient();
        var aocPuzzlePreparer = new AdventOfCodePuzzlePreparer(projectDirectory, year, aocSessionCookie, httpClient);
        aocPuzzlePreparer.prepareAvailableAocPuzzle();
        System.exit(0);
    }


}
