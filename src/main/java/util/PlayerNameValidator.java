package util;

public class PlayerNameValidator {
    private final int MAX_NAME_LENGTH = 30;

    public boolean isEqualsNames(String firstPlayerName, String secondPlayerName) {
        return firstPlayerName.replaceAll("[ _]+", " ").trim()
                .equalsIgnoreCase(secondPlayerName.replaceAll("[ _]+", " ").trim());
    }

    public boolean isNameConsistsOfLetters(String name) {
        return !name.isBlank() && name.replaceAll("\\s+", "").matches("^[a-zA-Zа-яА-ЯёЁ'-]+$");
    }

    public boolean isNameLongerThanMaxLength(String name) {
        return name.length() > MAX_NAME_LENGTH;
    }

    public int getMaxNameLength() {
        return MAX_NAME_LENGTH;
    }
}
