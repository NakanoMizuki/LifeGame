package jp.ac.titech.psg.nakano.lifegame;

import javax.swing.JFrame;

import static jp.ac.titech.psg.nakano.lifegame.Const.*;
import jp.ac.titech.psg.nakano.lifegame.component.CellCanvas;
import jp.ac.titech.psg.nakano.lifegame.component.ToolBar;
import jp.ac.titech.psg.nakano.lifegame.controller.Controller;
import jp.ac.titech.psg.nakano.lifegame.model.Area;

@SuppressWarnings("serial")
public class Frame extends JFrame {

    public Frame() {
        super(TITLE);
        setVisible(true);
        setBounds(0, 0, FRAME_W, FRAME_H);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Area area = new Area();
        Controller controller = new Controller(area);
        setLayout(null);
        ToolBar bar = new ToolBar(controller);
        add(bar);
        CellCanvas canvas = new CellCanvas(controller);
        add(canvas);
        controller.start(canvas, bar.getToggleBtn());
    }

    public static void main(String[] args) {
        new Frame();
    }
}
