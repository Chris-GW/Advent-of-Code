package de.adventofcode.chrisgw.day04;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class SecurityThroughObscurity {


    public static EncyptedRoom parseEncryptedRoom(String encryptedRoom) {
        return EncyptedRoom.parseEncryptedRoom(encryptedRoom);
    }

    public static List<EncyptedRoom> parseEncryptedRoomList(String encryptedRooms) {
        String[] splittedEncryptedRooms = encryptedRooms.split("\n");
        List<EncyptedRoom> encyptedRoomList = new ArrayList<>(splittedEncryptedRooms.length);
        for (String splittedEncryptedRoom : splittedEncryptedRooms) {
            EncyptedRoom encyptedRoom = parseEncryptedRoom(splittedEncryptedRoom);
            encyptedRoomList.add(encyptedRoom);
        }
        return encyptedRoomList;
    }


    public static class EncyptedRoom {

        public static Pattern ENCRYPTED_ROOM_PATTERN = Pattern.compile("([a-z\\-]+)-(\\d+)(\\[([a-z]+)])?");

        public String roomName;
        public int sectorId;
        public String checksum;


        public EncyptedRoom(String roomName, int sectorId, String checksum) {
            this.roomName = roomName;
            this.sectorId = sectorId;
            this.checksum = checksum;
        }


        public static EncyptedRoom parseEncryptedRoom(String encryptedRoom) {
            Matcher encryptedRoomMatcher = ENCRYPTED_ROOM_PATTERN.matcher(encryptedRoom);
            if (!encryptedRoomMatcher.matches()) {
                String message = String.format("Encrypted room doesn't match expected pattern '%s': %s",
                        ENCRYPTED_ROOM_PATTERN.pattern(), encryptedRoom);
                throw new IllegalArgumentException(message);
            }

            String roomName = encryptedRoomMatcher.group(1);
            int sectorId = Integer.parseInt(encryptedRoomMatcher.group(2));
            String checksum = encryptedRoomMatcher.group(4);
            return new EncyptedRoom(roomName, sectorId, checksum);
        }


        public boolean isValid() {
            Map<Character, LetterOccurence> letterOccurrence = new HashMap<>();
            for (int i = 0; i < roomName.length(); i++) {
                char c = roomName.charAt(i);
                if (c == '-') {
                    continue;
                }

                LetterOccurence letterOccurence = letterOccurrence.computeIfAbsent(c, LetterOccurence::new);
                letterOccurence.occurence++;
            }

            List<LetterOccurence> letterRank = letterOccurrence.values()
                    .stream()
                    .sorted(LetterOccurence.compareByOccurrence())
                    .collect(Collectors.toList());
            for (int i = 0; i < checksum.length(); i++) {
                char letter = letterRank.get(i).letter;
                char checksumLetter = checksum.charAt(i);
                if (checksumLetter != letter) {
                    return false;
                }
            }
            return true;
        }

        public String decryptRoomName() {
            StringBuilder decryptedRoomName = new StringBuilder(roomName);
            int cipherShift = sectorId % 26;

            for (int i = 0; i < decryptedRoomName.length(); i++) {
                char letter = decryptedRoomName.charAt(i);
                if (letter == '-') {
                    decryptedRoomName.setCharAt(i, ' ');
                    continue;
                }
                letter += cipherShift;
                if (letter > 'z') {
                    letter -= 26;
                }
                decryptedRoomName.setCharAt(i, letter);
            }
            return decryptedRoomName.toString();
        }


        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            EncyptedRoom that = (EncyptedRoom) o;

            if (sectorId != that.sectorId)
                return false;
            if (roomName != null ? !roomName.equals(that.roomName) : that.roomName != null)
                return false;
            return checksum != null ? checksum.equals(that.checksum) : that.checksum == null;
        }

        @Override
        public int hashCode() {
            int result = roomName != null ? roomName.hashCode() : 0;
            result = 31 * result + sectorId;
            result = 31 * result + (checksum != null ? checksum.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "EncyptedRoom{" + "roomName='" + roomName + '\'' + ", sectorId=" + sectorId + ", checksum='"
                    + checksum + '\'' + '}';
        }

    }


    public static class LetterOccurence implements Comparable<LetterOccurence> {

        public char letter;
        public int occurence;

        public LetterOccurence(char letter) {
            this.letter = letter;
            this.occurence = 0;
        }


        @Override
        public int compareTo(LetterOccurence o) {
            return Character.compare(letter, o.letter);
        }

        public static Comparator<LetterOccurence> compareByOccurrence() {
            return Comparator.comparingInt((LetterOccurence o) -> o.occurence)
                    .reversed()
                    .thenComparingInt(o -> o.letter);
        }

    }

}
