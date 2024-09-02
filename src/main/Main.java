package main;

import javax.swing.*;

public class Main {

    public static JFrame frame;

    public static void main(String[] args) {

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Visual Venture");
        frame.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }

}
