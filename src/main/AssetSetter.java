package main;

import object.Boots;
import object.Chest;
import object.Door;
import object.Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new Key();
        gp.obj[0].worldX = gp.tileSize * 23;
        gp.obj[0].worldY = gp.tileSize * 7;

        gp.obj[1] = new Key();
        gp.obj[1].worldX = gp.tileSize * 23;
        gp.obj[1].worldY = gp.tileSize * 40;

        gp.obj[2] = new Key();
        gp.obj[2].worldX = gp.tileSize * 38;
        gp.obj[2].worldY = gp.tileSize * 8;

        gp.obj[3] = new Door();
        gp.obj[3].worldX = gp.tileSize * 10;
        gp.obj[3].worldY = gp.tileSize * 11;

        gp.obj[4] = new Door();
        gp.obj[4].worldX = gp.tileSize * 8;
        gp.obj[4].worldY = gp.tileSize * 28;

        gp.obj[5] = new Door();
        gp.obj[5].worldX = gp.tileSize * 12;
        gp.obj[5].worldY = gp.tileSize * 22;

        gp.obj[6] = new Chest();
        gp.obj[6].worldX = gp.tileSize * 10;
        gp.obj[6].worldY = gp.tileSize * 7;

        gp.obj[7] = new Boots();
        gp.obj[7].worldX = gp.tileSize * 37;
        gp.obj[7].worldY = gp.tileSize * 42;
    }
}
