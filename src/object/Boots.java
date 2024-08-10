package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

@SuppressWarnings("all")
public class Boots extends SuperObject {

    GamePanel gp;

    public Boots(GamePanel gp) {
        this.gp = gp;

        name = "Boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            tool.scaledImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
