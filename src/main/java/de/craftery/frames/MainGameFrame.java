package de.craftery.frames;

import de.craftery.*;
import sas.Polygon;
import sas.Rectangle;
import sas.Text;
import sas.Sprite;

import java.awt.*;

public class MainGameFrame extends GameFrame {
    private long y;
    private long hitY;

    public MainGameFrame(Game game) {
        super(game);
    }

    private Sprite arrow;

    private AimState aimState = AimState.AIM_STATE_1;

    private AimDirection aimDirection = AimDirection.UP;

    private long startedShooting;

    @Override
    public void render() {
        game.displayHint(Hint.PRESS_SPACE_TO_START_AIMING);

        Text text = new Text(0,0,"Spieler "+ this.game.getCurrentPlayer() +" ist dran");
        game.addObject(text);

        this.arrow = new Sprite();
        Rectangle part1 = new Rectangle(10, 5, 50, 5, Color.BLACK);
        Polygon part2 = new Polygon(60, 0, Color.BLACK);
        part2.add(20, 7.5);
        part2.add(0, 15);
        arrow.add(part1);
        arrow.add(part2);

        game.addObject(arrow);

        this.paintBoard();
    }

    private void paintBoard() {
        for (TargetPart targetPart : game.getTargetParts()) {
            Rectangle rectangle = new Rectangle(700, targetPart.getStartY(), 20, targetPart.getEndY()-targetPart.getStartY(), targetPart.getColor());
            int yp = targetPart.getStartY()+((targetPart.getEndY()-targetPart.getStartY())/2)-15;
            Text text = new Text(721, yp, targetPart.getValue()+"");
            this.game.addObject(rectangle);
            this.game.addObject(text);
        }
    }

    @Override
    public void update(long dt) {
        switch (this.aimState) {
            case AIM_STATE_1 -> {
                dt = dt / 2;
                long y;
                if (dt % 900 > 450) {
                    y = 550 - (dt % 450);
                } else {
                    y = (dt % 900) + 100;
                }
                this.arrow.moveTo(10, y);
                this.y = y;
            }
            case AIM_STATE_2 -> {
                if (this.arrow.getDirection() < 45) {
                    this.aimDirection = AimDirection.DOWN;
                } else if (this.arrow.getDirection() > 135) {
                    this.aimDirection = AimDirection.UP;
                }
                this.arrow.turn(this.aimDirection.getFactor());
            }
            case SHOOTING -> {
                int msShootTime = 1000;
                if (System.currentTimeMillis()-this.startedShooting < msShootTime) {
                    double shootX = (double) (System.currentTimeMillis()-this.startedShooting)/msShootTime * 640;

                    double y;
                    if (this.arrow.getDirection() > 90) {
                        double alpha = this.arrow.getDirection()-90;
                        y = Math.tan(Math.toRadians(alpha)) * shootX + this.y;
                    } else if(this.arrow.getDirection() < 90) {
                        double alpha = 90-this.arrow.getDirection();
                        y = this.y - Math.tan(Math.toRadians(alpha)) * shootX;
                    } else {
                        y = (int) this.y;
                    }

                    this.arrow.moveTo(shootX, y);
                    this.hitY = (int) y;
                } else {
                    if (this.hitY < 100 || this.hitY >= 500) {
                        this.game.displayHint(Hint.MISSED_TARGET);
                    } else {
                        for (TargetPart targetPart : this.game.getTargetParts()) {
                            if (this.hitY >= targetPart.getStartY() && this.hitY <= targetPart.getEndY()) {
                                this.game.displayHint(Hint.SCORED_POINTS, targetPart.getValue()+"");
                                if (this.game.getCurrentPlayer() == 1) {
                                    this.game.setPlayer1Score(this.game.getPlayer1Score()+targetPart.getValue());
                                } else {
                                    this.game.setPlayer2Score(this.game.getPlayer2Score()+targetPart.getValue());
                                }
                                break;
                            }
                        }
                    }
                    this.game.nextPlayer();
                }

            }
        }
    }

    @Override
    public void spacePressed() {
        switch (this.aimState) {
            case AIM_STATE_1 -> {
                this.aimState = AimState.AIM_STATE_2;
                this.game.displayHint(Hint.PRESS_SPACE_TO_SHOOT);
            }
            case AIM_STATE_2 -> {
                this.aimState = AimState.SHOOTING;
                this.startedShooting = System.currentTimeMillis();
            }
        }
    }
}

