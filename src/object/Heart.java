package object;

import main.GamePanel;
import entity.Entity;

public class Heart extends Entity {

    public Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");

    }

}
