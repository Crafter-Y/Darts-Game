package de.craftery.frames;

import de.craftery.Game;
import de.craftery.GameFrame;
import de.craftery.Hint;

public class ReadyFrame extends GameFrame {
    public ReadyFrame(Game game) {
        super(game);
    }

    @Override
    public void spacePressed() {
        game.setGameFrame(new Player1TurnFrame(game));
    }

    @Override
    public void render() {
        game.displayHint(Hint.PRESS_SPACE_TO_START);
    }
}

