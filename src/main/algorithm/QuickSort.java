package algorithm;

import algorithm.SortAlgorithm;
import model.SortModel;

import java.util.ArrayList;
import java.util.List;

public class QuickSort implements SortAlgorithm {
    public void step_model(SortModel model) {
        int a=model.seq[model.p1], b=model.seq[model.p2];
        int p = model.seq[model.bounds.get(0)[0]];

        if(a < p) {model.p1++;}
        if(b > p) {model.p2--;}
        if(a>p && b<p) {
            model.seq[model.p1] = b;
            model.seq[model.p2] = a;
            model.p1++;
            model.p2--;
        }

        if(model.p1 >= model.p2) {
            int middle_b = (model.p1 + model.p2)/2;
            int left_b = model.bounds.get(0)[0];
            int right_b = model.bounds.get(0)[1];
            model.bounds.remove(0);

            if(right_b-left_b>=2) {
                if (middle_b - left_b >= 1) {
                    model.bounds.add(new int[]{left_b, middle_b});
                }
                if (right_b - middle_b >= 1) {
                    model.bounds.add(new int[]{middle_b, right_b});
                }
            }

            if(!model.bounds.isEmpty()) {
                model.p1 = model.bounds.get(0)[0] + 1;
                model.p2 = model.bounds.get(0)[1];
            }

            while(model.seq[left_b] > model.seq[left_b +1]) {
                int temp = model.seq[left_b];
                model.seq[left_b] = model.seq[left_b +1];
                model.seq[left_b +1] = temp;
            }
        }
    }

    public void reset(SortModel model) {
        model.p1=1;
        model.p2=model.seq_length-1;

        model.left=0;
        model.right=model.seq_length-1;

        model.bounds = new ArrayList<>();
        model.bounds.add(new int[] {model.left, model.right});
    }
}
