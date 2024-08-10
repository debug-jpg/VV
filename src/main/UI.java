package main;

import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial, end;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat format = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial = new Font("Arial", Font.PLAIN, 40);
        end = new Font("Arial", Font.BOLD, 80);

        Key key = new Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished) {

            g2.setFont(arial);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x, y;

            text = "You found the treasure";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);

            g2.drawString(text, x, y);

            g2.setFont(end);
            g2.setColor(Color.yellow);

            text = "Omedetou Gozaimasu";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);

            g2.drawString(text, x, y);

            gp.gameThread = null;

        }
        else {
            g2.setFont(arial);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            // TIME
            playTime += (double) 1/60;
            g2.drawString("Time: " + format.format(playTime), gp.tileSize * 11, 65);

            // MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}