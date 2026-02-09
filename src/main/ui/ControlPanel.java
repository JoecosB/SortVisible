package ui;

import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import controller.SortController;
import ui.SortPanel;

public class ControlPanel extends JPanel {
    private Timer timer;

    public ControlPanel(SortController controller, SortPanel sortPanel) {
        setLayout(new GridLayout(1, 6, 40, 10));

        // 建立算法选单
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("BubbleSort");
        comboBox.addItem("QuickSort");
        comboBox.addItem("SelectionSort");
        comboBox.addItem("MergeSort");

        // 建立“开始”按钮
        JButton startBtn = new JButton("Start/Resume");
        startBtn.addActionListener(e -> {
            if(timer==null || !timer.isRunning()) {
                String algorithm = (String) comboBox.getSelectedItem();
                controller.update_sorter(algorithm);
                timer = new Timer(controller.delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (controller.model.is_sorted()) {
                            timer.stop();
                            controller.beep_cleanup();
                        } else {
                            controller.step();
                            sortPanel.repaint();
                        }
                    }
                });
                timer.start();
            }
        });

        // 建立“停止”按钮
        JButton stopBtn = new JButton("Stop");
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timer!=null) {
                    timer.stop();
                    controller.beep_cleanup();
                }
            }
        });

        // 建立“摇匀”按钮
        JButton shuffleBtn = new JButton("Shuffle");
        shuffleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.shuffle_seq();
                controller.model.reset_ptr();
                sortPanel.repaint();
            }
        });

        // 建立“改变长度”按钮
        JButton lengthBtn = new JButton("Change Sequence Length");
        lengthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length = SeqLengthInput();
                controller.update_length(length);
                sortPanel.repaint();
            }
        });

        // 建立“改变延迟”按钮
        JButton delayBtn = new JButton("Change Delay");
        delayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.delay = DelayInput();
            }
        });

        add(startBtn);
        add(stopBtn);
        add(shuffleBtn);
        add(lengthBtn);
        add(delayBtn);
        add(comboBox);

    }

    public int SeqLengthInput() {
        return Integer.parseInt(JOptionPane.showInputDialog("Sequence Length"));
    }

    public int DelayInput() {
        return Integer.parseInt(JOptionPane.showInputDialog("Delay (ms)"));
    }
}
