package algorithm;

import algorithm.SortAlgorithm;
import model.SortModel;

import java.util.ArrayList;

public class MergeSort implements SortAlgorithm {
    public void step_model(SortModel model) {
        int a=model.seq[model.p1], b=model.seq[model.p2], c=model.p2-model.p1;
        if(model.do_reverse ? (a<b):(a>b)) {
            for(int i=model.p2; i>model.p1; i--) {
                int temp = model.seq[i];
                model.seq[i] = model.seq[i-1];
                model.seq[i-1] = temp;
            }
            model.p2++;
        }

        model.p1++;

        int l1=model.bounds.get(0).length, l2=model.bounds.get(1).length;
        if(model.p1 >= model.p2 || model.p2 > model.bounds.get(1)[l2-1]) {
            model.p2 = model.bounds.get(1)[l2-1];
            int[] temp = new int[l1+l2];
            System.arraycopy(model.bounds.get(0), 0, temp, 0, l1);
            System.arraycopy(model.bounds.get(1), 0, temp, l1, l2);
            model.bounds.add(temp);

            model.bounds.remove(0);
            model.bounds.remove(0);

            if(model.bounds.size() > 1) {
                if (model.bounds.get(0).length < model.bounds.get(1).length / 2 + 1) {
                    model.bounds.add(model.bounds.get(0));
                    model.bounds.remove(0);
                }


                model.p1 = model.bounds.get(0)[0];
                model.p2 = model.bounds.get(1)[0];
            }
        }


    }

    public void reset(SortModel model) {
        model.p1=0;
        model.p2=1;

        model.left=0;
        model.right=model.seq_length-1;

        model.bounds = new ArrayList<>();
        for(int i=0; i<model.seq_length; i++) {
            int[] temp = new int[] {i};
            model.bounds.add(temp);
        }
    }
}
