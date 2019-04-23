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
    public int wynik = 0;
    public static int MIN = -1000;
    public static int MAX = 1000;

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
        przelaczGracza();
    }
    private void Init() {
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
                    wynik = 1;
                    JOptionPane.showMessageDialog(null, "Gracz" + NazwaGracza + "WYgrał");
                } else if (b[row][0].getText().equals("o")) {
                    wygrany = true;
                    wynik = 0;
                    JOptionPane.showMessageDialog(null, "Komputer WYgrał!");

                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (b[0][col].getText().equals(b[1][col].getText()) && b[1][col].getText().equals(b[2][col].getText())) {
                if (b[0][col].getText().equals(NazwaGracza)) {
                    wygrany = true;
                    wynik = 1;
                    JOptionPane.showMessageDialog(null, "Gracz" + NazwaGracza + "WYgrał");
                } else if (b[0][col].getText().equals("o")) {
                    wygrany = true;
                    wynik = 0;
                    JOptionPane.showMessageDialog(null, "Komputer WYgrał!");

                }
            }
        }

        if (b[0][0].getText().equals(b[1][1].getText()) && b[1][1].getText().equals(b[2][2].getText())) {
            if (b[0][0].getText().equals("x")) {
                wygrany = true;
                wynik = 1;
                JOptionPane.showMessageDialog(null, "Gracz" + NazwaGracza + "WYgrał");
            } else if (b[0][0].getText().equals("o")) {
                wygrany = true;
                wynik = 0;
                JOptionPane.showMessageDialog(null, "Komputer WYgrał!");

            }
        }

        if (b[0][2].getText().equals(b[1][1].getText()) && b[1][1].getText().equals(b[2][0].getText())) {
            if (b[0][2].getText().equals("x")) {
                wygrany = true;
                wynik = 1;
                JOptionPane.showMessageDialog(null, "Gracz" + NazwaGracza + "WYgrał");
            } else if (b[0][2].getText().equals("o")) {
                wygrany = true;
                wynik = 0;
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
                        if(wygrany == false && ((JButton)actionEvent.getSource()).getText().equals("")) {
                            btn.setText(NazwaGracza);
                            wygrany();
                            przelaczGracza();
                            wygrany();
                        }
                    }
                });
               panel.add(btn);
            }
        }


    }
    private void przelaczGracza()
    {
        Ruch g = findBestMove();
        b[g.col][g.row].setText("o");
    }
    int evaluate() {
        for (int row = 0; row<3; row++)
            if (b[row][0].getText().equals(b[row][1].getText()) && b[row][1].getText().equals(b[row][2].getText())) {
                if (b[row][0].getText().equals("x")) return 1;
                else if (b[row][0].getText().equals("o")) return -1;
            }

        for (int col = 0; col<3; col++)
            if (b[0][col].getText().equals(b[1][col].getText()) && b[1][col].getText().equals(b[2][col].getText())) {
                if (b[0][col].getText().equals("x")) return 1;
                else if (b[0][col].getText().equals("o")) return -1;
            }

        if (b[0][0].getText().equals(b[1][1].getText()) && b[1][1].getText().equals(b[2][2].getText())) {
            if (b[0][0].getText().equals("x")) return 1;
            else if (b[0][0].getText().equals("o")) return -1;
        }

        if (b[0][2].getText().equals(b[1][1].getText()) && b[1][1].getText().equals(b[2][0].getText())) {
            if (b[0][2].getText().equals("x")) return 1;
            else if (b[0][2].getText().equals("o")) return -1;
        }

        return 0;
    }
    int minmax(JButton board[][], int depth, boolean isMax) {
        int wynik = evaluate();
        if (wynik == 1) return wynik;
        if (wynik == -1) return wynik;
        if (isMovesLeft()==false) return 0;

        int best = isMax ? -1000 : 1000;
        for (int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().equals("")) {
                    board[i][j].setText(isMax ? "x" : "o");
                    best = isMax ? Math.max(best, minmax(board, depth + 1, !isMax)) : Math.min(best, minmax(board, depth + 1, !isMax));
                    board[i][j].setText("");
                }
            }
        }
                return best;
    }

    boolean isMovesLeft()
    {
        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 3; ++j)
            {
                if(b[i][j].getText().equals("")) return true;
            }
        }
        return false;
    }

    public Ruch findBestMove()
    {
        int bestVal = -1000;
        Ruch bestMove = new Ruch();
        bestMove.row = -1;
        bestMove.col = -1;
        for(int i = 0; i < 3; ++i)
            for(int j = 0; j < 3;++j)
                if(b[i][j].getText().equals(""))
                {   b[i][j].setVisible(false);
                    b[i][j].setText("o");
                    int moveVal = minmax(b,0,false);
                    b[i][j].setText("");
                    b[i][j].setVisible(true);
                    if(moveVal > bestVal)
                    {
                        bestMove.row = j;
                        bestMove.col = i;
                        bestVal = moveVal;
                    }
                }
        return bestMove;

    }
}
