package main;

import javax.imageio.ImageIO;

//import entity.NPC_OldMan;
import entity.NPC_OldMan;
import object.*;

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
        gp.obj[0] = new Key(gp);
        gp.obj[0].worldX = gp.tileSize * 15;
        gp.obj[0].worldY = gp.tileSize * 35;

        gp.obj[1] = new Fence(gp);
        gp.obj[1].worldX = gp.tileSize * 16;
        gp.obj[1].worldY = gp.tileSize * 29;

        gp.obj[2] = new Fence(gp);
        gp.obj[2].worldX = gp.tileSize * 16;
        gp.obj[2].worldY = gp.tileSize * 33;

        gp.obj[3] = new Key(gp);
        gp.obj[3].worldX = gp.tileSize * 17;
        gp.obj[3].worldY = gp.tileSize * 35;

        gp.obj[4] = new Fence2(gp);
        gp.obj[4].worldX = gp.tileSize * 19;
        gp.obj[4].worldY = gp.tileSize * 27;

        gp.obj[5] = new Fence2(gp);
        gp.obj[5].worldX = gp.tileSize * 23;
        gp.obj[5].worldY = gp.tileSize * 27;

        gp.obj[6] = new Fence(gp);
        gp.obj[6].worldX = gp.tileSize * 24;
        gp.obj[6].worldY = gp.tileSize * 23;

        gp.obj[7] = new Fence(gp);
        gp.obj[7].worldX = gp.tileSize * 25;
        gp.obj[7].worldY = gp.tileSize * 19;

        gp.obj[8] = new Fence2(gp);
        gp.obj[8].worldX = gp.tileSize * 21;
        gp.obj[8].worldY = gp.tileSize * 18;

        gp.obj[9] = new Fence(gp);
        gp.obj[9].worldX = gp.tileSize * 19;
        gp.obj[9].worldY = gp.tileSize * 20;

        gp.obj[10] = new Key(gp);
        gp.obj[10].worldX = gp.tileSize * 17;
        gp.obj[10].worldY = gp.tileSize * 26;

        gp.obj[11] = new Key(gp);
        gp.obj[11].worldX = gp.tileSize * 21;
        gp.obj[11].worldY = gp.tileSize * 27;

        gp.obj[12] = new Key(gp);
        gp.obj[12].worldX = gp.tileSize * 22;
        gp.obj[12].worldY = gp.tileSize * 25;

        gp.obj[13] = new Key(gp);
        gp.obj[13].worldX = gp.tileSize * 25;
        gp.obj[13].worldY = gp.tileSize * 24;

        gp.obj[14] = new Key(gp);
        gp.obj[14].worldX = gp.tileSize * 23;
        gp.obj[14].worldY = gp.tileSize * 17;

        gp.obj[15] = new Key(gp);
        gp.obj[15].worldX = gp.tileSize;
        gp.obj[15].worldX = gp.tileSize;

        gp.obj[16] = new Fence2(gp);
        gp.obj[16].worldX = gp.tileSize * 16;
        gp.obj[16].worldX = gp.tileSize * 22;

        gp.obj[17] = new Fence2(gp);
        gp.obj[17].worldX = gp.tileSize * 12;
        gp.obj[17].worldY = gp.tileSize * 22;

        gp.obj[18] = new Fence(gp);
        gp.obj[18].worldX = gp.tileSize * 9;
        gp.obj[18].worldY = gp.tileSize * 25;

        gp.obj[19] = new Key(gp);
        gp.obj[19].worldX = gp.tileSize * 18;
        gp.obj[19].worldY = gp.tileSize * 19;

        gp.obj[20] = new Key(gp);
        gp.obj[20].worldX = gp.tileSize * 15;
        gp.obj[20].worldY = gp.tileSize * 21;

        gp.obj[21] = new Key(gp);
        gp.obj[21].worldX = gp.tileSize;
        gp.obj[21].worldY = gp.tileSize;

        gp.obj[22] = new Key(gp);
        gp.obj[22].worldX = gp.tileSize;
        gp.obj[22].worldY = gp.tileSize;

        gp.obj[23] = new Key(gp);
        gp.obj[23].worldX = gp.tileSize;
        gp.obj[23].worldY = gp.tileSize;

        gp.obj[24] = new Key(gp);
        gp.obj[24].worldX = gp.tileSize;
        gp.obj[24].worldY = gp.tileSize;

    }

    public void setNPC() {

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 17;
        gp.npc[0].worldY = gp.tileSize * 26;

    }


}
