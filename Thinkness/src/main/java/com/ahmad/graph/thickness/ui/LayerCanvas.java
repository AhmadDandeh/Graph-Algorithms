package com.ahmad.graph.thickness.ui;


import com.ahmad.graph.thickness.model.CurveEdge;
import com.ahmad.graph.thickness.model.Point;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

public class LayerCanvas extends Canvas {
    double upLoop;
    double r;
    double r1;
    float r2 = 0.0F;
    float r3 = 0.0F;
    static int me = 0;
    public ArrayList<CurveEdge> Crves;
    public int[][] ArrayConn;
    int n;
    int l1 = 0;
    public int[] ArrayValue;
    public Point[] ArrayPoints;
    public int[] ArrayWW;

    public LayerCanvas(ArrayList<CurveEdge> a, int[][] b, int[] c, Point[] d, int[] e) {
        this.setBackground(Color.white);
        this.setSize(1500, 750);
        this.Crves = a;
        ++me;
        this.n = b.length;
        this.r = (double)(1000 / this.n);
        this.ArrayConn = new int[this.n][this.n];

        for(int i = 0; i < this.n; ++i) {
            for(int j = 0; j < this.n; ++j) {
                this.ArrayConn[i][j] = b[i][j];
            }
        }

        this.ArrayValue = new int[this.n];

        for(int i = 0; i < this.n; ++i) {
            this.ArrayValue[i] = c[i];
        }

        this.ArrayPoints = new Point[this.n];

        for(int i = 0; i < this.n; ++i) {
            this.ArrayPoints[i] = d[i];
        }

        this.ArrayWW = new int[this.n];

        for(int i = 0; i < this.n; ++i) {
            this.ArrayWW[i] = e[i];
        }

        if (this.n < 8) {
            this.r1 = this.r / (double)4.0F;
        } else if (this.n < 16) {
            this.r1 = this.r / (double)2.0F;
        } else {
            this.r1 = this.r;
        }

        if (this.n > 50) {
            this.r2 = 300.0F;
            this.r3 = 15.0F;
        }

        int tt = this.calcUpRoundCurve();
        if (tt >= 160) {
            this.l1 = 19;
        } else if (tt >= 100) {
            this.l1 = 18;
        } else if (tt >= 80) {
            this.l1 = 17;
        } else if (tt >= 60) {
            this.l1 = 16;
        } else if (tt >= 45) {
            this.l1 = 15;
        } else if (tt >= 40) {
            this.l1 = 14;
        } else if (tt >= 35) {
            this.l1 = 13;
        } else if (tt >= 30) {
            this.l1 = 12;
        } else if (tt >= 25) {
            this.l1 = 11;
        } else if (tt >= 18) {
            this.l1 = 10;
        }

    }

    public final int calcUpRoundCurve() {
        int t = 0;

        for(int i = 0; i < this.Crves.size(); ++i) {
            if (((CurveEdge)this.Crves.get(i)).place == 3) {
                ++t;
            } else if (((CurveEdge)this.Crves.get(i)).place == 4) {
                ++t;
            }
        }

        return t;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D)graphics;
        this.upLoop = (double)30.0F + this.r * (double)this.n;

        for(int i = 1; i < this.n; ++i) {
            if (this.ArrayConn[this.ArrayWW[i - 1]][this.ArrayWW[i]] == 1) {
                Line2D.Double l = new Line2D.Double(this.ArrayPoints[i - 1].x, this.ArrayPoints[i - 1].y, this.ArrayPoints[i].x, this.ArrayPoints[i].y);
                g2.draw(l);
            }
        }

