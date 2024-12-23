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
        ObjectData[] objects = {
            new ObjectData("Key", 15, 35),
            new ObjectData("Fence", 16, 29),
            new ObjectData("Fence", 16, 33),
            new ObjectData("Key", 17, 35),
            new ObjectData("Fence2", 19, 27),
            new ObjectData("Fence2", 23, 27),
            new ObjectData("Fence", 24, 23),
            new ObjectData("Fence", 25, 19),
            new ObjectData("Fence2", 21, 18),
            new ObjectData("Fence", 19, 20),
            new ObjectData("Key", 17, 26),
            new ObjectData("Key", 21, 27),
            new ObjectData("Key", 22, 25),
            new ObjectData("Key", 25, 24),
            new ObjectData("Key", 23, 17),
            new ObjectData("Key", 1, 1),
            new ObjectData("Fence2", 16, 22),
            new ObjectData("Fence2", 12, 22),
            new ObjectData("Fence", 9, 25),
            new ObjectData("Key", 18, 19),
            new ObjectData("Key", 15, 21),
            new ObjectData("Key", 1, 1),
            new ObjectData("Key", 1, 1),
            new ObjectData("Key", 1, 1),
            new ObjectData("Key", 1, 1)
        };

        for (int i = 0; i < objects.length; i++) {
            switch (objects[i].type) {
                case "Key":
                    gp.obj[i] = new Key(gp);
                    break;
                case "Fence":
                    gp.obj[i] = new Fence(gp);
                    break;
                case "Fence2":
                    gp.obj[i] = new Fence2(gp);
                    break;
            }
            gp.obj[i].worldX = gp.tileSize * objects[i].worldX;
            gp.obj[i].worldY = gp.tileSize * objects[i].worldY;
        }

    }

    public void setNPC() {

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 17;
        gp.npc[0].worldY = gp.tileSize * 26;

    }
}

class ObjectData {
    String type;
    int worldX, worldY;

    ObjectData(String type, int worldX, int worldY) {
        this.type = type;
        this.worldX = worldX;
        this.worldY = worldY;
    }
}
