package com.ahmad.graph.thickness.ui;


import com.ahmad.graph.thickness.model.CurveEdge;
import com.ahmad.graph.thickness.model.Point;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;

public class ResultViewFrame extends JFrame {
    private static ArrayList<ArrayList<CurveEdge>> aa;
    private static ArrayList<int[][]> bb;
    private static ArrayList<int[]> cc;
    private static ArrayList<Point[]> dd;
    private static ArrayList<int[]> ee;
    private static boolean ff;

    public ResultViewFrame(ArrayList<ArrayList<CurveEdge>> a, ArrayList<int[][]> b, ArrayList<int[]> c, ArrayList<Point[]> d, ArrayList<int[]> e, boolean f) {
        aa = a;
        bb = b;
        cc = c;
        dd = d;
        ee = e;
        ff = f;
        this.initComponents();
        this.setSize(1366, 768);
        LayerCanvas fc = new LayerCanvas((ArrayList)a.get(0), (int[][])b.get(0), (int[])c.get(0), (Point[])d.get(0), (int[])e.get(0));
        ResultStatsPanel tp = new ResultStatsPanel(a, b, c, d, e, this, fc);
        this.getContentPane().add(tp, "North");
        this.getContentPane().add(fc, "South");
    }

    private void initComponents() {
        this.setDefaultCloseOperation(3);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGap(0, 677, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGap(0, 411, 32767));
        this.pack();
    }

    public static void main(String[] args) {
        try {
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ResultViewFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ResultViewFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ResultViewFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ResultViewFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

        EventQueue.invokeLater(() -> {
            ArrayList<int[]> e = new ArrayList<>();

            new ResultViewFrame(aa, bb, cc, dd, ee, ff).setVisible(true);
        });
    }
}
