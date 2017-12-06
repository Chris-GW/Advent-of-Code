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


}
