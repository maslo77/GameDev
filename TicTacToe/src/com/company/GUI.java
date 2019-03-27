package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI  extends JFrame {
    private Container panel;
    private String NazwaGracza;
    private JButton[][] b;
    private boolean wygrany;
    private JMenuBar menuGry;
    private JMenu menu;
    private JMenuItem NowaGra;
    private JMenuItem wyjscie;

    public GUI() {
        //super();
        panel = this.getContentPane();
        panel.setLayout(new GridLayout(3,3));
        setTitle("Kółko i Krzyżyk");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        NazwaGracza ="x";
        wygrany = false;
        b = new JButton[3][3];
        plansza();
        Init();
    }
    private void Init(){
        menuGry = new JMenuBar();
        menu = new JMenu("Opcje");
        NowaGra = new JMenuItem("Nowa Gra");
        NowaGra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reset();
            }
        });
       wyjscie = new JMenuItem("Wyjdź");
       wyjscie.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {
               System.exit(0);
           }
       });
       menu.add(NowaGra);
       menu.add(wyjscie);
       menuGry.add(menu);
       setJMenuBar(menuGry);
    }
    private void wygrany() {
        for (int row = 0; row < 3; row++) {
            if (b[row][0].getText().equals(b[row][1].getText()) && b[row][1].getText().equals(b[row][2].getText())) {
                if (b[row][0].getText().equals(NazwaGracza)) {
                    wygrany = true;
                    JOptionPane.showMessageDialog(null, "Gracz" + NazwaGracza + "WYgrał");
                } else if (b[row][0].getText().equals("o")) {
                    wygrany = true;
                    JOptionPane.showMessageDialog(null, "Komputer WYgrał!");

                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (b[0][col].getText().equals(b[1][col].getText()) && b[1][col].getText().equals(b[2][col].getText())) {
                if (b[0][col].getText().equals(NazwaGracza)) {
                    wygrany = true;
                    JOptionPane.showMessageDialog(null, "Gracz" + NazwaGracza + "WYgrał");
                } else if (b[0][col].getText().equals("o")) {
                    wygrany = true;
                    JOptionPane.showMessageDialog(null, "Komputer WYgrał!");

                }
            }
        }

        if (b[0][0].getText().equals(b[1][1].getText()) && b[1][1].getText().equals(b[2][2].getText())) {
            if (b[0][0].getText().equals("x")) {
                wygrany = true;
                JOptionPane.showMessageDialog(null, "Gracz" + NazwaGracza + "WYgrał");
            } else if (b[0][0].equals("o")) {
                wygrany = true;
                JOptionPane.showMessageDialog(null, "Komputer WYgrał!");

            }
        }

        if (b[0][2].getText().equals(b[1][1].getText()) && b[1][1].getText().equals(b[2][0].getText())) {
            if (b[0][2].getText().equals("x")) {
                wygrany = true;
                JOptionPane.showMessageDialog(null, "Gracz" + NazwaGracza + "WYgrał");
            } else if (b[0][2].getText().equals("o")) {
                wygrany = true;
                JOptionPane.showMessageDialog(null, "Komputer WYgrał!");

            }

        }
    }

    private void reset()
    {   NazwaGracza = "x";
        wygrany = false;
        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 3;++j)
            {
                b[i][j].setText("");
            }
        }

    }
    private void plansza() {
        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 3;++j)
            {
                JButton btn  = new JButton();
                btn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,120));
                b[i][j] = btn;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if(wygrany == false && ((JButton)actionEvent.getSource()).getText().equals(""))
                        {
                            btn.setText(NazwaGracza);
                            wygrany();
                            przelaczGracza();
                        }else if(wygrany == true)
                        {

                        }
                    }
                });
                panel.add(btn);
            }
        }

    }
    private void przelaczGracza()
    {   if(NazwaGracza.equals("x"))
    {
        NazwaGracza = "o";
    }else NazwaGracza = "x";

    }
}
