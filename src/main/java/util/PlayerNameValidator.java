package util;

public class PlayerNameValidator {
    public boolean areNamesEquals(String firstPlayerName, String secondPlayerName) {
        return firstPlayerName.equals(secondPlayerName);
    }

    public boolean isNameConsistsOfLetters(String name) {
        return !name.isBlank() && name.replaceAll("\\s+", "").matches("^[a-zA-Zа-яА-ЯёЁ]+$");
    }
}
