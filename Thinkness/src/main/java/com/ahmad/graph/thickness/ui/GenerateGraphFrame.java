package com.ahmad.graph.thickness.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GenerateGraphFrame extends JFrame {
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JPanel jPanel1;

    public GenerateGraphFrame() {
        this.initComponents();
        this.setLocation(450, 300);
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.setDefaultCloseOperation(3);
        this.jPanel1.setBackground(new Color(51, 255, 204));
        this.jLabel1.setText("                        Please Chose the type of the graph you want....");
        this.jButton1.setText("K n,n");
        this.jButton1.addActionListener(this::jButton1ActionPerformed);
        this.jButton2.setText("K n");
        this.jButton2.addActionListener(this::jButton2ActionPerformed);
        this.jButton3.setText("Random");
        this.jButton3.addActionListener(this::jButton3ActionPerformed);
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(45, 45, 45).addComponent(this.jLabel1, -2, 355, -2).addContainerGap(83, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addGap(77, 77, 77).addComponent(this.jButton1).addGap(77, 77, 77).addComponent(this.jButton2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton3).addGap(71, 71, 71)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel1, -2, 25, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2).addComponent(this.jButton3)).addContainerGap(12, 32767)));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, Alignment.TRAILING, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767));
        this.pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        int n;
        for(n = Integer.parseInt(JOptionPane.showInputDialog("Insert The Number Of The Nodes")); n < 0; n = Integer.parseInt(JOptionPane.showInputDialog("Insert The Number Of The Nodes"))) {
        }

        int[][] graphArray = new int[2 * n][2 * n];

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                graphArray[i][j] = 0;
                graphArray[i + n][j + n] = 0;
            }
        }

        for(int i = n; i < 2 * n; ++i) {
            for(int j = 0; j < n; ++j) {
                graphArray[i][j] = 1;
                graphArray[j][i] = 1;
            }
        }

        this.dispose();
        DisplayGraphFrame sf = new DisplayGraphFrame(graphArray, graphArray.length, 1);
        sf.setVisible(true);
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        int n;
        for(n = Integer.parseInt(JOptionPane.showInputDialog("Insert The Number Of The Nodes")); n < 0; n = Integer.parseInt(JOptionPane.showInputDialog("Insert The Number Of The Nodes"))) {
        }

        int[][] graphArray = new int[n][n];

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                if (i == j) {
                    graphArray[i][j] = 0;
                } else {
                    graphArray[i][j] = 1;
                }
            }
        }

        this.dispose();
        DisplayGraphFrame sf = new DisplayGraphFrame(graphArray, graphArray.length, 0);
        sf.setVisible(true);
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        boolean ttt = true;

        while(ttt) {
            int n = Integer.parseInt(JOptionPane.showInputDialog("Insert The Max Number Of The Nodes More Than Two "));
            if (n > 2) {
                this.randArray(n);
                ttt = false;
            }
        }

    }

    public void randArray(int a) {
        Random r = new Random();
        int[][] graphArray1 = new int[a][a];

        for(int i = 0; i < a - 1; ++i) {
            for(int j = i + 1; j < a; ++j) {
                graphArray1[i][j] = r.nextInt(2);
                graphArray1[j][i] = graphArray1[i][j];
            }
        }

        for(int i = 0; i < a; ++i) {
            graphArray1[i][i] = 0;
        }

        boolean tt = true;

        while(tt) {
            tt = false;

            for(int i = 0; i < a; ++i) {
                if (this.detDeg(i, graphArray1) == 1) {
                    for(int k = 0; k < a; ++k) {
                        graphArray1[i][k] = 0;
                        graphArray1[k][i] = 0;
                    }

                    tt = true;
                }
            }
        }

        int deleteCount = 0;

        for(int i = 0; i < a; ++i) {
            if (this.detDeg(i, graphArray1) == 0) {
                ++deleteCount;
            }
        }

        if (deleteCount == a) {
            this.randArray(a);
        } else {
            int[][] graphArray = new int[a - deleteCount][a - deleteCount];
            int ii = 0;

            for(int i = 0; i < a; ++i) {
                if (this.detDeg(i, graphArray1) != 0) {
                    int jj = 0;

                    for(int j = 0; j < a; ++j) {
                        if (this.detDeg(j, graphArray1) != 0) {
                            graphArray[ii][jj] = graphArray1[i][j];
                            ++jj;
                        }
                    }

                    ++ii;
                }
            }

            this.dispose();
            DisplayGraphFrame sf = new DisplayGraphFrame(graphArray, graphArray.length, 0);
            sf.setVisible(true);
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

    public static void main(String[] args) {
        try {
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenerateGraphFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GenerateGraphFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenerateGraphFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GenerateGraphFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

        EventQueue.invokeLater(() -> {
            new GenerateGraphFrame().setVisible(true);
        });
    }
}
