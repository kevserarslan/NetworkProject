package com.mycompany.networkproject;

import javax.swing.*;
import java.awt.*;

public class TavlaGuı extends JFrame {
    private JLayeredPane layeredPane;
    private JLabel[][] taslar = new JLabel[24][5];

    public TavlaGuı() {
        setTitle("Tavla Oyunu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(612, 495));

        JLabel arkaPlan = new JLabel(new ImageIcon(getClass().getResource("/com/mycompany/networkproject/images/backgomman.jpg")));
        arkaPlan.setBounds(0, 0, 612, 495);
        layeredPane.add(arkaPlan, Integer.valueOf(0));

        // Siyah taşların başlangıç dizilimi
        addTas(0, 2, Color.BLACK);
        addTas(11, 5, Color.BLACK);
        addTas(16, 3, Color.BLACK);
        addTas(18, 5, Color.BLACK);

        // Beyaz taşların başlangıç dizilimi
        addTas(23, 2, Color.WHITE);
        addTas(12, 5, Color.WHITE);
        addTas(7, 3, Color.WHITE);
        addTas(5, 5, Color.WHITE);

        add(layeredPane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTas(int triangleIndex, int count, Color color) {
        for (int i = 0; i < count; i++) {
            JLabel tas = yeniTas(color);
            tas.setBounds(getX(triangleIndex), getY(triangleIndex, i), 34, 34);
            layeredPane.add(tas, Integer.valueOf(1));
            taslar[triangleIndex][i] = tas;
        }
    }

    private int getX(int triangleIndex) {
        int triangleWidth = 51;
        int boardStartX = 0;
        if (triangleIndex < 12) {
            return boardStartX + (11 - triangleIndex) * triangleWidth + 8;
        } else {
            return boardStartX + (triangleIndex - 12) * triangleWidth + 8;
        }
    }

    private int getY(int triangleIndex, int layer) {
        int offsetY = 36;
        int barHeight = 100;
        int triangleHeight = 197;
        int boardTopY = 0;
        if (triangleIndex < 12) {
            return boardTopY + layer * offsetY;
        } else {
            int baseY = boardTopY + triangleHeight + barHeight;
            return baseY - (layer + 1) * offsetY;
        }
    }

    private JLabel yeniTas(Color color) {
        JLabel label = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(color);
                g.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        label.setPreferredSize(new Dimension(34, 34));
        label.setSize(34, 34);
        label.setOpaque(false);
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TavlaGuı::new);
    }
}