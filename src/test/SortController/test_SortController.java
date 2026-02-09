package SortController;

import controller.SortController;
import model.SortModel;

public class test_SortController {
    public static void main(String[] args) {
        SortController controller = new SortController(3, 1000, 0, false);
        controller.loop(false);
    }
}
