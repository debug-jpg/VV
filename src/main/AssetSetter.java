package main;

import javax.imageio.ImageIO;

import entity.NPC_OldMan;
import object.Door;

import java.awt.image.BufferedImage;
import java.io.IOException;

@SuppressWarnings("all")
public class AssetSetter {

    GamePanel gp;
    ;

    public BufferedImage TextBox, backgroundImage, logoImage, btnImage;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

        try {

            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/background/bg_main.png"));
            logoImage = ImageIO.read(getClass().getResourceAsStream("/background/CamhiLogo.png"));
            TextBox = ImageIO.read(getClass().getResourceAsStream("/background/bg_main.png"));
            btnImage = ImageIO.read(getClass().getResourceAsStream("/background/bg_btn.png"));

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
        gp.obj[3] = new Door(gp);
        gp.obj[3].worldX = gp.tileSize * 17;
        gp.obj[3].worldY = gp.tileSize * 10;

        gp.obj[4] = new Door(gp);
        gp.obj[4].worldX = gp.tileSize * 17;
        gp.obj[4].worldY = gp.tileSize * 18;

        gp.obj[5] = new Door(gp);
        gp.obj[5].worldX = gp.tileSize * 17;
        gp.obj[5].worldY = gp.tileSize * 23;

    }

    public void setNPC() {

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 17;
        gp.npc[0].worldY = gp.tileSize * 25;

    }


}
