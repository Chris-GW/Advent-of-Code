package de.adventofcode.chrisgw.day02;

public record ProductId(long id) {


    public boolean isValid() {
        String idStr = String.valueOf(id);
        String firstPart = idStr.substring(0, idStr.length() / 2);
        String secondPart = idStr.substring(idStr.length() / 2);
        return !firstPart.equals(secondPart);
    }

}
