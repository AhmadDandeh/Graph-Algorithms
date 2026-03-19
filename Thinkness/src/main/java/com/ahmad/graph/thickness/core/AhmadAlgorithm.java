package com.ahmad.graph.thickness.core;


import com.ahmad.graph.thickness.model.CurveEdge;
import com.ahmad.graph.thickness.model.Point;
import com.ahmad.graph.thickness.model.SimpleEdge;

import java.util.ArrayList;
import java.util.Arrays;

public class AhmadAlgorithm {
    double r;
    final int c0 = 30;
    final int c1 = 350;
    public int[][] graphArray;
    public int[][] graphArray1;
    public int[][] graphArray3;
    int n;
    public int[][] T;
    public int[] ww;
    public int[] wwValue;
    public int[] wwNew;
    public Point[] GraphPoints;
    public ArrayList<CurveEdge> GraphCurve = new ArrayList();
    public ArrayList<SimpleEdge> GraphsMore = new ArrayList();
    int lm;
    ArrayList<ArrayList> H = new ArrayList();
    ArrayList<ArrayList> H1 = new ArrayList();
    ArrayList ww1 = new ArrayList();
    ArrayList e1 = new ArrayList();
    ArrayList<Object> newArray = new ArrayList();

    public AhmadAlgorithm(int[][] a) {
        this.n = a.length;
        this.graphArray1 = (int[][])Arrays.copyOf(a, this.n);
        if (this.n > 0) {
            this.r = (double)(1000 / this.n);
        }

        this.graphArray = new int[this.n][this.n];

        for(int i = 0; i < this.n; ++i) {
            System.arraycopy(a[i], 0, this.graphArray[i], 0, this.n);
        }

        this.T = new int[this.n + 1][this.n];
        this.ww = new int[this.n];
        this.wwValue = new int[this.n];
        this.GraphPoints = new Point[this.n];

        for(int i = 0; i < this.n; this.wwValue[i] = i++) {
        }

        this.GraphCurve.clear();
        this.GraphsMore.clear();
        this.H.clear();
        this.H1.clear();
        this.ww1.clear();
        this.e1.clear();
        this.newArray.clear();
        if (this.n > 0) {
            this.detectNodes();
            this.fillGraphPoints();
            if (this.n > 2) {
                this.detectCrve();
            }
        }

    }

    public AhmadAlgorithm(int[][] a, int[] b) {
        this.n = a.length;
        this.graphArray1 = (int[][])Arrays.copyOf(a, this.n);
        this.r = (double)(1000 / this.n);
        this.graphArray = new int[this.n][this.n];

        for(int i = 0; i < this.n; ++i) {
            System.arraycopy(a[i], 0, this.graphArray[i], 0, this.n);
        }

        this.T = new int[this.n + 1][this.n];
        this.ww = new int[this.n];
        this.wwValue = new int[this.n];
        this.GraphPoints = new Point[this.n];

        for(int i = 0; i < this.n; ++i) {
            this.wwValue[i] = b[i];
        }

        this.GraphCurve.clear();
        this.GraphsMore.clear();
        this.H.clear();
        this.H1.clear();
        this.ww1.clear();
        this.e1.clear();
        this.newArray.clear();
        if (this.n > 0) {
            this.detectNodes();
            this.fillGraphPoints();
            if (this.n > 2) {
                this.detectCrve();
            }
        }

    }

