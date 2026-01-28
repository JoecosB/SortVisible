import ui.*;
import controller.SortController;

public class Main {
    public static void main(String[] args) {
        SortController controller = new SortController(2, 500, 0, false);
        controller.shuffle_seq();
        MainFrame mainFrame = new MainFrame(controller);
        mainFrame.show_visualizer();
    }
}
