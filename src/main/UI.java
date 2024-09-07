package main;

import entity.Entity;
import object.Heart;

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
    public int subState = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        arial = new Font("Arial", Font.PLAIN, 40);
        end = new Font("Arial", Font.BOLD, 80);
        jp = new Font("Meiryo", Font.PLAIN, 20);
        jpTitle = new Font("Meiryo", Font.BOLD, 80);

//        Key key = new Key(gp);
//        keyImage = key.image;

        // CREATE HUD OBJECT
        Entity heart = new Heart(gp);
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
        if (gp.gameState == gp.optionState) {

            drawOptionScreen();

        }
    }

    public void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // MAX LIFE
        while (i < gp.player.maxLife / 2) {
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
            String text = "VISUAL VENTURE";
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
            g2.setFont(arial);

            text = "PLAY";
            x = getXforCenteredText(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);

            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "ABOUT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);

            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "HELP";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);

            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "EXIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);

            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }

        else if (titleScreenState == 1) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // CLASS SELECTION
            g2.setFont(jp);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 42));

            String text = "Who's my crush?";
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

    public void drawOptionScreen() {

        g2.setColor(Color.white);
        g2.setFont(arial);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUBWINDOW
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                topOption(frameX, frameY);
                break;
            case 1:
                controls(frameX, frameY);
                break;
            case 2:
                endGame(frameX, frameY);
                break;
        }

        gp.key.enter = false;

    }

    public void topOption(int frameX, int frameY) {

        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

//        // FULL SCREEN
//        textX = frameX + gp.tileSize;
//        textY += gp.tileSize * 2;
//        g2.drawString("Full Screen", textX, textY);
//        if (commandNum == 0) {
//            g2.drawString(">", textX - 25, textY);
//
//            if (gp.key.enter) {
//                if (gp.fullScreen != true) {
//                    gp.fullScreen = true;
//                }
//                else if (gp.fullScreen) {
//                    gp.fullScreen = false;
//                }
//                subState = 1;
//            }
//        }

        // MUSIC
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Music", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
        }

        // SOUND EFFECT
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        // CONTROL
        textY += gp.tileSize;
        g2.drawString("CONTROL", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter) {
                subState = 1;
                commandNum = 0;
            }
        }


        // END GAME
        textY += gp.tileSize;
        g2.drawString("QUIT", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter) {
                subState = 2;
                commandNum = 0;
            }
        }

        // BACK
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
        }

//        // FULL SCREEN CHECKBOX
//        textX = frameX + (int)(gp.tileSize * 4.5);
//        textY = frameY + gp.tileSize * 2 + 24;
//        g2.setStroke(new BasicStroke(3));
//        g2.drawRect(textX, textY, 24,  24);
//        if (gp.fullScreen) {
//            g2.fillRect(textX, textY, 24,  24);
//        }

        // MUSIC RECT
        textX = frameX + (int)(gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 2 + 24;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SOUND EFFECT RECT
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.effects.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

    }

//    public void fullScreenNotif(int frameX, int frameY) {
//
//        int textX = frameX + gp.tileSize;
//        int textY = frameY + gp.tileSize * 3;
//
//        currentDialog = "Restart the game for \nthe change to effect";
//        for (String line : currentDialog.split("\n")) {
//            g2.drawString(line, textX, textY);
//            textY += 40;
//        }
//
//        // BACK
//        textY = frameY + gp.tileSize * 9;
//        g2.drawString("Back", textX, textY);
//        if (commandNum == 0) {
//            g2.drawString(">", textX - 25, textY);
//            if (gp.key.enter) {
//                subState = 0;
//            }
//        }
//
//    }

    public void controls(int frameX, int frameY) {

        int textX, textY;

        // TITLE
        String text = "CONTROLS";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Interact", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        // BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter) {
                subState = 0;
                commandNum = 2;
            }
        }

    }

    public void endGame(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialog = "Return to the title \nscreen?";

        for (String line: currentDialog.split("\n")) {
            g2.drawString(line, textX, textY);
            textX += 40;
        }

        // YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter) {
                subState = 0;
                titleScreenState = 0;
                gp.gameState = gp.titleState;
                gp.stopMusic();
            }
        }

        // NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter) {
                subState = 0;
                commandNum = 3;
            }
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









