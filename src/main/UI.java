package main;

import object.Heart;
import object.Key;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("all")
public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial, end, jp, jpTitle;
    BufferedImage keyImage, heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialog = "";
    public int commandNum = 0;
    public int titleScreenState = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        arial = new Font("Arial", Font.PLAIN, 40);
        end = new Font("Arial", Font.BOLD, 80);
        jp = new Font("Meiryo", Font.PLAIN, 20);
        jpTitle = new Font("Meiryo", Font.BOLD, 80);

//        Key key = new Key(gp);
//        keyImage = key.image;

        // CREATE HUD OBJECT
        SuperObject heart = new Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            // ------------------- //
            if (gameFinished) {
//
//                g2.setFont(arial);
//                g2.setColor(Color.white);
//
//                String text;
//                int textLength;
//                int x, y;
//
//                text = "You found the treasure";
//                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//
//                x = gp.screenWidth / 2 - textLength / 2;
//                y = gp.screenHeight / 2 - (gp.tileSize * 3);
//
//                g2.drawString(text, x, y);
//
//                g2.setFont(end);
//                g2.setColor(Color.yellow);
//
//                text = "Omedetou Gozaimasu";
//                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//
//                x = gp.screenWidth / 2 - textLength / 2;
//                y = gp.screenHeight / 2 + (gp.tileSize * 2);
//
//                g2.drawString(text, x, y);
//
//                gp.gameThread = null;
//
            }
            else {
//                g2.setFont(arial);
//                g2.setColor(Color.white);
//                g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
//                g2.drawString("x " + gp.player.hasKey, 74, 65);
//
//                // MESSAGE
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
            // ------------------------- //

            drawPlayerLife();

        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogState) {
            drawDialogScreen();
        }
    }

    public void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // MAX LIFE
        while (i < gp.player.life / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setFont(jpTitle);
            String text = "好きだから";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // SHADOW COLOR
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);

            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // LOGO
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48));

            text = "新しいゲーム";
            x = getXforCenteredText(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);

            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "オプション";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);

            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "止める";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);

            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }

        else if (titleScreenState == 1) {

            // CLASS SELECTION
            g2.setFont(jp);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 42));

            String text = "選択してね";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "初音ミク";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);

            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "重音テト";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);

            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "亞北ネル";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);

            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "戻る";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);

            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }

        }

    }

    public void drawPauseScreen() {

        g2.setFont(end);
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);

    }

    public void drawDialogScreen() {

        // WINDOW
        int x, y, width, height;
        x = gp.tileSize * 2;
        y = gp.tileSize / 2;
        width = gp.screenWidth - (gp.tileSize * 4);
        height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(jp);
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color color = new Color(0, 0, 0, 190);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;

        return x;
    }
}









