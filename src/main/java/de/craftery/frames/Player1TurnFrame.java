package de.craftery.frames;

import de.craftery.Game;
import de.craftery.GameFrame;
import de.craftery.Hint;
import sas.Text;

public class Player1TurnFrame extends GameFrame {
    public Player1TurnFrame(Game game) {
        super(game);
    }

    @Override
    public void render() {
        game.displayHint(Hint.PRESS_SPACE_TO_START_AIMING);

        Text text = new Text(0,0,"Spieler 1 ist dran");
        game.addObject(text);
    }
}

