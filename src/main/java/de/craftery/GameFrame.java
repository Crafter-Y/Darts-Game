package de.craftery;

public abstract class GameFrame {
    public final Game game;

    public GameFrame(Game game) {
        this.game = game;
    }

    public void spacePressed() {}

    public void render () {}

    public void update (long dt) {}
}
