package com.ahmad.graph.thickness.ui;

import com.ahmad.graph.thickness.core.AhmadAlgorithm;
import com.ahmad.graph.thickness.core.GhaithAlgorithm;
import com.ahmad.graph.thickness.model.CurveEdge;
import com.ahmad.graph.thickness.model.Point;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ProcessControlPanel extends JPanel {
    public int[][] graphArray1;
    public int[][] graphArray;
    public int n;
    public ArrayList<ArrayList<CurveEdge>> GraphCurveColl = new ArrayList();
    public ArrayList<int[][]> GraphsArrayConn = new ArrayList();
    public ArrayList<int[]> GraphsArrayValue = new ArrayList();
    public ArrayList<Point[]> GraphsArrayPoint = new ArrayList();
    public ArrayList<int[]> GraphsArrayWW = new ArrayList();
    private final DisplayGraphFrame ff;
    public ArrayList<ResultViewFrame> thirdF = new ArrayList();
    private JButton jButton1;
    private JButton jButton2;

    public ProcessControlPanel(int[][] a, int n, DisplayGraphFrame ff) {
        this.thirdF.clear();
        this.initComponents();
        this.jButton1.setLocation(700, 25);
        this.setSize(1500, 50);
        this.n = n;
        this.graphArray1 = (int[][])Arrays.copyOf(a, n);
        this.ff = ff;
    }

    private void initComponents() {
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.setBackground(new Color(255, 255, 255));
        this.setMaximumSize(new Dimension(1500, 100));
        this.jButton1.setText("Plan the graph with Dr.Ghaith Blal algorithim");
        this.jButton1.addActionListener(this::jButton1ActionPerformed);
        this.jButton2.setText("Plan the graph with our alghorithim");
        this.jButton2.setPreferredSize(new Dimension(243, 23));
        this.jButton2.addActionListener(this::jButton2ActionPerformed);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(200, 200, 200).addComponent(this.jButton1, -2, 340, -2).addPreferredGap(ComponentPlacement.RELATED, 295, 32767).addComponent(this.jButton2, -2, 340, -2).addGap(285, 285, 285)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.jButton2, -1, -1, 32767).addComponent(this.jButton1, -1, 30, 32767)).addContainerGap(-1, 32767)));
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        this.GraphCurveColl.clear();
        this.GraphsArrayConn.clear();
        this.GraphsArrayPoint.clear();
        this.GraphsArrayValue.clear();
        this.GraphsArrayWW.clear();
        if (this.n > 0) {
            GhaithAlgorithm p = new GhaithAlgorithm(this.graphArray1);
            this.GraphCurveColl.add(p.GraphCurve);
            this.GraphsArrayConn.add(p.graphArray);
            this.GraphsArrayValue.add(p.wwValue);
            this.GraphsArrayPoint.add(p.GraphPoints);
            this.GraphsArrayWW.add(p.ww);

            while(!p.GraphsMore.isEmpty()) {
                p = new GhaithAlgorithm(p.graphArray3, p.wwNew);
                this.GraphCurveColl.add(p.GraphCurve);
                this.GraphsArrayConn.add(p.graphArray);
                this.GraphsArrayValue.add(p.wwValue);
                this.GraphsArrayPoint.add(p.GraphPoints);
                this.GraphsArrayWW.add(p.ww);
            }

            this.ff.dispose();
            ResultViewFrame f = new ResultViewFrame(this.GraphCurveColl, this.GraphsArrayConn, this.GraphsArrayValue, this.GraphsArrayPoint, this.GraphsArrayWW, true);
            f.setVisible(true);
        }

    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        this.GraphCurveColl.clear();
        this.GraphsArrayConn.clear();
        this.GraphsArrayPoint.clear();
        this.GraphsArrayValue.clear();
        this.GraphsArrayWW.clear();
        if (this.n > 0) {
            AhmadAlgorithm p = new AhmadAlgorithm(this.graphArray1);
            this.GraphCurveColl.add(p.GraphCurve);
            this.GraphsArrayConn.add(p.graphArray);
            this.GraphsArrayValue.add(p.wwValue);
            this.GraphsArrayPoint.add(p.GraphPoints);
            this.GraphsArrayWW.add(p.ww);

            while(!p.GraphsMore.isEmpty()) {
                p = new AhmadAlgorithm(p.graphArray3, p.wwNew);
                this.GraphCurveColl.add(p.GraphCurve);
                this.GraphsArrayConn.add(p.graphArray);
                this.GraphsArrayValue.add(p.wwValue);
                this.GraphsArrayPoint.add(p.GraphPoints);
                this.GraphsArrayWW.add(p.ww);
            }

            this.ff.dispose();
            ResultViewFrame f = new ResultViewFrame(this.GraphCurveColl, this.GraphsArrayConn, this.GraphsArrayValue, this.GraphsArrayPoint, this.GraphsArrayWW, true);
            f.setVisible(true);
        }

    }
}

