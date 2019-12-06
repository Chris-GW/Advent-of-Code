package de.adventofcode.chrisgw.day06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * 2019 Day 6: Universal Orbit Map
 * https://adventofcode.com/2019/day/6
 */
public class AdventOfCodeDay06 {

    public static final Pattern ORBIT_RELATIONSHIP_PATTERN = Pattern.compile("(.+)\\)(.+)");

    private Map<String, ObjectInSpace> universalOrbitMap = new HashMap<>();


    public static AdventOfCodeDay06 parseUniversalOrbitMap(List<String> lines) {
        AdventOfCodeDay06 aocDay06 = new AdventOfCodeDay06();
        ObjectInSpace centerOfMass = new ObjectInSpace(ObjectInSpace.CENTER_OF_MASS_NAME);
        aocDay06.putObjectInSpace(centerOfMass);
        lines.forEach(line -> parseObjectInSpace(aocDay06, line));
        lines.forEach(line -> parseOrbitRelationship(aocDay06, line));
        return aocDay06;
    }

    private static void parseObjectInSpace(AdventOfCodeDay06 aocDay06, String line) {
        Matcher matcher = ORBIT_RELATIONSHIP_PATTERN.matcher(line);
        if (matcher.matches()) {
            String orbitObjectName = matcher.group(2);
            ObjectInSpace orbitObject = new ObjectInSpace(orbitObjectName);
            aocDay06.universalOrbitMap.putIfAbsent(orbitObjectName, orbitObject);
        } else {
            throw new IllegalArgumentException("Expect line to match pattern: " + ORBIT_RELATIONSHIP_PATTERN);
        }
    }

    private static void parseOrbitRelationship(AdventOfCodeDay06 aocDay06, String line) {
        Matcher matcher = ORBIT_RELATIONSHIP_PATTERN.matcher(line);
        if (matcher.matches()) {
            String centerObjectName = matcher.group(1);
            ObjectInSpace centerObject = aocDay06.findObjectInSpace(centerObjectName);

            String orbitObjectName = matcher.group(2);
            ObjectInSpace orbitObject = aocDay06.findObjectInSpace(orbitObjectName);
            orbitObject.setCenterObject(centerObject);
        }
    }


    private void putObjectInSpace(ObjectInSpace objectInSpace) {
        String name = objectInSpace.getName();
        universalOrbitMap.put(name, objectInSpace);
    }


    public ObjectInSpace findObjectInSpace(String name) {
        return universalOrbitMap.get(name);
    }

    public ObjectInSpace centerOfMass() {
        return findObjectInSpace(ObjectInSpace.CENTER_OF_MASS_NAME);
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
