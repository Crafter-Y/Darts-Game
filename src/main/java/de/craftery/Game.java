package de.craftery;

import de.craftery.frames.MainGameFrame;
import de.craftery.frames.ReadyFrame;
import sas.Shapes;
import sas.Text;
import sas.View;

import java.awt.*;
import java.util.ArrayList;

public class Game {
    private final View view;
    private final int height;
    private final int width;

    private Text hint;

    private Text score1;
    private Text score2;

    private String currentHint;

    private GameFrame gameFrame;

    private final ArrayList<Shapes> currentRenderObjects = new ArrayList<>();

    private final ArrayList<TargetPart> targetParts = new ArrayList<>();

    private int currentPlayer = 1;
    private int player1Score = 0;
    private int player2Score = 0;

    public Game(int width, int height, String title) {
        this.height = height;
        this.width = width;
        this.view = new View(width, height, title);
        this.view.setBackgroundColor(Color.decode("0xf1f1f1"));

        this.setGameFrame(new ReadyFrame(this));
        this.renderScores();
        this.initializeParts();

        this.startGameLoop();
    }

    private void initializeParts() {
        this.targetParts.add(new TargetPart(40, 100, 119, Color.RED));
        this.targetParts.add(new TargetPart(20, 120, 189, Color.BLACK));
        this.targetParts.add(new TargetPart(60, 190, 199, Color.RED));
        this.targetParts.add(new TargetPart(20, 200, 269, Color.BLACK));

        this.targetParts.add(new TargetPart(25, 270, 294, Color.GREEN));
        this.targetParts.add(new TargetPart(50, 295, 304, Color.RED));
        this.targetParts.add(new TargetPart(25, 305, 329, Color.GREEN));

        this.targetParts.add(new TargetPart(3, 330, 399, Color.BLACK));
        this.targetParts.add(new TargetPart(9, 400, 409, Color.RED));
        this.targetParts.add(new TargetPart(3, 410, 479, Color.BLACK));
        this.targetParts.add(new TargetPart(6, 480, 499, Color.RED));
    }

    public ArrayList<TargetPart> getTargetParts() {
        return this.targetParts;
    }

    private void startGameLoop() {
        long dt = 0;
        long lastTime = System.currentTimeMillis();
        while (true) {
            dt += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            this.gameFrame.update(dt);

            if (this.view.keyPressed(' ')) {
                this.gameFrame.spacePressed();
                this.view.keyBufferDelete();
            }
        }
    }

    public void setGameFrame(GameFrame gameFrame) {
        currentRenderObjects.forEach(view::remove);
        this.gameFrame = gameFrame;
        this.gameFrame.render();
    }

    public void addObject(Shapes object) {
        currentRenderObjects.add(object);
    }

    public void displayHint(Hint hint) {
        this.displayHint(hint.getMessage());
    }

    public void displayHint(Hint hint, String replacement) {
        this.displayHint(hint.getMessage().replace("%s", replacement));
    }

    private void displayHint(String hint) {
        if (hint.equals(currentHint)) return;
        this.currentHint = hint;

        if (this.hint != null) {
            this.view.remove(this.hint);
        }
        int x = (this.width - hint.length() * 10) / 2;
        this.hint = new Text(x, this.height-50, hint);
    }

    public void nextPlayer() {
        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.renderScores();
        this.setGameFrame(new MainGameFrame(this));
    }

    private void renderScores() {
        if (this.score1 != null) {
            this.view.remove(this.score1);
        }
        if (this.score2 != null) {
            this.view.remove(this.score2);
        }

        this.score1 = new Text(600, 20, "Spieler 1: " + this.player1Score);
        this.score2 = new Text(600, 40, "Spieler 2: " + this.player2Score);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }
}
