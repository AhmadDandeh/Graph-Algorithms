package com.ahmad.graph.thickness.ui;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;

public class DisplayGraphFrame extends JFrame {
    public static int[][] graphArray;
    public static int nValue;
    public static int typeValue;

    public DisplayGraphFrame(int[][] a, int n, int type) {
        graphArray = a;
        nValue = n;
        typeValue = type;
        this.initComponents();
        this.setSize(1366, 768);
        ProcessControlPanel tp = new ProcessControlPanel(a, n, this);
        InitialGraphCanvas fc = new InitialGraphCanvas(a, n, type);
        this.getContentPane().add(tp, "North");
        this.getContentPane().add(fc, "South");
    }

    private void initComponents() {
        this.setDefaultCloseOperation(3);
        this.setSize(new Dimension(1450, 750));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGap(0, 860, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGap(0, 450, 32767));
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
            Logger.getLogger(DisplayGraphFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DisplayGraphFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DisplayGraphFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DisplayGraphFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

        EventQueue.invokeLater(() -> {
            new DisplayGraphFrame(graphArray, nValue, typeValue).setVisible(true);
        });
    }
}
