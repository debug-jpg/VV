package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest extends SuperObject {

    GamePanel gp;

    public Chest(GamePanel gp) {
        this.gp = gp;

        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            tool.scaledImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