    public final void detectNodes() {
        this.lm = 0;
        int[][] C = new int[2][this.n];

        for(int i = 0; i < this.n; ++i) {
            this.T[0][i] = i;
            C[0][i] = i;
            this.T[1][i] = this.detDeg(i, this.graphArray1);
            C[1][i] = this.T[1][i];
        }

        int help1 = this.n;
        boolean tt = true;

        while(tt) {
            int oo = 1;
            C = this.sortNodes(C, help1);
            this.ww[this.lm] = C[0][0];
            this.setPerform(this.ww[this.lm], this.lm);
            this.setPerform2(this.ww[this.lm], this.lm);
            this.makeZero(this.ww[this.lm]);

            for(ArrayList H11 : this.H1) {
                H11.remove((Object) this.ww[this.lm]);
            }

            if (this.lm + 1 == this.n && this.testGraphArray()) {
                tt = false;

                for(int i = 0; i < this.n; ++i) {
                    boolean ff = false;

                    for(int j = 0; j < this.ww.length - 1; ++j) {
                        if (this.ww[j] == i) {
                            j = this.ww.length;
                            ff = true;
                        }
                    }

                    if (!ff) {
                        this.ww[this.n - 1] = i;
                        break;
                    }
                }
            } else {
                ++this.lm;

                for(int i = 0; i < this.n; ++i) {
                    this.T[this.lm + 1][i] = this.detDeg(i, this.graphArray1);
                }

                boolean testWay;
                for(testWay = true; ((ArrayList)this.H1.get(this.lm - oo)).isEmpty(); ++oo) {
                    if (oo == this.lm) {
                        testWay = false;
                        break;
                    }
                }

                if (testWay) {
                    help1 = ((ArrayList)this.H1.get(this.lm - oo)).size();
                    C = new int[2][help1];

                    for(int i = 0; i < help1; ++i) {
                        C[0][i] = (Integer)((ArrayList)this.H1.get(this.lm - oo)).get(i);
                        C[1][i] = this.detDeg((Integer)((ArrayList)this.H1.get(this.lm - oo)).get(i), this.graphArray1);
                    }
                } else {
                    int ii = 0;
                    help1 = this.n - this.lm;
                    C = new int[2][help1];

                    for(int i = 0; i < this.n; ++i) {
                        boolean test = true;

                        for(int j = 0; j < this.lm; ++j) {
                            if (this.ww[j] == i) {
                                test = false;
                            }
                        }

                        if (test) {
                            C[0][ii] = i;
                            C[1][ii] = this.detDeg(i, this.graphArray1);
                            ++ii;
                        }
                    }
                }
            }
        }

    }

    public final int detDeg(int a, int[][] b) {
        int deg = 0;

        for(int k = 0; k < b.length; ++k) {
            if (b[a][k] == 1) {
                ++deg;
            }
        }

        return deg;
    }

    public int[][] sortNodes(int[][] a, int b) {
        for(int i = 0; i < b - 1; ++i) {
            for(int j = i + 1; j < b; ++j) {
                if (a[1][j] < a[1][i]) {
                    int t = a[1][j];
                    a[1][j] = a[1][i];
                    a[1][i] = t;
                    t = a[0][j];
                    a[0][j] = a[0][i];
                    a[0][i] = t;
                }
            }
        }

        return a;
    }

    public void setPerform(int a, int b) {
        ArrayList help = new ArrayList();
        help.clear();

        for(int i = 0; i < this.n; ++i) {
            if (this.graphArray1[a][i] == 1) {
                boolean test = true;

                for(int j = 0; j < this.lm; ++j) {
                    if (this.ww[j] == i) {
                        test = false;
                    }
                }

                if (test) {
                    help.add(i);
                }
            }
        }

        this.H.add(help);
    }

    public void setPerform2(int a, int b) {
        ArrayList help = new ArrayList();
        help.clear();

        for(int i = 0; i < this.n; ++i) {
            if (this.graphArray1[a][i] == 1) {
                boolean test = true;

                for(int j = 0; j < this.lm; ++j) {
                    if (this.ww[j] == i) {
                        test = false;
                    }
                }

                if (test) {
                    help.add(i);
                }
            }
        }

        this.H1.add(help);
    }

    public void makeZero(int a) {
        for(int i = 0; i < this.n; ++i) {
            this.graphArray1[i][a] = 0;
            this.graphArray1[a][i] = 0;
        }

    }

