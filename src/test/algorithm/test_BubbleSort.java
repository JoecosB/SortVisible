package algorithm;

import algorithm.BubbleSort;
import model.SortModel;
import java.util.Arrays;

public class test_BubbleSort {
        public static void main(String[] args) {
            SortModel model = new SortModel(30, false);
            BubbleSort bubbleSort = new BubbleSort();

            while(!model.is_sorted()) {
                bubbleSort.step_model(model);
                System.out.println(Arrays.toString(model.seq));
        }
    }
}

