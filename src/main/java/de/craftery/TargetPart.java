package de.craftery;

import java.awt.*;

public class TargetPart {
    private final int value;
    private final int startY;
    private final int endY;
    private final Color color;

    public TargetPart(int value, int startY, int endY, Color color) {
        this.value = value;
        this.startY = startY;
        this.endY = endY;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }

    public Color getColor() {
        return color;
    }
}
