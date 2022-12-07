package de.craftery;

public enum Hint {
    PRESS_SPACE_TO_START("Drücken sie LEERTASTE um zu starten"),
    PRESS_SPACE_TO_START_AIMING("Drücken sie LEERTASTE um zu zielen"),
    PRESS_SPACE_TO_SHOOT("Drücken sie LEERTASTE um zu schießen"),
    MISSED_TARGET("Sie haben das Ziel verfehlt"),
    SCORED_POINTS("Sie haben %s Punkte erzielt");

    private final String message;
    Hint(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
