package de.adventofcode.chrisgw.day06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static de.adventofcode.chrisgw.day06.ObjectInSpace.CENTER_OF_MASS_NAME;


/**
 * 2019 Day 6: Universal Orbit Map
 * https://adventofcode.com/2019/day/6
 */
public class AdventOfCodeDay06 {

    public static final Pattern ORBIT_RELATIONSHIP_PATTERN = Pattern.compile("(.+)\\)(.+)");

    private Map<String, ObjectInSpace> universalOrbitMap = new HashMap<>();


    public static AdventOfCodeDay06 parseUniversalOrbitMap(List<String> lines) {
        AdventOfCodeDay06 aocDay06 = new AdventOfCodeDay06();
        lines.forEach(line -> putOrbitRelationship(aocDay06, line));
        return aocDay06;
    }

    private static void putOrbitRelationship(AdventOfCodeDay06 aocDay06, String line) {
        Matcher matcher = ORBIT_RELATIONSHIP_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(line + " must match pattern: " + ORBIT_RELATIONSHIP_PATTERN);
        }
        String centerObjectName = matcher.group(1);
        ObjectInSpace centerObject = aocDay06.putObjectInSpace(centerObjectName);

        String orbitObjectName = matcher.group(2);
        ObjectInSpace orbitObject = aocDay06.putObjectInSpace(orbitObjectName);
        orbitObject.setCenterObject(centerObject);
    }

    private ObjectInSpace putObjectInSpace(String objectName) {
        return universalOrbitMap.computeIfAbsent(objectName, ObjectInSpace::new);
    }


    public ObjectInSpace findObjectInSpace(String name) {
        return universalOrbitMap.get(name);
    }

    public ObjectInSpace centerOfMass() {
        return findObjectInSpace(CENTER_OF_MASS_NAME);
    }


    public long countTotalOrbits() {
        return universalOrbitMap.values()
                .stream()
                .mapToLong(objectInSpace -> objectInSpace.toCenterOfMass().count())
                .sum();
    }


    public int countNeededOrbitalTransfers() {
        ObjectInSpace youObject = findObjectInSpace("YOU");
        ObjectInSpace sanObject = findObjectInSpace("SAN");

        List<ObjectInSpace> toCenterPathYou = youObject.toCenterOfMass().collect(Collectors.toList());
        List<ObjectInSpace> toCenterPathSan = sanObject.toCenterOfMass().collect(Collectors.toList());

        ObjectInSpace intersectionObject = toCenterPathYou.stream()
                .filter(toCenterPathSan::contains)
                .findFirst()
                .orElseThrow();

        int stepsYou = toCenterPathYou.indexOf(intersectionObject);
        int stepsSan = toCenterPathSan.indexOf(intersectionObject);
        return stepsYou + stepsSan;
    }


}
