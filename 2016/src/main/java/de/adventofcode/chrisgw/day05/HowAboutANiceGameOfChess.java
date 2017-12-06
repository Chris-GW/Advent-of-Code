package de.adventofcode.chrisgw.day05;


import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HowAboutANiceGameOfChess {

    public static String generatePassword(String doorId) throws NoSuchAlgorithmException {
        StringBuilder password = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("MD5");

        for (int i = 0; password.length() < 8; i++) {
            String nextPassword = doorId + i;
            md.update(nextPassword.getBytes());
            byte[] digest = md.digest();
            String passwordHashHexadecimal = DatatypeConverter.printHexBinary(digest);

            if (isValidPasswordHash(passwordHashHexadecimal)) {
                char newPasswordLetter = passwordHashHexadecimal.charAt(5);
                password.append(newPasswordLetter);
            }
        }
        return password.toString().toLowerCase();
    }

    private static boolean isValidPasswordHash(String passwordHashHexadecimal) {
        return passwordHashHexadecimal.startsWith("00000");
    }


}
