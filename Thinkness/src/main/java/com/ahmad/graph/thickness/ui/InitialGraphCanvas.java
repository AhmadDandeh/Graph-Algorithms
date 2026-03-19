package com.ahmad.graph.thickness.ui;


import com.ahmad.graph.thickness.model.Point;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class InitialGraphCanvas extends Canvas {
    public int[][] graphArray1;
    public int n;
    public int type;
    final int c0 = 650;
    final int c1 = 400;
    final int r0 = 300;
    Point[] GraphPoints;
    int r2;

    public InitialGraphCanvas(int[][] a, int n, int type) {
        this.setBackground(Color.white);
        this.setSize(1500, 750);
        this.n = n;
        this.GraphPoints = new Point[n];
        this.type = type;
        this.graphArray1 = (int[][])Arrays.copyOf(a, n);
        if (type == 0) {
            if (n >= 80) {
                this.r2 = 18;
            } else if (n >= 55) {
                this.r2 = 20;
            } else if (n >= 40) {
                this.r2 = 30;
            } else {
                this.r2 = 40;
            }

            this.fillGraphPoints();
        } else {
            if (n >= 80) {
                this.r2 = 20;
            } else if (n >= 60) {
                this.r2 = 30;
            } else {
                this.r2 = 40;
            }

            this.fillGraphPoints1();
        }

    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);

        for(int i = 0; i < this.n; ++i) {
            for(int j = i + 1; j < this.n; ++j) {
                if (this.graphArray1[i][j] == 1) {
                    graphics.drawLine((int)this.GraphPoints[i].x, (int)this.GraphPoints[i].y, (int)this.GraphPoints[j].x, (int)this.GraphPoints[j].y);
                }
            }
        }

        for(int i = 0; i < this.n; ++i) {
            graphics.setColor(Color.CYAN);
            graphics.fillOval((int)this.GraphPoints[i].x - this.r2 / 2, (int)this.GraphPoints[i].y - this.r2 / 2, this.r2, this.r2);
            graphics.setColor(Color.black);
            graphics.drawString(i + 1 + "", (int)this.GraphPoints[i].x - 7, (int)this.GraphPoints[i].y + 4);
        }

    }

    public final void fillGraphPoints() {
        for(int i = 0; i < this.n; ++i) {
            double theta = (double)(360 * i / this.n);
            double radian = Math.toRadians(theta);
            this.GraphPoints[i] = new Point((double)650.0F + (double)300.0F * Math.cos(radian), (double)400.0F + (double)300.0F * Math.sin(radian));
        }

    }

    public final void fillGraphPoints1() {
        double z = (double)(2500 / this.n);

        for(int i = 0; i < this.n / 2; ++i) {
            this.GraphPoints[i] = new Point((double)100.0F + z * (double)i, (double)200.0F);
            this.GraphPoints[i + this.n / 2] = new Point((double)100.0F + z * (double)i, (double)600.0F);
        }

    }
}
