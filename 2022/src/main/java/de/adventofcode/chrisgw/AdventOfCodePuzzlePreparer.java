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
import java.util.Scanner;
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
        createJavaAocDayClass(day);
        createJavaAocDayTestClass(day);
        if (ZonedDateTime.now().isAfter(aocPuzzle.puzzleOpening())) {
            createPuzzleInputFiel(aocPuzzle);
        }
    }

    private void createPuzzleInputFiel(AdventOfCodePuzzle aocPuzzle) throws IOException {
        Path puzzleInputFile = projectDirectory.resolve("src")
                .resolve("test")
                .resolve("resources")
                .resolve("puzzleInputDay%02d.txt".formatted(aocPuzzle.day()));
        if (!Files.exists(puzzleInputFile)) {
            List<String> inputLines = requestMyPuzzleInput(aocPuzzle);
            Files.createDirectories(puzzleInputFile.getParent());
            Files.write(projectDirectory.resolve(puzzleInputFile), inputLines, CREATE_NEW);
        }
    }


    private void createJavaAocDayClass(int day) throws IOException {
        Path javaFile = projectDirectory.resolve("src")
                .resolve("main")
                .resolve(javaPackageDirs())
                .resolve("day%02d".formatted(day))
                .resolve("AdventOfCodeDay%02d.java".formatted(day));
        if (!Files.exists(javaFile)) {
            Files.createDirectories(javaFile.getParent());
            Files.writeString(javaFile, javaClassTemplate(day), CREATE_NEW);
        }
    }

    private void createJavaAocDayTestClass(int day) throws IOException {
        Path javaTestFile = projectDirectory.resolve("src")
                .resolve("test")
                .resolve(javaPackageDirs())
                .resolve("day%02d".formatted(day))
                .resolve("AdventOfCodeDay%02dTest.java".formatted(day));
        if (!Files.exists(javaTestFile)) {
            Files.createDirectories(javaTestFile.getParent());
            Files.writeString(javaTestFile, javaTestClassTemplate(day), CREATE_NEW);
        }
    }

    private Path javaPackageDirs() {
        return Paths.get("java", "de", "adventofcode", "chrisgw");
    }


    private CharSequence javaClassTemplate(int day) {
        String formattedDay = "%02d".formatted(day);
        StringBuilder sb = new StringBuilder();
        sb.append("package de.adventofcode.chrisgw.day").append(formattedDay).append(";\n\n");
        sb.append("import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;\n\n");
        sb.append("import java.time.Year;\n");
        sb.append("import java.util.List;\n\n");
        sb.append("/**\n");
        sb.append(" * https://adventofcode.com/").append(year).append("/day/").append(day).append("\n");
        sb.append(" */\n");
        sb.append("public class AdventOfCodeDay")
                .append(formattedDay)
                .append(" extends AdventOfCodePuzzleSolver<Integer> {\n\n");
        sb.append("    public AdventOfCodeDay").append(formattedDay).append("(List<String> inputLines) {\n");
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
        String formattedDay = "%02d".formatted(day);
        StringBuilder sb = new StringBuilder();
        sb.append("package de.adventofcode.chrisgw.day").append(formattedDay).append(";\n\n");
        sb.append("import org.junit.Test;\n");
        sb.append("import java.util.List;\n");
        sb.append("import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;\n");
        sb.append("import static org.junit.Assert.*;\n\n");
        sb.append("public class AdventOfCodeDay").append(formattedDay).append("Test {\n\n");
        sb.append("    private static final List<String> inputLinesExample = List.of(\"\");\n\n");
        sb.append("    @Test\n");
        sb.append("    public void solveAocPuzzle_firstPart_example() {\n");
        sb.append("        int result = new AdventOfCodeDay")
                .append(formattedDay)
                .append("(inputLinesExample).solveFirstPart();\n");
        sb.append("        assertEquals(\"firstPart example\", 1, result);\n");
        sb.append("    }\n\n");
        sb.append("    @Test\n");
        sb.append("    public void solveAocPuzzle_firstPart_myPuzzleInput() {\n");
        sb.append("        List<String> inputLines = readAllLinesOfClassPathResource(\"/puzzleInputDay")
                .append(formattedDay)
                .append(".txt\");\n");
        sb.append("        int result = new AdventOfCodeDay")
                .append(formattedDay)
                .append("(inputLines).solveFirstPart();\n");
        sb.append("        assertEquals(\"firstPart myPuzzleInput\", 1, result);\n");
        sb.append("    }\n\n");
        sb.append("    @Test\n");
        sb.append("    public void solveAocPuzzle_secondPart_example() {\n");
        sb.append("        int result = new AdventOfCodeDay")
                .append(formattedDay)
                .append("(inputLinesExample).solveSecondPart();\n");
        sb.append("        assertEquals(\"secondPart example\", 1, result);\n");
        sb.append("    }\n\n");
        sb.append("    @Test\n");
        sb.append("    public void solveAocPuzzle_secondPart_myPuzzleInput() {\n");
        sb.append("        List<String> inputLines = readAllLinesOfClassPathResource(\"/puzzleInputDay")
                .append(formattedDay)
                .append(".txt\");\n");
        sb.append("        int result = new AdventOfCodeDay")
                .append(formattedDay)
                .append("(inputLines).solveSecondPart();\n");
        sb.append("        assertEquals(\"secondPart myPuzzleInput\", 1, result);\n");
        sb.append("    }\n\n");
        sb.append("}\n");
        return sb;
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
        Year year = promptForYear();
        Path projectDirectory = Paths.get(year.toString()).toAbsolutePath();
        System.out.println("prepare Advent of Code " + year + " in project directory: " + projectDirectory);
        HttpClient httpClient = HttpClient.newHttpClient();
        var aocPuzzlePreparer = new AdventOfCodePuzzlePreparer(projectDirectory, year, aocSessionCookie, httpClient);
        aocPuzzlePreparer.prepareAvailableAocPuzzle();
        System.exit(0);
    }

    private static Year promptForYear() {
        Scanner scIn = new Scanner(System.in);
        Year year = null;
        while (year == null) {
            try {
                System.out.print("enter AoC year: ");
                year = Year.of(scIn.nextInt());
            } catch (Exception e) {
            }
        }
        return year;
    }


}
