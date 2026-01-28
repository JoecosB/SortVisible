package ui;

import ui.*;
import model.SortModel;
import controller.SortController;

public class test_MainFrame {
    public static void main(String[] args) {
        SortController controller = new SortController(2, 500, 0, false);
        controller.shuffle_seq();
        MainFrame mainFrame = new MainFrame(controller);
        mainFrame.show_visualizer();
    }
}
