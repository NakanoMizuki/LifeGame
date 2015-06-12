package jp.ac.titech.psg.nakano.lifegame.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import jp.ac.titech.psg.nakano.lifegame.controller.Controller;
import jp.ac.titech.psg.nakano.lifegame.model.Area;
import static jp.ac.titech.psg.nakano.lifegame.Const.*;

@SuppressWarnings("serial")
public class CellCanvas extends JPanel {

    public CellCanvas(Controller controller) {
        this.setBounds(0, BAR_H, FRAME_W, CANVAS_H);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                controller.clickCell(x / CELL_W, y / CELL_H);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public void draw(Graphics g, Area area) {
        g.setColor(Color.white);
        g.fillRect(0, 0, FRAME_W, FRAME_H);
        g.setColor(COLOR);
        for (int i = 0; i < CELL_RAW; i++) {
            for (int j = 0; j < CELL_COLUMN; j++) {
                if (area.isAlive(i, j)) {
                    g.fillRect(CELL_W * i, CELL_H * j, CELL_W, CELL_H);
                } else {
                    g.drawRect(CELL_W * i, CELL_H * j, CELL_W, CELL_H);
                }
            }
        }
    }

}
