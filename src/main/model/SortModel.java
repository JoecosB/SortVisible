package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortModel {
    public int p1=0, p2=1, left=0, right, seq_length;
    public boolean do_reverse;
    public int[] seq;
    public List<int[]> bounds;

    public SortModel(int seq_length, boolean do_reverse) {
        this.do_reverse = do_reverse;
        make_data(seq_length);
    }

    public boolean is_sorted() {
        for(int i=0; i<this.seq_length-1; i++) {
            if(this.do_reverse ? (this.seq[i] < this.seq[i+1]):(this.seq[i] > this.seq[i+1])) {
                return false;
            }
        }
        return true;
    }

    public void shuffle() {
        int temp, j;
        Random random = new Random();
        for (int i = 0; i < seq_length; i++) {
            j = random.nextInt(i + 1);
            temp = this.seq[i];
            this.seq[i] = this.seq[j];
            this.seq[j] = temp;
        }
    }

    public void show_seq() {
        System.out.println(Arrays.toString(this.seq));
    }

    public void make_data(int length) {
        this.seq_length = length;
        this.right = seq_length-1;
        this.left = 0;
        this.p1=0;
        this.p2=1;
        this.bounds = new ArrayList<>();
        this.bounds.add(new int[] {this.left, this.right});
        this.seq = new int[length];
        for(int i=0; i<length; i++) {
            this.seq[i] = i+1;
        }
    }

    public void reset_ptr() {
        this.right = this.seq_length-1;
        this.left = 0;
        this.p1=0;
        this.p2=1;
    }
}
