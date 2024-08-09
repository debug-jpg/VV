package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    Entity entity;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tile.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tile.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tile.tile[tileNum1].collision || gp.tile.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tile.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tile.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tile.tile[tileNum1].collision || gp.tile.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tile.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tile.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tile.tile[tileNum1].collision || gp.tile.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tile.mapTileNum[entityRightCol][entityBottomRow];
                tileNum2 = gp.tile.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tile.tile[tileNum1].collision || gp.tile.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
