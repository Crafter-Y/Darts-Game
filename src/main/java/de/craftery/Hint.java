package de.craftery;

public enum Hint {
    PRESS_SPACE_TO_START("Drücken sie LEERTASTE um zu starten"),
    PRESS_SPACE_TO_START_AIMING("Drücken sie LEERTASTE um zu zielen");

    private final String message;
    Hint(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
