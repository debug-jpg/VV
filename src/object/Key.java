package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends SuperObject{

    GamePanel gp;

    public Key(GamePanel gp) {
        this.gp = gp;

        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            tool.scaledImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
