package tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.swing.*;

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

        setup(0, "PlainGrass_2", false);
        setup(1, "TextureGrass_3", false);
        setup(2, "water", true);
        setup(3, "PlainGrass_1", false);
        setup(4, "treewgrass", true);
        setup(5, "redmrwgrass", true);
        setup(6, "bluemrwgrass", true);
        setup(7, "lake1grass", true);
        setup(8, "lake2grass", true);
        setup(9, "TextureGrass_2", false);

    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool tool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = tool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error has occured. Exiting.", "Visual Venture", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void loadMap(String filePath) {
        try (InputStream is = getClass().getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            for (int row = 0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                if (line == null) break;

                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.maxWorldCol && col < numbers.length; col++) {
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading map: " + filePath + ". Exiting.", "Visual Venture", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
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























