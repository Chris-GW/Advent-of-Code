package de.adventofcode.chrisgw;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class TestUtils {


    public static List<String> readAllLinesOfClassPathResource(String classpathResource) {
        try {
            Path resourcePath = Paths.get(TestUtils.class.getResource(classpathResource).toURI());
            return Files.readAllLines(resourcePath);
        } catch (Exception e) {
            throw new RuntimeException("Could not read all lines of classpath resource: " + classpathResource, e);
        }
    }

    public static String readFirstLineOfClassPathResource(String classpathResource) {
        List<String> allLines = readAllLinesOfClassPathResource(classpathResource);
        if (allLines.size() < 1) {
            throw new IllegalStateException("Expect classpath resource with a first line");
        }
        return allLines.get(0);
    }


}
