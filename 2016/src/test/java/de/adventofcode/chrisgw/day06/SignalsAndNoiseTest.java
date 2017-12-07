package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class SignalsAndNoiseTest {

    @Test
    public void getErrorCorrectedMessageByMostOccurenceColumnLetter_easter() {
        // @formatter:off
        List<String> messages = Arrays.asList(
                "eedadn",
                "drvtee",
                "eandsr",
                "raavrd",
                "atevrs",
                "tsrnev",
                "sdttsa",
                "rasrtv",
                "nssdts",
                "ntnada",
                "svetve",
                "tesnvt",
                "vntsnd",
                "vrdear",
                "dvrsen",
                "enarar");
        // @formatter:on
        String expectedErrorCorrectedMessage = "easter";

        String errorCorrectedMessage = SignalsAndNoise.getErrorCorrectedMessageByMostOccurenceColumnLetter(messages);
        System.out.println(errorCorrectedMessage);

        Assert.assertEquals("Expect errorCorrectedMessage", expectedErrorCorrectedMessage, errorCorrectedMessage);
    }

    @Test
    public void getErrorCorrectedMessageByMostOccurenceColumnLetter_myTask() {
        // @formatter:off
        List<String> messages = TestUtils.readAllLinesOfClassPathResource("/day06/SignalsAndNoise.txt");
        String expectedErrorCorrectedMessage = "kqsdmzft";

        String errorCorrectedMessage = SignalsAndNoise.getErrorCorrectedMessageByMostOccurenceColumnLetter(messages);
        System.out.println(errorCorrectedMessage);

        Assert.assertEquals("Expect errorCorrectedMessage", expectedErrorCorrectedMessage, errorCorrectedMessage);
    }


    // --- part 2

    @Test
    public void getErrorCorrectedMessageByLeastOccurenceColumnLetter_easter() {
        // @formatter:off
        List<String> messages = Arrays.asList(
                "eedadn",
                "drvtee",
                "eandsr",
                "raavrd",
                "atevrs",
                "tsrnev",
                "sdttsa",
                "rasrtv",
                "nssdts",
                "ntnada",
                "svetve",
                "tesnvt",
                "vntsnd",
                "vrdear",
                "dvrsen",
                "enarar");
        // @formatter:on
        String expectedErrorCorrectedMessage = "advent";

        String errorCorrectedMessage = SignalsAndNoise.getErrorCorrectedMessageByLeastOccurenceColumnLetter(messages);
        System.out.println(errorCorrectedMessage);

        Assert.assertEquals("Expect errorCorrectedMessage", expectedErrorCorrectedMessage, errorCorrectedMessage);
    }

    @Test
    public void getErrorCorrectedMessageByLeastOccurenceColumnLetter_myTask() {
        // @formatter:off
        List<String> messages = TestUtils.readAllLinesOfClassPathResource("/day06/SignalsAndNoise.txt");
        String expectedErrorCorrectedMessage = "tpooccyo";

        String errorCorrectedMessage = SignalsAndNoise.getErrorCorrectedMessageByLeastOccurenceColumnLetter(messages);
        System.out.println(errorCorrectedMessage);

        Assert.assertEquals("Expect errorCorrectedMessage", expectedErrorCorrectedMessage, errorCorrectedMessage);
    }

}
