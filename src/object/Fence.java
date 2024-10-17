package object;

import entity.Entity;
import main.GamePanel;

public class Fence extends Entity {

    public Fence(GamePanel gp) {
        super(gp);
        name = "Fence";
        down1 = setup("/tiles/fence");
        collision = true;
    }

}
