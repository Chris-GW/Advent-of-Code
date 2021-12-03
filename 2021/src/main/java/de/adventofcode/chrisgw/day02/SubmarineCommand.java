package de.adventofcode.chrisgw.day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public record SubmarineCommand(SubmarineCommandType commandType, int units) {

    public static final Pattern SUBMARIEN_COMMAND_PATTERN = Pattern.compile("(forward|down|up)\\s+(\\d+)");

    public static SubmarineCommand parseSubmarineCommand(String commandLine) {
        Matcher matcher = SUBMARIEN_COMMAND_PATTERN.matcher(commandLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("expect command matching %s, but was: %s" //
                    .formatted(SUBMARIEN_COMMAND_PATTERN, commandLine));
        }
        SubmarineCommandType commandType = SubmarineCommandType.parse(matcher.group(1));
        int units = Integer.parseInt(matcher.group(2));
        return new SubmarineCommand(commandType, units);
    }

}
