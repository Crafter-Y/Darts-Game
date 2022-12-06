package de.craftery;

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
    //private final Text fps;

    private Hint currentHint;

    private GameFrame gameFrame;

    private final ArrayList<Shapes> currentRenderObjects = new ArrayList<>();

    public Game(int width, int height, String title) {
        this.height = height;
        this.width = width;
        this.view = new View(width, height, title);
        this.view.setBackgroundColor(Color.decode("0xf1f1f1"));

        this.setGameFrame(new ReadyFrame(this));
        //this.fps = new Text(0, 0, "0");

        startGameLoop();
    }

    private void startGameLoop() {
        //long dt = System.currentTimeMillis()-1;
        while (true) {
            /*long td = System.currentTimeMillis() - dt; // milliseconds since last frame
            dt = System.currentTimeMillis();
            System.out.println(td);
            int fps = td == 0 ? 60 : (int) (1000 / td);
            this.fps.setText(fps + "");*/

            if (view.keyPressed(' ')) {
                this.gameFrame.spacePressed();
            }
            /*try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
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
        if (currentHint != null && hint.getMessage().equals(currentHint.getMessage())) return;
        this.currentHint = hint;

        if (this.hint != null) {
            this.view.remove(this.hint);
        }
        int x = (this.width - hint.getMessage().length() * 10) / 2;
        this.hint = new Text(x, 0, hint.getMessage());
    }
}
