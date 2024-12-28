package object;

import entity.Entity;
import main.GamePanel;

public class Key extends Entity {
    public boolean accessibilty = false;

    public Key(GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("/objects/key");
    }
}
