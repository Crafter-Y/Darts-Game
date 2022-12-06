package de.craftery;

public enum Hint {
    PRESS_SPACE_TO_START("Drücken sie LEERTASTE um zu starten"),
    EEEEE("eeeeeee");

    private final String message;
    Hint(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
