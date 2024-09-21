package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("all")
public class Saki extends Entity {

    KeyHandler key;

    public final int screenX, screenY;
    public int hasKey = 0;
    int standCounter;

    public Saki(GamePanel gp, KeyHandler key) {
        super(gp);

        this.key = key;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getSakiImage();

    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;

    }

    public void getSakiImage() {

        up1 = setup("/player/saki_up1");
        up2 = setup("/player/saki_up2");
        down1 = setup("/player/saki_down1");
        down2 = setup("/player/saki_down2");
        left1 = setup("/player/saki_left1");
        right1 = setup("/player/saki_right1");
        idleDown = setup("/player/saki_idle_down");
        right2 = setup("/player/saki_idle_right");
        left2 = setup("/player/saki_idle_left");
        idleUp = setup("/player/saki_idle_up");

    }

    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Door Unlocked.");
                    }
                    else {
                        gp.ui.showMessage("Key is required.");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed Up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }

        }

    }

    public void interactNPC(int i) {

        if (i != 999) {

            if (gp.key.enter) {
                gp.gameState = gp.dialogState;
                gp.npc[i].speak();
            }
        }
    }

    public void update() {

        if (key.up || key.down || key.left || key.right) {
            if (key.up) {
                direction = "up";
            }
            else if (key.down) {
                direction = "down";
            }
            else if (key.left) {
                direction = "left";
            }
            else if (key.right) {
                direction = "right";
            }


            //COLLISION
            collisionOn = false;
            gp.hitbox.checkTile(this);

            //OBJECT COLLISION
            int objIndex = gp.hitbox.checkObject(this, true);
            pickUpObject(objIndex);

            //NPC COLLISION
            int npcIndex = gp.hitbox.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK EVENT
            gp.handler.checkEvent();

            gp.key.enter = false;


            //NULL
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

        else {
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, null);

    }

}




















