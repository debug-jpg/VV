package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

@SuppressWarnings("all")
public class Entity {

    GamePanel gp;

    public int worldX, worldY;
    public int speed;

    public BufferedImage

            up1, up2, down1, down2, left1, left2, right1, right2, bgImage,
            idleUp, idleDown, idleLeft, idleRight, fence;

    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;

    public String dialogs[] = new String[20];
    int dialogIndex = 0;

    // SUPEROBJECTS
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    // CHARACTER STATS
    public int maxLife;
    public int life;

    private final UtilityTool tool = new UtilityTool();


    public Entity(GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, 48, 48);
    }

    public void setAction() {}

    /**
     * Initiates a speaking action for the entity, displaying a dialog and adjusting the entity's direction based on the player's direction.
     *
     * @return  None
     */
    public void speak() {

        if (dialogs[dialogIndex] == null) {
            dialogIndex = 0;
        }
        gp.ui.currentDialog = dialogs[dialogIndex];
        dialogIndex++;

        switch (gp.saki.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }

    }

    /**
     * Updates the entity's state, performing actions such as setting the entity's action, checking for collisions, and updating the entity's position and sprite.
     *
     * @return  None
     */
    public void update() {
        setAction();
        collisionOn = false;
        gp.hitbox.checkTile(this);
        gp.hitbox.checkObject(this, false);
        gp.hitbox.checkPlayer(this);

        if (!collisionOn) {
            move();
        }

        updateSprite();
    }

    private void move() {
        switch (direction) {
            case "up" -> worldY -= speed;
            case "down" -> worldY += speed;
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }
    }

    private void updateSprite() {
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    /**
     * Draws the entity on the screen using the provided Graphics2D object.
     *
     * @param  g2  the Graphics2D object used for drawing
     * @return     None
     */
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.saki.worldX + gp.saki.screenX;
        int screenY = worldY - gp.saki.worldY + gp.saki.screenY;

        if (worldX + gp.tileSize > gp.saki.worldX - gp.saki.screenX &&
                worldX - gp.tileSize < gp.saki.worldX + gp.saki.screenX &&
                worldY + gp.tileSize > gp.saki.worldY - gp.saki.screenY &&
                worldY - gp.tileSize < gp.saki.worldY + gp.saki.screenY) {


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

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }

    /**
     * Sets up a BufferedImage by reading an image from the specified path and scaling it to the game's tile size.
     *
     * @param  imagePath	the path to the image file
     * @return         	the scaled BufferedImage
     */
    public BufferedImage setup(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            if (image != null) {
                image = tool.scaledImage(image, gp.tileSize, gp.tileSize);
            }
            else {
                JOptionPane.showMessageDialog(null, "Image not found: " + imagePath + ".png", "Visual Venture", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading image: " + imagePath + ".png", "Visual Venture", JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

}
