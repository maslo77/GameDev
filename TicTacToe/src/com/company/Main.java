package com.company;

import javax.swing.*;
import java.security.Guard;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });

    }
}
