package algorithm;

import model.SortModel;
import algorithm.SortAlgorithm;

public class BubbleSort implements SortAlgorithm {
    public void step_model(SortModel model) {
        // 比较当前两项
        int a=model.seq[model.p1], b=model.seq[model.p2];
        if(model.do_reverse ? (a < b):(a > b)) {
            model.seq[model.p1] = b;
            model.seq[model.p2] = a;
        }

        // 移动数组角标至 下一项/开头
        if(model.p2 < model.right) {
            model.p1++;
            model.p2++;
        } else {
            model.p1 = 0;
            model.p2 = 1;
            model.right--;
        }
    }

    public void reset(SortModel model) {
        model.p1 = 0;
        model.p2 = 1;
        model.left =0;
        model.right = model.seq_length-1;
    }
}
