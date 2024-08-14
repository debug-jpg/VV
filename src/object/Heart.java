package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Heart extends SuperObject {

    GamePanel gp;

    public Heart(GamePanel gp) {
        this.gp = gp;

        name = "Heart";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            image = tool.scaledImage(image, gp.tileSize, gp.tileSize);
            image2 = tool.scaledImage(image2, gp.tileSize, gp.tileSize);
            image3 =tool.scaledImage(image3, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
