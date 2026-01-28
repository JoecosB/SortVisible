package algorithm;

import algorithm.SortAlgorithm;
import model.SortModel;

public class SelectionSort implements SortAlgorithm {
    public void step_model(SortModel model) {
        // 从未排序区间中选取 p1，与其后的 p2 比较
        int a=model.seq[model.p1], b=model.seq[model.p2];
        if(model.do_reverse ? (a>b):(a<b)) {
            model.p1 = model.p2;
        } else {
            model.p2++;
        }

        // 当 p2 达到右边界，交换第 p1 项与右边界，p1、p2初始化，右边界左移
        if(model.p2 > model.right) {
            int temp = model.seq[model.right];
            model.seq[model.right] = model.seq[model.p1];
            model.seq[model.p1] = temp;

            model.p1=0;
            model.p2=1;
            model.right--;
        }
    }

    public void reset(SortModel model) {
        model.p1=0;
        model.p2=1;
        model.left=0;
        model.right=model.seq_length-1;
    }
}