        for(int i = 0; i < this.Crves.size(); ++i) {
            double q1 = this.ArrayPoints[this.findIndex(((CurveEdge)this.Crves.get(i)).x)].x;
            double q3 = this.ArrayPoints[this.findIndex(((CurveEdge)this.Crves.get(i)).y)].x;
            if (((CurveEdge)this.Crves.get(i)).place == 1) {
                QuadCurve2D.Double c = new QuadCurve2D.Double(q1, (double)350.0F, (q1 + q3) / (double)2.0F, (double)350.0F - 0.4 * Math.abs(q3 - q1), q3, (double)350.0F);
                g2.draw(c);
            } else if (((CurveEdge)this.Crves.get(i)).place == 2) {
                QuadCurve2D.Double c = new QuadCurve2D.Double(q1, (double)350.0F, (q1 + q3) / (double)2.0F, (double)350.0F + 0.4 * Math.abs(q3 - q1), q3, (double)350.0F);
                g2.draw(c);
            } else if (((CurveEdge)this.Crves.get(i)).place == 3) {
                QuadCurve2D.Double c = new QuadCurve2D.Double(q1, (double)350.0F, (q1 + this.upLoop) / (double)2.0F, (double)350.0F - 0.4 * Math.abs(this.upLoop - q1), this.upLoop, (double)350.0F);
                QuadCurve2D.Double c1 = new QuadCurve2D.Double(q3, (double)350.0F, (q3 + this.upLoop) / (double)2.0F, (double)350.0F + 0.4 * Math.abs(this.upLoop - q3), this.upLoop, (double)350.0F);
                this.upLoop = this.upLoop + (double)20.0F - (double)this.l1;
                g2.draw(c);
                g2.draw(c1);
            } else if (((CurveEdge)this.Crves.get(i)).place == 4) {
                QuadCurve2D.Double c = new QuadCurve2D.Double(q3, (double)350.0F, (q3 + this.upLoop) / (double)2.0F, (double)350.0F + 0.4 * Math.abs(this.upLoop - q3), this.upLoop, (double)350.0F);
                QuadCurve2D.Double c1 = new QuadCurve2D.Double(q1, (double)350.0F, (q1 + this.upLoop) / (double)2.0F, (double)350.0F - 0.4 * Math.abs(this.upLoop - q1), this.upLoop, (double)350.0F);
                this.upLoop = this.upLoop + (double)20.0F - (double)this.l1;
                g2.draw(c);
                g2.draw(c1);
            }
        }

        for(int i = 0; i < this.n; ++i) {
            g2.setColor(Color.cyan);
            Ellipse2D s = new Ellipse2D.Double(this.ArrayPoints[i].x - this.r1 / (double)4.0F, this.ArrayPoints[i].y - this.r1 / (double)4.0F, this.r1 / (double)2.0F, this.r1 / (double)2.0F);
            g2.draw(s);
            g2.fill(s);
            g2.setColor(Color.black);
            if (i % 2 == 0) {
                g2.drawString(this.ArrayValue[this.ArrayWW[i]] + 1 + "", (float)this.ArrayPoints[i].x - 5.0F, (float)this.ArrayPoints[i].y + 5.0F + this.r2);
            } else {
                g2.drawString(this.ArrayValue[this.ArrayWW[i]] + 1 + "", (float)this.ArrayPoints[i].x - 5.0F, (float)this.ArrayPoints[i].y + 5.0F + this.r2 + this.r3);
            }
        }

    }

    public int findIndex(int l) {
        for(int i = 0; i < this.ArrayWW.length; ++i) {
            if (this.ArrayWW[i] == l) {
                return i;
            }
        }

        return -1;
    }

    public void dos(ArrayList<CurveEdge> a, int[][] b, int[] c, Point[] d, int[] e) {
        this.Crves = a;
        ++me;
        this.n = b.length;
        this.r = (double)(1000 / this.n);
        this.ArrayConn = new int[this.n][this.n];

        for(int i = 0; i < this.n; ++i) {
            for(int j = 0; j < this.n; ++j) {
                this.ArrayConn[i][j] = b[i][j];
            }
        }

        this.ArrayValue = new int[this.n];

        for(int i = 0; i < this.n; ++i) {
            this.ArrayValue[i] = c[i];
        }

        this.ArrayPoints = new Point[this.n];

        for(int i = 0; i < this.n; ++i) {
            this.ArrayPoints[i] = d[i];
        }

        this.ArrayWW = new int[this.n];

        for(int i = 0; i < this.n; ++i) {
            this.ArrayWW[i] = e[i];
        }

        if (this.n < 8) {
            this.r1 = this.r / (double)4.0F;
        } else if (this.n < 16) {
            this.r1 = this.r / (double)2.0F;
        } else {
            this.r1 = this.r;
        }

        if (this.n > 50) {
            this.r2 = 300.0F;
            this.r3 = 15.0F;
        }

        int tt = this.calcUpRoundCurve();
        if (tt >= 160) {
            this.l1 = 19;
        } else if (tt >= 100) {
            this.l1 = 18;
        } else if (tt >= 80) {
            this.l1 = 17;
        } else if (tt >= 60) {
            this.l1 = 16;
        } else if (tt >= 45) {
            this.l1 = 15;
        } else if (tt >= 40) {
            this.l1 = 14;
        } else if (tt >= 35) {
            this.l1 = 13;
        } else if (tt >= 30) {
            this.l1 = 12;
        } else if (tt >= 25) {
            this.l1 = 11;
        } else if (tt >= 18) {
            this.l1 = 10;
        }

        this.repaint();
    }
}
