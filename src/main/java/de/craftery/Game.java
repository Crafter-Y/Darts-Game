package de.craftery;

import sas.Text;
import sas.View;

import java.awt.*;

public class Game {
    private final View view;
    private final int height;
    private final int width;

    private Text hint;

    private Hint currentHint;

    public Game(int width, int height, String title) {
        this.height = height;
        this.width = width;
        this.view = new View(width, height, title);
        this.view.setBackgroundColor(Color.decode("0xf1f1f1"));
        displayHint(Hint.PRESS_SPACE_TO_START);

        while (true) {
            if (view.keyPressed('e')) {
                displayHint(Hint.EEEEE);
            }
            if (view.keyPressed(' ')) {
                displayHint(Hint.PRESS_SPACE_TO_START);
            }
        }


    }

    private void displayHint(Hint hint) {
        if (hint.getMessage().equals(currentHint.getMessage())) return;
        this.currentHint = hint;

        if (this.hint != null) {
            this.view.remove(this.hint);
        }
        int x = (this.width - hint.getMessage().length() * 10) / 2;
        this.hint = new Text(x, 0, hint.getMessage());
    }
}
