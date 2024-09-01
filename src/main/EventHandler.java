package main;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("all")
public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle(23, 23, 2, 2);
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

    }

    public void checkEvent() {

        if (hit(24, 9, "right") == true) { damagePit(gp.dialogState); }
        if (hit(23, 7, "up") == true) { healingPool(gp.dialogState); }
        
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;


        return hit;

    }

    public void damagePit(int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialog = "You fall to my...";
        gp.player.life -= 1;

    }

    public void healingPool(int gameState) {

        if (gp.key.enter == true) {
            gp.gameState = gameState;
            gp.ui.currentDialog = "I love you!!!";
            gp.player.life = gp.player.maxLife;
        }

    }

}

















