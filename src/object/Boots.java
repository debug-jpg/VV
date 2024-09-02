package object;

import main.GamePanel;
import entity.Entity;

@SuppressWarnings("all")
public class Boots extends Entity {

    public Boots(GamePanel gp) {
        super(gp);

        name = "Boots";
        down1 = setup("/objects/boots");


    }
}
