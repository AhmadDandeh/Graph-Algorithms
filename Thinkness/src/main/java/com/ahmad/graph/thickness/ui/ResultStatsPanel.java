package com.ahmad.graph.thickness.ui;


import com.ahmad.graph.thickness.model.CurveEdge;
import com.ahmad.graph.thickness.model.Point;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ResultStatsPanel extends JPanel {
    private ProcessControlPanel tp1;
    private ResultViewFrame tf;
    private LayerCanvas sc;
    ArrayList<ArrayList<CurveEdge>> aPC;
    ArrayList<int[][]> bPC;
    ArrayList<int[]> cPC;
    ArrayList<Point[]> dPC;
    ArrayList<int[]> ePC;
    private JButton jButton1;
    private JComboBox jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;

    public ResultStatsPanel(ArrayList<ArrayList<CurveEdge>> a, ArrayList<int[][]> b, ArrayList<int[]> c, ArrayList<Point[]> d, ArrayList<int[]> e, ResultViewFrame tf, LayerCanvas sc) {
        this.initComponents();
        this.setSize(1500, 86);
        this.setBackground(Color.cyan);
        this.sc = sc;
        this.aPC = new ArrayList(a);
        this.bPC = new ArrayList(b);
        this.cPC = new ArrayList(c);
        this.dPC = new ArrayList(d);
        this.ePC = new ArrayList(e);
        this.jLabel1.setForeground(Color.black);
        this.tf = tf;
        this.jComboBox1.setBackground(Color.white);
        this.jComboBox1.removeAllItems();

        for(int i = 0; i < a.size(); ++i) {
            this.jComboBox1.addItem("Layer " + (i + 1));
        }

        Font f = new Font("Algerian", 0, 16);
        this.jLabel1.setFont(f);
        this.jLabel2.setFont(f);
        this.jLabel3.setFont(f);
        this.jLabel4.setFont(f);
        this.jLabel5.setFont(f);
        this.jLabel6.setFont(f);
        this.jLabel7.setFont(f);
        this.jButton1.setFont(f);
        this.jButton1.setText("Restart");
        this.jLabel1.setText("Layer Number is 1");
        this.jLabel7.setText("");
        this.jLabel4.setText(((int[][])b.get(0)).length * 3 - 6 + "");
        int k = this.calcConn(0);
        this.jLabel6.setText(k + "");
        if (k == ((int[][])b.get(0)).length * 3 - 6) {
            this.jLabel2.setText("The Layer is Saturated");
        } else {
            this.jLabel2.setText("The Layer is not Saturated");
        }

        int l = 0;

        for(int i = 0; i < b.size(); ++i) {
            if (this.calcConn(i) == ((int[][])b.get(i)).length * 3 - 6) {
                ++l;
            }
        }

        this.jLabel7.setText("The Number Of Saturated Layer is " + l);
    }

    private int calcConn(int a) {
        int k = ((ArrayList)this.aPC.get(a)).size();

        for(int i = 0; i < ((int[])this.ePC.get(a)).length - 1; ++i) {
            if (((int[][])this.bPC.get(a))[((int[])this.ePC.get(a))[i]][((int[])this.ePC.get(a))[i + 1]] == 1) {
                ++k;
            }
        }

        return k;
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jButton1 = new JButton();
        this.jComboBox1 = new JComboBox();
        this.setBackground(new Color(255, 255, 255));
        this.jPanel1.setBackground(new Color(51, 255, 255));
        this.jLabel1.setText("The Layer number");
        this.jLabel2.setForeground(new Color(0, 153, 255));
        this.jLabel2.setText("test the layer if saturated");
        this.jLabel3.setText("The max number of the edges in the layer");
        this.jLabel4.setForeground(new Color(0, 153, 255));
        this.jLabel4.setText("the number");
        this.jLabel5.setText("The number of edges in this layer");
        this.jLabel6.setForeground(new Color(0, 153, 255));
        this.jLabel6.setText("the number");
        this.jLabel7.setForeground(new Color(0, 0, 204));
        this.jLabel7.setText("the number of the saturated layers");
        this.jButton1.setBackground(new Color(255, 255, 255));
        this.jButton1.setText("Restart");
        this.jButton1.addActionListener(this::jButton1ActionPerformed);
        this.jComboBox1.setForeground(new Color(0, 0, 204));
        this.jComboBox1.setMaximumRowCount(30);
        this.jComboBox1.setModel(new DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        this.jComboBox1.addActionListener(this::jComboBox1ActionPerformed);
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(40, 40, 40).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel1, -1, -1, 32767).addComponent(this.jLabel2, -1, 300, 32767)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel5, -2, 300, -2).addGap(18, 18, 18)).addComponent(this.jLabel3, -1, 320, 32767)).addGap(12, 12, 12).addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jLabel4, -2, 79, -2).addComponent(this.jLabel6, -2, 79, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel7, -1, 289, 32767).addComponent(this.jComboBox1, 0, -1, 32767)).addGap(34, 34, 34).addComponent(this.jButton1, -2, 140, -2).addContainerGap(70, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jComboBox1, -2, 38, -2).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel1, -2, 40, -2).addComponent(this.jLabel3, -2, 40, -2).addComponent(this.jLabel4, -2, 40, -2))).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel2, -2, 40, -2).addComponent(this.jLabel5, -2, 40, -2).addComponent(this.jLabel6, -2, 39, -2).addComponent(this.jLabel7, -2, 36, -2))).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(1, 1, 1).addComponent(this.jButton1, -1, -1, 32767)));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -2, -1, -2));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jPanel1, -2, -1, -2)));
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        this.tf.dispose();
        GenerateGraphFrame fff = new GenerateGraphFrame();
        fff.setVisible(true);
    }

    private void jComboBox1ActionPerformed(ActionEvent evt) {
        int layer = 0;

        for(int i = 0; i < this.aPC.size(); ++i) {
            if (this.jComboBox1.getSelectedIndex() == i) {
                layer = i;
                break;
            }
        }

        this.jLabel1.setText("Layer Number is " + (layer + 1));
        this.jLabel4.setText(((int[][])this.bPC.get(layer)).length * 3 - 6 + "");
        int k = this.calcConn(layer);
        this.jLabel6.setText(k + "");
        if (k == ((int[][])this.bPC.get(layer)).length * 3 - 6) {
            this.jLabel2.setText("The Layer is Saturated");
        } else {
            this.jLabel2.setText("The Layer is not Saturated");
        }

        this.sc.dos((ArrayList)this.aPC.get(layer), (int[][])this.bPC.get(layer), (int[])this.cPC.get(layer), (Point[])this.dPC.get(layer), (int[])this.ePC.get(layer));
    }
}
