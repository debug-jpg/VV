package main;

import entity.Entity;
import object.Heart;
import object.Key;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("all")
public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial, end, jp, jpTitle;
    BufferedImage heart_full, heart_half, heart_blank, keyImage;
    AssetSetter assets;
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
        this.assets = new AssetSetter(gp);

        arial = new Font("Arial", Font.PLAIN, 40);
        end = new Font("Arial", Font.BOLD, 80);
        jp = new Font("Meiryo", Font.PLAIN, 20);
        jpTitle = new Font("Meiryo", Font.BOLD, 80);

        Key key = new Key(gp);
        keyImage = key.image;


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

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        g2.setFont(arial);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) { drawTitleScreen(); }
        else if (gp.gameState == gp.playState) { drawPlayState(); }
        else if (gp.gameState == gp.pauseState) { drawPauseScreen(); }
        else if (gp.gameState == gp.dialogState) { drawQuizState(); }
        else if (gp.gameState == gp.optionState) { drawOptionScreen(); }
        else {
            JOptionPane.showMessageDialog(null, "Unexpected game state: " + gp.gameState);
        }
    }

    public void drawPlayState() {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setFont(arial);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
        String score = "SCORE: ";
        int x = gp.tileSize * 2;
        int y = gp.tileSize;
        g2.drawString(score, x, y);
    }

    public void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;

        for (int i = 0; i < gp.saki.maxLife / 2; i++) {
            g2.drawImage(heart_blank, x, y, null);
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;

        for (int i = 0; i < gp.saki.life; i++) {
            if (i % 2 == 0) {
                g2.drawImage(heart_half, x, y, null);
            }
            else {
                g2.drawImage(heart_full, x, y, null);
                x += gp.tileSize;
            }
        }

        if (gp.saki.life % 2 != 0) {
            x += gp.tileSize;
        }

    }

    public void drawTitleScreen() {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        g2.setFont(arial);
        g2.setColor(Color.white);

        g2.drawImage(assets.backgroundImage, 0, 0, null);

        switch (titleScreenState) {
            case 0 -> MainMenu(g2);
            case 1 -> MenuToPlay(g2);
            case 2 -> MenuAbout(g2);
        }
    }

    public void MainMenu(Graphics2D g2) {
        g2.drawImage(assets.logoImage, 0, 0, gp.tileSize * 2, gp.tileSize * 2, null);

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        String soundStatus = mixerInfo.length == 0 ? "No sound card detected." : "Sound card detected.";
        Color soundColor = mixerInfo.length == 0 ? Color.red : Color.green;

        g2.setFont(arial.deriveFont(Font.BOLD, 55));
        g2.setColor(soundColor);
        g2.drawString(soundStatus, gp.tileSize, gp.tileSize * 12);

        String title = "VISUAL VENTURE";
        int x = getXforCenteredText(title);
        int y = gp.tileSize * 3;

        g2.setColor(Color.GRAY);
        g2.drawString(title, x + 2, y + 2);
        g2.setColor(Color.WHITE);
        g2.drawString(title, x, y);

        String [] menuItems = {"Play", "Help", "About", "Exit"};
        y += gp.tileSize * 4;
        MenuOptions(g2, menuItems, y);
    }

    public void MenuOptions(Graphics2D g2, String[] options, int startY) {
        g2.setFont(arial.deriveFont(Font.PLAIN, 32));
        for (int i = 0; i < options.length; i++) {
            int x = getXforCenteredText(options[i]);
            g2.drawString(options[i], x, startY);

            if (commandNum == i) {
                g2.drawString(">", x - gp.tileSize, startY);
            }

            startY += gp.tileSize;
        }
    }

    public void MenuToPlay(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        g2.setFont(arial.deriveFont(Font.PLAIN, 60));
        g2.setColor(Color.BLACK);

        String title = "HOW TO PLAY";
        int x = getXforCenteredText(title);
        int y = gp.tileSize * 2;
        g2.drawString(title, x, y);

        g2.setFont(arial.deriveFont(Font.PLAIN, 32));
        String[] instructions = {
                "1. Start your journey: Begin with 10 points and embark on",
                "your adventure.",
                "2. Unlock obstacles: Answer questions to unlock obstacles",
                "and continue your journey.",
                "3. Earn points: Correct answers will earn you 10 points, while",
                "incorrect answers will deduct 3 points.",
                "4. Reach your destination: Accumulate 50 points to reach",
                "a beautiful destination and complete your journey."
        };

        x = gp.tileSize * 2;
        y += gp.tileSize * 2;

        for (String line : instructions) {
            g2.drawString(line, x, y);
            y += gp.tileSize;
        }

        g2.setFont(arial.deriveFont(Font.PLAIN, 20));
        g2.drawString("Press ESC to back", gp.tileSize, gp.tileSize);
    }

    public void MenuAbout(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        g2.setFont(arial.deriveFont(Font.BOLD, 60));
        g2.setColor(Color.BLACK);

        String title = "ABOUT GAME";
        int x = getXforCenteredText(title);
        int y = gp.tileSize * 2;
        g2.drawString(title, x, y);

        g2.setFont(arial.deriveFont(Font.PLAIN, 40));
        String[] aboutText = {
                "Explore the Philippines in \"Visual Venture\"!",
                "Answer questions, unlock obstacles, and learn",
                "about the country's amazing landmarks",
                "and culture.",
                "Reach 50 points to complete your journey!"
        };

        x = gp.tileSize * 2;
        y += gp.tileSize * 2;
        for (String line : aboutText) {
            g2.drawString(line, x, y);
            y += gp.tileSize;
        }

        g2.setFont(arial.deriveFont(Font.PLAIN, 20));
        g2.drawString("Press ESC to back", gp.tileSize, gp.tileSize);
    }

    public void drawPauseScreen() {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        g2.setFont(end);
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);

    }

    public void drawOptionScreen() {

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

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

    public void drawQuizState() {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.BLACK);

        if (gp.quiz.currentQuestionIndex < gp.quiz.questions.size()) {
            Question question = gp.quiz.questions.get(gp.quiz.currentQuestionIndex);
            g2.drawString("Question: " + question.questionText, 50, 100);

            g2.setFont(new Font("Arial", Font.PLAIN, 18));
            for (int i = 0; i < question.options.length; i++) {
                g2.drawString(question.options[i], 70, 150 + i * 30);
            }

            if (gp.quiz.showAnswerFeedback) {
                g2.setColor(Color.GREEN);
                g2.drawString("Correct!", 200, 300);
            } else if (gp.quiz.currentQuestionIndex > 0) {
                g2.setColor(Color.RED);
                g2.drawString("Incorrect", 200, 300);
            }

            g2.setColor(Color.BLACK);
            g2.drawString("Score: " + gp.quiz.score, 10, 20);
        }
    }

    public void topOption(int frameX, int frameY) {

        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        String[] options = {"Music", "SE", "Control", "Quit", "Resume"};

        int spacing = gp.tileSize;
        textX = frameX + gp.tileSize;
        textY += spacing;

        for (int i = 0; i < options.length; i++) {
            g2.drawString(options[i], textX, textY);

            if (commandNum == i) {
                g2.drawString(">", textX - 25, textY);

                if (gp.key.enter) {
                    if (i == 2) { // CONTROL
                        subState = 1;
                        commandNum = 0;
                    } else if (i == 3) { // QUIT
                        subState = 2;
                        commandNum = 0;
                    } else if (i == 4) { // BACK
                        gp.gameState = gp.playState; // Go back to playState
                    }
                }
            }
            textY += spacing;
        }

        int volumeBarX = frameX + (int) (gp.tileSize * 4.5);
        int musicBarY = frameY + gp.tileSize * 2 + 24;
        int seBarY = frameY + gp.tileSize * 3 + 24;

        VolumeBar(frameX + (int) (gp.tileSize * 4.5), frameY + gp.tileSize * 2 + 24, gp.music.volumeScale);
        VolumeBar(frameX + (int) (gp.tileSize * 4.5), frameY + gp.tileSize * 3 + 24, gp.effects.volumeScale);
    }

    public void VolumeBar(int x, int y, int volumeScale) {
        g2.drawRect(x, y, 120, 24);
        g2.fillRect(x, y, 24 * volumeScale, 24);
    }

    public void controls(int frameX, int frameY) {
        int textX, textY;

        String title = "CONTROLS";
        textX = getXforCenteredText(title);
        textY = frameY + gp.tileSize;
        g2.drawString(title, textX, textY);

        String[] labels = {"Move", "Interact", "Pause", "Options"};
        String[] keys = {"WASD", "ENTER", "P", "ESC"};

        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 2;
        for (String label : labels) {
            g2.drawString(label, textX, textY);
            textY += gp.tileSize;
        }

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        for (String key : keys) {
            g2.drawString(key, textX, textY);
            textY += gp.tileSize;
        }

        String backText = "Back";
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString(backText, textX, textY);

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

        currentDialog = "Return to title screen?";

        for (String line : currentDialog.split("\n")) {
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
