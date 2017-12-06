package de.adventofcode.chrisgw.day05;

import org.junit.Assert;
import org.junit.Test;


public class HowAboutANiceGameOfChessTest {


    @Test
    public void generateSecurityPassword_abc() throws Exception {
        String doorId = "abc";
        String expectedGeneratedPassword = "18f47a30";

        String generatePassword = HowAboutANiceGameOfChess.generateSecurePassword(doorId);
        System.out.println(generatePassword);

        Assert.assertEquals("Expect generated password", expectedGeneratedPassword, generatePassword);
    }

    @Test
    public void generateSecurityPassword_myTask_wtnhxymk() throws Exception {
        String doorId = "wtnhxymk";
        String expectedGeneratedPassword = "2414bc77";

        String generatePassword = HowAboutANiceGameOfChess.generateSecurePassword(doorId);
        System.out.println(generatePassword);

        Assert.assertEquals("Expect generated password", expectedGeneratedPassword, generatePassword);
    }


    // --- part 2

    @Test
    public void generateBetterSecurePassword_abc() throws Exception {
        String doorId = "abc";
        String expectedGeneratedPassword = "05ace8e3";

        String generatePassword = HowAboutANiceGameOfChess.generateBetterSecurePassword(doorId);
        System.out.println(generatePassword);

        Assert.assertEquals("Expect generated password", expectedGeneratedPassword, generatePassword);
    }

    @Test
    public void generateBetterSecurePassword_myTask_wtnhxymk() throws Exception {
        String doorId = "wtnhxymk";
        String expectedGeneratedPassword = "437e60fc";

        String generatePassword = HowAboutANiceGameOfChess.generateBetterSecurePassword(doorId);
        System.out.println(generatePassword);

        Assert.assertEquals("Expect generated password", expectedGeneratedPassword, generatePassword);
    }

}