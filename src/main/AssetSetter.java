package main;

import javax.imageio.ImageIO;

import entity.NPC_OldMan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

@SuppressWarnings("all")
public class AssetSetter {

    GamePanel gp;;

    public BufferedImage TextBox, backgroundImage, logoImage, btnImage;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

        try {

            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/background/bg_main.png"));
            logoImage = ImageIO.read(getClass().getResourceAsStream("/background/CamhiLogo.png"));
            TextBox = ImageIO.read(getClass().getResourceAsStream("/background/bg_main.png"));
            btnImage = ImageIO.read(getClass().getResourceAsStream("/background/bg_main.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void setObject() {
        // gp.obj[0] = new Key(gp);
        // gp.obj[0].worldX = gp.tileSize * 23;
        // gp.obj[0].worldY = gp.tileSize * 7;
        //
        // gp.obj[1] = new Key(gp);
        // gp.obj[1].worldX = gp.tileSize * 23;
        // gp.obj[1].worldY = gp.tileSize * 40;
        //
        // gp.obj[2] = new Key(gp);
        // gp.obj[2].worldX = gp.tileSize * 38;
        // gp.obj[2].worldY = gp.tileSize * 8;
        //
        // gp.obj[3] = new Door(gp);
        // gp.obj[3].worldX = gp.tileSize * 10;
        // gp.obj[3].worldY = gp.tileSize * 11;
        //
        // gp.obj[4] = new Door(gp);
        // gp.obj[4].worldX = gp.tileSize * 8;
        // gp.obj[4].worldY = gp.tileSize * 28;
        //
        // gp.obj[5] = new Door(gp);
        // gp.obj[5].worldX = gp.tileSize * 12;
        // gp.obj[5].worldY = gp.tileSize * 22;
        //
        // gp.obj[6] = new Chest(gp);
        // gp.obj[6].worldX = gp.tileSize * 10;
        // gp.obj[6].worldY = gp.tileSize * 7;
        //
        // gp.obj[7] = new Boots(gp);
        // gp.obj[7].worldX = gp.tileSize * 37;
        // gp.obj[7].worldY = gp.tileSize * 42;
    }

    public void setNPC() {

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 17;
        gp.npc[0].worldY = gp.tileSize * 25;

    }

    public void printText(String text, int x, int y, int ln, int size, boolean forceColor, Graphics2D g2) {
        if (forceColor) {
            g2.setColor(new Color(73, 29, 0));
        }
        g2.setFont(gp.ui.arial);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32));
        for (String line : text.split("\n")) {
            g2.drawString(line, x, y);
            y += ln;
        }
    }

    public void textBox(int x, int y, int width, int height, Graphics2D g2) {
        g2.drawImage(TextBox, x, y, width, height, null);
    }

    public void button(int x, int y, int width, int height, Graphics2D g2) {
        g2.drawImage(btnImage, x, y, width, height, null);
    }
}
