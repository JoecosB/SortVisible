package ui;

import model.SortModel;

import javax.swing.*;
import java.awt.*;

public class SortPanel extends JPanel {
    SortModel model;

    public SortPanel(SortModel model) {
        this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int block_width = getWidth()/this.model.seq_length;
        int block_height = getHeight()/(this.model.seq_length+1);
        int x_start = (getWidth() - this.model.seq_length*block_width)/2;

        // 绘制一般项
        for(int i=0; i<model.seq_length; i++) {
            if(i%2 == 0) {
                g.setColor(Color.DARK_GRAY);
            } else {
                g.setColor(Color.GRAY);
            }
            g.fillRect(i*block_width + x_start, getHeight()-model.seq[i]*block_height, block_width, model.seq[i]*block_height);
        }

        // 绘制比较项
        int p1=model.p1, p2=model.p2;
        g.setColor(Color.CYAN);
        g.fillRect(p1*block_width + x_start, getHeight()-model.seq[p1]*block_height, block_width, model.seq[p1]*block_height);
        g.setColor(Color.MAGENTA);
        g.fillRect(p2*block_width + x_start, getHeight()-model.seq[p2]*block_height, block_width, model.seq[p2]*block_height);

        // 绘制线框
        for(int i=0; i<model.seq_length; i++) {
            g.setColor(Color.BLACK);
            g.drawRect(i*block_width + x_start, getHeight()-model.seq[i]*block_height, block_width, model.seq[i]*block_height);
        }
    }
}
