
@SuppressWarnings("all")
public class UnusedCode {

    private void setupObjects() {
//        gp.obj[0] = new Key(gp);
//        gp.obj[0].worldX = gp.tileSize * 23;
//        gp.obj[0].worldY = gp.tileSize * 7;
//
//        gp.obj[1] = new Key(gp);
//        gp.obj[1].worldX = gp.tileSize * 23;
//        gp.obj[1].worldY = gp.tileSize * 40;
//
//        gp.obj[2] = new Key(gp);
//        gp.obj[2].worldX = gp.tileSize * 38;
//        gp.obj[2].worldY = gp.tileSize * 8;
//
//        gp.obj[3] = new Door(gp);
//        gp.obj[3].worldX = gp.tileSize * 10;
//        gp.obj[3].worldY = gp.tileSize * 11;
//
//        gp.obj[4] = new Door(gp);
//        gp.obj[4].worldX = gp.tileSize * 8;
//        gp.obj[4].worldY = gp.tileSize * 28;
//
//        gp.obj[5] = new Door(gp);
//        gp.obj[5].worldX = gp.tileSize * 12;
//        gp.obj[5].worldY = gp.tileSize * 22;
//
//        gp.obj[6] = new Chest(gp);
//        gp.obj[6].worldX = gp.tileSize * 10;
//        gp.obj[6].worldY = gp.tileSize * 7;
//
//        gp.obj[7] = new Boots(gp);
//        gp.obj[7].worldX = gp.tileSize * 37;
//        gp.obj[7].worldY = gp.tileSize * 42;
    }
    private void pickupObjects() {
        //    String objectName = gp.obj[i].name;
//
//        switch (objectName) {
//        case "Key":
//            gp.playSE(1);
//            hasKey++;
//            gp.obj[i] = null;
//            gp.ui.showMessage("You got a key!");
//            break;
//        case "Door":
//            if (hasKey > 0) {
//                gp.playSE(3);
//                gp.obj[i] = null;
//                hasKey--;
//                gp.ui.showMessage("Door Unlocked.");
//            }
//            else {
//                gp.ui.showMessage("Key is required.");
//            }
//            break;
//        case "Boots":
//            gp.playSE(2);
//            speed += 2;
//            gp.obj[i] = null;
//            gp.ui.showMessage("Speed Up!");
//            break;
//        case "Chest":
//            gp.ui.gameFinished = true;
//            gp.stopMusic();
//            gp.playSE(4);
//            break;
//    }
    }
    private void playTime() {
//    double playTime;
//    DecimalFormat format = new DecimalFormat("#0.00");
    }
    private void update() {
//    if (gameFinished) {
//
//        g2.setFont(arial);
//        g2.setColor(Color.white);
//
//        String text;
//        int textLength;
//        int x, y;
//
//        text = "You found the treasure";
//        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//
//        x = gp.screenWidth / 2 - textLength / 2;
//        y = gp.screenHeight / 2 - (gp.tileSize * 3);
//
//        g2.drawString(text, x, y);
//
//        g2.setFont(end);
//        g2.setColor(Color.yellow);
//
//        text = "Omedetou Gozaimasu";
//        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//
//        x = gp.screenWidth / 2 - textLength / 2;
//        y = gp.screenHeight / 2 + (gp.tileSize * 2);
//
//        g2.drawString(text, x, y);
//
//        gp.gameThread = null;
//
//    }
//        else {
//        g2.setFont(arial);
//        g2.setColor(Color.white);
//        g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
//        g2.drawString("x " + gp.player.hasKey, 74, 65);
//
//        // TIME
//        playTime += (double) 1/60;
//        g2.drawString("Time: " + format.format(playTime), gp.tileSize * 11, 65);
//
//        // MESSAGE
//        if (messageOn) {
//            g2.setFont(g2.getFont().deriveFont(30f));
//            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
//
//            messageCounter++;
//
//            if (messageCounter > 120) {
//                messageCounter = 0;
//                messageOn = false;
//            }
//        }
//    }
    }

}