    public boolean testGraphArray() {
        for(int i = 0; i < this.n - 1; ++i) {
            for(int j = i + 1; j < this.n; ++j) {
                if (this.graphArray1[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public final void fillGraphPoints() {
        for(int i = 0; i < this.n; ++i) {
            this.GraphPoints[i] = new Point((double)30.0F + (double)i * this.r, (double)350.0F);
        }

    }

    public final void detectCrve() {
        this.ww1.add(this.ww[this.lm]);
        if (this.graphArray[this.ww[this.lm]][this.ww[this.lm - 1]] == 1) {
            this.e1.add(this.detDeg(this.ww[this.lm], this.graphArray) - 1);
        } else {
            this.e1.add(this.detDeg(this.ww[this.lm], this.graphArray));
        }

        --this.lm;
        this.ww1.add(this.ww[this.lm]);
        this.ww1.add(0, this.ww[this.lm]);
        if (this.graphArray[this.ww[this.lm]][this.ww[this.lm + 1]] == 1) {
            this.e1.add(this.detDeg(this.ww[this.lm], this.graphArray) - 1);
            this.e1.add(0, this.detDeg(this.ww[this.lm], this.graphArray) - 1);
        } else {
            this.e1.add(this.detDeg(this.ww[this.lm], this.graphArray));
            this.e1.add(0, this.detDeg(this.ww[this.lm], this.graphArray));
        }

        this.thirdStep();
        boolean ttt = true;

        while(ttt) {
            if (((ArrayList)this.H.get(this.lm)).size() == 1) {
                if (this.lm == 0) {
                    ttt = false;
                } else {
                    this.thirdStep();
                }
            } else if (((ArrayList)this.H.get(this.lm)).isEmpty()) {
                this.thirdStep();
            } else {
                this.sexStep();
            }
        }

        if (!this.GraphsMore.isEmpty()) {
            this.buildArray();
        }

    }

    public void thirdStep() {
        if (this.graphArray[this.ww[this.lm]][this.ww[this.lm - 1]] == 1) {
            int xx = (Integer)this.e1.get(0) - 1;
            this.e1.set(0, xx);
            this.e1.set(this.e1.size() - 1, xx);
        }

        --this.lm;
        this.ww1.add(this.ww[this.lm]);
        this.ww1.add(0, this.ww[this.lm]);
        if (this.graphArray[this.ww[this.lm]][this.lm + 1] == 1) {
            this.e1.add(this.detDeg(this.ww[this.lm], this.graphArray) - 1);
            this.e1.add(0, this.detDeg(this.ww[this.lm], this.graphArray) - 1);
        } else {
            this.e1.add(this.detDeg(this.ww[this.lm], this.graphArray));
            this.e1.add(0, this.detDeg(this.ww[this.lm], this.graphArray));
        }

    }

    public void sexStep() {
        int right = this.readRight();
        int left = this.readLeft();
        if (right != -1) {
            if ((Integer)this.e1.get(right) < (Integer)this.e1.get(left)) {
                int s1 = this.findSumOfDegree(right, this.ww1.size() - 1);
                int s2 = this.findSumOfDegree(0, right);
                if (s1 < s2) {
                    if (this.testFlexibilityDown(right)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(right), (Integer)this.ww1.get(this.ww1.size() - 1), 4));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(right), (Integer)this.ww1.get(this.ww1.size() - 1), 2));
                    }

                    this.sevenStep(this.ww1.size() - 1, right);
                } else {
                    if (this.testFlexibilityUp(right)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(right), 3));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(right), 1));
                    }

                    this.sevenStep(0, right);
                }
            } else if ((Integer)this.e1.get(right) > (Integer)this.e1.get(left)) {
                int s1 = this.findSumOfDegree(left, this.ww1.size() - 1);
                int s2 = this.findSumOfDegree(0, left);
                if (s1 < s2) {
                    if (this.testFlexibilityDown(left)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(left), (Integer)this.ww1.get(this.ww1.size() - 1), 4));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(left), (Integer)this.ww1.get(this.ww1.size() - 1), 2));
                    }

                    this.sevenStep(this.ww1.size() - 1, left);
                } else {
                    if (this.testFlexibilityUp(left)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(left), 3));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(left), 1));
                    }

                    this.sevenStep(0, left);
                }
            } else {
                int s1 = this.findSumOfDegree(right, this.ww1.size() - 1);
                int s2 = this.findSumOfDegree(0, left);
                if (s1 < s2) {
                    if (this.testFlexibilityDown(right)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(right), (Integer)this.ww1.get(this.ww1.size() - 1), 4));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(right), (Integer)this.ww1.get(this.ww1.size() - 1), 2));
                    }

                    this.sevenStep(this.ww1.size() - 1, right);
                } else {
                    if (this.testFlexibilityUp(left)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(left), 3));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(left), 1));
                    }

                    this.sevenStep(0, left);
                }
            }
        } else {
            for(int j = 0; j < ((ArrayList)this.H.get(this.lm)).size(); ++j) {
                if ((Integer)this.ww1.get(1) != (Integer)((ArrayList)this.H.get(this.lm)).get(j)) {
                    this.GraphsMore.add(new SimpleEdge((Integer)this.ww1.get(0), (Integer)((ArrayList)this.H.get(this.lm)).get(j)));
                    this.eightStep(((ArrayList)this.H.get(this.lm)).get(j));
                }
            }
        }

    }

    public void sevenStep(int a, int b) {
        int xx = (Integer)this.e1.get(0) - 1;
        this.e1.set(0, xx);
        this.e1.set(this.e1.size() - 1, xx);
        xx = (Integer)this.e1.get(b) - 1;
        this.e1.set(b, xx);
        this.eightStep(this.ww1.get(b));
        if (a == 0) {
            for(int i = 1; i < b; ++i) {
                this.e1.set(i, -1);
            }
        } else {
            for(int i = b + 1; i < a; ++i) {
                this.e1.set(i, -1);
            }
        }

    }

    public void eightStep(Object a) {
        ((ArrayList)this.H.get(this.lm)).remove(a);
    }

    public int readRight() {
        for(int i = this.ww1.size() - 2; i > 0; --i) {
            if ((Integer)this.e1.get(i) > 0) {
                for(int j = 0; j < ((ArrayList)this.H.get(this.lm)).size(); ++j) {
                    if ((Integer)this.ww1.get(i) == (Integer)((ArrayList)this.H.get(this.lm)).get(j) && (Integer)this.ww1.get(i) != this.ww[this.lm + 1]) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    public int readLeft() {
        for(int i = 0; i < this.ww1.size() - 2; ++i) {
            if ((Integer)this.e1.get(i) > 0) {
                for(int j = 0; j < ((ArrayList)this.H.get(this.lm)).size(); ++j) {
                    if ((Integer)this.ww1.get(i) == (Integer)((ArrayList)this.H.get(this.lm)).get(j) && (Integer)this.ww1.get(i) != this.ww[this.lm + 1]) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    private int findSumOfDegree(int a, int b) {
        int t = 0;

        for(int i = a + 1; i < b; ++i) {
            if ((Integer)this.e1.get(i) > 0) {
                t += (Integer)this.e1.get(i);
            }
        }

        return t;
    }

    public boolean testFlexibilityUp(int b) {
        int d = (Integer)this.ww1.get(b);

        for(int i = 1; i < b; ++i) {
            if ((Integer)this.ww1.get(i) == d) {
                return true;
            }
        }

        return false;
    }

    public boolean testFlexibilityDown(int b) {
        int d = (Integer)this.ww1.get(b);

        for(int i = b + 1; i < this.ww1.size(); ++i) {
            if ((Integer)this.ww1.get(i) == d) {
                return true;
            }
        }

        return false;
    }

    public int findIndex(int l) {
        for(int i = 0; i < this.ww.length; ++i) {
            if (this.ww[i] == l) {
                return i;
            }
        }

        return -1;
    }

    public final void buildArray() {
        this.CalcNewItemNumber();
        this.wwNew = new int[this.newArray.size()];
        this.graphArray3 = new int[this.newArray.size()][this.newArray.size()];

        for(int i = 0; i < this.wwNew.length; ++i) {
            this.wwNew[i] = this.wwValue[(Integer)this.newArray.get(i)];
        }

        for(int i = 0; i < this.newArray.size(); ++i) {
            for(int j = 0; j < this.newArray.size(); ++j) {
                this.graphArray3[i][j] = 0;
            }
        }

        for(int i = 0; i < this.newArray.size(); ++i) {
            for(int j = 0; j < this.newArray.size(); ++j) {
                for(int k = 0; k < this.GraphsMore.size(); ++k) {
                    if (((SimpleEdge)this.GraphsMore.get(k)).x == (Integer)this.newArray.get(i) && ((SimpleEdge)this.GraphsMore.get(k)).y == (Integer)this.newArray.get(j)) {
                        this.graphArray3[i][j] = 1;
                        this.graphArray3[j][i] = 1;
                    }
                }
            }
        }

    }

    private void CalcNewItemNumber() {
        for(SimpleEdge GraphsMore1 : this.GraphsMore) {
            if (!this.newArray.contains(GraphsMore1.x)) {
                this.newArray.add(GraphsMore1.x);
            }

            if (!this.newArray.contains(GraphsMore1.y)) {
                this.newArray.add(GraphsMore1.y);
            }
        }

    }

    public void sexStep1(int a) {
        int aa = (Integer)((ArrayList)this.H.get(this.lm)).get(a);
        int right = this.readRight1(aa);
        int left = this.readLeft1(aa);
        if (right != -1) {
            if ((Integer)this.e1.get(right) > (Integer)this.e1.get(left)) {
                int s1 = this.findSumOfDegree(right, this.ww1.size() - 1);
                int s2 = this.findSumOfDegree(0, right);
                if (s1 < s2) {
                    if (this.testFlexibilityDown(right)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(right), (Integer)this.ww1.get(this.ww1.size() - 1), 4));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(right), (Integer)this.ww1.get(this.ww1.size() - 1), 2));
                    }

                    this.sevenStep(this.ww1.size() - 1, right);
                } else {
                    if (this.testFlexibilityUp(right)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(right), 3));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(right), 1));
                    }

                    this.sevenStep(0, right);
                }
            } else {
                int s1 = this.findSumOfDegree(left, this.ww1.size() - 1);
                int s2 = this.findSumOfDegree(0, left);
                if (s1 < s2) {
                    if (this.testFlexibilityDown(left)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(left), (Integer)this.ww1.get(this.ww1.size() - 1), 4));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(left), (Integer)this.ww1.get(this.ww1.size() - 1), 2));
                    }

                    this.sevenStep(this.ww1.size() - 1, right);
                } else {
                    if (this.testFlexibilityUp(left)) {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(left), 3));
                    } else {
                        this.GraphCurve.add(new CurveEdge((Integer)this.ww1.get(0), (Integer)this.ww1.get(left), 1));
                    }

                    this.sevenStep(0, left);
                }
            }
        } else {
            for(int j = 0; j < ((ArrayList)this.H.get(this.lm)).size(); ++j) {
                if ((Integer)this.ww1.get(1) != (Integer)((ArrayList)this.H.get(this.lm)).get(j)) {
                    this.GraphsMore.add(new SimpleEdge((Integer)this.ww1.get(0), (Integer)((ArrayList)this.H.get(this.lm)).get(j)));
                    this.eightStep(((ArrayList)this.H.get(this.lm)).get(j));
                }
            }
        }

    }

    public int readRight1(int a) {
        for(int i = this.ww1.size() - 2; i > 0; --i) {
            if ((Integer)this.ww1.get(i) == a && (Integer)this.e1.get(i) > 0) {
                return i;
            }
        }

        return -1;
    }

    public int readLeft1(int a) {
        for(int i = 0; i < this.ww1.size() - 2; ++i) {
            if ((Integer)this.ww1.get(i) == a && (Integer)this.e1.get(i) > 0) {
                return i;
            }
        }

        return -1;
    }

    public boolean testConn(int x, int y) {
        boolean t = true;
        if (x == 0) {
            for(int i = x + 1; i < y; ++i) {
                if ((Integer)this.e1.get(i) > 0) {
                    for(int j = y + 1; j < this.ww1.size() - 1; ++j) {
                        t = false;
                        if ((Integer)this.ww1.get(i) == (Integer)this.ww1.get(j) && (Integer)this.e1.get(i) == (Integer)this.e1.get(j)) {
                            j = this.ww1.size();
                            t = true;
                        }
                    }

                    if (!t) {
                        return t;
                    }
                }
            }

            return true;
        } else {
            for(int i = y + 1; i < x; ++i) {
                if ((Integer)this.e1.get(i) > 0) {
                    for(int j = 1; j < y; ++j) {
                        t = false;
                        if ((Integer)this.ww1.get(i) == (Integer)this.ww1.get(j) && (Integer)this.e1.get(i) == (Integer)this.e1.get(j)) {
                            j = this.ww1.size();
                            t = true;
                        }
                    }

                    if (!t) {
                        return t;
                    }
                }
            }

            return true;
        }
    }

    public void sortH(int v) {
        for(int k = 0; k < ((ArrayList)this.H.get(v)).size() - 1; ++k) {
            for(int kk = k + 1; kk < ((ArrayList)this.H.get(v)).size(); ++kk) {
                int rr1 = this.findIndex((Integer)((ArrayList)this.H.get(v)).get(k));
                int rr2 = this.findIndex((Integer)((ArrayList)this.H.get(v)).get(kk));
                if (rr2 < rr1) {
                    int ll1 = (Integer)((ArrayList)this.H.get(v)).get(k);
                    int ll2 = (Integer)((ArrayList)this.H.get(v)).get(kk);
                    ((ArrayList)this.H.get(v)).remove(kk);
                    ((ArrayList)this.H.get(v)).remove(k);
                    ((ArrayList)this.H.get(v)).add(k, ll2);
                    ((ArrayList)this.H.get(v)).add(kk, ll1);
                }
            }
        }

    }
}
