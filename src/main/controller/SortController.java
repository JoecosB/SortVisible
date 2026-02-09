package controller;

import java.util.Arrays;
import algorithm.*;

import model.SortModel;
import beep.Beeper;

public class SortController {
    SortAlgorithm sorter;
    public SortModel model;
    public int delay;
    private final Beeper beeperA, beeperB;

    public SortController(int sort_type, int seq_length, int delay, boolean do_reverse) {
        switch (sort_type) {
            case 1:
                this.sorter = new QuickSort();
                break;
            case 2:
                this.sorter = new SelectionSort();
                break;
            case 3:
                this.sorter = new MergeSort();
                break;
            default:
                this.sorter = new BubbleSort();
        }
        this.model = new SortModel(seq_length, do_reverse);
        this.sorter.reset(model);
        this.model.shuffle();
        this.delay = delay;
        this.beeperA = new Beeper();
        this.beeperB = new Beeper();
    }

    public void loop(boolean debug) {
        while(!this.model.is_sorted()) {
            sorter.step_model(this.model);
            try {
                Thread.sleep(this.delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(debug){ System.out.println(Arrays.toString(this.model.seq)); }
        }
    }

    public void step() {
        int freqA = this.model.p1*1800/this.model.seq_length + 200 ;
        int freqB = this.model.p2*1800/this.model.seq_length + 200 ;
        beeperA.beep(freqA);
        beeperB.beep(freqB);
        sorter.step_model(this.model);
    }

    public void shuffle_seq() {
        this.model.shuffle();
    }

    public void update_length(int length) {
        model.make_data(length);
        sorter.reset(model);
        model.shuffle();
    }

    public void update_sorter(String algorithm) {
        switch (algorithm) {
            case "BubbleSort":
                sorter = new BubbleSort();
                break;
            case "QuickSort":
                sorter = new QuickSort();
                break;
            case "SelectionSort":
                sorter = new SelectionSort();
                break;
            case "MergeSort":
                sorter = new MergeSort();
            default:
                break;
        }
        sorter.reset(model);
    }

    public void beep_cleanup() {
        beeperA.beep(0);
        beeperB.beep(0);
    }
}
