package object;

import main.GamePanel;
import entity.Entity;

public class Door extends Entity {

    public Door(GamePanel gp) {
        super(gp);

        name = "Door";
        down1 = setup("/objects/door");
        collision = true;
    }
}
