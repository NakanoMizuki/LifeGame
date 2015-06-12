package jp.ac.titech.psg.nakano.lifegame.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static jp.ac.titech.psg.nakano.lifegame.Const.*;
import jp.ac.titech.psg.nakano.lifegame.controller.Controller;

@SuppressWarnings("serial")
public class ToolBar extends JPanel {
    private final JToggleButton playBtn;

    public ToolBar(Controller controller) {
        setBounds(0, 0, FRAME_W, BAR_H);

        playBtn = new JToggleButton("Play");
        playBtn.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.changePlayButton(e);
            }
        });
        add(playBtn);

        JButton step = new JButton("Step");
        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.pushStepButton();
            }
        });
        add(step);

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.pushResetButton();
            }
        });
        add(reset);
    }

    public JToggleButton getToggleBtn() {
        return playBtn;
    }
}
