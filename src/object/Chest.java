package object;

import main.GamePanel;
import entity.Entity;

public class Chest extends Entity {

    public Chest(GamePanel gp) {
        super(gp);

        name = "Chest";
        down1 = setup("/objects/chest");

    }
}
