package jp.ac.titech.psg.nakano.lifegame.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;

import jp.ac.titech.psg.nakano.lifegame.component.CellCanvas;
import jp.ac.titech.psg.nakano.lifegame.model.Area;
import static jp.ac.titech.psg.nakano.lifegame.Const.*;

public class Controller implements Runnable {
    private static final Logger logger = Logger.getLogger(TITLE);
    private final Area area;
    private CellCanvas canvas;
    private JToggleButton playBtn;
    private boolean playing, stepFlag, resetFlag;
    private int count;
    private List<Point> clickedCells;

    public Controller(Area area) {
        this.area = area;
        playing = false;
        stepFlag = false;
        resetFlag = false;
        count = 0;
        clickedCells = new ArrayList<Point>();
    }

    public void changePlayButton(ChangeEvent e) {
        logger.info("changePlayButton");
        if (playBtn.isSelected()) {
            playing = true;
            stepFlag = false;
            resetFlag = false;
            playBtn.setText("Stop");
        } else {
            stopPlaying();
        }
    }

    public void pushStepButton() {
        logger.info("pushStepButton");
        stopPlaying();
        stepFlag = true;
    }

    public void pushResetButton() {
        logger.info("pushResetButton");
        stopPlaying();
        resetFlag = true;
    }

    public void clickCell(int raw, int column) {
        if (playing)
            return;
        assert 0 <= raw && raw < CELL_RAW;
        assert 0 <= column && column < CELL_COLUMN;
        logger.info("clickCell raw=" + raw + "column=" + column);
        clickedCells.add(new Point(raw, column));
    }

    private void stopPlaying() {
        playing = false;
        count = 0;
        playBtn.setSelected(false);
        playBtn.setText("Play");
    }

    private void update() {
        assert !(playing && (resetFlag || stepFlag));
        if (!clickedCells.isEmpty()) {
            synchronized (clickedCells) {
                for (Point p : clickedCells) {
                    area.changeStatus((int) p.getX(), (int) p.getY());
                }
                clickedCells.clear();
            }
        }
        if (playing) {
            if (count == 0)
                area.update();
            count++;
            if (count > COUNT)
                count = 0;
        } else {
            if (stepFlag) {
                area.update();
                stepFlag = false;
            }
            if (resetFlag) {
                area.reset();
                resetFlag = false;
            }
        }
    }

    public void start(CellCanvas canvas, JToggleButton btn) {
        this.canvas = canvas;
        this.playBtn = btn;
        new Thread(this).start();
    }

    @Override
    public void run() {
        long beforeTime, afterTime, timeDiff, sleepTime;
        long overSleepTime = 0L;
        int noDelays = 0;

        beforeTime = System.nanoTime();
        Image back = canvas.createImage(FRAME_W, CANVAS_H);
        Graphics buffer = back.getGraphics();
        while (true) {
            update();
            canvas.draw(buffer, area);
            canvas.getGraphics().drawImage(back, 0, 0, null);

            // 以下はFPS関連
            afterTime = System.nanoTime();
            timeDiff = afterTime - beforeTime;
            sleepTime = (PERIOD - timeDiff) - overSleepTime;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000L); // nano->ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
            } else {
                overSleepTime = 0L;
                // 休止なしが16回以上続いたら
                if (++noDelays >= 16) {
                    Thread.yield(); // 他のスレッドを強制実行
                    noDelays = 0;
                }
            }
            beforeTime = System.nanoTime();
        }
    }

}
