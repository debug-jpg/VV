package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.*;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

@SuppressWarnings("all")
public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world02.txt");
    }

    public void getTileImage() {

        setup(0, "grass", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "earth", false);
        setup(4, "treewgrass", true);
        setup(5, "redmrwgrass", true);
        setup(6, "bluemrwgrass", true);
        setup(7, "lake1grass", true);
        setup(8, "lake2grass", true);

    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool tool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = tool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    if (col < numbers.length) {
                        int num = Integer.parseInt(numbers[col]);

                        if (col < mapTileNum.length && row < mapTileNum[col].length) {
                            mapTileNum[col][row] = num;
                        }
                    }
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.saki.worldX + gp.saki.screenX;
            int screenY = worldY - gp.saki.worldY + gp.saki.screenY;

            if (worldX + gp.tileSize > gp.saki.worldX - gp.saki.screenX &&
                    worldX - gp.tileSize < gp.saki.worldX + gp.saki.screenX &&
                    worldY + gp.tileSize > gp.saki.worldY - gp.saki.screenY &&
                    worldY - gp.tileSize < gp.saki.worldY + gp.saki.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}























