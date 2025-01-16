package main;

@SuppressWarnings("all")
public class EventHandler {

    GamePanel gp;
    AssetSetter setter;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 32;
            eventRect[col][row].height = 32;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        // If player is > 1 tile then check
        int xDistance = Math.abs(gp.saki.worldX - previousEventX);
        int yDistance = Math.abs(gp.saki.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(15, 25, "any") == true) {
                damagePit(15, 25, gp.dialogState);
            }
            if (hit(23, 7, "up") == true) {
                healingPool(23, 7, gp.playState);
            }
        }

    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.saki.solidArea.x = gp.saki.worldX + gp.saki.solidArea.x;
        gp.saki.solidArea.y = gp.saki.worldY + gp.saki.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.saki.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if (gp.saki.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.saki.worldX;
                previousEventY = gp.saki.worldY;
            }
        }

        gp.saki.solidArea.x = gp.saki.solidAreaDefaultX;
        gp.saki.solidArea.y = gp.saki.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState) {
        if (gp.key.enter == true) {
            gp.gameState = gameState;
            gp.ui.currentDialog = "I love you!!!";
            gp.saki.life = gp.saki.maxLife;
        }
    }
}
