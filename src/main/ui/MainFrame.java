package ui;

import javax.swing.*;
import java.awt.*;
import ui.SortPanel;
import ui.ControlPanel;
import model.SortModel;
import controller.SortController;

public class MainFrame extends JFrame {
    SortController controller;
    SortModel model;

    public MainFrame(SortController controller) {
        this.controller = controller;
        this.model = controller.model;

        // 设置标题、大小、默认关闭动作
        setTitle("Sorting Visualizer");
        setSize(1920, 1080);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // 设置容器布局
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // 建立绘制面板、控制面板
        SortPanel sortPanel = new SortPanel(this.model);
        ControlPanel controlPanel = new ControlPanel(this.controller, sortPanel);
        sortPanel.setBorder(BorderFactory.createTitledBorder("Sequence Visualizer"));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));

        // 在容器中添加面板组件
        container.add(sortPanel, BorderLayout.CENTER);
        container.add(controlPanel, BorderLayout.SOUTH);
    }

    public void show_visualizer() {
        setVisible(true);
    }
}
