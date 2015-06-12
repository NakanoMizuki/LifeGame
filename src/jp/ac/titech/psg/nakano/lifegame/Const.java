package jp.ac.titech.psg.nakano.lifegame;

import java.awt.Color;

public class Const {
    // system
    public static final String TITLE = "LifeGame";
    public static final boolean ALIVE = true, DEAD = false;

    // speed
    public static final int FPS = 50;
    public static final long PERIOD = 1000 / FPS; // 1000ms / FPS
    public static final int COUNT = FPS; // change per COUNT frames

    // num
    public static final int CELL_RAW = 30, CELL_COLUMN = CELL_RAW;

    // layout
    public static final int CELL_W = 20, CELL_H = CELL_W;
    public static final int BAR_H = 50;
    public static final int CANVAS_H = (CELL_RAW + 1) * CELL_H;
    public static final int FRAME_W = CELL_COLUMN * CELL_W, FRAME_H = BAR_H
            + CANVAS_H;

    // color
    public static final Color COLOR = Color.BLACK;

}
