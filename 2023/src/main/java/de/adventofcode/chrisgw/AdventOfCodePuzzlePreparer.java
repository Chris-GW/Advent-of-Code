package de.adventofcode.chrisgw;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.AdventOfCodePuzzle.ADVENT_OF_CODE_PUZZLE_COUNT;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE_NEW;


public class AdventOfCodePuzzlePreparer {

    private final Path projectDirectory;
    private final Year year;
    private final String sessionCookie;
    private final HttpClient httpClient;


    public AdventOfCodePuzzlePreparer(Path projectDirectory, Year year, String sessionCookie, HttpClient httpClient) {
        this.projectDirectory = projectDirectory;
        this.year = year;
        this.sessionCookie = sessionCookie;
        this.httpClient = httpClient;
    }


    public void prepareAvailableAocPuzzle() throws IOException {
        LocalDate now = LocalDate.now();
        for (int day = 1; day < ADVENT_OF_CODE_PUZZLE_COUNT; day++) {
            AdventOfCodePuzzle aocPuzzle = new AdventOfCodePuzzle(year, day);
            if (now.isBefore(aocPuzzle.date())) {
                break;
            }
            prepareAocPuzzle(aocPuzzle);
        }
    }

    public void prepareAocPuzzle(AdventOfCodePuzzle aocPuzzle) throws IOException {
        createJavaAocDayClass(aocPuzzle);
        createJavaAocDayTestClass(aocPuzzle);
        if (ZonedDateTime.now().isAfter(aocPuzzle.puzzleOpening())) {
            createPuzzleInputFiel(aocPuzzle);
        }
    }


    private void createJavaAocDayClass(AdventOfCodePuzzle aocPuzzle) throws IOException {
        Path javaFile = projectDirectory.resolve("src")
                .resolve("main")
                .resolve(javaPackageDirs())
                .resolve("day%02d".formatted(aocPuzzle.day()))
                .resolve("AdventOfCodeDay%02d.java".formatted(aocPuzzle.day()));
        writeTemplateFile(aocPuzzle, javaFile, "/AdventOfCodeTemplate.java");
    }

    private void createJavaAocDayTestClass(AdventOfCodePuzzle aocPuzzle) throws IOException {
        Path javaTestFile = projectDirectory.resolve("src")
                .resolve("test")
                .resolve(javaPackageDirs())
                .resolve("day%02d".formatted(aocPuzzle.day()))
                .resolve("AdventOfCodeDay%02dTest.java".formatted(aocPuzzle.day()));
        if (Files.exists(javaTestFile)) {
            return;
        }
        Files.createDirectories(javaTestFile.getParent());
        writeTemplateFile(aocPuzzle, javaTestFile, "/AdventOfCodeTestTemplate.java");
    }


    private static void writeTemplateFile(AdventOfCodePuzzle aocPuzzle, Path javaFile, String templateResource)
            throws IOException {
        if (Files.exists(javaFile)) {
            return;
        }
        Files.createDirectories(javaFile.getParent());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("day", aocPuzzle.day());
        parameters.put("day0", String.format("%02d", aocPuzzle.day()));
        parameters.put("year", aocPuzzle.year().getValue());

        InputStream templateInput = AdventOfCodePuzzlePreparer.class.getResourceAsStream(templateResource);
        if (templateInput == null) {
            throw new IllegalArgumentException("could not find templateResource: " + templateResource);
        }
        try (Scanner sc = new Scanner(templateInput, UTF_8);
                PrintWriter writer = new PrintWriter(Files.newBufferedWriter(javaFile, UTF_8))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String formattedLine = format(line, parameters);
                writer.println(formattedLine);
            }
        }
    }

    public static String format(String template, Map<String, Object> parameters) {
        StringBuilder newTemplate = new StringBuilder(template);
        List<Object> valueList = new ArrayList<>();

        Matcher matcher = Pattern.compile("[$][{](\\w+)}").matcher(template);
        while (matcher.find()) {
            String key = matcher.group(1);

            String paramName = "${" + key + "}";
            int index = newTemplate.indexOf(paramName);
            if (index != -1) {
                newTemplate.replace(index, index + paramName.length(), "%s");
                valueList.add(parameters.get(key));
            }
        }
        return String.format(newTemplate.toString(), valueList.toArray());
    }

    private Path javaPackageDirs() {
        return Paths.get("java", "de", "adventofcode", "chrisgw");
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("missing first argument for session cookie value for advent of code page");
            System.exit(1);
        }
        String aocSessionCookie = args[0];
        Year year;
        if (args.length >= 2) {
            year = Year.of(Integer.parseInt(args[1]));
        } else {
            year = promptForYear();
        }
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
                System.out.println("Could not read year: " + e.getMessage());
            }
        }
        return year;
    }


}
