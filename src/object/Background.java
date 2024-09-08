package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class Background extends Entity {

    UtilityTool tool = new UtilityTool();

    public Background(GamePanel gp) {
        super(gp);

        image = setup("/background/bg_main");
        image = tool.scaledImage(image, gp.screenWidth, gp.screenHeight);
    }
}







