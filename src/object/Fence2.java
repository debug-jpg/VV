package object;

import entity.Entity;
import main.GamePanel;

public class Fence2 extends Entity {
    public Fence2(GamePanel gp) {
        super(gp);
        name = "Fence";
        down1 = setup("/tiles/fencee2");
        collision = true;
    }
}
